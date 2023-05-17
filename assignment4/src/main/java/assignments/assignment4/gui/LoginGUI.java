package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JPanel {
    public static final String KEY = "LOGIN";
    private JPanel mainPanel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    private LoginManager loginManager;

    public LoginGUI(LoginManager loginManager) {
        super(new BorderLayout()); // Setup layout, Feel free to make any changes
        this.loginManager = loginManager;

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        // TODO
        idLabel = new JLabel("Masukkan ID anda: ");
        idTextField = new JTextField();

        passwordLabel = new JLabel("Masukkan password anda:");
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        backButton = new JButton("Kembali");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBack();
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0); // top, left, bottom, right
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(idLabel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        mainPanel.add(idTextField, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        mainPanel.add(passwordLabel, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 10, 0);
        mainPanel.add(passwordField, gbc);

        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        mainPanel.add(loginButton, gbc);

        gbc.gridy = 5;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        mainPanel.add(backButton, gbc);
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.navigateTo("HOME");
    }

    /**
     * Method untuk login pada sistem.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    private void handleLogin() {
        // TODO
        MainFrame mainFrame = MainFrame.getInstance();

        String id = idTextField.getText();
        String password = passwordField.getText();

        if (id.isBlank() || password.isBlank()){
            JOptionPane.showMessageDialog(null, "Semua field harus diisi!");
        }
        if (mainFrame.login(id,password)){
            idTextField.setText("");
            passwordField.setText("");
        }
        else {
            JOptionPane.showMessageDialog(null, "Invalid ID or password.");
            idTextField.setText("");
            passwordField.setText("");

        }
    }
}
