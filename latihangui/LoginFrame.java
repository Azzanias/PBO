/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package latihangui;

/**
 *
 * @author DIAZ
 */
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnRegister, btnCancel, btnClear;
    private Connection conn;

    public LoginFrame() {
        setTitle("Login Form");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Judul
        JLabel lblTitle = new JLabel("Welcome - Please Login", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lblTitle, gbc);

        // Username
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        txtUsername = new JTextField(15);
        panel.add(txtUsername, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        txtPassword = new JPasswordField(15);
        panel.add(txtPassword, gbc);

        // Tombol
        btnLogin = new JButton("Login");
        btnLogin.setBackground(new Color(100, 149, 237));
        btnLogin.setForeground(Color.WHITE);

        btnRegister = new JButton("Register");
        btnRegister.setBackground(new Color(60, 179, 113));
        btnRegister.setForeground(Color.WHITE);

        btnCancel = new JButton("Cancel");
        btnCancel.setBackground(new Color(220, 20, 60));
        btnCancel.setForeground(Color.WHITE);

        btnClear = new JButton("Clear");
        btnClear.setBackground(new Color(255, 165, 0));
        btnClear.setForeground(Color.WHITE);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(panel.getBackground());
        btnPanel.add(btnLogin);
        btnPanel.add(btnRegister);
        btnPanel.add(btnCancel);
        btnPanel.add(btnClear);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(btnPanel, gbc);

        add(panel);

        connectDB();

        // Event tombol
        btnLogin.addActionListener(e -> loginAction());
        btnRegister.addActionListener(e -> openRegisterForm());
        btnCancel.addActionListener(e -> cancelAction());
        btnClear.addActionListener(e -> clearAction());
    }

    // Koneksi DB
    private void connectDB() {
        try {
            String url = "jdbc:postgresql://localhost:5432/login_db";
            String user = "postgres";
            String password = "password";
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Koneksi berhasil.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Koneksi gagal: " + e.getMessage());
        }
    }

    // Login
    private void loginAction() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        try {
            String sql = "SELECT role FROM users WHERE username=? AND password=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");
                JOptionPane.showMessageDialog(this,
                        "Login berhasil! Selamat datang " + username + " (" + role + ")");
                openWelcomeFrame(username);
            } else {
                JOptionPane.showMessageDialog(this, "Username atau Password salah!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    // Clear
    private void clearAction() {
        txtUsername.setText("");
        txtPassword.setText("");
    }

    // Cancel
    private void cancelAction() {
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin keluar?",
                "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // Welcome Frame (setelah login berhasil)
    private void openWelcomeFrame(String username) {
        JFrame welcomeFrame = new JFrame("Welcome");
        welcomeFrame.setSize(300, 150);
        welcomeFrame.setLocationRelativeTo(null);
        JLabel lbl = new JLabel("Selamat datang, " + username, SwingConstants.CENTER);
        lbl.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeFrame.add(lbl);
        welcomeFrame.setVisible(true);
    }

    // Register Form
    private void openRegisterForm() {
        JFrame regFrame = new JFrame("Register User");
        regFrame.setSize(400, 300);
        regFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField regUsername = new JTextField();
        JPasswordField regPassword = new JPasswordField();
        JTextField regRole = new JTextField();

        JButton btnSubmit = new JButton("Submit");

        panel.add(new JLabel("Username:"));
        panel.add(regUsername);
        panel.add(new JLabel("Password:"));
        panel.add(regPassword);
        panel.add(new JLabel("Role (admin/mahasiswa):"));
        panel.add(regRole);
        panel.add(new JLabel(""));
        panel.add(btnSubmit);

        regFrame.add(panel);
        regFrame.setVisible(true);

        btnSubmit.addActionListener(e -> {
            try {
                String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, regUsername.getText());
                pst.setString(2, new String(regPassword.getPassword()));
                pst.setString(3, regRole.getText());
                pst.executeUpdate();

                JOptionPane.showMessageDialog(regFrame, "Registrasi berhasil!");
                regFrame.dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(regFrame, "Error: " + ex.getMessage());
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}
