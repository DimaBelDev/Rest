package ru.example.rest.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class MyErrorMessage {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;
}
