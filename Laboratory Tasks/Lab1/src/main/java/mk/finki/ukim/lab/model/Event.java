package mk.finki.ukim.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Event {
    private String name;
    private String description;
    private double popularityScore;
    private Long id;
    private Location location;
    private boolean hasIncreased;
    private boolean hasDecreased;
    public Event(String name, String description, double popularityScore,Location location,boolean hasIncreased,boolean hasDecreased) {
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.id = (long) (Math.random()*1000);
        this.location=location;
        this.hasIncreased=hasIncreased;
        this.hasDecreased=hasDecreased;
    }
}
