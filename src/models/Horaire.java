package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Horaire implements Serializable {

    private static int counter = 1;
    private int id;
    private String campus;
    private ArrayList<ClasseCouple> horaire[][];

    @SuppressWarnings("unchecked")
    public Horaire(String campus) {
        this.id = counter++;
        this.campus = campus;
        this.horaire = (ArrayList<ClasseCouple>[][]) new ArrayList[4][5];
        for (int i = 0; i < horaire.length; i++) {
            for (int j = 0; j < horaire[i].length; j++) {
                horaire[i][j] = new ArrayList<ClasseCouple>();
            }
        }
    }

    // * Setters

    public static void setCounter(int i) {
        counter = i;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public void setHoraire(ArrayList<ClasseCouple> horaire[][]) {
        this.horaire = horaire;
    }

    public void setTableCell(ClasseCouple cla, int row, int col) {
        horaire[row][col].add(cla);
    }

    // * Getters

    public int getId() {
        return id;
    }

    public String getCampus() {
        return campus;
    }

    public ArrayList<ClasseCouple>[][] getHoraire() {
        return horaire;
    }

    public ArrayList<ClasseCouple> getTableCell(int row, int col) {
        return horaire[row][col];
    }
}
