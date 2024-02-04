package com.example.umoove;

public class MesCouponsModel {

    Boolean date_used;
    String description;
    String code;
    String entreprise;

    public MesCouponsModel(Boolean date_used, String description, String code, String entreprise) {
        this.date_used = date_used;
        this.description = description;
        this.code = code;
        this.entreprise = entreprise;
    }


    public Boolean getDate_used() {
        return date_used;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    public String getEntreprise() {
        return entreprise;
    }
}
