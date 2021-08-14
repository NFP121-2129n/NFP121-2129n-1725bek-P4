package models;

public class Classe {
    private static int counter = 1;
    private int id;
    private String code;
    private int capacite;
    private Matiere matiere;
    private String campus;

    public Classe(String code, int capacite, Matiere matiere, String campus) {
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

    public String getCode() {
        return code;
    }

    public int getId() {
        return id;
    }

    public Matiere getMatiere() {
        return matiere;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public String toString() {
        return "Classe " + getCode() + " : " + getMatiere() + "(cap : " + getCapacite() + ")";
    }
}
