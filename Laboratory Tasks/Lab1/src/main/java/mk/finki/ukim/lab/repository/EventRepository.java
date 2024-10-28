package mk.finki.ukim.lab.repository;

import mk.finki.ukim.lab.bootstrap.DataHolder;
import mk.finki.ukim.lab.model.Event;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventRepository {
    public List<Event> findAll(){
        return DataHolder.events;
    }
    public List<Event> searchEvents(String text){
        return DataHolder.events.stream()
                .filter(e->e.getName().contains(text)||e.getDescription().contains(text))
                .collect(Collectors.toList());
    }

}
