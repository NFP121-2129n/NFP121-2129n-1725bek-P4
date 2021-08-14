package views;

import models.Enseignant;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import main.App;

public class EnseignantView implements ActionListener {

    public JPanel mainPanel;
    JPanel pLeft, pRight, pInput;
    JLabel pageTitle, labelNom;
    JTextField tfNom;
    JButton btnSubmit;
    JList<Enseignant> list;
    DefaultListModel<Enseignant> listModel;

    public EnseignantView() {
        mainPanel = new JPanel();
        Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        TitledBorder title = BorderFactory.createTitledBorder(loweredetched, "Enseignant");
        title.setTitleJustification(TitledBorder.CENTER);
        mainPanel.setBorder(title);
        mainPanel.setLayout(new GridLayout(1, 2));
        // * Labels
        pageTitle = new JLabel("Entrer les infos enseignants :");
        labelNom = new JLabel("Nom :");
        // * Textfields
        tfNom = new JTextField(20);
        // * Buttons
        btnSubmit = new JButton("Enregistrer");
        btnSubmit.addActionListener(this);
        // * Form
        pLeft = new JPanel();
        pLeft.setLayout(new GridLayout(6, 1));
        pInput = new JPanel();
        pInput.add(labelNom);
        pInput.add(tfNom);
        pLeft.add(pageTitle);
        pLeft.add(pInput);
        pLeft.add(btnSubmit);
        // * List
        pRight = new JPanel();
        pRight.setLayout(new BorderLayout(10, 10));
        listModel = new DefaultListModel<Enseignant>();
        if (!App.listEns.isEmpty()) {
            App.listEns.forEach((c) -> {
                listModel.addElement(c);
            });
        }
        list = new JList<Enseignant>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TitledBorder listBorder = new TitledBorder(null, "Enseignants");
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
            Enseignant ens = new Enseignant(tfNom.getText());
            App.listEns.add(ens);
            App.panel = new EnseignantView().mainPanel;
            App.frame.setContentPane(App.panel);
            App.frame.revalidate();
            App.frame.repaint();
        }
    }

}
