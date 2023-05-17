package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterGUI extends JPanel {
    public static final String KEY = "REGISTER";
    private JPanel mainPanel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel phoneLabel;
    private JTextField phoneTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton registerButton;
    private LoginManager loginManager;
    private JButton backButton;

    public RegisterGUI(LoginManager loginManager) {
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
        nameLabel = new JLabel("Masukan nama Anda: ");
        nameTextField = new JTextField();

        phoneLabel = new JLabel("Masukan nomor handphone Anda: ");
        phoneTextField = new JTextField();

        passwordLabel = new JLabel("Masukan password Anda: ");
        passwordField = new JPasswordField();

        registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegister();
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
        mainPanel.add(nameLabel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        mainPanel.add(nameTextField, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        mainPanel.add(phoneLabel, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 10, 0);
        mainPanel.add(phoneTextField, gbc);

        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        mainPanel.add(passwordLabel, gbc);

        gbc.gridy = 5;
        gbc.insets = new Insets(0, 0, 10, 0);
        mainPanel.add(passwordField, gbc);

        gbc.gridy = 6;
        gbc.insets = new Insets(0, 0, 10, 0);
        mainPanel.add(registerButton, gbc);

        gbc.gridy = 7;
        gbc.insets = new Insets(0, 0, 0, 0);
        mainPanel.add(backButton, gbc);
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.navigateTo("HOME");

        nameTextField.setText("");
        phoneTextField.setText("");
    }

    /**
    * Method untuk mendaftarkan member pada sistem.
    * Akan dipanggil jika pengguna menekan "registerButton"
    * */
    private void handleRegister() {
        // TODO
        MainFrame mainFrame = MainFrame.getInstance();

        String nama = nameTextField.getText();
        String password = passwordField.getText();

        try {
            Integer.parseInt(phoneTextField.getText());
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Nomor HP hanya boleh angka!");
            phoneTextField.setText("");
            return;
        }
        String noHp = phoneTextField.getText();

        Member registeredMember = loginManager.register(nama, noHp, password);
        if (registeredMember != null){
            if(mainFrame.login(registeredMember.getId(), registeredMember.getPassword())){
                JOptionPane.showMessageDialog(null, "Berhasil membuat user dengan ID " + registeredMember.getId());
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "User dengan nama " + nama + " dan nomor hp " + noHp + " sudah ada!\n");
        }

        nameTextField.setText("");
        phoneTextField.setText("");
        passwordField.setText("");
    }
}
