package lesson3;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetTest extends AbstractTest{
    //private final String apiKey = "8a067c5f5a6441ad8e3e422f201c9302";

    @Test
    void getDietIncludeIngredientsTypePositiveTest(){
        given()
                .queryParam("apiKey", apiKey)
                .queryParam("diet", "ketogenic")
                .queryParam("includeIngredients", "chicken")
                .queryParam("type", "main course")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(5000L))
                .body("results[0].id", equalTo(716427))
                .body("results[0].title", equalTo("Roasted Butterflied Chicken w. Onions & Carrots"))
                .body("results[1].id", equalTo(780001))
                .body("results[1].title", equalTo("Pesto Chicken Zoodles"))
                .body("offset", equalTo(0))
                .body("number", equalTo(10))
                .body("totalResults", equalTo(12));
    }

    @Test
    void getQueryNamePositiveTest(){
        given()
                .queryParam("apiKey", apiKey)
                .queryParam("query", "Greek Side Salad")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(5000L))
                .body("results[0].id", equalTo(645348))
                .body("results[0].title", equalTo("Greek Side Salad"))
                .body("offset", equalTo(0))
                .body("number", equalTo(10))
                .body("totalResults", equalTo(1));
    }

    @Test
    void getErrorRequestNegativeTest(){
        given()
                .queryParam("apiKey", apiKey)
                .queryParam("query", "pharmacy")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(5000L))
                .body("offset", equalTo(0))
                .body("number", equalTo(10))
                .body("totalResults", equalTo(0));
    }

    @Test
    void getNutriensZincPositiveTest(){
        given()
                .queryParam("apiKey", apiKey)
                .queryParam("minZinc", 33)
                .queryParam("maxZinc", 35)
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(5000L))
                .body("results[0].id", equalTo(640869))
                .body("results[0].title", equalTo("Crock Pot Shredded French Dip"))
                .body("offset", equalTo(0))
                .body("number", equalTo(10))
                .body("totalResults", equalTo(1));
    }

    @Test
    void getVeganMeatNegativeTest(){
        given()
                .queryParam("apiKey", apiKey)
                .queryParam("query", "pork")
                .queryParam("diet", "vegetarian")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(5000L))
                .body("offset", equalTo(0))
                .body("number", equalTo(10))
                .body("totalResults", equalTo(0));
    }
}