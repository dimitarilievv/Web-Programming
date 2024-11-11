package mk.finki.ukim.lab.repository;

import mk.finki.ukim.lab.bootstrap.DataHolder;
import mk.finki.ukim.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LocationRepository {
    public List<Location> findAll(){
        return DataHolder.locations;
    }
    public Optional<Location> findById(Long id){
        return DataHolder.locations.stream().filter(i->i.getId().equals(id)).findFirst();
    }
}
