package models;

import java.io.Serializable;

public class Matiere implements Serializable {
    private static int counter = 1;
    private int id;
    private String code;

    public Matiere(String code) {
        this.id = counter++;
        this.code = code;
    }

    // * Setters
    public static void setCounter(int i) {
        counter = i;
    }

    public void setCode(String code) {
        this.code = code;
    }

    // * Getters

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String toString() {
        return getCode();
    }
}
