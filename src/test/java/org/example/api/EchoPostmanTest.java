package org.example.api;

import org.example.api.spec.Specifications;
import org.example.echopostman.get.EchoResp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class EchoPostmanTest {
    private final String URL = "https://postman-echo.com";

    @AfterEach
    void tearDown() {
        System.out.println("******************************************");
    }

    @Test
    public void checkGetTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOk200());
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
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOk200());
        String rawText = "This is expected to be sent back as part of response body.";
        EchoResp response = given()
                .body(rawText)
                .when()
                .post("/post")
                .then().log().all()
                .extract()
                .as(EchoResp.class);

        checkHeaders(response, URL.concat("/post"));

        assertAll("Проверка данных в теле",
                () -> assertThat(response.getData(), equalTo(rawText))
        );
    }

    @Test
    public void checkPostFormDataTest() {
        Specifications.installSpecification(Specifications.urlEncodedRequestSpec(URL), Specifications.responseSpecOk200());

        EchoResp response = given()
                .formParams("foo1", "bar1")
                .formParams("foo2", "bar2")
                .when()
                .post("/post")
                .then().log().all()
                .extract()
                .as(EchoResp.class);

        checkHeaders(response, URL.concat("/post"));

        assertAll("Проверка данных в теле",
                () -> assertThat(response.getForm().getFoo1(), equalTo("bar1")),
                () -> assertThat(response.getForm().getFoo2(), equalTo("bar2"))
        );
    }

    @Test
    public void checkPutRequestTest() {
        Specifications.installSpecification(Specifications.textPlainRequestSpec(URL), Specifications.responseSpecOk200());
        String rawText = "This is expected to be sent back as part of response body.";
        EchoResp response = given()
                .body(rawText)
                .when()
                .put("/put")
                .then().log().all()
                .extract()
                .as(EchoResp.class);

        checkHeaders(response, URL.concat("/put"));

        assertAll("Проверка данных в теле",
                () -> assertThat(response.getData(), equalTo(rawText))
        );
    }

    @Test
    public void checkPatchRequestTest() {
        Specifications.installSpecification(Specifications.textPlainRequestSpec(URL), Specifications.responseSpecOk200());
        String rawText = "This is expected to be sent back as part of response body.";
        EchoResp response = given()
                .body(rawText)
                .when()
                .patch("/patch")
                .then().log().all()
                .extract()
                .as(EchoResp.class);

        checkHeaders(response, URL.concat("/patch"));

        assertAll("Проверка данных в теле",
                () -> assertThat(response.getData(), equalTo(rawText))
        );
    }

    @Test
    public void checkDeleteRequestTest() {
        Specifications.installSpecification(Specifications.textPlainRequestSpec(URL), Specifications.responseSpecOk200());
        String rawText = "This is expected to be sent back as part of response body.";
        EchoResp response = given()
                .body(rawText)
                .when()
                .delete("/delete")
                .then().log().all()
                .extract()
                .as(EchoResp.class);

        checkHeaders(response, URL.concat("/delete"));

        assertAll("Проверка данных в теле",
                () -> assertThat(response.getData(), equalTo(rawText))
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
