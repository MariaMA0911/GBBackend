package lesson4;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class AbstractPostTest {
    final String apiKey = "8a067c5f5a6441ad8e3e422f201c9302";
    final String hash = "10b215e3fe51a0295be2d5563fec619a1997922d";
    ResponseSpecification responseSpecificationPostPositive = null;
    RequestSpecification requestSpecificationPost = null;
    ResponseSpecification responseSpecificationPostNegative = null;

    @BeforeEach
    void beforeTest() {
        //спецификация для post запросов
        requestSpecificationPost = new RequestSpecBuilder()
                .addQueryParam("apiKey", apiKey)
                .setContentType(ContentType.JSON)
                .build();

        //спецификация для post позитивных ответов
        responseSpecificationPostPositive = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .build();

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        //спецификация для post негативных ответов
        responseSpecificationPostNegative = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .expectBody("cuisine", CoreMatchers.equalTo("Mediterranean"))
                .expectBody("cuisines", hasItems("Mediterranean","European","Italian"))
                .expectBody("confidence", CoreMatchers.equalTo(0.0F))
                .build();
    }
}
