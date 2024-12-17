package mk.finki.ukim.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.lab.model.Event;
import mk.finki.ukim.lab.model.Location;
import mk.finki.ukim.lab.service.EventService;
import mk.finki.ukim.lab.service.LocationService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final LocationService locationService;

    public EventController(EventService eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String error,
                                @RequestParam(required = false) String minRating,
                                @RequestParam(required = false) String searchText,
                                Model model,
                                HttpServletRequest req
    ) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Event> eventList;
        List<Location> locations=locationService.findAll();

        if ((searchText == null || searchText.isEmpty()) && (minRating == null || minRating.isEmpty())) {
            eventList = eventService.listAll();
        } else {
            double minRatingP = minRating != null && !minRating.isEmpty() ? Double.parseDouble(minRating) : 0.0;
            eventList = eventService.searchEvents(searchText, minRatingP);
        }

        // Add attributes to model
        model.addAttribute("attendeeIpAddress", req.getRemoteAddr());
        model.addAttribute("attendeeName",req.getRemoteUser());
        model.addAttribute("events", eventList);
        model.addAttribute("locations",locations);
        model.addAttribute("bodyContent","listEvents");
        return "master-template";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        if (eventService.findById(id).isPresent()) {
            this.eventService.deleteById(id);
            return "redirect:/events";
        } else return "redirect:/events?error=Event not found";
    }
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    @GetMapping("/edit-form/{id}")
    public String editEvent(@PathVariable Long id, Model model) {
        if (this.eventService.findById(id).isPresent()) {
            Event event = this.eventService.findById(id).get();
            List<Location> locations = locationService.findAll();
            model.addAttribute("locations", locations);
            model.addAttribute("event", event);
            model.addAttribute("bodyContent","add-event");
            return "master-template";
        }
        return "redirect:/events?error=EventNotFound";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add-form")
    public String addEvent(Model model) {
        List<Event> events = this.eventService.listAll();
        List<Location> locations = locationService.findAll();
        model.addAttribute("events", events);
        model.addAttribute("locations", locations);
        model.addAttribute("bodyContent","add-event");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveEvent(@RequestParam(required = false) Long id,
                            @RequestParam String name,
                            @RequestParam String description,
                            @RequestParam Double popularityScore,
                            @RequestParam Long locationId,
                            @RequestParam(required = false) Boolean hasInc,
                            @RequestParam(required = false) Boolean hasDecr) {
        hasInc = (hasInc != null) ? hasInc : false;
        hasDecr = (hasDecr != null) ? hasDecr : false;
        if (id != null) {
            this.eventService.update(id, name, description, popularityScore, locationId,hasInc,hasDecr);
        } else {
            this.eventService.save(name, description, popularityScore, locationId, hasInc, hasDecr);
        }
        return "redirect:/events";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/updatePopularity")
    public String updatePopularity(@RequestParam Long eventId,
                                   @RequestParam String action) {
        Optional<Event> event = eventService.findById(eventId);

        if (event.isPresent()) {
            Event current = event.get();
            if ("increase".equals(action) && !current.isHasIncreased()) {
                current.setPopularityScore(current.getPopularityScore() + 1);
                current.setHasIncreased(true);
            } else if ("decrease".equals(action) && !current.isHasDecreased()) {
                current.setPopularityScore(current.getPopularityScore() - 1);
                current.setHasDecreased(true);
            }
            eventService.update(current.getId(),
                    current.getName(),
                    current.getDescription(),
                    current.getPopularityScore(),
                    current.getLocation().getId(),
                    current.isHasIncreased(),
                    current.isHasDecreased());
            return "redirect:/events";
        }
        return "redirect:/events?error=EventNotFound";

    }

}
