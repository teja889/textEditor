import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ATMApplication extends JFrame {
    private String userId = "12345";
    private String userPin = "6789";
    private double balance = 1000.0;
    private ArrayList<String> transactionHistory = new ArrayList<>();

    private JTextField idField;
    private JPasswordField pinField;
    private JButton loginButton;
    private JButton transactionHistoryButton;
    private JButton withdrawButton;
    private JButton depositButton;
    private JButton transferButton;
    private JButton quitButton;

    public ATMApplication() {
        setTitle("ATM Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        idField = new JTextField(15);
        pinField = new JPasswordField(15);
        loginButton = new JButton("Login");
        transactionHistoryButton = new JButton("Transaction History");
        withdrawButton = new JButton("Withdraw");
        depositButton = new JButton("Deposit");
        transferButton = new JButton("Transfer");
        quitButton = new JButton("Quit");

        add(new JLabel("User ID:"));
        add(idField);
        add(new JLabel("PIN:"));
        add(pinField);
        add(loginButton);
        add(transactionHistoryButton);
        add(withdrawButton);
        add(depositButton);
        add(transferButton);
        add(quitButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        transactionHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTransactionHistory();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transfer();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quit();
            }
        });

        setVisible(true);
    }

    private void login() {
        String enteredId = idField.getText();
        String enteredPin = new String(pinField.getPassword());

        if (enteredId.equals(userId) && enteredPin.equals(userPin)) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid User ID or PIN. Please try again.");
        }
    }

    private void showTransactionHistory() {
        StringBuilder history = new StringBuilder("Transaction History:\n");
        for (String transaction : transactionHistory) {
            history.append(transaction).append("\n");
        }
        JOptionPane.showMessageDialog(this, history.toString());
    }

    private void withdraw() {
        String amountStr = JOptionPane.showInputDialog(this, "Enter withdrawal amount:");
        try {
            double amount = Double.parseDouble(amountStr);
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                transactionHistory.add("Withdrawal: $" + amount);
                JOptionPane.showMessageDialog(this, "Withdrawal successful!");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid amount or insufficient balance.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid amount.");
        }
    }

    private void deposit() {
        String amountStr = JOptionPane.showInputDialog(this, "Enter deposit amount:");
        try {
            double amount = Double.parseDouble(amountStr);
            if (amount > 0) {
                balance += amount;
                transactionHistory.add("Deposit: $" + amount);
                JOptionPane.showMessageDialog(this, "Deposit successful!");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid amount.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid amount.");
        }
    }

    private void transfer() {
        // Implement the transfer functionality here.
        // This is a simplified example, and a real implementation would involve
        // verifying the recipient's account and updating balances accordingly.
    }

    private void quit() {
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ATMApplication();
            }
        });
    }
}
