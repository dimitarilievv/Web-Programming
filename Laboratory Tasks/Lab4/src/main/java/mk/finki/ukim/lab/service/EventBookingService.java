package mk.finki.ukim.lab.service;

import mk.finki.ukim.lab.model.EventBooking;
import mk.finki.ukim.lab.repository.jpa.BookingRepository;
import java.util.List;

public interface EventBookingService {
    EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets);
    List<EventBooking> filterBookings(String name);
}
