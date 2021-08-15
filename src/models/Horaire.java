package models;

public class Horaire {

    private static int counter = 1;
    private int id;
    private String campus;
    private ClasseCouple horaire[][];

    public Horaire(String campus) {
        this.id = counter++;
        this.campus = campus;
        this.horaire = new ClasseCouple[4][5];
    }

    // * Setters

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public void setHoraire(ClasseCouple horaire[][]) {
        this.horaire = horaire;
    }

    public void setTableCell(ClasseCouple cla, int row, int col) {
        horaire[row][col] = cla;
    }

    // * Getters

    public int getId() {
        return id;
    }

    public String getCampus() {
        return campus;
    }

    public ClasseCouple[][] getHoraire() {
        return horaire;
    }

    public ClasseCouple getTableCell(int row, int col) {
        return horaire[row][col];
    }
}
