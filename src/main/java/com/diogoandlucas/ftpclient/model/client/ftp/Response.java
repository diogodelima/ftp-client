package com.diogoandlucas.ftpclient.model.client.ftp;

public class Response {

    private final ResponseCode code;
    private final String message;

    public Response(ResponseCode code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseCode getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return """
                {
                    "code": "%s",
                    "message": "%s"
                }
                """.formatted(getCode().name(), message);
    }
}
