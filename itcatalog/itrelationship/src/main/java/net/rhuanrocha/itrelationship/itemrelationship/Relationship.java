package net.rhuanrocha.itrelationship.itemrelationship;

public enum Relationship {

    SUPPORTS("SUPPORTS"),
    INTEGRATE("INTEGRATE"),
    USES("USES");

    private String value;

    Relationship(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
