package models;

abstract public class Classe {
    private static int counter = 1;
    private int id;
    private String code;
    private int capacite;
    private Matiere matiere;
    private String campus;

    public Classe(int capacite, Matiere matiere, String campus) {
        this.id = counter++;
        this.capacite = capacite;
        this.matiere = matiere;
        this.campus = campus;
        setCode();
    }

    // * Setters

    public static void setCounter(int i) {
        counter = i;
    }

    public void setCode() {
        this.code = matiere.getCode() + "-" + campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
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

    public Matiere getMatiere() {
        return matiere;
    }

    abstract public String toString();
}
