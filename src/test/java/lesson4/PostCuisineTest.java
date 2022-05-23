package lesson4;


import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class PostCuisineTest extends AbstractPostTest{
    @Test
    void postIngridientsPositiveTest(){
        Response response = given()
                .spec(requestSpecificationPost)
                .formParam("ingredientList", "3 oz pork shoulder")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .spec(responseSpecificationPostPositive)
                .extract()
                .response()
                .body()
                .as(Response.class);
        assertThat(response.getCuisine(), containsString("Mediterranean"));
        assertThat(response.getCuisines(), hasItems("Mediterranean","European","Italian"));
        assertThat(response.getConfidence(), equalTo(0.0));
    }

    @Test
    void postTitlePositiveTest(){
        Response response1 = given()
                .spec(requestSpecificationPost)
                .formParam("title", "Layered Greek Salad")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .spec(responseSpecificationPostPositive)
                .extract()
                .response()
                .body()
                .as(Response.class);
        assertThat(response1.getCuisine(), containsString("Mediterranean"));
        assertThat(response1.getCuisines(), hasItems("Mediterranean","European","Greek"));
        assertThat(response1.getConfidence(), equalTo(0.95));

    }

    @Test
    void postTitleErrorInNamePositiveTest(){
        Response response2 = given()
                .spec(requestSpecificationPost)
                .formParam("title", "Crack Pod Shredded French Dip")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .spec(responseSpecificationPostPositive)
                .extract()
                .response()
                .body()
                .as(Response.class);
        assertThat(response2.getCuisine(), containsString("Mediterranean"));
        assertThat(response2.getCuisines(), hasItems("Mediterranean","European","French"));
        assertThat(response2.getConfidence(), equalTo(0.95));
                    }

    @Test
    void postTitleSymbolInNameNegativeTest(){
        given()
                .spec(requestSpecificationPost)
                .formParam("title", "++++++++++++++++++++++")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .spec(responseSpecificationPostPositive);
    }
    @Test
    void postTitlePartNameNegativeTest(){
        given()
                .spec(requestSpecificationPost)
                .formParam("title", "Crock Pot")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .then()
                .spec(responseSpecificationPostPositive);
    }

}
