package view;

import controller.PelangganController;
import model.Pelanggan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PelangganView extends JFrame {
    private PelangganController pelangganController;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtNama, txtEmail, txtPassword, txtSearch;
    private JButton btnTambah, btnUpdate, btnHapus, btnClear, btnSearch;
    private int selectedId = -1;
    
    public PelangganView() {
        this.pelangganController = new PelangganController();
        initializeUI();
        loadData();
    }
    
    private void initializeUI() {
        setTitle("Manajemen Data Pelanggan - Lala Laundry");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        
        // Main panel dengan border layout
        setLayout(new BorderLayout(10, 10));
        
        // === PANEL ATAS: FORM INPUT ===
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Form Pelanggan"));
        inputPanel.setPreferredSize(new Dimension(800, 150));
        
        inputPanel.add(new JLabel("Nama Lengkap:*"));
        txtNama = new JTextField();
        inputPanel.add(txtNama);
        
        inputPanel.add(new JLabel("Email:*"));
        txtEmail = new JTextField();
        inputPanel.add(txtEmail);
        
        inputPanel.add(new JLabel("Password:*"));
        txtPassword = new JTextField();
        inputPanel.add(txtPassword);
        
        // Panel untuk tombol aksi form
        JPanel formButtonPanel = new JPanel(new FlowLayout());
        btnTambah = new JButton("Tambah");
        btnUpdate = new JButton("Update");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");
        
        formButtonPanel.add(btnTambah);
        formButtonPanel.add(btnUpdate);
        formButtonPanel.add(btnHapus);
        formButtonPanel.add(btnClear);
        
        inputPanel.add(new JLabel()); // placeholder
        inputPanel.add(formButtonPanel);
        
        // === PANEL TENGAH: SEARCH ===
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setBorder(BorderFactory.createTitledBorder("Pencarian"));
        
        searchPanel.add(new JLabel("Cari:"));
        txtSearch = new JTextField(20);
        searchPanel.add(txtSearch);
        
        btnSearch = new JButton("Search");
        searchPanel.add(btnSearch);
        
        JButton btnRefresh = new JButton("Refresh");
        searchPanel.add(btnRefresh);
        
        // === PANEL BAWAH: TABLE ===
        String[] columnNames = {"ID", "Nama Lengkap", "Email", "Tanggal Daftar"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Membuat tabel tidak bisa diedit langsung
            }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Daftar Pelanggan"));
        
        // === MENYATUKAN SEMUA KOMPONEN ===
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.SOUTH);
        
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        // Status bar
        JLabel statusLabel = new JLabel(" Total pelanggan: 0");
        add(statusLabel, BorderLayout.SOUTH);
        
        // === EVENT LISTENERS ===
        setupEventListeners();
    }
    
    private void setupEventListeners() {
        // Tombol Tambah
        btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahPelanggan();
            }
        });
        
        // Tombol Update
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePelanggan();
            }
        });
        
        // Tombol Hapus
        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hapusPelanggan();
            }
        });
        
        // Tombol Clear
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        
        // Tombol Search
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchPelanggan();
            }
        });
        
        // Refresh data
        Component[] components = ((JPanel)btnSearch.getParent()).getComponents();
        for (Component comp : components) {
            if (comp instanceof JButton && ((JButton)comp).getText().equals("Refresh")) {
                ((JButton)comp).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        loadData();
                    }
                });
            }
        }
        
        // Listener untuk tabel (saat baris dipilih)
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                int selectedRow = table.getSelectedRow();
                selectedId = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
                txtNama.setText(tableModel.getValueAt(selectedRow, 1).toString());
                txtEmail.setText(tableModel.getValueAt(selectedRow, 2).toString());
                // Password tidak ditampilkan untuk keamanan
                txtPassword.setText("");
            }
        });
        
        // Enter key untuk search
        txtSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchPelanggan();
            }
        });
    }
    
    private void tambahPelanggan() {
        String nama = txtNama.getText().trim();
        String email = txtEmail.getText().trim();
        String password = txtPassword.getText().trim();
        
        Pelanggan pelanggan = new Pelanggan(nama, email, password);
        
        // Validasi
        String error = pelangganController.validatePelanggan(pelanggan);
        if (error != null) {
            JOptionPane.showMessageDialog(this, error, "Validasi Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Konfirmasi
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Tambahkan pelanggan baru?\nNama: " + nama + "\nEmail: " + email, 
            "Konfirmasi Tambah", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            if (pelangganController.createPelanggan(pelanggan)) {
                JOptionPane.showMessageDialog(this, "Data pelanggan berhasil ditambahkan!");
                clearForm();
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menambahkan data pelanggan!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void updatePelanggan() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(this, "Pilih pelanggan yang akan diupdate!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String nama = txtNama.getText().trim();
        String email = txtEmail.getText().trim();
        String password = txtPassword.getText().trim();
        
        // Jika password kosong, ambil dari database yang ada
        Pelanggan existingPelanggan = pelangganController.getPelangganById(selectedId);
        if (password.isEmpty() && existingPelanggan != null) {
            password = existingPelanggan.getPassword();
        }
        
        Pelanggan pelanggan = new Pelanggan(selectedId, nama, email, password, existingPelanggan.getFotoProfil());
        
        // Validasi
        String error = pelangganController.validatePelanggan(pelanggan);
        if (error != null) {
            JOptionPane.showMessageDialog(this, error, "Validasi Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Konfirmasi
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Update data pelanggan?\nID: " + selectedId + "\nNama: " + nama + "\nEmail: " + email, 
            "Konfirmasi Update", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            if (pelangganController.updatePelanggan(pelanggan)) {
                JOptionPane.showMessageDialog(this, "Data pelanggan berhasil diupdate!");
                clearForm();
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal mengupdate data pelanggan!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void hapusPelanggan() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(this, "Pilih pelanggan yang akan dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Pelanggan pelanggan = pelangganController.getPelangganById(selectedId);
        if (pelanggan == null) {
            JOptionPane.showMessageDialog(this, "Pelanggan tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Yakin ingin menghapus pelanggan ini?\n\n" +
            "ID: " + selectedId + "\n" +
            "Nama: " + pelanggan.getNamaLengkap() + "\n" +
            "Email: " + pelanggan.getEmail(), 
            "Konfirmasi Hapus", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            if (pelangganController.deletePelanggan(selectedId)) {
                JOptionPane.showMessageDialog(this, "Data pelanggan berhasil dihapus!");
                clearForm();
                loadData();
            }
        }
    }
    
    private void searchPelanggan() {
        String keyword = txtSearch.getText().trim();
        
        if (keyword.isEmpty()) {
            loadData();
            return;
        }
        
        List<Pelanggan> listPelanggan = pelangganController.searchPelanggan(keyword);
        updateTable(listPelanggan);
        
        JOptionPane.showMessageDialog(this, 
            "Ditemukan " + listPelanggan.size() + " pelanggan dengan kata kunci: " + keyword,
            "Hasil Pencarian",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void clearForm() {
        txtNama.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
        selectedId = -1;
        table.clearSelection();
    }
    
    private void loadData() {
        List<Pelanggan> listPelanggan = pelangganController.getAllPelanggan();
        updateTable(listPelanggan);
        txtSearch.setText("");
    }
    
    private void updateTable(List<Pelanggan> listPelanggan) {
        // Clear table
        tableModel.setRowCount(0);
        
        // Load data ke table
        for (Pelanggan pelanggan : listPelanggan) {
            Object[] rowData = {
                pelanggan.getUserId(),
                pelanggan.getNamaLengkap(),
                pelanggan.getEmail(),
                "Terdaftar" // Placeholder untuk tanggal daftar
            };
            tableModel.addRow(rowData);
        }
        
        // Update status
        updateStatusBar(listPelanggan.size());
    }
    
    private void updateStatusBar(int total) {
        // Find and update status label
        Component[] components = getContentPane().getComponents();
        for (Component comp : components) {
            if (comp instanceof JLabel && ((JLabel)comp).getText().startsWith(" Total")) {
                ((JLabel)comp).setText(" Total pelanggan: " + total);
                break;
            }
        }
    }
    
    public static void main(String[] args) {
        // Improved Look and Feel initialization with better error handling
        initializeLookAndFeel();
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PelangganView().setVisible(true);
            }
        });
    }
    
    // Method untuk initialize Look and Feel dengan error handling yang lebih baik
    private static void initializeLookAndFeel() {
        try {
            // Coba system look and feel pertama
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e1) {
            try {
                // Jika gagal, coba cross-platform look and feel
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception e2) {
                try {
                    // Jika masih gagal, coba Nimbus (biasanya tersedia di Java 6+)
                    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                            UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                } catch (Exception e3) {
                    // Jika semua gagal, biarkan menggunakan default
                    System.err.println("Tidak dapat mengatur Look and Feel:");
                    System.err.println("System L&F: " + e1.getMessage());
                    System.err.println("Cross-platform L&F: " + e2.getMessage());
                    System.err.println("Nimbus L&F: " + e3.getMessage());
                }
            }
        }
    }
}