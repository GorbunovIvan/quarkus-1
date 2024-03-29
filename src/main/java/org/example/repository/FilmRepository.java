package org.example.repository;

import com.speedment.jpastreamer.application.JPAStreamer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.model.Film;
import org.example.model.Film$;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class FilmRepository {

    @Inject
    JPAStreamer jpaStreamer;

    public List<Film> findAll() {
        return jpaStreamer.stream(Film.class)
                .toList();
    }

    public Optional<Film> findById(Integer filmId) {
        return jpaStreamer.stream(Film.class)
                .filter(Film$.filmId.equal(filmId))
                .findAny();
    }
}
