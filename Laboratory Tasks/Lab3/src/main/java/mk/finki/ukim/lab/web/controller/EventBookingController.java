package mk.finki.ukim.lab.web.controller;

import mk.finki.ukim.lab.model.EventBooking;
import mk.finki.ukim.lab.repository.inmemory.InMemoryEventBookingRepository;
import mk.finki.ukim.lab.repository.jpa.BookingRepository;
import mk.finki.ukim.lab.service.EventBookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
            return "listEvents";
        }
        int numberOfTickets=Integer.parseInt(numTickets);
        EventBooking eventBooking=eventBookingService.placeBooking(eventName,attendeeName,attendeeAddress,numberOfTickets);
        bookingRepository.save(eventBooking);
//        bookingRepository.findAll().add(eventBooking);
        List<EventBooking> allbookings =eventBookingService.filterBookings(attendeeName);

        model.addAttribute("booking",eventBooking);
        model.addAttribute("allbookings",allbookings);

        return "bookingConfirmation.html";
    }

}
