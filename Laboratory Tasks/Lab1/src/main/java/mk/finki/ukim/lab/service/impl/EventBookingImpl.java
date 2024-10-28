package mk.finki.ukim.lab.service.impl;

import mk.finki.ukim.lab.model.EventBooking;
import mk.finki.ukim.lab.repository.EventBookingRepository;
import mk.finki.ukim.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventBookingImpl implements EventBookingService {
    public EventBookingRepository repository;

    public EventBookingImpl(EventBookingRepository repository) {
        this.repository = repository;
    }

    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        return new EventBooking(eventName,attendeeName,attendeeAddress, (long) numberOfTickets);
    }

    @Override
    public List<EventBooking> filterBookings(String name) {
        return repository.findAll().stream()
                .filter(booking -> booking.getAttendeeName().equals(name))
                .toList();
    }

}
