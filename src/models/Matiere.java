package models;

public class Matiere {
    private static int counter = 1;
    private int id;
    private String code;
    private String campus;

    public Matiere(String code, String campus) {
        this.id = counter++;
        this.code = code;
        this.campus = campus;
    }

    // * Getters
    public String getCampus() {
        return campus;
    }

    public String getCode() {
        return code;
    }

    public int getId() {
        return id;
    }

    // * Setters
    public void setCampus(String campus) {
        this.campus = campus;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setId(int id) {
        this.id = id;
    }
}
