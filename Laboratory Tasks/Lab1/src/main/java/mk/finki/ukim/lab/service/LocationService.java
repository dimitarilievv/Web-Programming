package mk.finki.ukim.lab.service;

import mk.finki.ukim.lab.model.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    public List<Location> findAll();

    Optional<Location> findById(Long locationId);
}
