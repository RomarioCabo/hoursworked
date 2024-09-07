package com.br;

import com.br.controller.JFormMain;

import javax.swing.*;

import static java.awt.EventQueue.invokeLater;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;

public class Main {
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            getLogger(Main.class.getName()).log(SEVERE, null, ex);
        }

        JFormMain mainForm = new JFormMain();
        invokeLater(() -> mainForm.setVisible(true));
    }
}