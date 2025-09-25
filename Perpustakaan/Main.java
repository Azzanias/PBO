/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Perpustakaan;

import java.util.ArrayList;

/**
 *
 * @author DIAZ
 */
public class Main {

    public static void main(String[] args) {
        // Menggunakan ArrayList untuk menyimpan berbagai jenis item (Polimorfisme)
        ArrayList<ItemPerpustakaan> daftarItem = new ArrayList<>();

        // Menambahkan berbagai item ke dalam perpustakaan
        daftarItem.add(new Buku("Pemrograman Java", 2023, "John Doe", 450));
        daftarItem.add(new Majalah("National Geographic", 2024, 5, 120));
        daftarItem.add(new DVD("The Matrix", 1999, 136, "Sci-Fi"));

        // Menampilkan informasi dari semua item
        System.out.println("=====================================");
        System.out.println("       DAFTAR ITEM PERPUSTAKAAN      ");
        System.out.println("=====================================");
        for (ItemPerpustakaan item : daftarItem) {
            item.tampilkanInfo();
        }
    }
}
