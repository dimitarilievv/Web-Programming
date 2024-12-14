package mk.finki.ukim.lab.repository.inmemory;

import mk.finki.ukim.lab.bootstrap.DataHolder;
import mk.finki.ukim.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryLocationRepository {
    public List<Location> findAll(){
        return DataHolder.locations;
    }
    public Optional<Location> findById(Long id){
        return DataHolder.locations.stream().filter(i->i.getId().equals(id)).findFirst();
    }
}
