package mk.finki.ukim.lab.service;

import mk.finki.ukim.lab.model.Event;

import java.util.List;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text,double minRating);
}
