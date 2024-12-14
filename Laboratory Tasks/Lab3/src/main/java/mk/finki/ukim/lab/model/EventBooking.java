package mk.finki.ukim.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

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
