package org.example.api;

import org.example.api.spec.Specifications;
import org.example.echopostman.get.EchoResp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class EchoPostmanTest {
    private final String URL = "https://postman-echo.com";

    @BeforeEach
    void setup() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOk200());
    }

    @AfterEach
    void tearDown() {
        System.out.println("******************************************");
    }

    @Test
    public void checkGetTest() {
        EchoResp response = given()
                .when()
                .get("/get?foo1=bar1&foo2=bar2")
                .then().log().all()
                .extract()
                .as(EchoResp.class);

        checkHeaders(response, URL.concat("/get?foo1=bar1&foo2=bar2"));
        checkArgs(response);

        assertAll("Проверка параметров в args",
                () -> assertThat(response.getArgs().getFoo1(), equalTo("bar1")),
                () -> assertThat(response.getArgs().getFoo2(), equalTo("bar2"))
        );
    }

    @Test
    public void checkPostRawTextTest() {
        String requestBody = "{\"test\":\"value\"}";
        EchoResp response = given()
                .body(requestBody)
                .when()
                .post("/post")
                .then().log().all()
                .extract()
                .as(EchoResp.class);

        checkHeaders(response, URL.concat("/post"));

        assertAll("Проверка данных в теле",
                () -> assertThat(response.getData().getTest(), equalTo("value"))
        );
    }

    private void checkHeaders(EchoResp response, String expectedUrl) {
        assertAll("Проверка заголовков",
                () -> assertThat(response.allHeadersNotNull(), is(true)),
                () -> assertThat(response.getHeaders().getHost(), equalTo("postman-echo.com")),
                () -> assertThat(response.getHeaders().getXRequestStart(), matchesPattern("^t=.+$")),
                () -> assertThat(response.getHeaders().getXForwardedProto(), equalTo("https")),
                () -> assertThat(response.getHeaders().getXForwardedPort(), equalTo("443")),
                () -> assertThat(response.getHeaders().getXAmznTraceId(), matchesPattern("^Root=.+$")),
                () -> assertThat(response.getHeaders().getUserAgent(), notNullValue()),
                () -> assertThat(response.getHeaders().getAccept(), equalTo("*/*")),
                () -> assertThat(response.getHeaders().getAcceptEncoding(), equalTo("gzip,deflate")),
                () -> assertThat(response.getUrl(), equalTo(expectedUrl))
        );
    }

    private void checkArgs(EchoResp response) {
        assertThat("Проверка параметров args", response.allArgsNotNull(), is(true));
    }
}
