package models;

public class Classe {
    private static int counter = 1;
    private int id;
    private int code;
    private int capacite;
    private String matiere;
    private String campus;

    public Classe(int code, int capacite, String matiere, String campus) {
        this.id = counter++;
        this.code = code;
        this.capacite = capacite;
        this.matiere = matiere;
        this.campus = campus;
    }

    // * Getters
    public String getCampus() {
        return campus;
    }

    public int getCapacite() {
        return capacite;
    }

    public int getCode() {
        return code;
    }

    public int getId() {
        return id;
    }

    public String getMatiere() {
        return matiere;
    }

    // * Setters
    public void setCampus(String campus) {
        this.campus = campus;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }
}
