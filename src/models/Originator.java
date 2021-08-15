package models;

public class Originator {

    private Horaire horaire;
    private String campus;

    public Memento saveToMemento() {
        return new Memento(this.horaire);
    }

    public void getStateFromMemento(Memento memento) {
        this.horaire = memento.getHoraire();
    }

    // * Setters

    public void setHoraire(Horaire horaire) {
        this.horaire = horaire;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    // * Getters

    public Horaire getHoraire() {
        return this.horaire;
    }

    public String getCampus() {
        return this.campus;
    }
}
