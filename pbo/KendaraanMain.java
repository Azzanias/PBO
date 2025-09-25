/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbo;

/**
 *
 * @author DIAZ
 */
public class KendaraanMain {

    public static void main(String[] args) {
        Mobil mobilSaya = new Mobil("Toyota", 2022, 4);
        Motor motorSaya = new Motor("Honda", 2023, "150cc");

        System.out.println("--- Info Kendaraan ---");
        mobilSaya.info();
        System.out.println();
        motorSaya.info();
    }
}
