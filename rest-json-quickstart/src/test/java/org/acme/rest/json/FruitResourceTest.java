package org.acme.rest.json;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;
import io.quarkus.panache.common.Sort;

//import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.panache.mock.*;
import org.junit.jupiter.api.Assertions;
import io.restassured.response.Response;

@QuarkusTest
public class FruitResourceTest {

    @Test
    public void testHelloEndpoint() {
        List<Fruit> lfruits = Fruit.listAll(Sort.by("name"));
        given()
          .when().get("/fruits")
          .then()
             .statusCode(200)
             .body(containsString(lfruits.get(0).toString()));
    }

    @Test
    public void testMocking() {
        
        // Making the mock on the Fruit's class
        PanacheMock.mock(Fruit.class);

        // Mocked classes always return a default value
        Assertions.assertEquals(0, Fruit.count());

        // Response to list all fuits
        Response res = given().when().get("/fruits");

        Fruit.streamAll().forEach(x-> res.then().body(containsString(x.toString())));

    }



}