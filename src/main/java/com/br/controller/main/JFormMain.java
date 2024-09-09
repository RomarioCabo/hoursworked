package com.br.controller.main;

import com.br.domain.csv.CsvService;
import com.br.domain.csv.impl.CsvServiceImpl;
import com.br.domain.employee.Employee;
import com.br.domain.employee.service.EmployeeService;
import com.br.domain.employee.service.impl.EmployeeServiceImpl;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;
import java.util.Locale;

public class JFormMain extends JFrame {

    private JButton jButtonCalculate;
    private JLabel jLabelWait;
    private JTextField jTextFieldPathToExcel;
    private JTextField jTextFieldPathToSaveNewExcel;

    private final CsvService csvService = new CsvServiceImpl();
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    public JFormMain() {
        initComponents();
    }

    private void initComponents() {
        // Define o Locale para português do Brasil
        Locale.setDefault(new Locale("pt", "BR"));
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        UIManager.put("FileChooser.openDialogTitleText", "Abrir");
        UIManager.put("FileChooser.saveDialogTitleText", "Salvar");
        UIManager.put("FileChooser.cancelButtonText", "Cancelar");
        UIManager.put("FileChooser.openButtonText", "Abrir");
        UIManager.put("FileChooser.saveButtonText", "Salvar");
        UIManager.put("FileChooser.fileNameLabelText", "Nome do Arquivo");
        UIManager.put("FileChooser.filesOfTypeLabelText", "Tipo de Arquivo");
        UIManager.put("FileChooser.lookInLabelText", "Procurar em");
        UIManager.put("FileChooser.folderNameLabelText", "Nome da Pasta");

        JPanel jPanel1 = new JPanel();
        JLabel jLabelTitle = new JLabel();
        JLabel jLabel1 = new JLabel();
        jTextFieldPathToExcel = new JTextField();
        JButton jButtonSelectExcel = new JButton();
        JLabel jLabel2 = new JLabel();
        jTextFieldPathToSaveNewExcel = new JTextField();
        JButton jButtonSelectPathSave = new JButton();
        jButtonCalculate = new JButton();
        jLabelWait = new JLabel();

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

        jTextFieldPathToSaveNewExcel.setEditable(false);

        jButtonSelectPathSave.setFont(new Font("Segoe UI", Font.BOLD, 12));
        jButtonSelectPathSave.setText("...");
        jButtonSelectPathSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButtonSelectPathSave.addActionListener(this::jButtonSelectPathSaveActionPerformed);

        jButtonCalculate.setFont(new Font("Segoe UI", Font.BOLD, 12));
        jButtonCalculate.setText("Calcular");
        jButtonCalculate.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButtonCalculate.addActionListener(this::jButtonCalculateActionPerformed);

        jLabelWait.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jLabelWait.setForeground(Color.RED);
        jLabelWait.setText("Aguarde...");
        jLabelWait.setVisible(false);

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
                                                                .addComponent(jTextFieldPathToSaveNewExcel, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jButtonSelectPathSave))
                                                        .addComponent(jButtonCalculate, GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabelWait, GroupLayout.Alignment.TRAILING))
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
                                        .addComponent(jTextFieldPathToSaveNewExcel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonSelectPathSave))
                                .addGap(18, 18, 18)
                                .addComponent(jButtonCalculate)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelWait)
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
        setLocationRelativeTo(null);
    }

    private void jButtonSelectExcelActionPerformed(ActionEvent evt) {
        String selectedFilePath = selectFileOrDirectory(false);
        if (selectedFilePath != null) {
            jTextFieldPathToExcel.setText(selectedFilePath);
        }
    }

    private void jButtonSelectPathSaveActionPerformed(ActionEvent evt) {
        String selectedDirectory = selectFileOrDirectory(true);
        if (selectedDirectory != null) {
            jTextFieldPathToSaveNewExcel.setText(selectedDirectory);
        }
    }

    private void jButtonCalculateActionPerformed(ActionEvent evt) {
        if (checkStringAndShowWarning(jTextFieldPathToExcel.getText(), "Você deve selecionar um CSV antes de calcular.")) {
            return;
        }

        if (checkStringAndShowWarning(jTextFieldPathToSaveNewExcel.getText(), "Você deve selecionar uma pasta para salvar o novo CSV.")) {
            return;
        }

        jLabelWait.setVisible(true);
        jButtonCalculate.setEnabled(false);

        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                List<Employee> mappedEmployees = employeeService
                        .mapperHoursWorked(csvService.readEmployeesFromCsv(jTextFieldPathToExcel.getText()));

                csvService.writeEmployeesToCsv(mappedEmployees, jTextFieldPathToSaveNewExcel.getText() + "\\Ponto funcionários calculado.csv");
                return null;
            }

            @Override
            protected void done() {
                jLabelWait.setVisible(false);
                jButtonCalculate.setEnabled(true);

                JOptionPane.showMessageDialog(null, "CSV gerado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
        };

        worker.execute();
    }

    /**
     * Seleciona e retorna o caminho absoluto de um arquivo .csv ou de um diretório,
     * dependendo do parâmetro 'directoryOnly'.
     *
     * @param directoryOnly se for true, o JFileChooser irá permitir apenas a seleção de diretórios.
     *                      Se for false, permitirá a seleção de arquivos .csv.
     * @return o caminho absoluto do arquivo ou diretório selecionado, ou null se nenhuma seleção for feita.
     */
    private String selectFileOrDirectory(boolean directoryOnly) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setLocale(new Locale("pt", "BR"));

        if (directoryOnly) {
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        } else {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
            fileChooser.setFileFilter(filter);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        }

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile.getAbsolutePath();
        }
        return null;
    }

    private boolean checkStringAndShowWarning(final String input, final String message) {
        if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, message, "Aviso", JOptionPane.WARNING_MESSAGE);
            return true;
        }

        return false;
    }
}
