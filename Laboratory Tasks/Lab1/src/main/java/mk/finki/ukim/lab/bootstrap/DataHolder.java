package mk.finki.ukim.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.lab.model.Event;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Event> events=new ArrayList<>();

    @PostConstruct
    void init(){
        events.add(new Event("Event1", "E1 description", 7.8));
        events.add(new Event("Event2", "E2 description", 3.0));
        events.add(new Event("Event3", "E3 description", 5.2));
        events.add(new Event("Event4", "E4 description", 9.1));
        events.add(new Event("Event5", "E5 description", 7.3));
        events.add(new Event("Event6", "E6 description", 6.8));
        events.add(new Event("Event7", "E7 description", 8.3));
        events.add(new Event("Event8", "E8 description", 2.0));
        events.add(new Event("Event9", "E9 description", 9.5));
        events.add(new Event("Event10", "E10 description", 4.9));
    }
}
