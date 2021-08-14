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
    private int debut;
    private int fin;
    private boolean coupled;

    public Classe(int capacite, Matiere matiere, String campus) {
        this.id = counter++;
        this.capacite = capacite;
        this.matiere = matiere;
        this.campus = campus;
        this.coupled = false;
        setCode();
    }

    // * Getters
    public boolean isCoupled() {
        return coupled;
    }

    public int getFin() {
        return fin;
    }

    public int getDebut() {
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
    public void setFin(int fin) {
        this.fin = fin;
    }

    public void setDebut(int debut) {
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

    public void setCode() {
        this.code = matiere.getCode() + "-" + campus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public String toString() {
        if (isCoupled()) {
            return getCode() + ", " + getSalle().getCode() + " avec " + getEnseignant().getNom() + " : " + getJour()
                    + " " + getDebut() + "h00-" + getFin() + "h00";
        }
        return getCode() + " : " + "(cap : " + getCapacite() + ")";
    }

    public void setCoupled() {
        this.coupled = true;
    }
}
