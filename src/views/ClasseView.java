package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.App;
import models.*;

public class ClasseView implements ActionListener, ListSelectionListener {

    public JPanel mainPanel;
    JPanel pLeft, pRight, pInput2, pInput3, pInput4;
    JLabel pageTitle, labelQty, labelMatiere, labelCampus;
    JTextField tfCapacity;
    JButton btnSubmit;
    JComboBox<String> cbCampus;
    JComboBox<Matiere> cbMatiere;
    JList<Classe> list;
    DefaultListModel<Classe> listModel;
    ClasseFactory classeFactory = ClasseFactory.getInstance();

    public ClasseView() {
        mainPanel = new JPanel();
        Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        TitledBorder title = BorderFactory.createTitledBorder(loweredetched, "Classe");
        title.setTitleJustification(TitledBorder.CENTER);
        mainPanel.setBorder(title);
        mainPanel.setLayout(new GridLayout(1, 2));
        // * Labels
        pageTitle = new JLabel("Entrer les infos classes :");
        labelQty = new JLabel("Quantité :");
        labelMatiere = new JLabel("Matière :");
        labelCampus = new JLabel("Campus :");
        // * Input Fields
        tfCapacity = new JTextField(20);
        cbCampus = new JComboBox<String>();
        if (!App.listCampus.isEmpty()) {
            App.listCampus.forEach((c) -> {
                cbCampus.addItem(c);
            });
        }
        cbCampus.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXX");
        cbCampus.addActionListener(this);
        cbMatiere = new JComboBox<Matiere>();
        if (!App.listMat.isEmpty()) {
            App.listMat.forEach((c) -> {
                cbMatiere.addItem(c);
            });
        } else {
            cbMatiere.setEnabled(false);
        }
        cbMatiere.setPrototypeDisplayValue(new Matiere("XXXXXXXXXXXXXXXXXXXX"));
        // * Buttons
        btnSubmit = new JButton("Enregistrer");
        btnSubmit.addActionListener(this);
        // * Form
        pLeft = new JPanel();
        pLeft.setLayout(new GridLayout(9, 1));
        pInput2 = new JPanel();
        pInput2.add(labelQty);
        pInput2.add(tfCapacity);
        pInput3 = new JPanel();
        pInput3.add(labelCampus);
        pInput3.add(cbCampus);
        pInput4 = new JPanel();
        pInput4.add(labelMatiere);
        pInput4.add(cbMatiere);
        pLeft.add(pageTitle);
        pLeft.add(pInput2);
        pLeft.add(pInput3);
        pLeft.add(pInput4);
        pLeft.add(btnSubmit);
        // * List
        pRight = new JPanel();
        pRight.setLayout(new BorderLayout(10, 10));
        listModel = new DefaultListModel<Classe>();
        if (!App.listCla.isEmpty()) {
            App.listCla.forEach((c) -> {
                if (c instanceof ClasseNonCouple) {
                    listModel.addElement(c);
                }
            });
        }
        list = new JList<Classe>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);
        TitledBorder listBorder = new TitledBorder(null, "Classes");
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
            if (cbMatiere.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Veuillez choisir une matiere pour enregistrer cette classe.");
                return;
            }
            if (tfCapacity.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Veuillez entrer le code de cette classe.");
                return;
            }
            try {
                Integer.parseInt(tfCapacity.getText());
            } catch (Exception exc) {
                JOptionPane.showMessageDialog(null, "La capacité doit être un entier.");
                return;
            }
            if (list.getSelectedValue() == null) {
                Classe cla = classeFactory.createClasse(Integer.parseInt(tfCapacity.getText()),
                        (Matiere) cbMatiere.getSelectedItem(), (String) cbCampus.getSelectedItem());
                App.listCla.add(cla);
            } else {
                Classe cla = list.getSelectedValue();
                cla.setCampus((String) cbCampus.getSelectedItem());
                cla.setMatiere((Matiere) cbMatiere.getSelectedItem());
                cla.setCapacite(Integer.parseInt(tfCapacity.getText()));
                cla.setCode();
            }
            App.panel = new ClasseView().mainPanel;
            App.frame.setContentPane(App.panel);
            App.frame.revalidate();
            App.frame.repaint();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == list) {
            if (list.getSelectedValue() == null) {
                btnSubmit.setText("Enregistrer");
                cbCampus.setSelectedIndex(0);
                if (cbMatiere.getItemCount() > 0) {
                    cbMatiere.setSelectedIndex(0);
                } else {
                    cbMatiere.setSelectedIndex(-1);
                }
                tfCapacity.setText("");
                return;
            }
            btnSubmit.setText("Mettre à jour");
            tfCapacity.setText(list.getSelectedValue().getCapacite() + "");
            cbMatiere.setSelectedItem(list.getSelectedValue().getMatiere());
            cbCampus.setSelectedItem(list.getSelectedValue().getCampus());
        }
    }
}
