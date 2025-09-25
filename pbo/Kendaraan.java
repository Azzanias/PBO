/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbo;

/**
 *
 * @author DIAZ
 */
public class Kendaraan {

    String merek;
    int tahunProduksi;

    public Kendaraan(String merek, int tahunProduksi) {
        this.merek = merek;
        this.tahunProduksi = tahunProduksi;
    }

    void info() {
        System.out.println("Merek: " + merek + ", Tahun: " + tahunProduksi);
    }
}

class Mobil extends Kendaraan {

    int jumlahPintu;

    public Mobil(String merek, int tahunProduksi, int jumlahPintu) {
        super(merek, tahunProduksi); // Memanggil constructor superclass
        this.jumlahPintu = jumlahPintu;
    }

    void info() { // Method Overriding
        super.info();
        System.out.println("Jenis: Mobil, Jumlah Pintu: " + jumlahPintu);
    }
}

class Motor extends Kendaraan {

    String tipeMesin;

    public Motor(String merek, int tahunProduksi, String tipeMesin) {
        super(merek, tahunProduksi);
        this.tipeMesin = tipeMesin;
    }

    void info() { // Method Overriding
        super.info();
        System.out.println("Jenis: Motor, Tipe Mesin: " + tipeMesin);
    }
}
