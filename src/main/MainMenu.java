package main;

import View.LaporanView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.PelangganView;
import view.PembayaranView;
import view.TransaksiView;

public class MainMenu extends JFrame {
    
    public MainMenu() {
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("LALA LAUNDRY - Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        
        // Panel utama
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Judul
        JLabel titleLabel = new JLabel("LALA LAUNDRY");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel subtitleLabel = new JLabel("Management System");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Tombol menu
        JButton btnPelanggan = new JButton("📋 Manajemen Pelanggan");
        JButton btnTransaksi = new JButton("💰 Manajemen Transaksi");
        JButton btnPembayaran = new JButton("💳 Manajemen Pembayaran");
        JButton btnLaporan = new JButton("📊 Laporan & Statistik");
        JButton btnExit = new JButton("❌ Keluar");
        
        // Style tombol
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);
        btnPelanggan.setFont(buttonFont);
        btnTransaksi.setFont(buttonFont);
        btnPembayaran.setFont(buttonFont);
        btnLaporan.setFont(buttonFont);
        btnExit.setFont(buttonFont);
        
        // Set alignment dan size
        Dimension buttonSize = new Dimension(250, 40);
        btnPelanggan.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnTransaksi.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnPembayaran.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLaporan.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        btnPelanggan.setMaximumSize(buttonSize);
        btnTransaksi.setMaximumSize(buttonSize);
        btnPembayaran.setMaximumSize(buttonSize);
        btnLaporan.setMaximumSize(buttonSize);
        btnExit.setMaximumSize(buttonSize);
        
        // Event listeners
        btnPelanggan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PelangganView().setVisible(true);
            }
        });
        
        btnTransaksi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TransaksiView().setVisible(true);
            }
        });
        
        btnPembayaran.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PembayaranView().setVisible(true);
            }
        });
        
        btnLaporan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LaporanView().setVisible(true);
            }
        });
        
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        // Tambahkan komponen ke panel
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(subtitleLabel);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(btnPelanggan);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(btnTransaksi);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(btnPembayaran);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(btnLaporan);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(btnExit);
        
        add(mainPanel);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }
}