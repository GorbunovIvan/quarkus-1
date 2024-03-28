package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "film")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode
@ToString
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Integer filmId;
    
    @Column(name = "title", nullable = false, length = 128)
    private String title;
    
    @Column(name = "description", nullable = true, length = -1)
    private String description;
    
    @Column(name = "release_year", nullable = true)
    private Integer releaseYear;
    
    @Column(name = "language_id", nullable = false)
    private Integer languageId;
    
    @Column(name = "original_language_id", nullable = true)
    private Integer originalLanguageId;
    
    @Column(name = "rental_duration", nullable = false)
    private Integer rentalDuration;
    
    @Column(name = "rental_rate", nullable = false, precision = 2)
    private BigDecimal rentalRate;
    
    @Column(name = "length", nullable = true)
    private Integer length;
    
    @Column(name = "replacement_cost", nullable = false, precision = 2)
    private BigDecimal replacementCost;
    
    @Column(name = "rating", columnDefinition = "enum('G', 'PG', 'PG-13', 'R', 'NC-17')")
    private Object rating;
    
    @Column(name = "special_features", columnDefinition = "set('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')")
    private Object specialFeatures;
    
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actors = new ArrayList<>();
}
