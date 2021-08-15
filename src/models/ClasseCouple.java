package models;

public class ClasseCouple extends Classe {
    private Enseignant enseignant;
    private Salle salle;
    private String jour;
    private String periode;

    public ClasseCouple(int capacite, Matiere matiere, String campus, String periode, String jour,
            Enseignant enseignant, Salle salle) {
        super(capacite, matiere, campus);
        setEnseignant(enseignant);
        setSalle(salle);
        setJour(jour);
        setPeriode(periode);
    }

    // * Setters

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    // * Getters

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public Salle getSalle() {
        return salle;
    }

    public String getJour() {
        return jour;
    }

    public String getPeriode() {
        return periode;
    }

    public String toString() {
        return getCode() + " " + getSalle() + " " + getEnseignant() + " " + getJour() + " " + getPeriode();
    }
}
