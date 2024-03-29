package org.example.repository;

import com.speedment.jpastreamer.application.JPAStreamer;
import com.speedment.jpastreamer.projection.Projection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.model.Film;
import org.example.model.Film$;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
public class FilmRepository {

    @Inject
    JPAStreamer jpaStreamer;

    public List<Film> findAll() {
        // Here we use Projection to avoid querying unnecessary fields from the database.
        return jpaStreamer.stream(Projection.select(Film$.title, Film$.releaseYear))
                .toList();
    }

    public Optional<Film> findById(Integer filmId) {
        return jpaStreamer.stream(Film.class)
                .filter(Film$.filmId.equal(filmId))
                .findAny();
    }

    public Stream<Film> paged(int page) {

        int pageSize = 20;

        // Here we use Projection to avoid querying unnecessary fields from the database.
        return jpaStreamer.stream(Projection.select(Film$.title, Film$.releaseYear))
                .sorted(Comparator.comparing(Film::getReleaseYear))
                .skip((long) (page-1) * pageSize)
                .limit(pageSize);
    }
}
