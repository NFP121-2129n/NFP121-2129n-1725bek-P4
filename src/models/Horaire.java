package models;

public class Horaire {
    private static int counter = 1;
    private int id;
    private String campus;
    private Classe horaire[][];

    public Horaire(String campus, Classe horaire[][]) {
        this.id = counter++;
        this.setCampus(campus);
        this.setHoraire(horaire);
    }

    // * Getters

    public Classe[][] getHoraire() {
        return horaire;
    }

    public String getCampus() {
        return campus;
    }

    public int getId() {
        return id;
    }

    // * Setters

    public void setHoraire(Classe horaire[][]) {
        this.horaire = horaire;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }
}
