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

    // * Setters
    public static void setCounter(int i) {
        counter = i;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    // * Getters

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getCampus() {
        return campus;
    }

    public int getCapacite() {
        return capacite;
    }

    public String tableString() {
        return getCode();
    }

    public String toString() {
        return "Salle " + getCode() + ", " + getCampus() + " (Capacité: " + getCapacite() + ")";
    }
}
