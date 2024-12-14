package mk.finki.ukim.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.lab.model.Event;
import mk.finki.ukim.lab.model.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Event> events=new ArrayList<>();
    public static List<Location> locations=new ArrayList<>();
//    @PostConstruct
//    void init(){
//        locations.add(new Location("Location1","Address1","1","Description1"));
//        locations.add(new Location("Location2","Address2","4","Description2"));
//        locations.add(new Location("Location3","Address3","2","Description3"));
//        locations.add(new Location("Location4","Address4","6","Description4"));
//        locations.add(new Location("Location5","Address5","1","Description5"));
//
//
//        events.add(new Event("Event1", "E1 description", 7.8,locations.get(3),false,false));
//        events.add(new Event("Event2", "E2 description", 3.0,locations.get(1),false,false));
//        events.add(new Event("Event3", "E3 description", 5.2,locations.get(0),false,false));
//        events.add(new Event("Event4", "E4 description", 9.1,locations.get(3),false,false));
//        events.add(new Event("Event5", "E5 description", 7.3,locations.get(2),false,false));
//        events.add(new Event("Event6", "E6 description", 6.8,locations.get(4),false,false));
//        events.add(new Event("Event7", "E7 description", 8.3,locations.get(2),false,false));
//        events.add(new Event("Event8", "E8 description", 2.0,locations.get(2),false,false));
//        events.add(new Event("Event9", "E9 description", 9.5,locations.get(1),false,false));
//        events.add(new Event("Event10", "E10 description", 4.9,locations.get(1),false,false));
//    }
}
