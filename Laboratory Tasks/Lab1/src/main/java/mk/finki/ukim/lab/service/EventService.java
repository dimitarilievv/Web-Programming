package mk.finki.ukim.lab.service;

import mk.finki.ukim.lab.model.Event;
import mk.finki.ukim.lab.model.Location;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text,double minRating);
    public void deleteById(Long id);
    public Optional<Event> findById(Long id);
    public Optional<Event> save(String name, String description,
                                Double popularityScore, Location location);
}
