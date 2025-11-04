package controller;

import model.Pelanggan;
import database.DBConnection;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PelangganController {
    private Connection connection;
    
    public PelangganController() {
        this.connection = DBConnection.getConnection();
    }
    
    // CREATE - Tambah pelanggan baru
    public boolean createPelanggan(Pelanggan pelanggan) {
        String sql = "INSERT INTO users (nama_lengkap, email, password) VALUES (?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pelanggan.getNamaLengkap());
            stmt.setString(2, pelanggan.getEmail());
            stmt.setString(3, pelanggan.getPassword());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.out.println("Error create pelanggan: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    // READ - Ambil semua pelanggan
    public List<Pelanggan> getAllPelanggan() {
        List<Pelanggan> listPelanggan = new ArrayList<>();
        String sql = "SELECT * FROM users ORDER BY nama_lengkap";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Pelanggan pelanggan = new Pelanggan(
                    rs.getInt("user_id"),
                    rs.getString("nama_lengkap"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("foto_profil")
                );
                listPelanggan.add(pelanggan);
            }
            
        } catch (SQLException e) {
            System.out.println("Error get all pelanggan: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return listPelanggan;
    }
    
    // READ - Ambil pelanggan berdasarkan ID
    public Pelanggan getPelangganById(int id) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Pelanggan(
                    rs.getInt("user_id"),
                    rs.getString("nama_lengkap"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("foto_profil")
                );
            }
            
        } catch (SQLException e) {
            System.out.println("Error get pelanggan by id: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return null;
    }
    
    // READ - Ambil pelanggan berdasarkan email
    public Pelanggan getPelangganByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Pelanggan(
                    rs.getInt("user_id"),
                    rs.getString("nama_lengkap"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("foto_profil")
                );
            }
            
        } catch (SQLException e) {
            System.out.println("Error get pelanggan by email: " + e.getMessage());
        }
        
        return null;
    }
    
    // UPDATE - Perbarui data pelanggan
    public boolean updatePelanggan(Pelanggan pelanggan) {
        String sql = "UPDATE users SET nama_lengkap = ?, email = ?, password = ?, foto_profil = ? WHERE user_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pelanggan.getNamaLengkap());
            stmt.setString(2, pelanggan.getEmail());
            stmt.setString(3, pelanggan.getPassword());
            stmt.setString(4, pelanggan.getFotoProfil());
            stmt.setInt(5, pelanggan.getUserId());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.out.println("Error update pelanggan: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    // DELETE - Hapus data pelanggan
    public boolean deletePelanggan(int id) {
        // Cek apakah pelanggan memiliki transaksi
        if (hasTransactions(id)) {
            JOptionPane.showMessageDialog(null, 
                "Tidak dapat menghapus pelanggan karena memiliki transaksi history!", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        String sql = "DELETE FROM users WHERE user_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.out.println("Error delete pelanggan: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    // Cek apakah pelanggan memiliki transaksi
    private boolean hasTransactions(int userId) {
        String sql = "SELECT COUNT(*) FROM orders WHERE user_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            System.out.println("Error check transactions: " + e.getMessage());
        }
        
        return false;
    }
    
    // Validasi input data pelanggan
    public String validatePelanggan(Pelanggan pelanggan) {
        if (pelanggan.getNamaLengkap() == null || pelanggan.getNamaLengkap().trim().isEmpty()) {
            return "Nama lengkap tidak boleh kosong";
        }
        if (pelanggan.getEmail() == null || pelanggan.getEmail().trim().isEmpty()) {
            return "Email tidak boleh kosong";
        }
        if (pelanggan.getPassword() == null || pelanggan.getPassword().trim().isEmpty()) {
            return "Password tidak boleh kosong";
        }
        if (!isValidEmail(pelanggan.getEmail())) {
            return "Format email tidak valid";
        }
        if (isEmailExists(pelanggan.getEmail(), pelanggan.getUserId())) {
            return "Email sudah terdaftar";
        }
        return null; // Tidak ada error
    }
    
    // Validasi format email
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }
    
    // Cek apakah email sudah ada (untuk create dan update)
    private boolean isEmailExists(String email, int currentUserId) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ? AND user_id != ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setInt(2, currentUserId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            System.out.println("Error check email exists: " + e.getMessage());
        }
        
        return false;
    }
    
    // Search pelanggan by nama atau email
    public List<Pelanggan> searchPelanggan(String keyword) {
        List<Pelanggan> listPelanggan = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE nama_lengkap ILIKE ? OR email ILIKE ? ORDER BY nama_lengkap";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Pelanggan pelanggan = new Pelanggan(
                    rs.getInt("user_id"),
                    rs.getString("nama_lengkap"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("foto_profil")
                );
                listPelanggan.add(pelanggan);
            }
            
        } catch (SQLException e) {
            System.out.println("Error search pelanggan: " + e.getMessage());
        }
        
        return listPelanggan;
    }
}