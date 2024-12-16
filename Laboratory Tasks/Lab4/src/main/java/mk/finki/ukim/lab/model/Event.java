package mk.finki.ukim.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
public class Event {
    private String name;
    private String description;
    private double popularityScore;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Location location;
    private boolean hasIncreased;
    private boolean hasDecreased;
    public Event(String name, String description, double popularityScore,Location location,boolean hasIncreased,boolean hasDecreased) {
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.location=location;
        this.hasIncreased=hasIncreased;
        this.hasDecreased=hasDecreased;
    }

    public Event() {

    }
}
