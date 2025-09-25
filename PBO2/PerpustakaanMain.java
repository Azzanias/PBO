/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PBO2;

/**
 *
 * @author DIAZ
 */
public class PerpustakaanMain {

    public static void main(String[] args) {
        // Membuat objek Buku secara mandiri
        Buku buku1 = new Buku("Laskar Pelangi", "Andrea Hirata");
        Buku buku2 = new Buku("Bumi Manusia", "Pramoedya Ananta Toer");
        Buku buku3 = new Buku("Cantik Itu Luka", "Eka Kurniawan");

        // Membuat objek Perpustakaan
        Perpustakaan perpusKota = new Perpustakaan("Perpustakaan Kota Surabaya");

        // Menambahkan buku ke perpustakaan
        perpusKota.tambahBuku(buku1);
        perpusKota.tambahBuku(buku2);
        perpusKota.tambahBuku(buku3);

        // Menampilkan daftar buku di perpustakaan
        perpusKota.tampilkanSemuaBuku();
    }
}
