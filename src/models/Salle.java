package models;

public class Salle {
    private static int counter = 1;
    private int id;
    private String name;
    private String campus;
    private int capacite;

    public Salle(String name, String campus, int capacite) {
        this.id = counter++;
        this.name = name;
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

    public String getName() {
        return name;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
