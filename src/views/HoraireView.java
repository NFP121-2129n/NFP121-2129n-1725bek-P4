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
import java.util.stream.Collectors;

import main.App;
import models.*;

public class HoraireView implements ActionListener, ListSelectionListener {

    public JPanel mainPanel;
    JPanel pTop, pBottom, pLeft, pRight, pInputCam, pInput1, pInput2, pInput3, pInput4, pInput5, pInput6;
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
    ClasseFactory classeFactory = ClasseFactory.getInstance();

    public HoraireView() {
        mainPanel = new JPanel();
        Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        TitledBorder title = BorderFactory.createTitledBorder(loweredetched, "Horaire");
        title.setTitleJustification(TitledBorder.CENTER);
        mainPanel.setBorder(title);
        mainPanel.setLayout(new GridLayout(2, 1));
        // * Labels
        pageTitle = new JLabel("Entrer les couples infos :");
        labelCam = new JLabel("Choisir campus :");
        labelCla = new JLabel("Classe :");
        labelEns = new JLabel("Enseignant :");
        labelSal = new JLabel("Salle :");
        labelDay = new JLabel("Jour :");
        labelTime = new JLabel("Période :");
        // * List
        pRight = new JPanel();
        pRight.setLayout(new BorderLayout(10, 10));
        listModel = new DefaultListModel<Classe>();
        populateList();
        list = new JList<Classe>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);
        TitledBorder listBorder = new TitledBorder(null, "Couples");
        listBorder.setTitleJustification(TitledBorder.CENTER);
        list.setBorder(listBorder);
        pRight.add(new JScrollPane(list), BorderLayout.CENTER);
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
        cbCla.setPrototypeDisplayValue(classeFactory.createClasse(0, new Matiere(""), "XXXXXXXXXXXXXXX"));
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
        // * Table
        pBottom = new JPanel();
        pBottom.setLayout(new GridBagLayout());
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
        table.setRowHeight(83);
        pBottom.add(table);
        pBottom.add(new JScrollPane(table), gridBC);
        pTop = new JPanel();
        pTop.setLayout(new GridLayout(1, 2));
        pTop.add(pLeft);
        pTop.add(pRight);
        // * Build View
        mainPanel.add(pTop);
        mainPanel.add(pBottom);
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
        ClasseNonCouple cla = (ClasseNonCouple) cbCla.getSelectedItem();
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
        populateList();
    }

    public void checkCellAvailabilty(ClasseNonCouple cla, int rowID, int colID) {
        if (tableModel.getValueAt(rowID, colID) != null) {
            for (Horaire hor : App.listHor) {
                if (hor.getCampus().equals((String) cbCam.getSelectedItem())) {
                    for (int k = 0; k < hor.getHoraire()[rowID][colID].size(); k++) {
                        if (hor.getHoraire()[rowID][colID].get(k).getSalle()
                                .getId() == ((Salle) cbSal.getSelectedItem()).getId()) {
                            JOptionPane.showMessageDialog(null,
                                    "La salle " + ((Salle) cbSal.getSelectedItem()).getCode()
                                            + " est deja occuper durant cette date.");
                            return;
                        }
                    }
                }
            }
        }
        ClasseCouple claCouple = coupleClasse(cla);
        currentHoraire.setTableCell(claCouple, rowID, colID);
    }

    public boolean isEnseignantAvailable(int rowID, int colID) {
        Enseignant tempEns = (Enseignant) cbEns.getSelectedItem();
        for (Horaire hor : App.listHor) {
            ArrayList<ClasseCouple>[][] tempHor = hor.getHoraire();
            for (int i = 0; i < tempHor.length; i++) {
                for (int j = 0; j < tempHor[i].length; j++) {
                    for (int k = 0; k < tempHor[i][j].size(); k++) {
                        if (tempHor[i][j].get(k).getEnseignant().getId() == tempEns.getId() && i == rowID
                                && j == colID) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public ClasseCouple coupleClasse(ClasseNonCouple cla) {
        ClasseCouple claCouple = (ClasseCouple) classeFactory.createClasse(cla.getCapacite(), cla.getMatiere(),
                cla.getCampus(), (String) cbTime.getSelectedItem(), (String) cbDay.getSelectedItem(),
                (Enseignant) cbEns.getSelectedItem(), (Salle) cbSal.getSelectedItem());
        App.listCla.add(claCouple);
        return claCouple;
    }

    public void populateHoraireTable() {
        tableModel = new DefaultTableModel(headers, 4);
        if (!App.listHor.isEmpty()) {
            for (Horaire hor : App.listHor) {
                if (hor.getCampus().equals((String) cbCam.getSelectedItem())) {
                    currentHoraire = hor;

                    ArrayList<ClasseCouple>[][] horTable = hor.getHoraire();
                    for (int i = 0; i < horTable.length; i++) {
                        for (int j = 0; j < horTable[i].length; j++) {
                            if (horTable[i][j] != null) {
                                tableModel.setValueAt(horTable[i][j].stream().map(Object::toString)
                                        .collect(Collectors.joining("----------------------\n")), i, j);
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
        listModel.clear();
        cbCla.setEnabled(false);
        cbSal.setEnabled(false);
        if (!App.listCla.isEmpty()) {
            App.listCla.forEach((v) -> {
                if (v.getCampus().equals((String) cbCam.getSelectedItem())) {
                    if (v instanceof ClasseNonCouple) {
                        cbCla.addItem(v);
                    }
                    if (v instanceof ClasseCouple) {
                        listModel.addElement(v);
                    }
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

    public void populateList() {
        listModel.clear();
        if (!App.listCla.isEmpty()) {
            App.listCla.forEach((v) -> {
                if (v instanceof ClasseCouple) {
                    listModel.addElement(v);
                }
            });
        }
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == list) {
            if (list.getSelectedValue() == null) {
                btnSubmit.setText("Enregistrer");
                cbCam.setSelectedIndex(0);
                cbDay.setSelectedIndex(0);
                cbTime.setSelectedIndex(0);
                if (cbCla.getItemCount() > 0) {
                    cbCla.setSelectedIndex(0);
                } else {
                    cbCla.setSelectedIndex(-1);
                }
                if (cbEns.getItemCount() > 0) {
                    cbEns.setSelectedIndex(0);
                } else {
                    cbEns.setSelectedIndex(-1);
                }
                if (cbSal.getItemCount() > 0) {
                    cbSal.setSelectedIndex(0);
                } else {
                    cbSal.setSelectedIndex(-1);
                }
                return;
            }
            btnSubmit.setText("Mettre à jour");
            cbCla.setSelectedItem(list.getSelectedValue());
            cbCam.setSelectedItem(list.getSelectedValue().getCampus());
        }
    }
}
