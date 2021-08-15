package models;

public class Horaire {
    private static int counter = 1;
    private int id;
    private String campus;
    private Classe horaire[][];

    public Horaire(String campus) {
        this.id = counter++;
        this.setCampus(campus);
        this.horaire = new Classe[4][5];
    }

    public Classe[][] getHoraire() {
        return horaire;
    }

    public void setTableCell(Classe cla, int row, int col) {
        horaire[row][col] = cla;
    }

    public Classe getTableCell(int row, int col) {
        return horaire[row][col];
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
}
