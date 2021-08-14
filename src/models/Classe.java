package models;

public class Classe {
    private static int counter = 1;
    private int id;
    private String code;
    private int capacite;
    private Matiere matiere;
    private String campus;
    private Salle salle;
    private Enseignant enseignant;
    private String jour;
    private double debut;
    private double fin;

    public Classe(String code, int capacite, Matiere matiere, String campus) {
        this.id = counter++;
        this.code = code;
        this.capacite = capacite;
        this.matiere = matiere;
        this.campus = campus;
    }

    // * Getters
    public double getFin() {
        return fin;
    }

    public double getDebut() {
        return debut;
    }

    public String getJour() {
        return jour;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public Salle getSalle() {
        return salle;
    }

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
    public void setFin(double fin) {
        this.fin = fin;
    }

    public void setDebut(double debut) {
        this.debut = debut;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

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
