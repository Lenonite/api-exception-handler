package com.example.apiexceptionhandler.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApiError {
    private int status;
    private  String message;
    private  String developerMessage;

    public ApiError(int status, String message, String developerMessage) {
        this.status = status;
        this.message = message;
        this.developerMessage = developerMessage;
    }

}
