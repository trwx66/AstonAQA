package org.example.echopostman.get;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EchoResp {
    private Args args;
    private Headers headers;
    private Data data;
    private String url;

    public String getUrl() {
        return url;
    }

    public Args getArgs() {
        return args;
    }

    public Headers getHeaders() {
        return headers;
    }

    public Data getData() {
        return data;
    }

    public boolean allArgsNotNull() {
        return args != null && args.allFieldsNotNull();
    }

    public boolean allHeadersNotNull() {
        return Stream.of(headers, url)
                .allMatch(Objects::nonNull) && headers.allFieldsNotNull();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Args {
        private String foo1;
        private String foo2;

        public Args() {
        }

        public String getFoo1() {
            return foo1;
        }

        public String getFoo2() {
            return foo2;
        }

        public boolean allFieldsNotNull() {
            return Stream.of(foo1, foo2)
                    .allMatch(Objects::nonNull);
        }
    }

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

        public String getAcceptEncoding() {
            return acceptEncoding;
        }

        public String getHost() {
            return host;
        }

        public String getXRequestStart() {
            return xRequestStart;
        }

        public String getXForwardedProto() {
            return xForwardedProto;
        }

        public String getXForwardedPort() {
            return xForwardedPort;
        }

        public String getXAmznTraceId() {
            return xAmznTraceId;
        }

        public String getUserAgent() {
            return userAgent;
        }

        public String getAccept() {
            return accept;
        }

        public boolean allFieldsNotNull() {
            return Stream.of(xRequestStart, xForwardedProto, xForwardedPort, xAmznTraceId, userAgent, host, accept)
                    .allMatch(Objects::nonNull);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {
        private String test;

        public String getTest() {
            return test;
        }
    }
}
