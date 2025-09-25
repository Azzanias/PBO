/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudiKasusModul2;

/**
 *
 * @author DIAZ
 */
public class KendaraanMainCS {

    public static void main(String[] args) {
        System.out.println("--- Informasi Mobil ---");
        Mobil mobilSaya = new Mobil("Toyota Avanza", 2023, 4);
        mobilSaya.info();

        System.out.println("\n--- Informasi Motor ---");
        Motor motorSaya = new Motor("Honda PCX", 2024, "160cc");
        motorSaya.info();
    }
}
