package models;

public class Horaire {
    private static int counter = 1;
    private int id;
    private String campus;
    private Classe horaire[][];

    public Horaire(String campus, Classe horaire[][]) {
        this.setId(counter++);
        this.setCampus(campus);
        this.setHoraire(horaire);
    }

    public Classe[][] getHoraire() {
        return horaire;
    }

    public void setHoraire(Classe horaire[][]) {
        this.horaire = horaire;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
