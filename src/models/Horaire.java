package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Horaire implements Serializable {

    private static int counter = 1;
    private int id;
    private String campus;
    // * The actual horaire of the campus is stored in a two dimensional array of
    // * arraylists. Basically the two dimensional array represents the JTable of 5
    // * days and 4 time intervals. And each of these time intervals can contain one
    // * or more Classes scheduled, hence the arraylist
    private ArrayList<ClasseCouple> horaire[][];

    @SuppressWarnings("unchecked")
    public Horaire(String campus) {
        this.id = counter++;
        this.campus = campus;
        // * Creating the 4x5 template of the horaire table and populating it with empty
        // * arraylists of Coupled Classes
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
