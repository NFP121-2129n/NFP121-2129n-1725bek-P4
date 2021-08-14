package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.App;
import models.Matiere;

public class MatiereView implements ActionListener, ListSelectionListener {

    public JPanel mainPanel;
    JPanel pLeft, pRight, pInput1;
    JLabel pageTitle, labelCode, labelCampus;
    JTextField tfCode;
    JButton btnSubmit;
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
        // * Input Fields
        tfCode = new JTextField(20);
        // * Buttons
        btnSubmit = new JButton("Enregistrer");
        btnSubmit.addActionListener(this);
        // * Form
        pLeft = new JPanel();
        pLeft.setLayout(new GridLayout(9, 1));
        pInput1 = new JPanel();
        pInput1.add(labelCode);
        pInput1.add(tfCode);
        pLeft.add(pageTitle);
        pLeft.add(pInput1);
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
        list.addListSelectionListener(this);
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
            if (tfCode.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Veuillez entrer le code de cette matière.");
                return;
            }
            if (list.getSelectedValue() == null) {
                Matiere mat = new Matiere(tfCode.getText());
                App.listMat.add(mat);
            } else {
                for (Matiere mat : App.listMat) {
                    if (mat.getId() == list.getSelectedValue().getId()) {
                        mat.setCode(tfCode.getText());
                        break;
                    }
                }
            }
            App.panel = new MatiereView().mainPanel;
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
                tfCode.setText("");
                return;
            }
            btnSubmit.setText("Mettre à jour");
            tfCode.setText(list.getSelectedValue().getCode());
        }

    }
}
