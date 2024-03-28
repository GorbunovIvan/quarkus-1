package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "actor")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode
@ToString
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Integer actorId;

    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;

    @ManyToMany(mappedBy = "actors")
    private Set<Film> films = new HashSet<>();
}
