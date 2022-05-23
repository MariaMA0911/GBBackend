package lesson4;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetComplexSearchTest extends AbstractGetTest {

    @Test
    void getDietIncludeIngredientsTypePositiveTest() {
        given()
                .spec(requestSpecificationGet)
                .queryParam("diet", "ketogenic")
                .queryParam("includeIngredients", "chicken")
                .queryParam("type", "main course")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .spec(responseSpecificationGetPositive)
                .body("results[0].id", equalTo(716427))
                .body("results[0].title", equalTo("Roasted Butterflied Chicken w. Onions & Carrots"))
                .body("results[1].id", equalTo(780001))
                .body("results[1].title", equalTo("Pesto Chicken Zoodles"))
                .body("totalResults", equalTo(12));

    }

    @Test
    void getQueryNamePositiveTest() {
        given()
                .spec(requestSpecificationGet)
                .queryParam("query", "Greek Side Salad")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .spec(responseSpecificationGetPositive)
                .body("results[0].id", equalTo(645348))
                .body("results[0].title", equalTo("Greek Side Salad"))
                .body("totalResults", equalTo(1));
    }

    @Test
    void getErrorRequestNegativeTest() {
        given()
                .spec(requestSpecificationGet)
                .queryParam("query", "pharmacy")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .spec(responseSpecificationGetNegative);

    }

    @Test
    void getNutriensZincPositiveTest() {
        given()
                .spec(requestSpecificationGet)
                .queryParam("minZinc", 33)
                .queryParam("maxZinc", 35)
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .spec(responseSpecificationGetPositive)
                .body("results[0].id", equalTo(640869))
                .body("results[0].title", equalTo("Crock Pot Shredded French Dip"))
                .body("totalResults", equalTo(1));
    }

    @Test
    void getVeganMeatNegativeTest() {
        given()
                .spec(requestSpecificationGet)
                .queryParam("query", "pork")
                .queryParam("diet", "vegetarian")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .then()
                .spec(responseSpecificationGetNegative);

    }
}
