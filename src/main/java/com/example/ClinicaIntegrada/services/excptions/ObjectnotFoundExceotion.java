package com.example.ClinicaIntegrada.services.excptions;

public class ObjectnotFoundExceotion extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ObjectnotFoundExceotion (String message, Throwable cause) {
        super(message, cause);
    }
    public ObjectnotFoundExceotion(String message){
        super(message);
    }
}