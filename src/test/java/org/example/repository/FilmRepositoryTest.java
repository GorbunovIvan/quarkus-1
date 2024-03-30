package org.example.repository;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class FilmRepositoryTest {

    @Inject
    FilmRepository filmRepository;

    @Test
    void testPaged() {
        var films = filmRepository.paged(1).toList();
        assertFalse(films.isEmpty());
        assertEquals(20, films.size());
    }
}