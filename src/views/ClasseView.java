package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

import main.App;
import models.Classe;
import models.Matiere;

public class ClasseView implements ActionListener {

    public JPanel mainPanel;
    JPanel pLeft, pRight, pInput1, pInput2, pInput3, pInput4;
    JLabel pageTitle, labelCode, labelQty, labelMatiere, labelCampus;
    JTextField tfCode, tfQty;
    JButton btnSubmit;
    JComboBox<String> cbCampus;
    JComboBox<Matiere> cbMatiere;
    JList<Classe> list;
    DefaultListModel<Classe> listModel;

    public ClasseView() {
        mainPanel = new JPanel();
        Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        TitledBorder title = BorderFactory.createTitledBorder(loweredetched, "Classe");
        title.setTitleJustification(TitledBorder.CENTER);
        mainPanel.setBorder(title);
        mainPanel.setLayout(new GridLayout(1, 2));
        // * Labels
        pageTitle = new JLabel("Entrer les infos classes :");
        labelCode = new JLabel("Code :");
        labelQty = new JLabel("Quantité :");
        labelMatiere = new JLabel("Matière :");
        labelCampus = new JLabel("Campus :");
        // * Input Fields
        tfCode = new JTextField(20);
        tfQty = new JTextField(20);
        cbCampus = new JComboBox<String>();
        if (!App.listCampus.isEmpty()) {
            App.listCampus.forEach((c) -> {
                cbCampus.addItem(c);
            });
        }
        cbCampus.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXX");
        cbCampus.addActionListener(this);
        cbMatiere = new JComboBox<Matiere>();
        populateListMat();
        cbMatiere.setPrototypeDisplayValue(new Matiere("XXXXXXXXXX", "XXXXXXXXXX"));
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
        pInput2.add(labelQty);
        pInput2.add(tfQty);
        pInput3 = new JPanel();
        pInput3.add(labelCampus);
        pInput3.add(cbCampus);
        pInput4 = new JPanel();
        pInput4.add(labelMatiere);
        pInput4.add(cbMatiere);
        pLeft.add(pageTitle);
        pLeft.add(pInput1);
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
                listModel.addElement(c);
            });
        }
        list = new JList<Classe>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
            Classe cla = new Classe(tfCode.getText(), Integer.parseInt(tfQty.getText()),
                    (Matiere) cbMatiere.getSelectedItem(), (String) cbCampus.getSelectedItem());
            App.listCla.add(cla);
            App.panel = new ClasseView().mainPanel;
            App.frame.setContentPane(App.panel);
            App.frame.revalidate();
            App.frame.repaint();
        }
        if (e.getSource() == cbCampus) {
            populateListMat();
        }
    }

    public void populateListMat() {
        cbMatiere.setEnabled(false);
        if (!App.listMat.isEmpty()) {
            cbMatiere.removeAllItems();
            ArrayList<Matiere> tempListMat = Matiere.getByCampus((String) cbCampus.getSelectedItem());
            tempListMat.forEach((m) -> {
                cbMatiere.addItem(m);
            });
            if (!tempListMat.isEmpty()) {
                cbMatiere.setEnabled(true);
            }
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }
}
