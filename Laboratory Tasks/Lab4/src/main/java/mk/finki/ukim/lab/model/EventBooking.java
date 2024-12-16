package mk.finki.ukim.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Entity
public class EventBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String eventName;
    String attendeeName;
    String attendeeAddress;
    Long numberOfTickets;
    public EventBooking() {

    }

    public EventBooking(String eventName, String attendeeName, String attendeeAddress, Long numberOfTickets) {
        this.eventName = eventName;
        this.attendeeName = attendeeName;
        this.attendeeAddress = attendeeAddress;
        this.numberOfTickets = numberOfTickets;
    }
}
