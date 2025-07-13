package com.example.bansalmotors.Bansal.Motors.advices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAPI<T> {
    private String message;
    private boolean success;
    private T data;

    public static <T> ResponseAPI<T> success(T data, String message) {
        return new ResponseAPI<>(message, true, data);
    }

    public static <T> ResponseAPI<T> failure(String message) {
        return new ResponseAPI<>(message, false, null);
    }
}
