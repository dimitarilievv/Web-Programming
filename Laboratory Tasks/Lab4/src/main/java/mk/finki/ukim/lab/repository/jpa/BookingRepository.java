package mk.finki.ukim.lab.repository.jpa;

import mk.finki.ukim.lab.model.Event;
import mk.finki.ukim.lab.model.EventBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<EventBooking,Long> {
}
