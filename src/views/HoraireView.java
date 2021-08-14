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
    JPanel pLeft, pRight, pInput1, pInput2, pInput3, pInput4, pInput5, pInput6;
    JLabel pageTitle, labelCla, labelEns, labelSal, labelDay, labelStart, labelEnd;
    JButton btnSubmit, btnGenerate;
    JComboBox<Classe> cbCla;
    JComboBox<Enseignant> cbEns;
    JComboBox<Salle> cbSal;
    JComboBox<String> cbDay, cbStart, cbEnd;
    JList<Classe> list;
    DefaultListModel<Classe> listModel;
    ArrayList<String> days = new ArrayList<String>(List.of("Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"));
    Map<String, Integer> timeMap = new LinkedHashMap<String, Integer>() {
        {
            put("15h00", 15);
            put("16h00", 16);
            put("17h00", 17);
            put("18h00", 18);
            put("19h00", 19);
            put("20h00", 20);
            put("21h00", 21);
        }
    };

    public HoraireView() {
        mainPanel = new JPanel();
        Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        TitledBorder title = BorderFactory.createTitledBorder(loweredetched, "Horaire");
        title.setTitleJustification(TitledBorder.CENTER);
        mainPanel.setBorder(title);
        mainPanel.setLayout(new GridLayout(1, 2));
        // * Labels
        pageTitle = new JLabel("Entrer les couples infos :");
        labelCla = new JLabel("Classe :");
        labelEns = new JLabel("Enseignant :");
        labelSal = new JLabel("Salle :");
        labelDay = new JLabel("Jour :");
        labelStart = new JLabel("Temps début :");
        labelEnd = new JLabel("Temps fin :");
        // * Inputs
        cbCla = new JComboBox<Classe>();
        if (!App.listCla.isEmpty()) {
            App.listCla.forEach((v) -> {
                cbCla.addItem(v);
            });
        } else {
            cbCla.setEnabled(false);
        }
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
        cbSal = new JComboBox<Salle>();
        if (!App.listSal.isEmpty()) {
            App.listSal.forEach((v) -> {
                cbSal.addItem(v);
            });
        } else {
            cbSal.setEnabled(false);
        }
        cbSal.setPrototypeDisplayValue(new Salle("XXX", "XXX", 0));
        cbDay = new JComboBox<String>();
        if (!days.isEmpty()) {
            days.forEach((v) -> {
                cbDay.addItem(v);
            });
        }
        cbDay.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXX");
        cbStart = new JComboBox<String>();
        if (!timeMap.isEmpty()) {
            timeMap.keySet().forEach((v) -> {
                cbStart.addItem(v);
            });
        }
        cbStart.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXX");
        cbStart.addActionListener(this);
        cbEnd = new JComboBox<String>();
        populateEndTime();
        cbEnd.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXX");
        // * Buttons
        btnSubmit = new JButton("Enregistrer");
        btnSubmit.addActionListener(this);
        btnGenerate = new JButton("Générer");
        btnGenerate.addActionListener(this);
        // * Form
        pLeft = new JPanel();
        pLeft.setLayout(new GridLayout(9, 1));
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
        pInput5.add(labelStart);
        pInput5.add(cbStart);
        pInput6 = new JPanel();
        pInput6.add(labelEnd);
        pInput6.add(cbEnd);
        pLeft.add(pageTitle);
        pLeft.add(pInput1);
        pLeft.add(pInput2);
        pLeft.add(pInput3);
        pLeft.add(pInput4);
        pLeft.add(pInput5);
        pLeft.add(pInput6);
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
        if (o == cbStart) {
            populateEndTime();
        }
        if (o == btnSubmit) {
            if (cbCla.getSelectedItem() == null || cbEns.getSelectedItem() == null || cbSal.getSelectedItem() == null
                    || cbEnd.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Assurez vous que toutes les valeurs sont choisies");
                return;
            }
            Classe cla = (Classe) cbCla.getSelectedItem();
            cla.setEnseignant((Enseignant) cbEns.getSelectedItem());
            cla.setSalle((Salle) cbSal.getSelectedItem());
            cla.setJour((String) cbDay.getSelectedItem());
            cla.setDebut(timeMap.get(((String) cbStart.getSelectedItem())));
            cla.setFin(timeMap.get(((String) cbEnd.getSelectedItem())));
            cla.setCoupled();
            App.panel = new HoraireView().mainPanel;
            App.frame.setContentPane(App.panel);
            App.frame.revalidate();
            App.frame.repaint();
        }

    }

    public void populateEndTime() {
        String timeStr = (String) cbStart.getSelectedItem();
        Map<String, Integer> tempTimeMap = new LinkedHashMap<String, Integer>();
        for (String timeKey : timeMap.keySet()) {
            if (timeMap.get(timeKey) > timeMap.get(timeStr)) {
                tempTimeMap.put(timeKey, timeMap.get(timeKey));
            }
        }
        cbEnd.setEnabled(false);
        cbEnd.removeAllItems();
        if (!tempTimeMap.isEmpty()) {
            tempTimeMap.keySet().forEach((v) -> {
                cbEnd.addItem(v);
            });
            cbEnd.setEnabled(true);
        }
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
