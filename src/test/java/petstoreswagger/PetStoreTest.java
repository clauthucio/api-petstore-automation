package petstoreswagger;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;

public class PetStoreTest {
    @Test
    public void testAdicionarPet(){
        baseURI = "https://petstore.swagger.io/v2";
        given()
            .contentType(ContentType.JSON)
            .body("{\n" +
                    "  \"id\": 0,\n" +
                    "  \"category\": {\n" +
                    "    \"id\": 0,\n" +
                    "    \"name\": \"cachorros\"\n" +
                    "  },\n" +
                    "  \"name\": \"Gregório\",\n" +
                    "  \"photoUrls\": [\n" +
                    "    \"https://i.pinimg.com/736x/47/e8/7a/47e87a0380c10295e686f75045bff52b.jpg\"\n" +
                    "  ],\n" +
                    "  \"tags\": [\n" +
                    "    {\n" +
                    "      \"id\": 0,\n" +
                    "      \"name\": \"vira-lata\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"status\": \"available\"\n" +
                    "}")
        .when()
            .post("/pet")
        .then()
            .assertThat()
                .statusCode(200)
                .body("id", not(empty()));;
    }

    @Test
    public void testEncontrarPorStatus() {
        baseURI = "https://petstore.swagger.io/v2";
        String status = "available";
        given()
            .queryParam("status", status)
        .when()
            .get("/pet/findByStatus")
        .then()
            .assertThat()
                .statusCode(200)
                .body("status", everyItem(equalTo(status)));
    }

    @Test
    public void testRemoverPet(){
        baseURI = "https://petstore.swagger.io/v2";
        long petID = given()
            .contentType(ContentType.JSON)
            .body("{\n" +
                    "  \"id\": 0,\n" +
                    "  \"category\": {\n" +
                    "    \"id\": 0,\n" +
                    "    \"name\": \"cachorros\"\n" +
                    "  },\n" +
                    "  \"name\": \"Gregório\",\n" +
                    "  \"photoUrls\": [\n" +
                    "    \"https://i.pinimg.com/736x/47/e8/7a/47e87a0380c10295e686f75045bff52b.jpg\"\n" +
                    "  ],\n" +
                    "  \"tags\": [\n" +
                    "    {\n" +
                    "      \"id\": 0,\n" +
                    "      \"name\": \"vira-lata\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"status\": \"available\"\n" +
                    "}")
        .when()
            .post("/pet")
        .then()
            .extract()
                .path("id");

        // Agora deleta o pet criado
        given()
            .pathParam("petId", petID)
        .when()
            .delete("/pet/{petId}")
        .then()
            .assertThat()
                .statusCode(200)
                .body("message", equalTo(String.valueOf(petID)));
    }
}
