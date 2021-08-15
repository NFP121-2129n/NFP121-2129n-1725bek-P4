package models;

public class ClasseNonCouple extends Classe {

    public ClasseNonCouple(int capacite, Matiere matiere, String campus) {
        super(capacite, matiere, campus);
    }

    @Override
    public String toString() {
        return getCode() + " : " + "(cap : " + getCapacite() + ")";
    }
}
