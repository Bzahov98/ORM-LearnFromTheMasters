package com.bzahov.exceptions;

public class MyDataErrorException extends Exception {
    private String message = "";
    @Override
    public String getMessage() {
        return "\n >> Data Error" + message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
