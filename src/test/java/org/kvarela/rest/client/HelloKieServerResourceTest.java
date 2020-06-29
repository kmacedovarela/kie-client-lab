package org.kvarela.rest.client;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class HelloKieServerResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello-kieserver")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}