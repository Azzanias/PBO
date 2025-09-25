/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Perpustakaan;

/**
 *
 * @author DIAZ
 */
public class Majalah extends ItemPerpustakaan {

    private int edisi;
    private int volume;

    public Majalah(String judul, int tahunTerbit, int edisi, int volume) {
        super(judul, tahunTerbit);
        this.edisi = edisi;
        this.volume = volume;
    }

    public void tampilkanInfo() {
        System.out.println("\n--- Info Majalah ---");
        super.tampilkanInfo();
        System.out.println("Edisi: " + edisi);
        System.out.println("Volume: " + volume);
    }
}
