import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ATM extends JFrame {
    private HashMap<String, User> userList;
    private JTextField userIdField;
    private JPasswordField pinField;
    private JTextArea displayArea;
    private User currentUser;

    ATM() {
        userList = new HashMap<>();
        userList.put("123456", new User("123456", "885094", 1000));

        setTitle("ATM Interface");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel userIdLabel = new JLabel("User ID:");
        userIdField = new JTextField();
        JLabel pinLabel = new JLabel("PIN:");
        pinField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });

        displayArea = new JTextArea();
        displayArea.setEditable(false);

        panel.add(userIdLabel);
        panel.add(userIdField);
        panel.add(pinLabel);
        panel.add(pinField);
        panel.add(loginButton);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        setVisible(true);
    }

    private void authenticateUser() {
        String id = userIdField.getText();
        String pin = new String(pinField.getPassword());

        User user = userList.get(id);

        if (user != null && user.getUserPIN().equals(pin)) {
            displayArea.setText("Authentication successful! Welcome, " + id + "!");
            currentUser = user;
            showMenu();
        } else {
            displayArea.setText("Invalid credentials. Please try again.");
        }
    }

    private void showMenu() {
        JPanel menuPanel = new JPanel(new GridLayout(4, 1));

        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayBalance();
            }
        });

        JButton withdrawButton = new JButton("Withdraw Money");
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdrawMoney();
            }
        });

        JButton depositButton = new JButton("Deposit Money");
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                depositMoney();
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuPanel.add(checkBalanceButton);
        menuPanel.add(withdrawButton);
        menuPanel.add(depositButton);
        menuPanel.add(exitButton);

        add(menuPanel, BorderLayout.SOUTH);
        revalidate();
    }

    private void displayBalance() {
        displayArea.setText("Your account balance is: $" + currentUser.getAccountBalance());
    }

    private void withdrawMoney() {
        String input = JOptionPane.showInputDialog("Enter the amount to withdraw:");
        double amount = Double.parseDouble(input);

        if (amount > 0 && amount <= currentUser.getAccountBalance()) {
            currentUser.setAccountBalance(currentUser.getAccountBalance() - amount);
            displayBalance();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid amount or insufficient funds.");
        }
    }

    private void depositMoney() {
        String input = JOptionPane.showInputDialog("Enter the amount to deposit:");
        double amount = Double.parseDouble(input);

        if (amount > 0) {
            currentUser.setAccountBalance(currentUser.getAccountBalance() + amount);
            displayBalance();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid amount.");
        }
    }


}
