package mk.finki.ukim.lab.service.impl;

import mk.finki.ukim.lab.model.Event;
import mk.finki.ukim.lab.model.Location;
import mk.finki.ukim.lab.repository.jpa.EventRepository;
import mk.finki.ukim.lab.repository.jpa.LocationRepository;
import mk.finki.ukim.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    public EventServiceImpl(EventRepository eventRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
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
    public Optional<Event> save(String name, String description, Double popularityScore, Long locationn,boolean hasIncreased,boolean hasDecreased) {
        Location location = locationRepository.findById(locationn).orElse(null);
        if (location != null) {
            Event event = new Event(name, description, popularityScore, location,hasIncreased,hasDecreased);
            return Optional.of(eventRepository.save(event));
        }
        return Optional.empty();
    }

    @Override
    public List<Event> findAllByLocationId(Long locationId) {
        return eventRepository.findAllByLocation_Id(locationId);
    }
    @Override
    public Optional<Event> update(Long id, String name, String description, double popularityScore, Long locationId, boolean hasIncreased, boolean hasDecreased) {
        Event event = eventRepository.findById(id).orElse(null);
        Location location = locationRepository.findById(locationId).orElse(null);

        if (event != null) {
            event.setName(name);
            event.setDescription(description);
            event.setPopularityScore(popularityScore);
            event.setLocation(location);
            event.setHasIncreased(hasIncreased);
            event.setHasDecreased(hasDecreased);
            return Optional.of(this.eventRepository.save(event));
        }

        return Optional.empty();
    }

}
