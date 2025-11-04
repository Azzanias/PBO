package controller;

import model.Pembayaran;
import database.DBConnection;

import javax.swing.*;
import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PembayaranController {
    private Connection connection;
    
    public PembayaranController() {
        this.connection = DBConnection.getConnection();
    }
    
    // CREATE - Tambah pembayaran baru
    public boolean createPembayaran(Pembayaran pembayaran) {
        String sql = "INSERT INTO pembayaran (order_id, metode, total_bayar, status_bayar) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pembayaran.getOrderId());
            stmt.setString(2, pembayaran.getMetode());
            stmt.setBigDecimal(3, pembayaran.getTotalBayar());
            stmt.setString(4, pembayaran.getStatusBayar());
            
            int result = stmt.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            showError("Error membuat pembayaran: " + e.getMessage());
            return false;
        }
    }
    
    // READ - Ambil semua pembayaran
    public List<Pembayaran> getAllPembayaran() {
        List<Pembayaran> list = new ArrayList<>();
        String sql = "SELECT * FROM pembayaran ORDER BY pembayaran_id DESC";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Pembayaran p = new Pembayaran();
                p.setPembayaranId(rs.getInt("pembayaran_id"));
                p.setOrderId(rs.getInt("order_id"));
                p.setMetode(rs.getString("metode"));
                p.setTotalBayar(rs.getBigDecimal("total_bayar"));
                p.setStatusBayar(rs.getString("status_bayar"));
                p.setTanggalBayar(rs.getTimestamp("tanggal_bayar"));
                list.add(p);
            }
            
        } catch (SQLException e) {
            showError("Error mengambil data pembayaran: " + e.getMessage());
        }
        return list;
    }
    
    // READ - Ambil pembayaran by ID
    public Pembayaran getPembayaranById(int id) {
        String sql = "SELECT * FROM pembayaran WHERE pembayaran_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Pembayaran p = new Pembayaran();
                p.setPembayaranId(rs.getInt("pembayaran_id"));
                p.setOrderId(rs.getInt("order_id"));
                p.setMetode(rs.getString("metode"));
                p.setTotalBayar(rs.getBigDecimal("total_bayar"));
                p.setStatusBayar(rs.getString("status_bayar"));
                p.setTanggalBayar(rs.getTimestamp("tanggal_bayar"));
                return p;
            }
            
        } catch (SQLException e) {
            showError("Error mengambil pembayaran: " + e.getMessage());
        }
        return null;
    }
    
    // UPDATE - Update status pembayaran
    public boolean updateStatusPembayaran(int id, String newStatus) {
        String sql = "UPDATE pembayaran SET status_bayar = ? WHERE pembayaran_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newStatus);
            stmt.setInt(2, id);
            
            // Jika status Lunas, set tanggal bayar
            if ("Lunas".equals(newStatus)) {
                String updateDateSql = "UPDATE pembayaran SET tanggal_bayar = CURRENT_TIMESTAMP WHERE pembayaran_id = ?";
                try (PreparedStatement dateStmt = connection.prepareStatement(updateDateSql)) {
                    dateStmt.setInt(1, id);
                    dateStmt.executeUpdate();
                }
            }
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            showError("Error update status pembayaran: " + e.getMessage());
            return false;
        }
    }
    
    // DELETE - Hapus pembayaran
    public boolean deletePembayaran(int id) {
        String sql = "DELETE FROM pembayaran WHERE pembayaran_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            showError("Error menghapus pembayaran: " + e.getMessage());
            return false;
        }
    }
    
    // Cek apakah order sudah punya pembayaran
    public boolean isOrderHasPayment(int orderId) {
        String sql = "SELECT COUNT(*) FROM pembayaran WHERE order_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            showError("Error cek pembayaran order: " + e.getMessage());
        }
        return false;
    }
    
    // Get available orders for payment (orders without payment)
    public List<String> getAvailableOrders() {
        List<String> orders = new ArrayList<>();
        String sql = "SELECT o.order_id, u.nama_lengkap, o.total_harga " +
                    "FROM orders o " +
                    "JOIN users u ON o.user_id = u.user_id " +
                    "WHERE o.order_id NOT IN (SELECT order_id FROM pembayaran) " +
                    "ORDER BY o.order_id";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                String orderInfo = String.format("Order #%d - %s - Rp %s", 
                    rs.getInt("order_id"), 
                    rs.getString("nama_lengkap"), 
                    rs.getBigDecimal("total_harga"));
                orders.add(orderInfo);
            }
            
        } catch (SQLException e) {
            showError("Error mengambil order tersedia: " + e.getMessage());
        }
        return orders;
    }
    
    // Validasi
    public String validatePembayaran(int orderId, String metode, BigDecimal total) {
        if (orderId <= 0) return "Pilih order yang valid";
        if (metode == null || metode.trim().isEmpty()) return "Pilih metode pembayaran";
        if (total == null || total.compareTo(BigDecimal.ZERO) <= 0) return "Total bayar harus lebih dari 0";
        if (isOrderHasPayment(orderId)) return "Order ini sudah memiliki pembayaran";
        return null;
    }
    
    // Helper methods
    public String[] getMetodePembayaran() {
        return new String[]{"Tunai", "Transfer", "QRIS"};
    }
    
    public String[] getStatusPembayaran() {
        return new String[]{"Belum Dibayar", "Lunas"};
    }
    
    private void showError(String message) {
        System.err.println("PembayaranController Error: " + message);
        JOptionPane.showMessageDialog(null, message, "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}