package mk.ukim.finki.wp2024.repository.impl.jpa;

import mk.ukim.finki.wp2024.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByNameLike(String text);
    void deleteByName(String name);
}
