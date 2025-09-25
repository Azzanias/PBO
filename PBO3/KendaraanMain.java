/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PBO3;

import java.util.Scanner;

/**
 *
 * @author DIAZ
 */
public class KendaraanMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Kendaraan kendaraan = null;

        System.out.println("Pilih jenis kendaraan:");
        System.out.println("1. Mobil");
        System.out.println("2. Motor");
        System.out.print("Masukkan pilihan Anda (1 atau 2): ");
        int pilihan = scanner.nextInt();

        switch (pilihan) {
            case 1:
                kendaraan = new Mobil(); // Polimorfisme
                break;
            case 2:
                kendaraan = new Motor(); // Polimorfisme
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                scanner.close();
                return;
        }

        // Memanggil metode pada objek yang dipilih
        kendaraan.nyalakanMesin();
        kendaraan.matikanMesin();

        scanner.close();
    }
}
