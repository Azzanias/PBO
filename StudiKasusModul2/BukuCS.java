/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudiKasusModul2;

/**
 *
 * @author DIAZ
 */
public class BukuCS {

    String judul;
    String penulis;

    public BukuCS(String judul, String penulis) {
        this.judul = judul;
        this.penulis = penulis;
    }

    @Override
    public String toString() {
        return "Buku [Judul: " + judul + ", Penulis: " + penulis + "]";
    }
}
