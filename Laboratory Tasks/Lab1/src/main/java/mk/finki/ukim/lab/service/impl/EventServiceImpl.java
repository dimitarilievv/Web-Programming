package mk.finki.ukim.lab.service.impl;

import mk.finki.ukim.lab.model.Event;
import mk.finki.ukim.lab.model.Location;
import mk.finki.ukim.lab.repository.EventRepository;
import mk.finki.ukim.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository repository) {
        this.eventRepository = repository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text,double minRating) {
        return eventRepository.findAll().stream()
                .filter(event -> (text == null || event.getName().toLowerCase().contains(text.toLowerCase()) ||
                        event.getDescription().toLowerCase().contains(text.toLowerCase())) &&
                        event.getPopularityScore() >= minRating)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }


    @Override
    public Optional<Event> save(String name, String description, Double popularityScore, Location location,boolean hasIncreased,boolean hasDecreased) {
        return eventRepository.save(name,description,popularityScore,location,hasIncreased,hasDecreased);
    }


}
