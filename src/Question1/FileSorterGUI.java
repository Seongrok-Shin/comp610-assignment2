/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question1;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ssr7324
 */
public class FileSorterGUI {

    private JFrame jFrame;
    private JPanel jPanel;
    private JLabel maxString, numOfItem, inputFile, inputName, outputFile, outputName, mergeProgress, splitProgress;
    private JButton processButton, enqueueButton;
    private JTextField maxStringTextField;
    private JProgressBar mergeBar, splitBar;
    private FileSorter fileSorter;
    private int numOfItemInQueue;
    private Font font;
    private File file;

    public FileSorterGUI() {
        numOfItemInQueue = 0;
        fileSorter = new FileSorter();
        file = new File("");
        jFrame = new JFrame("File Sorter");
        jFrame.setSize(400, 400);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(null);
        jFrame.setResizable(false);

        jPanel = new JPanel();
        jPanel.setSize(400, 400);
        jPanel.setBackground(Color.WHITE);
        jPanel.setVisible(true);
        jPanel.setLayout(new GridBagLayout());

        font = new Font("Segoe UI", Font.BOLD, 16);

        numOfItem = new JLabel("Number of items in queue: " + numOfItemInQueue);
        numOfItem.setFont(font);

        maxString = new JLabel("Max Strings in memory");
        maxString.setFont(font);
        maxStringTextField = new JTextField();
        maxStringTextField.setFont(font);

        inputFile = new JLabel("Input File: ");
        inputName = new JLabel();
        inputFile.setFont(font);
        inputName.setFont(font);

        outputFile = new JLabel("Output File: ");
        outputName = new JLabel();
        outputFile.setFont(font);
        outputName.setFont(font);

        mergeProgress = new JLabel("Merge Progress: ");
        mergeProgress.setFont(font);
        mergeBar = new JProgressBar(0, 100);

        splitProgress = new JLabel("Split Progress: ");
        splitProgress.setFont(font);
        splitBar = new JProgressBar(0, 100);

        processButton = new JButton("Process Task");
        processButton.setFont(font);
        processButton.addActionListener(new ProcessButtonListener());
        enqueueButton = new JButton("Enqueue Task");
        enqueueButton.setFont(font);
        enqueueButton.addActionListener(new EnqueueButtonListener());

        jPanel.add(numOfItem, generateNewConstraints(0, 0, 1, 1));
        jPanel.add(maxString, generateNewConstraints(0, 1, 1, 1));
        jPanel.add(maxStringTextField, generateNewConstraints(0, 2, 1, 1));
        jPanel.add(inputFile, generateNewConstraints(0, 3, 1, 1));
        jPanel.add(inputName, generateNewConstraints(0, 4, 1, 1));
        jPanel.add(outputFile, generateNewConstraints(0, 4, 1, 1));
        jPanel.add(outputName, generateNewConstraints(0, 5, 1, 1));
        jPanel.add(mergeProgress, generateNewConstraints(0, 6, 1, 1));
        jPanel.add(mergeBar, generateNewConstraints(0, 7, 1, 1));
        jPanel.add(splitProgress, generateNewConstraints(0, 8, 1, 1));
        jPanel.add(splitBar, generateNewConstraints(0, 9, 1, 1));
        jPanel.add(processButton, generateNewConstraints(0, 10, 1, 1));
        jPanel.add(enqueueButton, generateNewConstraints(1, 10, 1, 1));

        jFrame.getContentPane().add(jPanel);
        jFrame.setVisible(true);
    }

    private GridBagConstraints generateNewConstraints(int x, int y, int width, int height) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridheight = height;
        constraints.gridwidth = width;
        constraints.insets = new Insets(2, 2, 2, 2);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.BOTH;
        return constraints;
    }

    public static void main(String[] args) {
        new FileSorterGUI();
    }

    private class ProcessButtonListener implements ActionListener {

        JFileChooser inputFileChooser;
        int input;

        @Override
        public void actionPerformed(ActionEvent e) {
            inputFileChooser = new JFileChooser();
            input = inputFileChooser.showOpenDialog(null);

            if (maxStringTextField.getText().length() <= 0) {
                JOptionPane.showMessageDialog(null, "You should set the max number of string");
            } else if (numOfItemInQueue > 0) {
                fileSorter.setLimit(Integer.parseInt(maxStringTextField.getText()));
                mergeBar.setValue(0);
                splitBar.setValue(0);

                try {
                    fileSorter.split(file, splitBar);
                    splitBar.setValue(fileSorter.splitProgress);
                } catch (IOException ex) {
                    Logger.getLogger(FileSorterGUI.class.getName()).log(Level.SEVERE, null, ex);
                }

                JOptionPane.showMessageDialog(null, "Done!");

                numOfItemInQueue--;
            }
        }
    }

    private class EnqueueButtonListener implements ActionListener {

        JFileChooser enqueueFileChooser;
        int enqueue;

        @Override
        public void actionPerformed(ActionEvent e) {
            enqueueFileChooser = new JFileChooser();
            enqueue = enqueueFileChooser.showOpenDialog(null);
            JFileChooser outputFileChooser = new JFileChooser();
            int output = outputFileChooser.showOpenDialog(null);

            if (enqueue != JFileChooser.APPROVE_OPTION) {
                return;
            }

            if (output == JFileChooser.APPROVE_OPTION
                    && enqueue == JFileChooser.APPROVE_OPTION) {
                File enqueueFile = new File(enqueueFileChooser.getSelectedFile().getAbsolutePath());
                File outputFile = new File(outputFileChooser.getSelectedFile().getAbsolutePath());

                numOfItemInQueue++;
                numOfItem.setText("How many items in Queue? " + numOfItemInQueue);
            }
        }
    }
}
