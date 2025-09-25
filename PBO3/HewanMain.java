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
public class HewanMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Array Hewan diperluas untuk menyertakan Burung
        Hewan[] hewanArray = new Hewan[3];
        hewanArray[0] = new Anjing();
        hewanArray[1] = new Kucing();
        hewanArray[2] = new Burung(); // Objek Burung ditambahkan

        System.out.println("Pilih hewan dari 0 hingga 2 (0:Anjing, 1:Kucing, 2:Burung):");
        int pilihan = scanner.nextInt();

        if (pilihan >= 0 && pilihan < hewanArray.length) {
            // Memanggil metode suara secara polimorfik
            hewanArray[pilihan].suara();
        } else {
            System.out.println("Pilihan tidak valid.");
        }

        scanner.close();
    }
}
