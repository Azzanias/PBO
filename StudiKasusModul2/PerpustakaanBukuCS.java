/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudiKasusModul2;

/**
 *
 * @author DIAZ
 */
public class PerpustakaanBukuCS {

    public static void main(String[] args) {

        BukuCS buku1 = new BukuCS("Bumi Manusia", "Pramoedya Ananta Toer");
        BukuCS buku2 = new BukuCS("Laskar Pelangi", "Andrea Hirata");

        PerpustakaanCS perpusNasional = new PerpustakaanCS("Perpustakaan Nasional");

        perpusNasional.tambahBuku(buku1);
        perpusNasional.tambahBuku(buku2);

        perpusNasional.tampilkanKoleksi();
    }
}
