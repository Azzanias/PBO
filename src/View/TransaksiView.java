package view;

import controller.TransaksiController;
import controller.PelangganController;
import model.Transaksi;
import model.Pelanggan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;

public class TransaksiView extends JFrame {
    private TransaksiController transaksiController;
    private PelangganController pelangganController;
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> cmbPelanggan, cmbLayanan, cmbStatus;
    private JTextField txtAlamat, txtNomorHp, txtCatatan, txtBerat, txtTotal, txtSearch;
    private JButton btnTambah, btnUpdate, btnHapus, btnClear, btnHitung, btnSearch;
    private int selectedId = -1;
    
    public TransaksiView() {
        this.transaksiController = new TransaksiController();
        this.pelangganController = new PelangganController();
        initializeUI();
        loadData();
    }
    
    private void initializeUI() {
        setTitle("Manajemen Transaksi Laundry - Lala Laundry");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        
        setLayout(new BorderLayout(10, 10));
        
        // === PANEL ATAS: FORM INPUT ===
        JPanel inputPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Form Transaksi Laundry"));
        inputPanel.setPreferredSize(new Dimension(800, 250));
        
        // ComboBox Pelanggan
        inputPanel.add(new JLabel("Pelanggan:*"));
        cmbPelanggan = new JComboBox<>();
        loadPelangganComboBox();
        inputPanel.add(cmbPelanggan);
        
        // ComboBox Layanan
        inputPanel.add(new JLabel("Jenis Layanan:*"));
        cmbLayanan = new JComboBox<>(transaksiController.getJenisLayanan());
        inputPanel.add(cmbLayanan);
        
        // TextField Alamat
        inputPanel.add(new JLabel("Alamat:*"));
        txtAlamat = new JTextField();
        inputPanel.add(txtAlamat);
        
        // TextField Nomor HP
        inputPanel.add(new JLabel("Nomor HP:*"));
        txtNomorHp = new JTextField();
        inputPanel.add(txtNomorHp);
        
        // TextField Catatan
        inputPanel.add(new JLabel("Catatan:"));
        txtCatatan = new JTextField();
        inputPanel.add(txtCatatan);
        
        // Panel untuk berat dan total
        JPanel beratTotalPanel = new JPanel(new GridLayout(1, 4, 5, 5));
        beratTotalPanel.add(new JLabel("Berat (kg):*"));
        txtBerat = new JTextField();
        beratTotalPanel.add(txtBerat);
        
        btnHitung = new JButton("Hitung Total");
        beratTotalPanel.add(btnHitung);
        
        beratTotalPanel.add(new JLabel("Total Harga:"));
        txtTotal = new JTextField();
        txtTotal.setEditable(false);
        beratTotalPanel.add(txtTotal);
        
        inputPanel.add(beratTotalPanel);
        
        // ComboBox Status
        inputPanel.add(new JLabel("Status Order:"));
        cmbStatus = new JComboBox<>(transaksiController.getDaftarStatus());
        inputPanel.add(cmbStatus);
        
        // Panel untuk tombol aksi form
        JPanel formButtonPanel = new JPanel(new FlowLayout());
        btnTambah = new JButton("Tambah Transaksi");
        btnUpdate = new JButton("Update Transaksi");
        btnHapus = new JButton("Hapus Transaksi");
        btnClear = new JButton("Clear Form");
        
        formButtonPanel.add(btnTambah);
        formButtonPanel.add(btnUpdate);
        formButtonPanel.add(btnHapus);
        formButtonPanel.add(btnClear);
        
        inputPanel.add(new JLabel()); // placeholder
        inputPanel.add(formButtonPanel);
        
        // === PANEL TENGAH: SEARCH ===
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setBorder(BorderFactory.createTitledBorder("Pencarian Transaksi"));
        
        searchPanel.add(new JLabel("Cari (Nama/Laundry/Status):"));
        txtSearch = new JTextField(20);
        searchPanel.add(txtSearch);
        
        btnSearch = new JButton("Search");
        searchPanel.add(btnSearch);
        
        JButton btnRefresh = new JButton("Refresh");
        searchPanel.add(btnRefresh);
        
        // === PANEL BAWAH: TABLE ===
        String[] columnNames = {"ID", "Pelanggan", "Layanan", "Alamat", "Status", "Total Harga", "Tanggal Order"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Daftar Transaksi Laundry"));
        
        // === MENYATUKAN SEMUA KOMPONEN ===
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.SOUTH);
        
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        // Status bar
        JLabel statusLabel = new JLabel(" Total transaksi: 0");
        add(statusLabel, BorderLayout.SOUTH);
        
        // === EVENT LISTENERS ===
        setupEventListeners();
    }
    
    private void loadPelangganComboBox() {
        try {
            List<Pelanggan> listPelanggan = pelangganController.getAllPelanggan();
            cmbPelanggan.removeAllItems();
            cmbPelanggan.addItem("-- Pilih Pelanggan --");
            for (Pelanggan p : listPelanggan) {
                cmbPelanggan.addItem(p.getNamaLengkap() + " - " + p.getEmail());
            }
        } catch (Exception e) {
            System.err.println("Error loading pelanggan data: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "Error memuat data pelanggan: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void setupEventListeners() {
        btnTambah.addActionListener(e -> tambahTransaksi());
        btnUpdate.addActionListener(e -> updateTransaksi());
        btnHapus.addActionListener(e -> hapusTransaksi());
        btnClear.addActionListener(e -> clearForm());
        btnHitung.addActionListener(e -> hitungTotal());
        btnSearch.addActionListener(e -> searchTransaksi());
        
        // Refresh data
        Component[] components = ((JPanel)btnSearch.getParent()).getComponents();
        for (Component comp : components) {
            if (comp instanceof JButton && ((JButton)comp).getText().equals("Refresh")) {
                ((JButton)comp).addActionListener(e -> loadData());
            }
        }
        
        // Ketika pelanggan dipilih, auto-fill data jika ada
        cmbPelanggan.addActionListener(e -> {
            if (cmbPelanggan.getSelectedIndex() > 0) {
                // Auto-fill data pelanggan jika diperlukan
                autoFillPelangganData();
            }
        });
        
        // Listener untuk tabel
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                int selectedRow = table.getSelectedRow();
                selectedId = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
                isiFormDariTable(selectedRow);
            }
        });
        
        // Enter key untuk search
        txtSearch.addActionListener(e -> searchTransaksi());
    }
    
    private void autoFillPelangganData() {
        try {
            int selectedIndex = cmbPelanggan.getSelectedIndex() - 1;
            if (selectedIndex >= 0) {
                List<Pelanggan> listPelanggan = pelangganController.getAllPelanggan();
                if (selectedIndex < listPelanggan.size()) {
                    Pelanggan pelanggan = listPelanggan.get(selectedIndex);
                    // Isi nomor HP jika kosong
                    if (txtNomorHp.getText().trim().isEmpty()) {
                        // Anda bisa menambahkan field nomor HP di model Pelanggan jika diperlukan
                        // txtNomorHp.setText(pelanggan.getNoTelepon());
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error auto-filling pelanggan data: " + e.getMessage());
        }
    }
    
    private void hitungTotal() {
        try {
            if (cmbLayanan.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(this, "Pilih jenis layanan terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String layanan = (String) cmbLayanan.getSelectedItem();
            String beratText = txtBerat.getText().trim();
            
            if (beratText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Masukkan berat terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            double berat = Double.parseDouble(beratText);
            if (berat <= 0) {
                JOptionPane.showMessageDialog(this, "Berat harus lebih dari 0!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            BigDecimal total = transaksiController.hitungTotalHarga(layanan, berat);
            txtTotal.setText("Rp " + total.toString());
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Berat harus angka yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error menghitung total: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void tambahTransaksi() {
        try {
            if (cmbPelanggan.getSelectedIndex() <= 0) {
                JOptionPane.showMessageDialog(this, "Pilih pelanggan terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Dapatkan user_id dari pelanggan yang dipilih
            int selectedIndex = cmbPelanggan.getSelectedIndex() - 1;
            List<Pelanggan> listPelanggan = pelangganController.getAllPelanggan();
            if (selectedIndex < 0 || selectedIndex >= listPelanggan.size()) {
                JOptionPane.showMessageDialog(this, "Pelanggan tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int userId = listPelanggan.get(selectedIndex).getUserId();
            String jenisLayanan = (String) cmbLayanan.getSelectedItem();
            String alamat = txtAlamat.getText().trim();
            String nomorHp = txtNomorHp.getText().trim();
            String catatan = txtCatatan.getText().trim();
            String totalText = txtTotal.getText().replace("Rp", "").trim();
            
            if (totalText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Hitung total harga terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            BigDecimal totalHarga = new BigDecimal(totalText);
            Transaksi transaksi = new Transaksi(userId, jenisLayanan, alamat, nomorHp, catatan, totalHarga);
            
            String error = transaksiController.validateTransaksi(transaksi);
            if (error != null) {
                JOptionPane.showMessageDialog(this, error, "Validasi Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Tambahkan transaksi baru?\n" +
                "Pelanggan: " + listPelanggan.get(selectedIndex).getNamaLengkap() + "\n" +
                "Layanan: " + jenisLayanan + "\n" +
                "Total: Rp " + totalHarga, 
                "Konfirmasi Tambah", 
                JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                if (transaksiController.createTransaksi(transaksi)) {
                    JOptionPane.showMessageDialog(this, "Transaksi berhasil ditambahkan!\nID Transaksi: " + transaksi.getOrderId());
                    clearForm();
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(this, "Gagal menambahkan transaksi!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Format total harga tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error menambah transaksi: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateTransaksi() {
        try {
            if (selectedId == -1) {
                JOptionPane.showMessageDialog(this, "Pilih transaksi yang akan diupdate!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Untuk update, kita hanya mengubah status atau data tertentu
            String newStatus = (String) cmbStatus.getSelectedItem();
            
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Update status transaksi #" + selectedId + " menjadi: " + newStatus + "?", 
                "Konfirmasi Update", 
                JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                if (transaksiController.updateStatusTransaksi(selectedId, newStatus)) {
                    JOptionPane.showMessageDialog(this, "Status transaksi berhasil diupdate!");
                    clearForm();
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(this, "Gagal mengupdate status transaksi!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error mengupdate transaksi: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void hapusTransaksi() {
        try {
            if (selectedId == -1) {
                JOptionPane.showMessageDialog(this, "Pilih transaksi yang akan dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            Transaksi transaksi = transaksiController.getTransaksiById(selectedId);
            if (transaksi == null) {
                JOptionPane.showMessageDialog(this, "Transaksi tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Yakin ingin menghapus transaksi ini?\n\n" +
                "ID: " + selectedId + "\n" +
                "Pelanggan: " + transaksi.getNamaPelanggan() + "\n" +
                "Layanan: " + transaksi.getJenisLayanan() + "\n" +
                "Total: Rp " + transaksi.getTotalHarga(), 
                "Konfirmasi Hapus", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
            
            if (confirm == JOptionPane.YES_OPTION) {
                if (transaksiController.deleteTransaksi(selectedId)) {
                    JOptionPane.showMessageDialog(this, "Transaksi berhasil dihapus!");
                    clearForm();
                    loadData();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error menghapus transaksi: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void searchTransaksi() {
        try {
            String keyword = txtSearch.getText().trim();
            
            if (keyword.isEmpty()) {
                loadData();
                return;
            }
            
            List<Transaksi> listTransaksi = transaksiController.searchTransaksi(keyword);
            updateTable(listTransaksi);
            
            JOptionPane.showMessageDialog(this, 
                "Ditemukan " + listTransaksi.size() + " transaksi dengan kata kunci: " + keyword,
                "Hasil Pencarian",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error mencari transaksi: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void isiFormDariTable(int row) {
        try {
            // Ambil data dari tabel dan isi ke form
            String status = tableModel.getValueAt(row, 4).toString();
            cmbStatus.setSelectedItem(status);
            
            // Untuk keperluan development, tampilkan data yang dipilih di console
            System.out.println("Selected transaction ID: " + selectedId);
        } catch (Exception e) {
            System.err.println("Error filling form from table: " + e.getMessage());
        }
    }
    
    private void clearForm() {
        cmbPelanggan.setSelectedIndex(0);
        cmbLayanan.setSelectedIndex(0);
        txtAlamat.setText("");
        txtNomorHp.setText("");
        txtCatatan.setText("");
        txtBerat.setText("");
        txtTotal.setText("");
        cmbStatus.setSelectedIndex(0);
        selectedId = -1;
        table.clearSelection();
    }
    
    private void loadData() {
        try {
            List<Transaksi> listTransaksi = transaksiController.getAllTransaksi();
            updateTable(listTransaksi);
            txtSearch.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error memuat data transaksi: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateTable(List<Transaksi> listTransaksi) {
        tableModel.setRowCount(0);
        
        for (Transaksi transaksi : listTransaksi) {
            Object[] rowData = {
                transaksi.getOrderId(),
                transaksi.getNamaPelanggan(),
                transaksi.getJenisLayanan(),
                transaksi.getAlamat(),
                transaksi.getStatusOrder(),
                "Rp " + transaksi.getTotalHarga(),
                transaksi.getTanggalOrder()
            };
            tableModel.addRow(rowData);
        }
        
        updateStatusBar(listTransaksi.size());
    }
    
    private void updateStatusBar(int total) {
        Component[] components = getContentPane().getComponents();
        for (Component comp : components) {
            if (comp instanceof JLabel && ((JLabel)comp).getText().startsWith(" Total")) {
                ((JLabel)comp).setText(" Total transaksi: " + total);
                break;
            }
        }
    }
    
    public static void main(String[] args) {
        // Safe Look and Feel initialization
        setupSafeLookAndFeel();
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new TransaksiView().setVisible(true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, 
                        "Error启动应用程序: " + e.getMessage(), 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        });
    }
    
    // Method untuk initialize Look and Feel dengan error handling yang lebih baik
    private static void setupSafeLookAndFeel() {
        try {
            // Coba system look and feel pertama
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            System.out.println("Successfully set System Look and Feel");
        } catch (Exception e1) {
            try {
                // Jika gagal, coba cross-platform look and feel
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                System.out.println("Successfully set Cross-Platform Look and Feel");
            } catch (Exception e2) {
                try {
                    // Jika masih gagal, coba Nimbus (biasanya tersedia di Java 6+)
                    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                            UIManager.setLookAndFeel(info.getClassName());
                            System.out.println("Successfully set Nimbus Look and Feel");
                            break;
                        }
                    }
                } catch (Exception e3) {
                    // Jika semua gagal, biarkan menggunakan default
                    System.err.println("Tidak dapat mengatur Look and Feel:");
                    System.err.println("System L&F: " + e1.getMessage());
                    System.err.println("Cross-platform L&F: " + e2.getMessage());
                    System.err.println("Nimbus L&F: " + e3.getMessage());
                    System.out.println("Menggunakan Look and Feel default");
                }
            }
        }
    }
}