package views;

import main.App;
import models.Matiere;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class MatiereView implements ActionListener {

    public JPanel mainPanel;
    JPanel pLeft, pRight, pInput1, pInput2;
    JLabel pageTitle, labelCode, labelCampus;
    JTextField tfCode;
    JButton btnSubmit;
    JComboBox<String> cbCampus;
    JList<Matiere> list;
    DefaultListModel<Matiere> listModel;

    public MatiereView() {
        mainPanel = new JPanel();
        Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        TitledBorder title = BorderFactory.createTitledBorder(loweredetched, "Matière");
        title.setTitleJustification(TitledBorder.CENTER);
        mainPanel.setBorder(title);
        mainPanel.setLayout(new GridLayout(1, 2));
        // * Labels
        pageTitle = new JLabel("Entrer les infos matières :");
        labelCode = new JLabel("Code :");
        labelCampus = new JLabel("Campus :");
        // * Input Fields
        tfCode = new JTextField(20);
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
        pInput2.add(labelCampus);
        pInput2.add(cbCampus);
        pLeft.add(pageTitle);
        pLeft.add(pInput1);
        pLeft.add(pInput2);
        pLeft.add(btnSubmit);
        // * List
        pRight = new JPanel();
        pRight.setLayout(new BorderLayout(10, 10));
        listModel = new DefaultListModel<Matiere>();
        if (!App.listMat.isEmpty()) {
            App.listMat.forEach((c) -> {
                listModel.addElement(c);
            });
        }
        list = new JList<Matiere>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TitledBorder listBorder = new TitledBorder(null, "Matières");
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
            Matiere mat = new Matiere(tfCode.getText(), (String) cbCampus.getSelectedItem());
            App.listMat.add(mat);
            App.panel = new MatiereView().mainPanel;
            App.frame.setContentPane(App.panel);
            App.frame.revalidate();
            App.frame.repaint();
        }
    }
}
