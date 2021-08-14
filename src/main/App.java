package main;

import java.awt.*;
import java.util.List;
import java.awt.event.*;
import javax.swing.*;

import models.Classe;
import models.Enseignant;
import models.Matiere;
import models.Salle;

import java.util.*;
import views.*;

public class App implements ActionListener {

    public static JFrame frame;
    public static JPanel panel;
    JMenuBar mBar;
    JMenuItem iEns, iCla, iSal, iMat, iHor;
    public static ArrayList<Enseignant> listEns = new ArrayList<Enseignant>();
    public static ArrayList<Classe> listCla = new ArrayList<Classe>();
    public static ArrayList<Matiere> listMat = new ArrayList<Matiere>();
    public static ArrayList<Salle> listSal = new ArrayList<Salle>();
    public static ArrayList<String> listCampus;

    App() {
        frame = new JFrame("NFP121-2129n-1725bek-P4");
        frame.setPreferredSize(new Dimension(600, 400));
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
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
        // if (o == iHor) {
        // if (panel.isShowing()) {
        // frame.remove(panel);
        // }
        // panel = new HoraireView().mainPanel;
        // frame.setContentPane(panel);
        // frame.pack();
        // }
    }

    public static void main(String[] args) {
        new App();
    }
}
