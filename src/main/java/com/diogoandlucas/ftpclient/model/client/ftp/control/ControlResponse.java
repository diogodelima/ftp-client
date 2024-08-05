package com.diogoandlucas.ftpclient.model.client.ftp.control;

public class ControlResponse {

    private final ControlResponseCode code;
    private final String message;

    public ControlResponse(ControlResponseCode code, String message) {
        this.code = code;
        this.message = message;
    }

    public ControlResponseCode getCode() {
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
