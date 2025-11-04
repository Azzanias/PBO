package model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Pembayaran {
    private int pembayaranId;
    private int orderId;
    private String metode;
    private BigDecimal totalBayar;
    private String statusBayar;
    private Timestamp tanggalBayar;
    
    // Constructors
    public Pembayaran() {}
    
    public Pembayaran(int orderId, String metode, BigDecimal totalBayar) {
        this.orderId = orderId;
        this.metode = metode;
        this.totalBayar = totalBayar;
        this.statusBayar = "Belum Dibayar";
    }
    
    // Getters and Setters
    public int getPembayaranId() { return pembayaranId; }
    public void setPembayaranId(int pembayaranId) { this.pembayaranId = pembayaranId; }
    
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    
    public String getMetode() { return metode; }
    public void setMetode(String metode) { this.metode = metode; }
    
    public BigDecimal getTotalBayar() { return totalBayar; }
    public void setTotalBayar(BigDecimal totalBayar) { this.totalBayar = totalBayar; }
    
    public String getStatusBayar() { return statusBayar; }
    public void setStatusBayar(String statusBayar) { this.statusBayar = statusBayar; }
    
    public Timestamp getTanggalBayar() { return tanggalBayar; }
    public void setTanggalBayar(Timestamp tanggalBayar) { this.tanggalBayar = tanggalBayar; }
    
    @Override
    public String toString() {
        return "Pembayaran{id=" + pembayaranId + ", order=" + orderId + ", metode=" + metode + "}";
    }
}