package mk.finki.ukim.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.lab.model.EventBooking;
import mk.finki.ukim.lab.repository.inmemory.InMemoryEventBookingRepository;
import mk.finki.ukim.lab.repository.jpa.BookingRepository;
import mk.finki.ukim.lab.service.EventBookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/eventBooking")
public class EventBookingController {
    private final EventBookingService eventBookingService;
    private final BookingRepository bookingRepository;

    public EventBookingController(EventBookingService eventBookingService, BookingRepository bookingRepository) {
        this.eventBookingService = eventBookingService;
        this.bookingRepository = bookingRepository;
    }

    @PostMapping
    public String getBookingPage(@RequestParam String eventName,
                                 @RequestParam String attendeeName,
                                 @RequestParam String attendeeAddress,
                                 @RequestParam String numTickets,
                                 @RequestParam(required = false) String error,
                                 Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
            model.addAttribute("bodyContent","listEvents");
            return "master-template";
        }
        int numberOfTickets=Integer.parseInt(numTickets);
        EventBooking eventBooking=eventBookingService.placeBooking(eventName,attendeeName,attendeeAddress,numberOfTickets);
        bookingRepository.save(eventBooking);
        List<EventBooking> allbookings =eventBookingService.filterBookings(attendeeName);

        model.addAttribute("booking",eventBooking);
        model.addAttribute("allbookings",allbookings);
        model.addAttribute("bodyContent","bookingConfirmation");
        return "master-template";
    }
}
