/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Perpustakaan;

/**
 *
 * @author DIAZ
 */
public class DVD extends ItemPerpustakaan {

    private int durasi; // dalam menit
    private String genre;

    public DVD(String judul, int tahunTerbit, int durasi, String genre) {
        super(judul, tahunTerbit);
        this.durasi = durasi;
        this.genre = genre;
    }

    public void tampilkanInfo() {
        System.out.println("\n--- Info DVD ---");
        super.tampilkanInfo();
        System.out.println("Durasi: " + durasi + " menit");
        System.out.println("Genre: " + genre);
    }
}
