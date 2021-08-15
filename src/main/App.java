package main;

import java.awt.*;
import java.util.List;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import java.io.*;
import java.util.*;

import views.*;
import models.*;

public class App implements ActionListener {

    public static JFrame frame;
    public static JPanel panel;

    JMenuBar mBar;
    JMenuItem iEns, iCla, iSal, iMat, iHor;

    public static ArrayList<Enseignant> listEns = new ArrayList<Enseignant>();
    public static ArrayList<Classe> listCla = new ArrayList<Classe>();
    public static ArrayList<Matiere> listMat = new ArrayList<Matiere>();
    public static ArrayList<Salle> listSal = new ArrayList<Salle>();
    public static ArrayList<Horaire> listHor = new ArrayList<Horaire>();
    public static ArrayList<Caretaker> listCaretaker = new ArrayList<Caretaker>();
    public static ArrayList<Originator> listOriginator = new ArrayList<Originator>();
    public static ArrayList<String> listCampus;

    String currentPanel = "";

    App() {
        frame = new JFrame("NFP121-2129n-1725bek-P4");
        frame.setLocation((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - 600,
                (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - 420);
        frame.setPreferredSize(new Dimension(1200, 800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        mBar = new JMenuBar();
        iEns = new JMenuItem("Enseignant");
        iEns.addActionListener(this);
        iCla = new JMenuItem("Classe");
        iCla.addActionListener(this);
        iSal = new JMenuItem("Salle");
        iSal.addActionListener(this);
        iMat = new JMenuItem("Matière");
        iMat.addActionListener(this);
        iHor = new JMenuItem("Horaire");
        iHor.addActionListener(this);
        mBar.add(iEns);
        mBar.add(iCla);
        mBar.add(iSal);
        mBar.add(iMat);
        mBar.add(iHor);
        frame.setJMenuBar(mBar);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        // * Campus Array
        listCampus = new ArrayList<String>(
                List.of("Bikfaya", "Beyrouth", "Nahr Ibrahim", "Tripoli", "Bekaa", "Chtoura", "Baakline"));
        Collections.sort(listCampus);
        listCampus.forEach((c) -> {
            listHor.add(new Horaire(c));
            Caretaker tempCaretaker = new Caretaker();
            tempCaretaker.setCampus(c);
            listCaretaker.add(tempCaretaker);
            Originator tempOriginator = new Originator();
            tempOriginator.setCampus(c);
            listOriginator.add(tempOriginator);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().toString().equals(currentPanel)) {
            JOptionPane.showMessageDialog(null, "Vous êtes deja dans la section \"" + currentPanel + "\"");
            return;
        }
        Object o = e.getSource();
        currentPanel = e.getActionCommand().toString();
        if (o == iEns) {
            if (panel.isShowing()) {
                frame.remove(panel);
            }
            panel = new EnseignantView().mainPanel;
            frame.setContentPane(panel);
            frame.pack();
        }
        if (o == iCla) {
            if (panel.isShowing()) {
                frame.remove(panel);
            }
            panel = new ClasseView().mainPanel;
            frame.setContentPane(panel);
            frame.pack();
        }
        if (o == iSal) {
            if (panel.isShowing()) {
                frame.remove(panel);
            }
            panel = new SalleView().mainPanel;
            frame.setContentPane(panel);
            frame.pack();
        }
        if (o == iMat) {
            if (panel.isShowing()) {
                frame.remove(panel);
            }
            panel = new MatiereView().mainPanel;
            frame.setContentPane(panel);
            frame.pack();
        }
        if (o == iHor) {
            if (panel.isShowing()) {
                frame.remove(panel);
            }
            panel = new HoraireView().mainPanel;
            frame.setContentPane(panel);
            frame.pack();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadData() {
        File directory = new File("../../data");
        if (directory.isDirectory() && directory.exists()) {
            FileInputStream fis;
            ObjectInputStream ois;

            // * Classes
            try {
                File fileCla = new File(directory.getName() + "/classes");
                if (fileCla.exists()) {
                    fis = new FileInputStream(fileCla);
                    ois = new ObjectInputStream(fis);
                    ArrayList<Classe> tempList = (ArrayList<Classe>) ois.readObject();
                    ois.close();
                    if (!tempList.isEmpty()) {
                        listCla = tempList;
                        Classe.setCounter(listCla.size() + 1);
                    }
                }
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex);
            }

            // * Salle
            try {
                File fileSal = new File(directory.getName() + "/salles");
                if (fileSal.exists()) {
                    fis = new FileInputStream(fileSal);
                    ois = new ObjectInputStream(fis);
                    ArrayList<Salle> tempList = (ArrayList<Salle>) ois.readObject();
                    ois.close();
                    if (!tempList.isEmpty()) {
                        listSal = tempList;
                        Salle.setCounter(listSal.size() + 1);
                    }
                }
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex);
            }

            // * Enseignant
            try {
                File fileEns = new File(directory.getName() + "/enseignants");
                if (fileEns.exists()) {
                    fis = new FileInputStream(fileEns);
                    ois = new ObjectInputStream(fis);
                    ArrayList<Enseignant> tempList = (ArrayList<Enseignant>) ois.readObject();
                    ois.close();
                    if (!tempList.isEmpty()) {
                        listEns = tempList;
                        Enseignant.setCounter(listEns.size() + 1);
                    }
                }
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex);
            }

            // * Matiere
            try {
                File fileMat = new File(directory.getName() + "/matieres");
                if (fileMat.exists()) {
                    fis = new FileInputStream(fileMat);
                    ois = new ObjectInputStream(fis);
                    ArrayList<Matiere> tempList = (ArrayList<Matiere>) ois.readObject();
                    ois.close();
                    if (!tempList.isEmpty()) {
                        listMat = tempList;
                        Matiere.setCounter(listMat.size() + 1);
                    }
                }
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex);
            }

            // * Horaire
            try {
                File fileHor = new File(directory.getName() + "/horaires");
                if (fileHor.exists()) {
                    fis = new FileInputStream(fileHor);
                    ois = new ObjectInputStream(fis);
                    ArrayList<Horaire> tempList = (ArrayList<Horaire>) ois.readObject();
                    ois.close();
                    if (!tempList.isEmpty()) {
                        listHor = tempList;
                        Horaire.setCounter(listHor.size() + 1);
                    }
                }
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Répertoire non-trouvée!.");
        }
    }

    public static void main(String[] args) {
        new App();
    }
}
