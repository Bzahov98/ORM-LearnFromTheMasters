package com.bzahov.OnlineFilmSystem.exceptions;

public class MyDataErrorException extends Exception {
    @Override
    public String getMessage() {
        return "\n >> Data Error";
    }
}
