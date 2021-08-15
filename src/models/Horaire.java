package models;

public class Horaire {
    private static int counter = 1;
    private int id;
    private String campus;
    private ClasseCouple horaire[][];

    public Horaire(String campus) {
        this.id = counter++;
        this.setCampus(campus);
        this.horaire = new ClasseCouple[4][5];
    }

    public ClasseCouple[][] getHoraire() {
        return horaire;
    }

    public void setTableCell(ClasseCouple cla, int row, int col) {
        horaire[row][col] = cla;
    }

    public ClasseCouple getTableCell(int row, int col) {
        return horaire[row][col];
    }

    public void setHoraire(ClasseCouple horaire[][]) {
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
