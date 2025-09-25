/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PBO2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DIAZ
 */
public class Perpustakaan {

    private final String namaPerpustakaan;
    private final List<Buku> daftarBuku; // Agregasi: Perpustakaan HAS-A List of Buku

    public Perpustakaan(String nama) {
        this.namaPerpustakaan = nama;
        this.daftarBuku = new ArrayList<>();
    }

    public void tambahBuku(Buku buku) {
        daftarBuku.add(buku);
        System.out.println("Buku '" + buku.judul + "' telah ditambahkan ke " + this.namaPerpustakaan);
    }

    public void tampilkanSemuaBuku() {
        System.out.println("\n--- Daftar Buku di " + this.namaPerpustakaan + " ---");
        if (daftarBuku.isEmpty()) {
            System.out.println("Tidak ada buku di perpustakaan.");
        } else {
            for (Buku buku : daftarBuku) {
                System.out.println("- " + buku.getInfoBuku());
            }
        }
    }
}
