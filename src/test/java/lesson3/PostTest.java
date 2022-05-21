package lesson3;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.equalTo;



public class PostTest extends AbstractTest{

    @Test
    void postIngridientsPositiveTest(){
        given()
                .queryParam("apiKey", apiKey)
                .queryParam("ingredientList", "3 oz pork shoulder")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(5000L))
                .body("cuisine", equalTo("Mediterranean"))
                .body("cuisines", hasItems("Mediterranean","European","Italian"))
                .body("confidence", equalTo(0.0F));
    }

    @Test
    void postTitlePositiveTest(){
        given()
                .queryParam("apiKey", apiKey)
                .queryParam("title", "Layered Greek Salad")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(50000L))
                .body("cuisine", equalTo("Mediterranean"))
                .body("cuisines", hasItems("Mediterranean","European","Greek"))
                .body("confidence", equalTo(0.95F));
    }

    @Test
    void postTitleErrorInNamePositiveTest(){
        given()
                .queryParam("apiKey", apiKey)
                .queryParam("title", "Crack Pod Shredded French Dip")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(5000L))
                .body("cuisine", equalTo("Mediterranean"))
                .body("cuisines", hasItems("Mediterranean","European","French"))
                .body("confidence", equalTo(0.95F));
    }

    @Test
    void postTitleSymbolInNameNegativeTest(){
        given()
                .queryParam("apiKey", apiKey)
                .queryParam("title", "++++++++++++++++++++++")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(5000L))
                .body("cuisine", equalTo("Mediterranean"))
                .body("cuisines", hasItems("Mediterranean","European","Italian"))
                .body("confidence", equalTo(0.0F));
    }
    @Test
    void postTitlePartNameNegativeTest(){
        given()
                .queryParam("apiKey", apiKey)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .queryParam("title", "Crock Pot")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(5000L))
                .body("cuisine", equalTo("Mediterranean"))
                .body("cuisines", hasItems("Mediterranean","European","Italian"))
                .body("confidence", equalTo(0.0F));
    }


}