package views;

import main.App;
import models.Salle;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class SalleView implements ActionListener {

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
        pLeft.setLayout(new GridLayout(6, 1));
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
            try {
                Integer.parseInt(tfCapacity.getText());
            } catch (Exception exc) {
                JOptionPane.showMessageDialog(null, "La capacité doit être un entier.");
                return;
            }
            Salle sal = new Salle(tfCode.getText(), (String) cbCampus.getSelectedItem(),
                    Integer.parseInt(tfCapacity.getText()));
            App.listSal.add(sal);
            App.panel = new SalleView().mainPanel;
            App.frame.setContentPane(App.panel);
            App.frame.revalidate();
            App.frame.repaint();
        }
    }
}
