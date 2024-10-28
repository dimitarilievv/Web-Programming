package mk.finki.ukim.lab.service.impl;

import mk.finki.ukim.lab.model.EventBooking;
import mk.finki.ukim.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

@Service
public class EventBookingImpl implements EventBookingService {
    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        return new EventBooking(eventName,attendeeName,attendeeAddress, (long) numberOfTickets);
    }
}
