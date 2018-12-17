package com.sheygam.masa_2018_g1_05_12_18_hw.data.model;

public class ErrorDto {
    private int code;
    private String details;
    private String message;
    private String timestamp;

    public ErrorDto() {
    }

    public ErrorDto(int code, String details, String message, String timestamp) {
        this.code = code;
        this.details = details;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ErrorDto{" +
                "code=" + code +
                ", details='" + details + '\'' +
                ", message='" + message + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
