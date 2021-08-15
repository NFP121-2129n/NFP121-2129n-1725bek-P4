package models;

import java.util.*;

public class Caretaker {

    public Stack<Memento> mementoStack = new Stack<Memento>();
    private String campus;

    // * Setters

    public void add(Memento m) {
        mementoStack.push(m);
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    // * Getters

    public Memento rollBack() {
        if (mementoStack.isEmpty()) {
            return new Memento(new Horaire(""));
        }
        Memento temp = mementoStack.pop();
        if (mementoStack.isEmpty()) {
            add(temp);
            return temp;
        }
        return mementoStack.peek();
    }

    public String getCampus() {
        return campus;
    }
}
