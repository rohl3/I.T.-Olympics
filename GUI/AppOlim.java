package com.GUI;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class AppOlim extends JFrame{
    ArrayList<Partecipante> list = new ArrayList<>();
    Partecipante p;
    static int block = 0;
    private JPanel Panel1;
    private JTextField codeText;
    private JLabel codeLabel;
    private JLabel nominativeLabel;
    private JTextField nominativeText;
    private JLabel classLabel;
    private JTextField classText;
    private JLabel pointLabel;
    private JTextField pointText;
    private JButton okBtn;
    private JButton viewBtn;
    private JTextArea outText;
    private JButton finalBtn;
    private JButton clearBtn;

    public AppOlim() {
        setContentPane(Panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        okBtn();
        viewBtn();
        viewAllBtn();
        clearBtn();

        //OPTIONAL STUFF
        //CODE TEXT METHOD TO CHECK THE CODE'S REQUIREMENTS
        codeText.addActionListener(e -> {
            if(codeText.getText().length() == 16){
                codeText.setEnabled(false);
            }
        });
        //NOMINATIVE TEXT METHOD TO CHECK THE NOMINATIVE'S REQUIREMENTS
        nominativeText.addActionListener(e -> {
            if(nominativeText.getText() != null && nominativeText.getText().length()>0){
                block++;
                nominativeText.setEnabled(false);
            }
        });

        //CLASS TEXT METHOD TO CHECK THE CLASS'S REQUIREMENTS
        classText.addActionListener(e -> {
            if   (  classText.getText().equalsIgnoreCase("prima")   ||
                    classText.getText().equalsIgnoreCase("seconda") ||
                    classText.getText().equalsIgnoreCase("terza")   ||
                    classText.getText().equalsIgnoreCase("quarta")){
                    block++;
                    classText.setEnabled(false);
                }
        });

        //POINT TEXT METHOD TO CHECK THE POINT'S REQUIREMENTS
        pointText.addActionListener(e -> {
            int point = Integer.parseInt(pointText.getText());
            if(point > -30 && point <30){
                block++;
                pointText.setEnabled(false);
            }
        });
    }

    //VIEW BUTTON TO PREVIEW THE LAST OBJECT ADDED
    private void viewBtn() {
        viewBtn.addActionListener(e -> {
            if (e.getSource().equals(viewBtn)) {
                outText.setText(p.toString() + "\n");
                /*JOptionPane.showMessageDialog(rootPane, list);*/
            }
        });
    }

    //OK BUTTON TO ADD OBJECTS (PARTICIPANT'S DETAILS) IN MY LIST AND TEXT FILE
    private void okBtn(){
        okBtn.addActionListener(e -> {
            if(e.getSource().equals(okBtn)){

                okBtn.setEnabled(false);
                codeText.setEnabled(false);
                nominativeText.setEnabled(false);
                classText.setEnabled(false);
                pointText.setEnabled(false);
                try {
                    double n = Double.parseDouble(pointText.getText());
                    p = new Partecipante(codeText.getText(), nominativeText.getText(), classText.getText(), n);
                } catch (Exception exception) {
                    outText.setText(exception.getMessage());
                }
                list.add(p);
                codeText.setEnabled(true);
                nominativeText.setEnabled(true);
                classText.setEnabled(true);
                pointText.setEnabled(true);
                okBtn.setEnabled(true);

                try {
                    FileWriter writer = new FileWriter("Data.txt",true);
                    writer.write(codeText.getText());
                    writer.write(System.getProperty("line.separator"));
                    writer.write(nominativeText.getText());
                    writer.write(System.getProperty("line.separator"));
                    writer.write(classText.getText());
                    writer.write(System.getProperty("line.separator"));
                    writer.write(pointText.getText());
                    writer.write(System.getProperty("line.separator"));
                    writer.write(System.getProperty("line.separator"));
                    writer.close();
                } catch (IOException ioException) {
                    outText.setText(ioException.getMessage());
                }
            }
        });
    }

    //FINAL BUTTON TO PREVIEW EVERY PARTICIPANT IN THE LIST
    private void viewAllBtn(){
        finalBtn.addActionListener(e -> {
            outText.setText("");
            list.sort(Partecipante.sortPoint);
            for (Partecipante p : list) {
                outText.append(p.toString() + "\n");
            }
        });
    }

    //CLEAR BUTTON TO CLEAR THE LIST/ARRAYLIST
    private void clearBtn() {
        clearBtn.addActionListener(e -> {
            list.clear();
            outText.setText("");
        });
    }
}
