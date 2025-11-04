package model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transaksi {
    private int orderId;
    private int userId;
    private String jenisLayanan;
    private String alamat;
    private String nomorHp;
    private String catatan;
    private Timestamp tanggalOrder;
    private String statusOrder;
    private BigDecimal totalHarga;
    private String namaPelanggan; // Untuk display
    
    // Constructors
    public Transaksi() {}
    
    public Transaksi(int userId, String jenisLayanan, String alamat, String nomorHp, 
                    String catatan, BigDecimal totalHarga) {
        this.userId = userId;
        this.jenisLayanan = jenisLayanan;
        this.alamat = alamat;
        this.nomorHp = nomorHp;
        this.catatan = catatan;
        this.totalHarga = totalHarga;
        this.statusOrder = "Diterima";
    }
    
    // Getters and Setters
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    
    public String getJenisLayanan() { return jenisLayanan; }
    public void setJenisLayanan(String jenisLayanan) { this.jenisLayanan = jenisLayanan; }
    
    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }
    
    public String getNomorHp() { return nomorHp; }
    public void setNomorHp(String nomorHp) { this.nomorHp = nomorHp; }
    
    public String getCatatan() { return catatan; }
    public void setCatatan(String catatan) { this.catatan = catatan; }
    
    public Timestamp getTanggalOrder() { return tanggalOrder; }
    public void setTanggalOrder(Timestamp tanggalOrder) { this.tanggalOrder = tanggalOrder; }
    
    public String getStatusOrder() { return statusOrder; }
    public void setStatusOrder(String statusOrder) { this.statusOrder = statusOrder; }
    
    public BigDecimal getTotalHarga() { return totalHarga; }
    public void setTotalHarga(BigDecimal totalHarga) { this.totalHarga = totalHarga; }
    
    public String getNamaPelanggan() { return namaPelanggan; }
    public void setNamaPelanggan(String namaPelanggan) { this.namaPelanggan = namaPelanggan; }
    
    @Override
    public String toString() {
        return "Transaksi{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", jenisLayanan='" + jenisLayanan + '\'' +
                ", statusOrder='" + statusOrder + '\'' +
                ", totalHarga=" + totalHarga +
                '}';
    }
}