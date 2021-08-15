package models;

public class Memento {

    private Horaire horaire;

    public Memento(Horaire horaire) {
        this.horaire = horaire;
    }

    public Horaire getHoraire() {
        return this.horaire;
    }
}
