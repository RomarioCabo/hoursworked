package com.br.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class JFormMain extends JFrame {

    private JButton jButtonCalculate;
    private JButton jButtonSelectExcel;
    private JButton jButtonSelectPathSave;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabelTitle;
    private JPanel jPanel1;
    private JTextField jTextFieldPathToExcel;
    private JTextField jTextFieldPathToSeveNewExcel;
    
    public JFormMain() {
        initComponents();
    }
    
    private void initComponents() {

        jPanel1 = new JPanel();
        jLabelTitle = new JLabel();
        jLabel1 = new JLabel();
        jTextFieldPathToExcel = new JTextField();
        jButtonSelectExcel = new JButton();
        jLabel2 = new JLabel();
        jTextFieldPathToSeveNewExcel = new JTextField();
        jButtonSelectPathSave = new JButton();
        jButtonCalculate = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calculadora de Horas Noturnas");
        setResizable(false);

        jPanel1.setBackground(new Color(255, 255, 255));

        jLabelTitle.setFont(new Font("Segoe UI", Font.BOLD, 24)); 
        jLabelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelTitle.setText("Calcular Horas Noturnas");
        jLabelTitle.setHorizontalTextPosition(SwingConstants.CENTER);

        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 12)); 
        jLabel1.setText("Caminho para a planilha com as horas.:");

        jTextFieldPathToExcel.setEditable(false);

        jButtonSelectExcel.setFont(new Font("Segoe UI", Font.BOLD, 12)); 
        jButtonSelectExcel.setText("...");
        jButtonSelectExcel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButtonSelectExcel.addActionListener(this::jButtonSelectExcelActionPerformed);

        jLabel2.setFont(new Font("Segoe UI", Font.BOLD, 12)); 
        jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel2.setText("Caminho para salvar os calculos.:");
        jLabel2.setHorizontalTextPosition(SwingConstants.RIGHT);

        jTextFieldPathToSeveNewExcel.setEditable(false);

        jButtonSelectPathSave.setFont(new Font("Segoe UI", Font.BOLD, 12)); 
        jButtonSelectPathSave.setText("...");
        jButtonSelectPathSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButtonSelectPathSave.addActionListener(this::jButtonSelectPathSaveActionPerformed);

        jButtonCalculate.setFont(new Font("Segoe UI", Font.BOLD, 12)); 
        jButtonCalculate.setText("Calcular");
        jButtonCalculate.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButtonCalculate.addActionListener(this::jButtonCalculateActionPerformed);

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldPathToExcel, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSelectExcel))
                            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldPathToSeveNewExcel, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSelectPathSave))
                            .addComponent(jButtonCalculate, GroupLayout.Alignment.TRAILING))
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitle)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldPathToExcel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSelectExcel))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldPathToSeveNewExcel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSelectPathSave))
                .addGap(18, 18, 18)
                .addComponent(jButtonCalculate)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(SwingConstants.VERTICAL, jLabel1, jTextFieldPathToExcel);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void jButtonSelectExcelActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jButtonSelectPathSaveActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jButtonCalculateActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }
}
