package org.example.echopostman.get;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import java.util.Objects;
import java.util.stream.Stream;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EchoResp {
    private Args args;
    private Headers headers;
    private String data;
    private Form form;
    private String url;

    public boolean allArgsNotNull() {
        return args != null && args.allFieldsNotNull();
    }

    public boolean allHeadersNotNull() {
        return Stream.of(headers, url)
                .allMatch(Objects::nonNull) && headers.allFieldsNotNull();
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Args {
        private String foo1;
        private String foo2;

        public Args() {
        }

        public boolean allFieldsNotNull() {
            return Stream.of(foo1, foo2)
                    .allMatch(Objects::nonNull);
        }
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Headers {
        @JsonProperty("x-request-start")
        private String xRequestStart;
        @JsonProperty("x-forwarded-proto")
        private String xForwardedProto;
        @JsonProperty("x-forwarded-port")
        private String xForwardedPort;
        @JsonProperty("x-amzn-trace-id")
        private String xAmznTraceId;
        @JsonProperty("user-agent")
        private String userAgent;
        @JsonProperty("accept-encoding")
        private String acceptEncoding;
        private String host;
        private String accept;

        public boolean allFieldsNotNull() {
            return Stream.of(xRequestStart, xForwardedProto, xForwardedPort, xAmznTraceId, userAgent, host, accept)
                    .allMatch(Objects::nonNull);
        }
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Form {
        private String foo1;
        private String foo2;
    }
}
