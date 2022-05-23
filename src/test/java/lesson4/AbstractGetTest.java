package lesson4;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.core.IsEqual.equalTo;

public class AbstractGetTest {
    final String apiKey = "8a067c5f5a6441ad8e3e422f201c9302";
    ResponseSpecification responseSpecificationGetPositive = null;
    RequestSpecification requestSpecificationGet = null;
    ResponseSpecification responseSpecificationGetNegative = null;

    @BeforeEach
    void beforeTest() {
        //спецификация для get позитивных ответов
        responseSpecificationGetPositive = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .expectBody("offset", equalTo(0))
                .expectBody("number", equalTo(10))
                .build();

        // RestAssured.responseSpecification = responseSpecification;

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

       //спецификация для get позитивных запросов
        requestSpecificationGet = new RequestSpecBuilder()
                .addQueryParam("apiKey", apiKey)
                .setContentType(ContentType.JSON)
                .build();

        // RestAssured.requestSpecification = requestSpecification;

        //спецификация для get негативных ответов
        responseSpecificationGetNegative = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .expectBody("offset", equalTo(0))
                .expectBody("number", equalTo(10))
                .expectBody("totalResults", equalTo(0))
                .build();
    }
}
