package view;

import controller.PembayaranController;
import model.Pembayaran;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;

public class PembayaranView extends JFrame {
    private PembayaranController controller;
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> cmbOrder, cmbMetode, cmbStatus;
    private JTextField txtTotal;
    private JButton btnTambah, btnUpdate, btnHapus, btnClear;
    private int selectedId = -1;
    
    public PembayaranView() {
        this.controller = new PembayaranController();
        initializeUI();
        loadData();
    }
    
    private void initializeUI() {
        setTitle("Manajemen Pembayaran - Lala Laundry");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        
        // Main layout
        setLayout(new BorderLayout(10, 10));
        
        // Input Panel
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);
        
        // Table
        JScrollPane tablePanel = createTablePanel();
        add(tablePanel, BorderLayout.CENTER);
        
        // Status bar
        JLabel statusLabel = new JLabel(" Total pembayaran: 0");
        add(statusLabel, BorderLayout.SOUTH);
        
        setupEventListeners();
    }
    
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Form Pembayaran"));
        panel.setPreferredSize(new Dimension(800, 200));
        
        // Order selection
        panel.add(new JLabel("Pilih Order:"));
        cmbOrder = new JComboBox<>();
        loadAvailableOrders();
        panel.add(cmbOrder);
        
        // Payment method
        panel.add(new JLabel("Metode Pembayaran:"));
        cmbMetode = new JComboBox<>(controller.getMetodePembayaran());
        panel.add(cmbMetode);
        
        // Total amount
        panel.add(new JLabel("Total Bayar:"));
        txtTotal = new JTextField();
        panel.add(txtTotal);
        
        // Status
        panel.add(new JLabel("Status:"));
        cmbStatus = new JComboBox<>(controller.getStatusPembayaran());
        panel.add(cmbStatus);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnTambah = new JButton("Tambah");
        btnUpdate = new JButton("Update Status");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");
        
        buttonPanel.add(btnTambah);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnHapus);
        buttonPanel.add(btnClear);
        
        panel.add(new JLabel());
        panel.add(buttonPanel);
        
        return panel;
    }
    
    private JScrollPane createTablePanel() {
        String[] columns = {"ID", "Order ID", "Metode", "Total Bayar", "Status", "Tanggal Bayar"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        return new JScrollPane(table);
    }
    
    private void loadAvailableOrders() {
        try {
            cmbOrder.removeAllItems();
            cmbOrder.addItem("-- Pilih Order --");
            
            List<String> availableOrders = controller.getAvailableOrders();
            for (String order : availableOrders) {
                cmbOrder.addItem(order);
            }
            
            if (availableOrders.isEmpty()) {
                cmbOrder.addItem("Tidak ada order tersedia");
            }
        } catch (Exception e) {
            showError("Error loading orders: " + e.getMessage());
        }
    }
    
    private void setupEventListeners() {
        // Tambah pembayaran
        btnTambah.addActionListener(e -> {
            try {
                tambahPembayaran();
            } catch (Exception ex) {
                showError("Error: " + ex.getMessage());
            }
        });
        
        // Update status
        btnUpdate.addActionListener(e -> {
            try {
                updatePembayaran();
            } catch (Exception ex) {
                showError("Error: " + ex.getMessage());
            }
        });
        
        // Hapus pembayaran
        btnHapus.addActionListener(e -> {
            try {
                hapusPembayaran();
            } catch (Exception ex) {
                showError("Error: " + ex.getMessage());
            }
        });
        
        // Clear form
        btnClear.addActionListener(e -> clearForm());
        
        // Auto-fill total when order selected
        cmbOrder.addActionListener(e -> {
            if (cmbOrder.getSelectedIndex() > 0) {
                autoFillTotal();
            }
        });
        
        // Table selection
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                try {
                    int row = table.getSelectedRow();
                    selectedId = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
                    cmbStatus.setSelectedItem(tableModel.getValueAt(row, 4).toString());
                } catch (Exception ex) {
                    showError("Error selecting row: " + ex.getMessage());
                }
            }
        });
    }
    
    private void autoFillTotal() {
        try {
            String selected = (String) cmbOrder.getSelectedItem();
            if (selected != null && selected.contains("Rp")) {
                String totalStr = selected.split("Rp")[1].trim();
                txtTotal.setText(totalStr);
            }
        } catch (Exception e) {
            System.err.println("Error auto-filling total: " + e.getMessage());
        }
    }
    
    private void tambahPembayaran() {
        // Validasi pilihan order
        if (cmbOrder.getSelectedIndex() <= 0) {
            showError("Pilih order terlebih dahulu!");
            return;
        }
        
        // Parse order ID dari combobox
        String selectedOrder = (String) cmbOrder.getSelectedItem();
        int orderId = extractOrderId(selectedOrder);
        
        if (orderId <= 0) {
            showError("Format order tidak valid!");
            return;
        }
        
        // Validasi input
        String metode = (String) cmbMetode.getSelectedItem();
        String totalText = txtTotal.getText().trim();
        
        if (totalText.isEmpty()) {
            showError("Total bayar tidak boleh kosong!");
            return;
        }
        
        try {
            BigDecimal total = new BigDecimal(totalText);
            String status = (String) cmbStatus.getSelectedItem();
            
            // Validasi business logic
            String error = controller.validatePembayaran(orderId, metode, total);
            if (error != null) {
                showError(error);
                return;
            }
            
            // Konfirmasi
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Buat pembayaran untuk Order #" + orderId + "?\nMetode: " + metode + "\nTotal: Rp " + total,
                "Konfirmasi Pembayaran",
                JOptionPane.YES_NO_OPTION
            );
            
            if (confirm == JOptionPane.YES_OPTION) {
                Pembayaran pembayaran = new Pembayaran(orderId, metode, total);
                pembayaran.setStatusBayar(status);
                
                if (controller.createPembayaran(pembayaran)) {
                    JOptionPane.showMessageDialog(this, "Pembayaran berhasil dibuat!");
                    clearForm();
                    loadData();
                    loadAvailableOrders();
                } else {
                    showError("Gagal membuat pembayaran!");
                }
            }
            
        } catch (NumberFormatException e) {
            showError("Format total bayar tidak valid! Gunakan angka.");
        } catch (Exception e) {
            showError("Error membuat pembayaran: " + e.getMessage());
        }
    }
    
    private void updatePembayaran() {
        if (selectedId == -1) {
            showError("Pilih pembayaran yang akan diupdate!");
            return;
        }
        
        String newStatus = (String) cmbStatus.getSelectedItem();
        
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Update status pembayaran #" + selectedId + " menjadi: " + newStatus + "?",
            "Konfirmasi Update",
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            if (controller.updateStatusPembayaran(selectedId, newStatus)) {
                JOptionPane.showMessageDialog(this, "Status pembayaran berhasil diupdate!");
                clearForm();
                loadData();
            } else {
                showError("Gagal mengupdate status pembayaran!");
            }
        }
    }
    
    private void hapusPembayaran() {
        if (selectedId == -1) {
            showError("Pilih pembayaran yang akan dihapus!");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Yakin ingin menghapus pembayaran #" + selectedId + "?",
            "Konfirmasi Hapus",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            if (controller.deletePembayaran(selectedId)) {
                JOptionPane.showMessageDialog(this, "Pembayaran berhasil dihapus!");
                clearForm();
                loadData();
                loadAvailableOrders();
            } else {
                showError("Gagal menghapus pembayaran!");
            }
        }
    }
    
    private void clearForm() {
        cmbOrder.setSelectedIndex(0);
        cmbMetode.setSelectedIndex(0);
        txtTotal.setText("");
        cmbStatus.setSelectedIndex(0);
        selectedId = -1;
        table.clearSelection();
    }
    
    private void loadData() {
        try {
            tableModel.setRowCount(0);
            List<Pembayaran> payments = controller.getAllPembayaran();
            
            for (Pembayaran p : payments) {
                Object[] row = {
                    p.getPembayaranId(),
                    p.getOrderId(),
                    p.getMetode(),
                    "Rp " + p.getTotalBayar(),
                    p.getStatusBayar(),
                    p.getTanggalBayar() != null ? p.getTanggalBayar().toString() : "-"
                };
                tableModel.addRow(row);
            }
            
            // Update status bar
            updateStatusBar(payments.size());
            
        } catch (Exception e) {
            showError("Error loading data: " + e.getMessage());
        }
    }
    
    private void updateStatusBar(int total) {
        Component[] components = getContentPane().getComponents();
        for (Component comp : components) {
            if (comp instanceof JLabel && ((JLabel)comp).getText().startsWith(" Total")) {
                ((JLabel)comp).setText(" Total pembayaran: " + total);
                break;
            }
        }
    }
    
    private int extractOrderId(String orderText) {
        try {
            if (orderText != null && orderText.startsWith("Order #")) {
                String idStr = orderText.split(" ")[1].replace("#", "").trim();
                return Integer.parseInt(idStr);
            }
        } catch (Exception e) {
            System.err.println("Error extracting order ID: " + e.getMessage());
        }
        return -1;
    }
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void main(String[] args) {
        // Simple startup - no complex Look and Feel
        try {
            SwingUtilities.invokeLater(() -> {
                PembayaranView view = new PembayaranView();
                view.setVisible(true);
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                "Error starting application: " + e.getMessage(), 
                "Startup Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}