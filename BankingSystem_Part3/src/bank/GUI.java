package bank;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GUI extends JFrame {
    private Transaction transferObject = new Transaction();
    private StringBuilder sbAllData = new StringBuilder();
    private LinkedList<Account> globalAccounts;

    private JLabel showAllData;
    private JButton showAllButton, depositButton, withdrawButton, transferButton;
    private JTextField accDeposit, depositInput;
    private JTextField accWithdraw, withdrawInput;
    private JTextField acc1Transfer, acc2Transfer, transferAmount;

    public GUI(LinkedList<Account> accounts) {
        super("🏦 Banking System GUI");
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(128, 128, 255));
        globalAccounts = accounts;

        refreshDisplay();

        showAllData = new JLabel(getFormattedAccounts());
        showAllData.setFont(new Font("SansSerif", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(showAllData);
        scrollPane.setBounds(20, 20, 540, 140);
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Account Details", TitledBorder.LEFT, TitledBorder.TOP));

        showAllButton = new JButton("🔄 Refresh");
        showAllButton.setBackground(new Color(128, 128, 255));
        showAllButton.setBounds(580, 20, 120, 30);

        depositButton = new JButton("Deposit");
        depositButton.setBounds(20, 180, 100, 30);
        accDeposit = new JTextField("Acc Num");
        accDeposit.setBounds(130, 180, 100, 30);
        depositInput = new JTextField("Amount");
        depositInput.setBounds(240, 180, 100, 30);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(20, 220, 100, 30);
        accWithdraw = new JTextField("Acc Num");
        accWithdraw.setBounds(130, 220, 100, 30);
        withdrawInput = new JTextField("Amount");
        withdrawInput.setBounds(240, 220, 100, 30);

        transferButton = new JButton("Transfer");
        transferButton.setBounds(20, 260, 100, 30);
        acc1Transfer = new JTextField("From Acc");
        acc1Transfer.setBounds(130, 260, 80, 30);
        acc2Transfer = new JTextField("To Acc");
        acc2Transfer.setBounds(220, 260, 80, 30);
        transferAmount = new JTextField("Amount");
        transferAmount.setBounds(310, 260, 80, 30);

        getContentPane().add(scrollPane);
        getContentPane().add(showAllButton);
        getContentPane().add(depositButton); getContentPane().add(accDeposit); getContentPane().add(depositInput);
        getContentPane().add(withdrawButton); getContentPane().add(accWithdraw); getContentPane().add(withdrawInput);
        getContentPane().add(transferButton); getContentPane().add(acc1Transfer); getContentPane().add(acc2Transfer); getContentPane().add(transferAmount);

        HandlerClass handler = new HandlerClass();
        showAllButton.addActionListener(handler);
        depositButton.addActionListener(handler);
        withdrawButton.addActionListener(handler);
        transferButton.addActionListener(handler);

        setSize(750, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private String getFormattedAccounts() {
        sbAllData.setLength(0);
        for (Account acc : globalAccounts) {
            sbAllData.append(acc.getFirstName()).append(" ").append(acc.getLastName())
                     .append(" | Acc #: ").append(acc.getAccountNum())
                     .append(" | Balance: ").append(acc.getBalance()).append("\n");
        }
        return "<html>" + sbAllData.toString().replaceAll("\n", "<br>") + "</html>";
    }

    private void refreshDisplay() {
        if (showAllData != null) {
            showAllData.setText(getFormattedAccounts());
        }
    }

    private class HandlerClass implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (e.getSource() == showAllButton) {
                    refreshDisplay();
                }

                if (e.getSource() == depositButton) {
                    int accNum = Integer.parseInt(accDeposit.getText().trim());
                    int amount = Integer.parseInt(depositInput.getText().trim());
                    for (Account acc : globalAccounts) {
                        if (acc.getAccountNum() == accNum) {
                            acc.deposit(amount);
                        }
                    }
                }

                if (e.getSource() == withdrawButton) {
                    int accNum = Integer.parseInt(accWithdraw.getText().trim());
                    int amount = Integer.parseInt(withdrawInput.getText().trim());
                    for (Account acc : globalAccounts) {
                        if (acc.getAccountNum() == accNum) {
                            acc.withdraw(amount);
                        }
                    }
                }

                if (e.getSource() == transferButton) {
                    int from = Integer.parseInt(acc1Transfer.getText().trim());
                    int to = Integer.parseInt(acc2Transfer.getText().trim());
                    int amount = Integer.parseInt(transferAmount.getText().trim());
                    Account fromAcc = null, toAcc = null;

                    for (Account acc : globalAccounts) {
                        if (acc.getAccountNum() == from) fromAcc = acc;
                        if (acc.getAccountNum() == to) toAcc = acc;
                    }

                    if (fromAcc != null && toAcc != null) {
                        transferObject.transfer(fromAcc, toAcc, amount);
                    }
                }

                refreshDisplay();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: Check input values.");
            }
        }
    }
}
