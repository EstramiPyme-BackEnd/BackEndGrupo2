package com.example.estramipymes.exception;

public class ServiceResponse<T> {
    private final T data;
    private final String message;
    private final boolean success;

    private ServiceResponse(T data, String message, boolean success) {
        this.data = data;
        this.message = message;
        this.success = success;
    }

    public T getData() { return data; }
    public String getMessage() { return message; }
    public boolean isSuccess() { return success; }

    public static <T> ServiceResponse<T> success(T data) {
        return new ServiceResponse<>(data, null, true);
    }

    public static <T> ServiceResponse<T> error(String message) {
        return new ServiceResponse<>(null, message, false);
    }
}