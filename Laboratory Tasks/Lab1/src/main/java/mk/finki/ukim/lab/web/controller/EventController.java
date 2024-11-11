package mk.finki.ukim.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.lab.model.Event;
import mk.finki.ukim.lab.model.Location;
import mk.finki.ukim.lab.service.EventService;
import mk.finki.ukim.lab.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                                Model model,
                                HttpServletRequest request,
                                @RequestParam(required = false) String minRating,
                                @RequestParam(required = false) String searchText) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Event> events=null;
        if ((searchText == null || searchText.isEmpty()) && (minRating == null || minRating.isEmpty())) {
            events = eventService.listAll();
        } else {
            double minRatingP = minRating != null && !minRating.isEmpty() ? Double.parseDouble(minRating) : 0.0;
            events = eventService.searchEvents(searchText, minRatingP);
        }

        model.addAttribute("attendeeIpAddress", request.getRemoteAddr());
        model.addAttribute("events", events);
        return "listEvents.html";
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        if (eventService.findById(id).isPresent()) {
            this.eventService.deleteById(id);
            return "redirect:/events";
        } else return "redirect:/events?error=Event not found";
    }

    @GetMapping("/edit-form/{id}")
    public String editEvent(@PathVariable Long id, Model model) {
        if (this.eventService.findById(id).isPresent()) {
            Event event = this.eventService.findById(id).get();
            List<Location> locations = locationService.findAll();
            model.addAttribute("locations", locations);
            model.addAttribute("event", event);
            return "add-event";
        }
        return "redirect:/events?error=EventNotFound";
    }

    @GetMapping("/add-form")
    public String addEvent(Model model) {
        List<Event> events = this.eventService.listAll();
        List<Location> locations = locationService.findAll();
        model.addAttribute("events", events);
        model.addAttribute("locations", locations);
        return "add-event";
    }

    @PostMapping("/add")
    public String saveEvent(@RequestParam String name,
                            @RequestParam String description,
                            @RequestParam Double popularityScore,
                            @RequestParam Long locationId) {
        Location location = locationService.findById(locationId).orElse(null);
        if (location == null) {
            return "redirect:/events?error=InvalidLocation";
        }
        this.eventService.save(name, description, popularityScore, location);
        return "redirect:/events";
    }

}
