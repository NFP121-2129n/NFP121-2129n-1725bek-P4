package views;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import main.App;
import models.*;

public class HoraireView implements ActionListener, ListSelectionListener {

    public JPanel mainPanel;
    JPanel pLeft, pRight, pRightList, pRightTable, pInputCam, pInput1, pInput2, pInput3, pInput4, pInput5, pInput6;
    JLabel pageTitle, labelCam, labelCla, labelEns, labelSal, labelDay, labelTime;
    JButton btnSubmit, btnSave;
    JComboBox<Classe> cbCla;
    JComboBox<Enseignant> cbEns;
    JComboBox<Salle> cbSal;
    JComboBox<String> cbCam, cbDay, cbTime;
    JList<Classe> list;
    DefaultListModel<Classe> listModel;
    ArrayList<String> days = new ArrayList<String>(List.of("Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"));
    public static ArrayList<String> timeMap = new ArrayList<String>(
            List.of("13h00-14h45", "15h00-16h54", "17h00-18h45", "19h00-20h45"));
    JTable table;
    DefaultTableModel tableModel;
    String[] headers = days.toArray(new String[0]);
    public Horaire currentHoraire;

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
        btnSave = new JButton("Sauvegarder");
        btnSave.addActionListener(this);
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
        pLeft.add(btnSave);
        // * List
        pRightList = new JPanel();
        pRightList.setLayout(new BorderLayout(10, 10));
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
        pRightList.add(new JScrollPane(list), BorderLayout.CENTER);
        // * Table
        pRightTable = new JPanel();
        pRightTable.setLayout(new GridBagLayout());
        GridBagConstraints gridBC = new GridBagConstraints();
        gridBC.weightx = 1;
        gridBC.weighty = 1;
        gridBC.fill = GridBagConstraints.BOTH;
        tableModel = new DefaultTableModel(headers, 4);
        table = new JTable(tableModel);
        populateHoraireTable();
        table.setEnabled(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setRowHeight(46);
        pRightTable.add(table);
        pRightTable.add(new JScrollPane(table), gridBC);
        pRight = new JPanel();
        pRight.setLayout(new GridLayout(2, 1));
        pRight.add(pRightList);
        pRight.add(pRightTable);
        // * Build View
        mainPanel.add(pLeft);
        mainPanel.add(pRight);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == btnSubmit) {
            enregistrer();
        }
        if (o == cbCam) {
            populateByCampus();
            populateHoraireTable();
        }
        if (o == btnSave) {
            System.out.println("Save horaire instance");
        }
    }

    public void enregistrer() {

        if (cbCla.getSelectedItem() == null || cbEns.getSelectedItem() == null || cbSal.getSelectedItem() == null
                || cbTime.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Assurez vous que toutes les valeurs sont choisies");
            return;
        }
        // * Check salle capacité
        if (((Salle) cbSal.getSelectedItem()).getCapacite() < ((Classe) cbCla.getSelectedItem()).getCapacite()) {
            JOptionPane.showMessageDialog(null, "La capacité de la salle est insuffisante pour cette classe");
            return;
        }
        Classe cla = (Classe) cbCla.getSelectedItem();
        // * Set classe in horaire table
        int colID = days.indexOf((String) cbDay.getSelectedItem());
        int rowID = timeMap.indexOf((String) cbTime.getSelectedItem());
        if (!isEnseignantAvailable(rowID, colID)) {
            JOptionPane.showMessageDialog(null, "L'enseignant " + ((Enseignant) cbEns.getSelectedItem()).getNom()
                    + " est occupé durant cette date");
            return;
        }
        checkCellAvailabilty(cla, rowID, colID);
        populateHoraireTable();
        App.frame.revalidate();
        App.frame.repaint();
    }

    public void checkCellAvailabilty(Classe cla, int rowID, int colID) {
        System.out.println(colID);
        System.out.println(rowID);

        if (tableModel.getValueAt(rowID, colID) != null) {
            if (JOptionPane.showConfirmDialog(null,
                    "Voulez vous remplacer la classe " + (Classe) tableModel.getValueAt(rowID, colID)
                            + " avec la classe " + cla + " ?",
                    "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                coupleClasse(cla);
                currentHoraire.setTableCell(cla, rowID, colID);
            }
        } else {
            coupleClasse(cla);
            currentHoraire.setTableCell(cla, rowID, colID);
        }
    }

    public boolean isEnseignantAvailable(int rowID, int colID) {
        Enseignant tempEns = (Enseignant) cbEns.getSelectedItem();
        for (Horaire hor : App.listHor) {
            Classe[][] tempHor = hor.getHoraire();
            for (int i = 0; i < tempHor.length; i++) {
                for (int j = 0; j < tempHor[i].length; j++) {
                    if (tempHor[i][j] != null && tempHor[i][j].getEnseignant().getNom().equals(tempEns.getNom())
                            && i == rowID && j == colID) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void coupleClasse(Classe cla) {
        cla.setEnseignant((Enseignant) cbEns.getSelectedItem());
        cla.setSalle((Salle) cbSal.getSelectedItem());
        cla.setJour((String) cbDay.getSelectedItem());
        cla.setPeriode((String) cbTime.getSelectedItem());
        cla.setCoupled();
    }

    public void populateHoraireTable() {
        tableModel = new DefaultTableModel(headers, 4);
        if (!App.listHor.isEmpty()) {
            for (Horaire hor : App.listHor) {
                if (hor.getCampus().equals((String) cbCam.getSelectedItem())) {
                    currentHoraire = hor;
                    Classe[][] horTable = hor.getHoraire();
                    for (int i = 0; i < horTable.length; i++) {
                        for (int j = 0; j < horTable[i].length; j++) {
                            if (horTable[i][j] != null) {
                                tableModel.setValueAt(horTable[i][j], i, j);
                            }
                        }
                    }
                    break;
                }
            }
        }
        table.setModel(tableModel);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void populateByCampus() {
        cbCla.removeAllItems();
        cbSal.removeAllItems();
        // listModel.clear();
        cbCla.setEnabled(false);
        cbSal.setEnabled(false);
        if (!App.listCla.isEmpty()) {
            App.listCla.forEach((v) -> {
                if (v.getCampus().equals((String) cbCam.getSelectedItem())) {
                    cbCla.addItem(v);
                    // if (v.isCoupled())
                    // listModel.addElement(v);
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

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
