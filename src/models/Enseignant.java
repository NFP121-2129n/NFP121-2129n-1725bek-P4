package models;

public class Enseignant {
    private static int counter = 1;
    private int id;
    private String nom;

    public Enseignant(String nom) {
        this.id = counter++;
        this.nom = nom;
    }

    // * Getters
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    // * Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
