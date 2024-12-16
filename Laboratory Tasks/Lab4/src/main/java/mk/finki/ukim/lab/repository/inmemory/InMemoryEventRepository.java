package mk.finki.ukim.lab.repository.inmemory;

import mk.finki.ukim.lab.bootstrap.DataHolder;
import mk.finki.ukim.lab.model.Event;
import mk.finki.ukim.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryEventRepository {
    public List<Event> findAll(){
        return DataHolder.events;
    }
    public List<Event> searchEvents(String text){
        return DataHolder.events.stream()
                .filter(e->e.getName().contains(text)||e.getDescription().contains(text))
                .collect(Collectors.toList());
    }
    public void deleteById(Long id){
        DataHolder.events.removeIf(i -> i.getId().equals(id));
    }
    public Optional<Event> findById(Long id){
        return DataHolder.events.stream().filter(i->i.getId().equals(id)).findFirst();
    }
    public Optional<Event> save(String name, String description,
                                Double popularityScore, Location location,boolean hasIncreased,boolean hasDecreased){
        if(location==null){
            throw new IllegalArgumentException();
        }
        Event event=new Event(name,description,popularityScore,location,hasIncreased,hasDecreased);
        DataHolder.events.removeIf(i->i.getName().equals(event.getName()));
        DataHolder.events.add(event);
        return Optional.of(event);
    }
}
