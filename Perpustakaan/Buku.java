/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Perpustakaan;

/**
 *
 * @author DIAZ
 */
public class Buku extends ItemPerpustakaan {

    private String pengarang;
    private int jumlahHalaman;

    public Buku(String judul, int tahunTerbit, String pengarang, int jumlahHalaman) {
        super(judul, tahunTerbit);
        this.pengarang = pengarang;
        this.jumlahHalaman = jumlahHalaman;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("\n--- Info Buku ---");
        super.tampilkanInfo();
        System.out.println("Pengarang: " + pengarang);
        System.out.println("Jumlah Halaman: " + jumlahHalaman);
    }
}
