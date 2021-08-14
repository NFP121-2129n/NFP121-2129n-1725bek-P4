package views;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

import main.App;
import models.Enseignant;
import models.Matiere;
import models.Classe;
import models.Salle;

public class HoraireView implements ActionListener {

    public JPanel mainPanel;
    JPanel pLeft, pRight, pInputCam, pInput1, pInput2, pInput3, pInput4, pInput5, pInput6;
    JLabel pageTitle, labelCam, labelCla, labelEns, labelSal, labelDay, labelTime;
    JButton btnSubmit, btnGenerate;
    JComboBox<Classe> cbCla;
    JComboBox<Enseignant> cbEns;
    JComboBox<Salle> cbSal;
    JComboBox<String> cbCam, cbDay, cbTime;
    JList<Classe> list;
    DefaultListModel<Classe> listModel;
    ArrayList<String> days = new ArrayList<String>(List.of("Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"));
    public static ArrayList<String> timeMap = new ArrayList<String>(
            List.of("13h00-14h45", "15h00-16h54", "17h00-18h45", "19h00-20h45"));

    public HoraireView() {
        mainPanel = new JPanel();
        Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        TitledBorder title = BorderFactory.createTitledBorder(loweredetched, "Horaire");
        title.setTitleJustification(TitledBorder.CENTER);
        mainPanel.setBorder(title);
        mainPanel.setLayout(new GridLayout(1, 2));
        // * Labels
        pageTitle = new JLabel("Entrer les couples infos :");
        labelCam = new JLabel("Choisir campus :");
        labelCla = new JLabel("Classe :");
        labelEns = new JLabel("Enseignant :");
        labelSal = new JLabel("Salle :");
        labelDay = new JLabel("Jour :");
        labelTime = new JLabel("Période :");
        // * Inputs
        cbCam = new JComboBox<String>();
        App.listCampus.forEach((v) -> {
            cbCam.addItem(v);
        });
        cbCam.addActionListener(this);
        cbCam.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXXX");
        cbCla = new JComboBox<Classe>();
        cbSal = new JComboBox<Salle>();
        populateByCampus();
        cbCla.setPrototypeDisplayValue(new Classe(0, new Matiere(""), "XXXXXXXXXXXXXXX"));
        cbEns = new JComboBox<Enseignant>();
        if (!App.listEns.isEmpty()) {
            App.listEns.forEach((v) -> {
                cbEns.addItem(v);
            });
        } else {
            cbEns.setEnabled(false);
        }
        cbEns.setPrototypeDisplayValue(new Enseignant("XXXXX"));
        cbSal.setPrototypeDisplayValue(new Salle("XXX", "XXX", 0));
        cbDay = new JComboBox<String>();
        if (!days.isEmpty()) {
            days.forEach((v) -> {
                cbDay.addItem(v);
            });
        }
        cbDay.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXX");
        cbTime = new JComboBox<String>();
        if (!timeMap.isEmpty()) {
            timeMap.forEach((v) -> {
                cbTime.addItem(v);
            });
        }
        cbTime.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXX");
        // * Buttons
        btnSubmit = new JButton("Enregistrer");
        btnSubmit.addActionListener(this);
        btnGenerate = new JButton("Générer");
        btnGenerate.addActionListener(this);
        // * Form
        pLeft = new JPanel();
        pLeft.setLayout(new GridLayout(9, 1));
        pInputCam = new JPanel();
        pInputCam.add(labelCam);
        pInputCam.add(cbCam);
        pInput1 = new JPanel();
        pInput1.add(labelCla);
        pInput1.add(cbCla);
        pInput2 = new JPanel();
        pInput2.add(labelEns);
        pInput2.add(cbEns);
        pInput3 = new JPanel();
        pInput3.add(labelSal);
        pInput3.add(cbSal);
        pInput4 = new JPanel();
        pInput4.add(labelDay);
        pInput4.add(cbDay);
        pInput5 = new JPanel();
        pInput5.add(labelTime);
        pInput5.add(cbTime);
        pLeft.add(pInputCam);
        pLeft.add(pageTitle);
        pLeft.add(pInput1);
        pLeft.add(pInput2);
        pLeft.add(pInput3);
        pLeft.add(pInput4);
        pLeft.add(pInput5);
        pLeft.add(btnSubmit);
        pLeft.add(btnGenerate);
        // * List
        pRight = new JPanel();
        pRight.setLayout(new BorderLayout(10, 10));
        listModel = new DefaultListModel<Classe>();
        if (!App.listCla.isEmpty()) {
            App.listCla.forEach((v) -> {
                if (v.isCoupled())
                    listModel.addElement(v);
            });
        }
        list = new JList<Classe>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TitledBorder listBorder = new TitledBorder(null, "Couples");
        listBorder.setTitleJustification(TitledBorder.CENTER);
        list.setBorder(listBorder);
        pRight.add(new JScrollPane(list), BorderLayout.CENTER);
        // * Build View
        mainPanel.add(pLeft);
        mainPanel.add(pRight);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == btnSubmit) {
            if (cbCla.getSelectedItem() == null || cbEns.getSelectedItem() == null || cbSal.getSelectedItem() == null
                    || cbTime.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Assurez vous que toutes les valeurs sont choisies");
                return;
            }
            Classe cla = (Classe) cbCla.getSelectedItem();
            cla.setEnseignant((Enseignant) cbEns.getSelectedItem());
            cla.setSalle((Salle) cbSal.getSelectedItem());
            cla.setJour((String) cbDay.getSelectedItem());
            cla.setPeriode((String) cbTime.getSelectedItem());
            cla.setCoupled();
            App.panel = new HoraireView().mainPanel;
            App.frame.setContentPane(App.panel);
            App.frame.revalidate();
            App.frame.repaint();
        } else if (o == cbCam) {
            populateByCampus();
        }
    }

    public void populateByCampus() {
        cbCla.removeAllItems();
        cbSal.removeAllItems();
        cbCla.setEnabled(false);
        cbSal.setEnabled(false);
        if (!App.listCla.isEmpty()) {
            App.listCla.forEach((v) -> {
                if (v.getCampus().equals((String) cbCam.getSelectedItem())) {
                    cbCla.addItem(v);
                }
            });
            if (cbCla.getItemCount() > 0) {
                cbCla.setEnabled(true);
            }
        }
        if (!App.listSal.isEmpty()) {
            App.listSal.forEach((v) -> {
                if (v.getCampus().equals((String) cbCam.getSelectedItem())) {
                    cbSal.addItem(v);
                }
            });
            if (cbSal.getItemCount() > 0) {
                cbSal.setEnabled(true);
            }
        }
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
