package Controller;

import database.DBConnection;
import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Laporan;

public class LaporanController {
    private Connection connection;
    
    public LaporanController() {
        this.connection = DBConnection.getConnection();
    }
    
    // Laporan Harian - SANGAT SEDERHANA
    public List<Laporan> getLaporanHarian(String tanggal) {
        List<Laporan> list = new ArrayList<>();
        String sql = "SELECT DATE(tanggal_order) as hari, COUNT(*) as total, COALESCE(SUM(total_harga),0) as pendapatan " +
                    "FROM orders " +
                    "WHERE DATE(tanggal_order) = ? " +
                    "GROUP BY DATE(tanggal_order)";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, java.sql.Date.valueOf(tanggal));
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Laporan laporan = new Laporan(
                    rs.getDate("hari").toString(),
                    rs.getInt("total"),
                    rs.getBigDecimal("pendapatan")
                );
                list.add(laporan);
            }
            
            rs.close();
            stmt.close();
            
        } catch (Exception e) {
            System.out.println("Error laporan harian: " + e.getMessage());
        }
        return list;
    }
    
    // Laporan Bulanan - SANGAT SEDERHANA
    public List<Laporan> getLaporanBulanan(String tahun, String bulan) {
        List<Laporan> list = new ArrayList<>();
        String sql = "SELECT TO_CHAR(tanggal_order, 'YYYY-MM-DD') as hari, COUNT(*) as total, COALESCE(SUM(total_harga),0) as pendapatan " +
                    "FROM orders " +
                    "WHERE EXTRACT(YEAR FROM tanggal_order) = ? AND EXTRACT(MONTH FROM tanggal_order) = ? " +
                    "GROUP BY TO_CHAR(tanggal_order, 'YYYY-MM-DD')";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(tahun));
            stmt.setInt(2, Integer.parseInt(bulan));
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Laporan laporan = new Laporan(
                    rs.getString("hari"),
                    rs.getInt("total"),
                    rs.getBigDecimal("pendapatan")
                );
                list.add(laporan);
            }
            
            rs.close();
            stmt.close();
            
        } catch (Exception e) {
            System.out.println("Error laporan bulanan: " + e.getMessage());
        }
        return list;
    }
    
    // Statistik Sederhana
    public String getStatistikSederhana() {
        StringBuilder stat = new StringBuilder();
        
        try {
            // Total transaksi
            String sql1 = "SELECT COUNT(*) as total FROM orders";
            Statement stmt1 = connection.createStatement();
            ResultSet rs1 = stmt1.executeQuery(sql1);
            if (rs1.next()) {
                stat.append("Total Transaksi: ").append(rs1.getInt("total")).append("\n");
            }
            rs1.close();
            stmt1.close();
            
            // Total pendapatan
            String sql2 = "SELECT COALESCE(SUM(total_harga),0) as total FROM orders";
            Statement stmt2 = connection.createStatement();
            ResultSet rs2 = stmt2.executeQuery(sql2);
            if (rs2.next()) {
                stat.append("Total Pendapatan: Rp ").append(rs2.getBigDecimal("total")).append("\n");
            }
            rs2.close();
            stmt2.close();
            
            // Transaksi hari ini
            String sql3 = "SELECT COUNT(*) as total FROM orders WHERE DATE(tanggal_order) = CURRENT_DATE";
            Statement stmt3 = connection.createStatement();
            ResultSet rs3 = stmt3.executeQuery(sql3);
            if (rs3.next()) {
                stat.append("Transaksi Hari Ini: ").append(rs3.getInt("total"));
            }
            rs3.close();
            stmt3.close();
            
        } catch (Exception e) {
            System.out.println("Error statistik: " + e.getMessage());
            return "Error mengambil statistik";
        }
        
        return stat.toString();
    }
}