package models;

import java.util.*;

import main.App;

public class Matiere {
    private static int counter = 1;
    private int id;
    private String code;
    private ArrayList<String> campus = new ArrayList<String>();

    public Matiere(String code, String campus) {
        this.id = counter++;
        this.code = code;
        this.campus.add(campus);
    }

    // * Getters
    public ArrayList<String> getCampusArray() {
        return campus;
    }

    public String getCode() {
        return code;
    }

    public int getId() {
        return id;
    }

    public static ArrayList<Matiere> getByCampus(String campus) {
        ArrayList<Matiere> listMat = App.listMat;
        ArrayList<Matiere> res = new ArrayList<Matiere>();
        for (Matiere matiere : listMat) {
            if (matiere.getCampusArray().contains(campus)) {
                res.add(matiere);
            }
        }
        return res;
    }

    // * Setters
    public void addCampus(String campus) {
        this.campus.add(campus);
    }

    public void removeCampus(String campus) {
        this.campus.remove(campus);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String toString() {
        return getCode() + " (" + String.join(", ", getCampusArray()) + ")";
    }
}
