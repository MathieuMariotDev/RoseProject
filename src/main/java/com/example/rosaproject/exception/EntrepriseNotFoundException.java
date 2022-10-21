package com.example.rosaproject.exception;

public class EntrepriseNotFoundException extends RuntimeException {

    public EntrepriseNotFoundException(long id) {
        super("Enterprise with id " + id + " does not exist");
    }
}
