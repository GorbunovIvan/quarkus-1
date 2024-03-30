package org.example;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.example.repository.FilmRepository;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@QuarkusTest
class FilmResourceTest {

    @Inject
    FilmRepository filmRepository;

    @Test
    void testHello() {
        given()
                .when()
                .get("/hello-world")
                .then()
                .statusCode(200)
                .body(containsString("Hello, World!"));
    }

    @Test
    void testGetFilm() {

        var id = 1;
        var film = filmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No film found to test"));

        given()
                .when()
                .get("/films/" + id)
                .then()
                .statusCode(200)
                .body(containsString(film.getTitle()));
    }
}