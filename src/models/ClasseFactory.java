package models;

public class ClasseFactory {

    // * The factory is also a singleton and has only one private instance
    private static ClasseFactory instance = null;

    // * Being a singleton, the constructor is also private
    private ClasseFactory() {
    }

    // * Grab the instance of the Factory
    public static ClasseFactory getInstance() {
        if (instance == null) {
            instance = new ClasseFactory();
        }
        return instance;
    }

    // * The subclasses of the factory have different arguments, so we made 2 copies
    // * of the same function, each taking in the arguments of the respective
    // * subclass it returns

    public Classe createClasse(int capacite, Matiere matiere, String campus) {
        return new ClasseNonCouple(capacite, matiere, campus);
    }

    public Classe createClasse(int capacite, Matiere matiere, String campus, String periode, String jour,
            Enseignant enseignant, Salle salle) {
        return new ClasseCouple(capacite, matiere, campus, periode, jour, enseignant, salle);
    }
}
