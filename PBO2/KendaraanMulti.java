/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PBO2;

/**
 *
 * @author DIAZ
 */
public class KendaraanMulti {

    String merek;

    void info() {
        System.out.println("Ini adalah kendaraan.");
    }
}

class Mobil extends KendaraanMulti {

    int jumlahPintu;

    void jenis() {
        System.out.println("Ini adalah mobil.");
    }
}

class MobilListrik extends Mobil {

    int kapasitasBaterai; // Atribut spesifik

    void teknologi() {
        System.out.println("Ini adalah mobil listrik.");
    }
}
