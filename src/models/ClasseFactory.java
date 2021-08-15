package models;

public class ClasseFactory {

    private static ClasseFactory instance = null;

    private ClasseFactory() {
    }

    public static ClasseFactory getInstance() {
        if (instance == null) {
            instance = new ClasseFactory();
        }
        return instance;
    }

    public Classe createClasse(int capacite, Matiere matiere, String campus) {
        return new ClasseNonCouple(capacite, matiere, campus);
    }

    public Classe createClasse(int capacite, Matiere matiere, String campus, String periode, String jour,
            Enseignant enseignant, Salle salle) {
        return new ClasseCouple(capacite, matiere, campus, periode, jour, enseignant, salle);
    }
}
