package mk.finki.ukim.lab.repository.jpa;

import mk.finki.ukim.lab.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {

}
