package models;

public class Matiere {
    private static int counter = 1;
    private int id;
    private String code;

    public Matiere(String code) {
        this.id = counter++;
        this.code = code;
    }

    // * Getters

    public String getCode() {
        return code;
    }

    public int getId() {
        return id;
    }

    // * Setters

    public void setCode(String code) {
        this.code = code;
    }

    public String toString() {
        return getCode();
    }
}
