/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudiKasusModul2;

/**
 *
 * @author DIAZ
 */
public class KendaraanCS {

    String merek;
    int tahunProduksi;

    public KendaraanCS(String merek, int tahunProduksi) {
        this.merek = merek;
        this.tahunProduksi = tahunProduksi;
    }

    public void info() {
        System.out.println("Merek: " + this.merek);
        System.out.println("Tahun Produksi: " + this.tahunProduksi);
    }
}

/**
 * Subclass Mobil yang mewarisi Kendaraan. Mobil IS-A Kendaraan.
 */
class Mobil extends KendaraanCS {

    int jumlahPintu;

    public Mobil(String merek, int tahunProduksi, int jumlahPintu) {
        // Memanggil constructor dari superclass (Kendaraan)
        super(merek, tahunProduksi);
        this.jumlahPintu = jumlahPintu;
    }

    // Overriding method info() dari superclass
    @Override
    public void info() {
        super.info(); // Memanggil method info() milik superclass
        System.out.println("Jumlah Pintu: " + this.jumlahPintu);
    }
}

/**
 * Subclass Motor yang mewarisi Kendaraan. Motor IS-A Kendaraan.
 */
class Motor extends KendaraanCS {

    String tipeMesin;

    public Motor(String merek, int tahunProduksi, String tipeMesin) {
        super(merek, tahunProduksi);
        this.tipeMesin = tipeMesin;
    }

    // Overriding method info() dari superclass
    @Override
    public void info() {
        super.info(); // Memanggil method info() milik superclass
        System.out.println("Tipe Mesin: " + this.tipeMesin);
    }
}
