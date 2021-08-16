package main;

import java.awt.*;
import java.util.List;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.util.*;

import views.*;
import models.*;

public class App implements ActionListener {

    public static JFrame frame;
    public static JPanel panel;

    JMenuBar mBar;
    JMenuItem iEns, iCla, iSal, iMat, iHor, iSave, iVer;

    public static ArrayList<Enseignant> listEns = new ArrayList<Enseignant>();
    public static ArrayList<Classe> listCla = new ArrayList<Classe>();
    public static ArrayList<Matiere> listMat = new ArrayList<Matiere>();
    public static ArrayList<Salle> listSal = new ArrayList<Salle>();
    public static ArrayList<Horaire> listHor = new ArrayList<Horaire>();
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
        iSave = new JMenuItem("Sauvegarder");
        iSave.addActionListener(this);
        iVer = new JMenuItem("Version");
        iVer.addActionListener(this);
        mBar.add(iEns);
        mBar.add(iCla);
        mBar.add(iSal);
        mBar.add(iMat);
        mBar.add(iHor);
        mBar.add(iSave);
        mBar.add(iVer);
        frame.setJMenuBar(mBar);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        // * Populating the Campus array
        listCampus = new ArrayList<String>(
                List.of("Bikfaya", "Beyrouth", "Nahr Ibrahim", "Tripoli", "Bekaa", "Chtoura", "Baakline"));
        Collections.sort(listCampus);
        listCampus.forEach((c) -> {
            listHor.add(new Horaire(c));
        });
        // * Loading the saved data immediately on run
        loadData();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // * Forbid the user to call the same panel twice
        if (e.getActionCommand().toString().equals(currentPanel)) {
            JOptionPane.showMessageDialog(null, "Vous êtes deja dans la section \"" + currentPanel + "\"");
            return;
        }
        Object o = e.getSource();
        currentPanel = e.getActionCommand().toString();
        // * Create Enseignant panel
        if (o == iEns) {
            if (panel.isShowing()) {
                frame.remove(panel);
            }
            panel = new EnseignantView().mainPanel;
            frame.setContentPane(panel);
            frame.pack();
        }
        // * Create Classe panel
        if (o == iCla) {
            if (panel.isShowing()) {
                frame.remove(panel);
            }
            panel = new ClasseView().mainPanel;
            frame.setContentPane(panel);
            frame.pack();
        }
        // * Create Salle panel
        if (o == iSal) {
            if (panel.isShowing()) {
                frame.remove(panel);
            }
            panel = new SalleView().mainPanel;
            frame.setContentPane(panel);
            frame.pack();
        }
        // * Create Matière panel
        if (o == iMat) {
            if (panel.isShowing()) {
                frame.remove(panel);
            }
            panel = new MatiereView().mainPanel;
            frame.setContentPane(panel);
            frame.pack();
        }
        // * Create Horaire panel
        if (o == iHor) {
            if (panel.isShowing()) {
                frame.remove(panel);
            }
            panel = new HoraireView().mainPanel;
            frame.setContentPane(panel);
            frame.pack();
        }
        // * Save data into files
        if (o == iSave) {
            saveData();
        }
        // * Check app version
        if (o == iVer) {
            JOptionPane.showMessageDialog(null, "Version 1.0.0");
        }
    }

    // * Function that loads data from files and saves them in the object arrays
    @SuppressWarnings("unchecked")
    public void loadData() {
        File directory = new File("data");
        if (directory.isDirectory() && directory.exists()) {
            FileInputStream fis;
            ObjectInputStream ois;
            try {
                File fileCla = new File(directory.getName() + "\\classes");
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
                JOptionPane.showMessageDialog(null, "Chargement des classes erronné!");
            }
            try {
                File fileSal = new File(directory.getName() + "\\salles");
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
                JOptionPane.showMessageDialog(null, "Chargement des salles erronné!");
            }
            try {
                File fileEns = new File(directory.getName() + "\\enseignants");
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
                JOptionPane.showMessageDialog(null, "Chargement des enseignants erronné!");
            }
            try {
                File fileMat = new File(directory.getName() + "\\matieres");
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
                JOptionPane.showMessageDialog(null, "Chargement des matières erronné");
            }
            try {
                File fileHor = new File(directory.getName() + "\\horaires");
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
                JOptionPane.showMessageDialog(null, "Chargement des horaires erronné");
            }
        }
        panel.revalidate();
        panel.repaint();
    }

    // * Reads data from the object arrays and saves it into files
    public void saveData() {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            File fileEns = new File(directory.getName() + "\\enseignants");
            fileEns.createNewFile();
            fos = new FileOutputStream(fileEns);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listEns);
            oos.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Sauvegarde des enseignants erronnée!");
            return;
        }
        try {
            File fileCla = new File(directory.getName() + "\\classes");
            fileCla.createNewFile();
            fos = new FileOutputStream(fileCla);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listCla);
            oos.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Sauvegarde des classes erronnée!");
            return;
        }
        try {
            File fileMat = new File(directory.getName() + "\\matieres");
            fileMat.createNewFile();
            fos = new FileOutputStream(fileMat);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listMat);
            oos.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Sauvegarde des matières erronnée!");
            return;
        }
        try {
            File fileSal = new File(directory.getName() + "\\salles");
            fileSal.createNewFile();
            fos = new FileOutputStream(fileSal);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listSal);
            oos.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Sauvegarde des salles erronnée!");
            return;
        }
        try {
            File fileHor = new File(directory.getName() + "\\horaires");
            fileHor.createNewFile();
            fos = new FileOutputStream(fileHor);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listHor);
            oos.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Sauvegarde des horaires erronnée!");
            return;
        }
        JOptionPane.showMessageDialog(null, "Sauvegarde réussie.");
    }

    // * Run the app
    public static void main(String[] args) {
        new App();
    }
}
