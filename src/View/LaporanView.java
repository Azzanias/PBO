package View;

import Controller.LaporanController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.Laporan;

public class LaporanView extends JFrame {
    private LaporanController controller;
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> cmbLaporan;
    private JTextField txtTanggal, txtTahun, txtBulan;
    private JButton btnTampil, btnStatistik;
    private JTextArea txtStatistik;
    
    public LaporanView() {
        this.controller = new LaporanController();
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("Laporan Sederhana - Lala Laundry");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        
        // Layout utama
        setLayout(new BorderLayout());
        
        // Panel input atas
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBorder(BorderFactory.createTitledBorder("Pilih Laporan"));
        
        topPanel.add(new JLabel("Jenis:"));
        cmbLaporan = new JComboBox<>(new String[]{"Harian", "Bulanan"});
        topPanel.add(cmbLaporan);
        
        topPanel.add(new JLabel("Tanggal (YYYY-MM-DD):"));
        txtTanggal = new JTextField(10);
        txtTanggal.setText("2024-01-15"); // Contoh
        topPanel.add(txtTanggal);
        
        topPanel.add(new JLabel("Tahun:"));
        txtTahun = new JTextField(5);
        txtTahun.setText("2024");
        topPanel.add(txtTahun);
        
        topPanel.add(new JLabel("Bulan:"));
        txtBulan = new JTextField(3);
        txtBulan.setText("1");
        topPanel.add(txtBulan);
        
        btnTampil = new JButton("Tampilkan");
        topPanel.add(btnTampil);
        
        add(topPanel, BorderLayout.NORTH);
        
        // Tabel tengah
        String[] kolom = {"Periode", "Jumlah Transaksi", "Total Pendapatan"};
        tableModel = new DefaultTableModel(kolom, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
        
        // Panel bawah untuk statistik
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Statistik"));
        
        txtStatistik = new JTextArea(4, 50);
        txtStatistik.setEditable(false);
        btnStatistik = new JButton("Refresh Statistik");
        
        bottomPanel.add(new JScrollPane(txtStatistik), BorderLayout.CENTER);
        bottomPanel.add(btnStatistik, BorderLayout.SOUTH);
        
        add(bottomPanel, BorderLayout.SOUTH);
        
        // Event listeners sederhana
        btnTampil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tampilkanLaporan();
            }
        });
        
        btnStatistik.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tampilkanStatistik();
            }
        });
        
        // Tampilkan statistik awal
        tampilkanStatistik();
    }
    
    private void tampilkanLaporan() {
        try {
            String jenis = (String) cmbLaporan.getSelectedItem();
            List<Laporan> data = null;
            
            if ("Harian".equals(jenis)) {
                data = controller.getLaporanHarian(txtTanggal.getText());
            } else if ("Bulanan".equals(jenis)) {
                data = controller.getLaporanBulanan(txtTahun.getText(), txtBulan.getText());
            }
            
            // Update tabel
            tableModel.setRowCount(0);
            if (data != null) {
                for (Laporan laporan : data) {
                    Object[] row = {
                        laporan.getPeriode(),
                        laporan.getTotalTransaksi(),
                        "Rp " + laporan.getTotalPendapatan()
                    };
                    tableModel.addRow(row);
                }
                
                if (data.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Tidak ada data untuk periode tersebut");
                }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    private void tampilkanStatistik() {
        String statistik = controller.getStatistikSederhana();
        txtStatistik.setText(statistik);
    }
    
    public static void main(String[] args) {
        // Startup paling sederhana
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LaporanView view = new LaporanView();
                view.setVisible(true);
            }
        });
    }
}