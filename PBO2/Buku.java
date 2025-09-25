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
public class Buku {

    String judul;
    String penulis;

    public Buku(String judul, String penulis) {
        this.judul = judul;
        this.penulis = penulis;
    }

    public String getInfoBuku() {
        return "Judul: '" + judul + "', Penulis: " + penulis;
    }
}
