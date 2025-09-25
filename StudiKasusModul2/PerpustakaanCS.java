/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudiKasusModul2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DIAZ
 */
public class PerpustakaanCS {

    private final String namaPerpustakaan;
    // Agregasi: List dari objek Buku
    private final List<BukuCS> koleksiBuku;

    public PerpustakaanCS(String nama) {
        this.namaPerpustakaan = nama;
        this.koleksiBuku = new ArrayList<>();
    }

    // Method untuk menambahkan buku yang sudah ada ke dalam perpustakaan
    public void tambahBuku(BukuCS buku) {
        this.koleksiBuku.add(buku);
    }

    public void tampilkanKoleksi() {
        System.out.println("Daftar Buku di " + this.namaPerpustakaan + ":");
        for (BukuCS buku : this.koleksiBuku) {
            System.out.println("- " + buku);
        }
    }
}
