/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PBO2;

/**
 *
 * @author DIAZ
 */
public class KendaraanMainMulti {

    public static void main(String[] args) {
        MobilListrik ev = new MobilListrik();
        ev.merek = "Tesla"; // Mewarisi dari Kendaraan
        ev.jumlahPintu = 4; // Mewarisi dari Mobil
        ev.kapasitasBaterai = 75; // Milik sendiri

        ev.info(); // Method dari Kendaraan
        ev.jenis(); // Method dari Mobil
        ev.teknologi(); // Method sendiri
        System.out.println("Merek: " + ev.merek);
    }
}
