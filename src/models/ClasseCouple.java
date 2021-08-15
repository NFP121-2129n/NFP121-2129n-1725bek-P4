package models;

public class ClasseCouple extends Classe {
    private Salle salle;
    private Enseignant enseignant;
    private String jour;
    private String periode;

    public ClasseCouple(int capacite, Matiere matiere, String campus, String periode, String jour,
            Enseignant enseignant, Salle salle) {
        super(capacite, matiere, campus);
        setPeriode(periode);
        setEnseignant(enseignant);
        setJour(jour);
        setSalle(salle);
    }

    // * Getters
    public String getPeriode() {
        return periode;
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

    // * Setters
    public void setPeriode(String periode) {
        this.periode = periode;
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

    public String toString() {
        return getCode() + " " + getSalle() + " " + getEnseignant() + " " + getJour() + " " + getPeriode();
    }

}
