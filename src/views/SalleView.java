package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.App;
import models.Salle;

public class SalleView implements ActionListener, ListSelectionListener {

    public JPanel mainPanel;
    JPanel pLeft, pRight, pInput1, pInput2, pInput3;
    JLabel pageTitle, labelCode, labelCapacity, labelCampus;
    JTextField tfCode, tfCapacity;
    JButton btnSubmit;
    JComboBox<String> cbCampus;
    JList<Salle> list;
    DefaultListModel<Salle> listModel;

    public SalleView() {
        mainPanel = new JPanel();
        Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        TitledBorder title = BorderFactory.createTitledBorder(loweredetched, "Salle");
        title.setTitleJustification(TitledBorder.CENTER);
        mainPanel.setBorder(title);
        mainPanel.setLayout(new GridLayout(1, 2));
        // * Labels
        pageTitle = new JLabel("Entrer les infos salles :");
        labelCode = new JLabel("Code :");
        labelCapacity = new JLabel("Capacité :");
        labelCampus = new JLabel("Campus :");
        // * Input Fields
        tfCode = new JTextField(20);
        tfCapacity = new JTextField(20);
        cbCampus = new JComboBox<String>();
        if (!App.listCampus.isEmpty()) {
            App.listCampus.forEach((c) -> {
                cbCampus.addItem(c);
            });
        }
        cbCampus.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXX");
        // * Buttons
        btnSubmit = new JButton("Enregistrer");
        btnSubmit.addActionListener(this);
        // * Form
        pLeft = new JPanel();
        pLeft.setLayout(new GridLayout(7, 1));
        pInput1 = new JPanel();
        pInput1.add(labelCode);
        pInput1.add(tfCode);
        pInput2 = new JPanel();
        pInput2.add(labelCapacity);
        pInput2.add(tfCapacity);
        pInput3 = new JPanel();
        pInput3.add(labelCampus);
        pInput3.add(cbCampus);
        pLeft.add(pageTitle);
        pLeft.add(pInput1);
        pLeft.add(pInput2);
        pLeft.add(pInput3);
        pLeft.add(btnSubmit);
        // * List
        pRight = new JPanel();
        pRight.setLayout(new BorderLayout(10, 10));
        listModel = new DefaultListModel<Salle>();
        if (!App.listSal.isEmpty()) {
            App.listSal.forEach((c) -> {
                listModel.addElement(c);
            });
        }
        list = new JList<Salle>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);
        TitledBorder listBorder = new TitledBorder(null, "Salles");
        listBorder.setTitleJustification(TitledBorder.CENTER);
        list.setBorder(listBorder);
        pRight.add(new JScrollPane(list), BorderLayout.CENTER);
        // * Build View
        mainPanel.add(pLeft);
        mainPanel.add(pRight);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSubmit) {
            // * Checking if the inputs are not empty
            if (tfCode.getText().isEmpty() || tfCapacity.getText().isEmpty()) {
                if (!tfCapacity.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer le code de cette classe.");
                } else if (!tfCode.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer la capacité de cette classe.");
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer le code et la capacité de cette classe.");
                }
                return;
            }
            // * Validating capacity is Integer
            try {
                Integer.parseInt(tfCapacity.getText());
            } catch (Exception exc) {
                JOptionPane.showMessageDialog(null, "La capacité doit être un entier.");
                return;
            }
            if (list.getSelectedValue() == null) {
                // * Creating the new Salle instance and saving it in its respective array
                Salle sal = new Salle(tfCode.getText(), (String) cbCampus.getSelectedItem(),
                        Integer.parseInt(tfCapacity.getText()));
                App.listSal.add(sal);
            } else {
                // * Updating the instance's info if user is in edit mode
                Salle sal = list.getSelectedValue();
                sal.setCampus((String) cbCampus.getSelectedItem());
                sal.setCode(tfCode.getText());
                sal.setCapacite(Integer.parseInt(tfCapacity.getText()));
            }
            App.panel = new SalleView().mainPanel;
            App.frame.setContentPane(App.panel);
            App.frame.revalidate();
            App.frame.repaint();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == list) {
            // * Populating inputs fields with selection's info and entering edit mode
            if (list.getSelectedValue() == null) {
                btnSubmit.setText("Enregistrer");
                tfCapacity.setText("");
                tfCode.setText("");
                cbCampus.setSelectedIndex(0);
                return;
            }
            btnSubmit.setText("Mettre à jour");
            tfCapacity.setText(list.getSelectedValue().getCapacite() + "");
            tfCode.setText(list.getSelectedValue().getCode());
            cbCampus.setSelectedItem(list.getSelectedValue().getCampus());
        }
    }
}
