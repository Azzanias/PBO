package controller;

import model.Transaksi;
import database.DBConnection;

import javax.swing.*;
import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransaksiController {
    private Connection connection;
    
    public TransaksiController() {
        this.connection = DBConnection.getConnection();
    }
    
    // CREATE - Buat transaksi baru
    public boolean createTransaksi(Transaksi transaksi) {
        String sql = "INSERT INTO orders (user_id, jenis_layanan, alamat, nomor_hp, catatan, total_harga) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, transaksi.getUserId());
            stmt.setString(2, transaksi.getJenisLayanan());
            stmt.setString(3, transaksi.getAlamat());
            stmt.setString(4, transaksi.getNomorHp());
            stmt.setString(5, transaksi.getCatatan());
            stmt.setBigDecimal(6, transaksi.getTotalHarga());
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    transaksi.setOrderId(generatedKeys.getInt(1));
                }
                return true;
            }
            return false;
            
        } catch (SQLException e) {
            System.out.println("Error create transaksi: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    // READ - Ambil semua transaksi dengan nama pelanggan
    public List<Transaksi> getAllTransaksi() {
        List<Transaksi> listTransaksi = new ArrayList<>();
        String sql = "SELECT o.*, u.nama_lengkap " +
                    "FROM orders o " +
                    "JOIN users u ON o.user_id = u.user_id " +
                    "ORDER BY o.tanggal_order DESC";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Transaksi transaksi = new Transaksi();
                transaksi.setOrderId(rs.getInt("order_id"));
                transaksi.setUserId(rs.getInt("user_id"));
                transaksi.setJenisLayanan(rs.getString("jenis_layanan"));
                transaksi.setAlamat(rs.getString("alamat"));
                transaksi.setNomorHp(rs.getString("nomor_hp"));
                transaksi.setCatatan(rs.getString("catatan"));
                transaksi.setTanggalOrder(rs.getTimestamp("tanggal_order"));
                transaksi.setStatusOrder(rs.getString("status_order"));
                transaksi.setTotalHarga(rs.getBigDecimal("total_harga"));
                transaksi.setNamaPelanggan(rs.getString("nama_lengkap"));
                listTransaksi.add(transaksi);
            }
            
        } catch (SQLException e) {
            System.out.println("Error get all transaksi: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return listTransaksi;
    }
    
    // READ - Ambil transaksi by ID
    public Transaksi getTransaksiById(int orderId) {
        String sql = "SELECT o.*, u.nama_lengkap FROM orders o " +
                    "JOIN users u ON o.user_id = u.user_id " +
                    "WHERE o.order_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Transaksi transaksi = new Transaksi();
                transaksi.setOrderId(rs.getInt("order_id"));
                transaksi.setUserId(rs.getInt("user_id"));
                transaksi.setJenisLayanan(rs.getString("jenis_layanan"));
                transaksi.setAlamat(rs.getString("alamat"));
                transaksi.setNomorHp(rs.getString("nomor_hp"));
                transaksi.setCatatan(rs.getString("catatan"));
                transaksi.setTanggalOrder(rs.getTimestamp("tanggal_order"));
                transaksi.setStatusOrder(rs.getString("status_order"));
                transaksi.setTotalHarga(rs.getBigDecimal("total_harga"));
                transaksi.setNamaPelanggan(rs.getString("nama_lengkap"));
                return transaksi;
            }
            
        } catch (SQLException e) {
            System.out.println("Error get transaksi by id: " + e.getMessage());
        }
        
        return null;
    }
    
    // UPDATE - Update status transaksi
    public boolean updateStatusTransaksi(int orderId, String newStatus) {
        String sql = "UPDATE orders SET status_order = ? WHERE order_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newStatus);
            stmt.setInt(2, orderId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.out.println("Error update status transaksi: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    // UPDATE - Update seluruh data transaksi
    public boolean updateTransaksi(Transaksi transaksi) {
        String sql = "UPDATE orders SET jenis_layanan = ?, alamat = ?, nomor_hp = ?, " +
                    "catatan = ?, total_harga = ? WHERE order_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, transaksi.getJenisLayanan());
            stmt.setString(2, transaksi.getAlamat());
            stmt.setString(3, transaksi.getNomorHp());
            stmt.setString(4, transaksi.getCatatan());
            stmt.setBigDecimal(5, transaksi.getTotalHarga());
            stmt.setInt(6, transaksi.getOrderId());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.out.println("Error update transaksi: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    // DELETE - Hapus transaksi
    public boolean deleteTransaksi(int orderId) {
        // Cek apakah ada pembayaran terkait
        if (hasPembayaran(orderId)) {
            JOptionPane.showMessageDialog(null, 
                "Tidak dapat menghapus transaksi karena sudah ada data pembayaran!", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        String sql = "DELETE FROM orders WHERE order_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.out.println("Error delete transaksi: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    // Cek apakah transaksi sudah memiliki pembayaran
    private boolean hasPembayaran(int orderId) {
        String sql = "SELECT COUNT(*) FROM pembayaran WHERE order_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            System.out.println("Error check pembayaran: " + e.getMessage());
        }
        
        return false;
    }
    
    // Hitung total harga berdasarkan jenis layanan dan berat
    public BigDecimal hitungTotalHarga(String jenisLayanan, double berat) {
        BigDecimal hargaPerKg;
        
        switch (jenisLayanan.toLowerCase()) {
            case "cuci kering":
                hargaPerKg = new BigDecimal("6000");
                break;
            case "setrika":
                hargaPerKg = new BigDecimal("5000");
                break;
            case "cuci komplit":
                hargaPerKg = new BigDecimal("10000");
                break;
            default:
                hargaPerKg = new BigDecimal("0");
        }
        
        return hargaPerKg.multiply(BigDecimal.valueOf(berat));
    }
    
    // Daftar status yang available
    public String[] getDaftarStatus() {
        return new String[]{"Diterima", "Dicuci", "Dijemur", "Diantar", "Selesai"};
    }
    
    // Daftar jenis layanan
    public String[] getJenisLayanan() {
        return new String[]{"Cuci Kering", "Setrika", "Cuci Komplit"};
    }
    
    // Validasi transaksi
    public String validateTransaksi(Transaksi transaksi) {
        if (transaksi.getUserId() <= 0) {
            return "Pilih pelanggan terlebih dahulu";
        }
        if (transaksi.getJenisLayanan() == null || transaksi.getJenisLayanan().trim().isEmpty()) {
            return "Jenis layanan harus dipilih";
        }
        if (transaksi.getAlamat() == null || transaksi.getAlamat().trim().isEmpty()) {
            return "Alamat tidak boleh kosong";
        }
        if (transaksi.getNomorHp() == null || transaksi.getNomorHp().trim().isEmpty()) {
            return "Nomor HP tidak boleh kosong";
        }
        if (transaksi.getTotalHarga() == null || transaksi.getTotalHarga().compareTo(BigDecimal.ZERO) <= 0) {
            return "Total harga tidak valid";
        }
        return null;
    }
    
    // Search transaksi
    public List<Transaksi> searchTransaksi(String keyword) {
        List<Transaksi> listTransaksi = new ArrayList<>();
        String sql = "SELECT o.*, u.nama_lengkap FROM orders o " +
                    "JOIN users u ON o.user_id = u.user_id " +
                    "WHERE u.nama_lengkap ILIKE ? OR o.jenis_layanan ILIKE ? OR o.status_order ILIKE ? " +
                    "ORDER BY o.tanggal_order DESC";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            stmt.setString(3, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Transaksi transaksi = new Transaksi();
                transaksi.setOrderId(rs.getInt("order_id"));
                transaksi.setUserId(rs.getInt("user_id"));
                transaksi.setJenisLayanan(rs.getString("jenis_layanan"));
                transaksi.setAlamat(rs.getString("alamat"));
                transaksi.setNomorHp(rs.getString("nomor_hp"));
                transaksi.setCatatan(rs.getString("catatan"));
                transaksi.setTanggalOrder(rs.getTimestamp("tanggal_order"));
                transaksi.setStatusOrder(rs.getString("status_order"));
                transaksi.setTotalHarga(rs.getBigDecimal("total_harga"));
                transaksi.setNamaPelanggan(rs.getString("nama_lengkap"));
                listTransaksi.add(transaksi);
            }
            
        } catch (SQLException e) {
            System.out.println("Error search transaksi: " + e.getMessage());
        }
        
        return listTransaksi;
    }
}