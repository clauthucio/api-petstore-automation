package petstoreswagger;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

public class PetStoreTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    public void testAdicionarPet(){
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
        // Cria um pet para depois remover
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

    @Test
    public void testEfetuarPedido(){
        given()
            .contentType(ContentType.JSON)
            .body("{\n" +
                    "  \"id\": 0,\n" +
                    "  \"petId\": 0,\n" +
                    "  \"quantity\": 0,\n" +
                    "  \"shipDate\": \"2025-06-30T22:04:14.473Z\",\n" +
                    "  \"status\": \"placed\",\n" +
                    "  \"complete\": true\n" +
                    "}")
        .when()
            .post("/store/order")
        .then()
            .assertThat()
                .statusCode(200)
                .body("id", not(empty()));
    }

    @Test
    public void testRemoverPedido(){
        // Cria um pedido para depois deletar
        long orderID = given()
            .contentType(ContentType.JSON)
            .body("{\n" +
                    "  \"id\": 0,\n" +
                    "  \"petId\": 0,\n" +
                    "  \"quantity\": 0,\n" +
                    "  \"shipDate\": \"2025-06-30T22:04:14.473Z\",\n" +
                    "  \"status\": \"placed\",\n" +
                    "  \"complete\": true\n" +
                    "}")
        .when()
            .post("/store/order")
        .then()
            .assertThat()
                .statusCode(200)
                .extract()
                    .path("id");

        // Agora deleta o pedido criado
        given()
            .pathParam("orderId", orderID)
        .when()
            .delete("/store/order/{orderId}")
        .then()
            .assertThat()
                .statusCode(200)
                .body("message", equalTo(String.valueOf(orderID)));
    }
}