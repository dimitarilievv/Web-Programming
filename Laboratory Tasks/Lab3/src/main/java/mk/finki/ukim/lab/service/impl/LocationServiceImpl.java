package mk.finki.ukim.lab.service.impl;

import mk.finki.ukim.lab.model.Location;
import mk.finki.ukim.lab.repository.jpa.LocationRepository;
import mk.finki.ukim.lab.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository repository;

    public LocationServiceImpl(LocationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Location> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Location> findById(Long locationId) {
        return repository.findById(locationId);
    }
}
