package models;

public class Salle {
    private static int counter = 1;
    private int id;
    private String code;
    private String campus;
    private int capacite;

    public Salle(String code, String campus, int capacite) {
        this.id = counter++;
        this.code = code;
        this.campus = campus;
        this.capacite = capacite;
    }

    // * Getters
    public int getCapacite() {
        return capacite;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getCampus() {
        return campus;
    }

    // * Setters
    public void setCampus(String campus) {
        this.campus = campus;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String toString() {
        return "Salle " + getCode() + ", " + getCampus() + " (Capacité: " + getCapacite() + ")";
    }
}
