package com.example.libraryProject.DTO;

import lombok.Data;

@Data
public class MessageResponseDTO {

    private String message;
    private int statusCode;
    private Object object;

    public MessageResponseDTO(String message, int statusCode, Object object) {
        this.message = message;
        this.statusCode = statusCode;
        this.object = object;
    }
}
