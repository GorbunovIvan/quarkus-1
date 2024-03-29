package org.example;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.example.model.Film;
import org.example.repository.FilmRepository;

import java.util.List;

@Path("/")
public class FilmResource {

    @Inject
    FilmRepository filmRepository;

    @GET
    @Path("/hello-world")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello, World!";
    }

    @GET
    @Path("/films")
    public List<String> getAllFilms() {
        return filmRepository.findAll().stream()
                .map(Film::getTitle)
                .toList();
    }

    @GET
    @Path("/films/{filmId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getFilm(Integer filmId) {
        return filmRepository.findById(filmId)
                .map(Film::getTitle)
                .orElse("No film found with filmId " + filmId);
    }

    @GET
    @Path("/films/paged/{page}")
    @Produces(MediaType.TEXT_PLAIN)
    public List<String> getFilmsPaged(int page) {
        return filmRepository.paged(page)
                .map(f -> String.format("%s (%d)", f.getTitle(), f.getReleaseYear()))
                .toList();
    }
}
