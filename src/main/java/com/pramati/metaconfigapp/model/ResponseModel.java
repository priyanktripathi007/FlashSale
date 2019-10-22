package com.pramati.metaconfigapp.model;

public class ResponseModel {
    String message;

    public String getMessage() {
        return message;
    }

    public ResponseModel() {
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseModel(String message) {
        this.message = message;
    }
}
