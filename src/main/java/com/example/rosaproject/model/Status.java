package com.example.rosaproject.model;

public enum Status { //TODO DELETE ?

    Aucun("A prospecter"),cours("En cours de prospection"),relancer("A relancer"),termine("Terminé");

    private String statusName;

    Status(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

}
