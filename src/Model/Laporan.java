package model;

import java.math.BigDecimal;

public class Laporan {
    private String periode;
    private int totalTransaksi;
    private BigDecimal totalPendapatan;
    
    public Laporan() {}
    
    public Laporan(String periode, int totalTransaksi, BigDecimal totalPendapatan) {
        this.periode = periode;
        this.totalTransaksi = totalTransaksi;
        this.totalPendapatan = totalPendapatan;
    }
    
    // Getter dan Setter sederhana
    public String getPeriode() { return periode; }
    public void setPeriode(String periode) { this.periode = periode; }
    
    public int getTotalTransaksi() { return totalTransaksi; }
    public void setTotalTransaksi(int totalTransaksi) { this.totalTransaksi = totalTransaksi; }
    
    public BigDecimal getTotalPendapatan() { return totalPendapatan; }
    public void setTotalPendapatan(BigDecimal totalPendapatan) { this.totalPendapatan = totalPendapatan; }
}