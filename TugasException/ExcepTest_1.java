/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TugasException;

/**
 *
 * @author DIAZ
 */
public class ExcepTest_1 {

    public static void main(String args[]) {
        // Analisis:
        // Kode yang berpotensi menimbulkan exception ditempatkan di dalam blok 'try'[cite: 45].
        try {
            int a[] = new int[2]; // Array dengan 2 elemen (indeks 0 dan 1).
            System.out.println("Mencoba mengakses elemen ketiga...");
            // Baris ini akan menyebabkan ArrayIndexOutOfBoundsException.
            System.out.println("Access element three :" + a[3]);
        } // Analisis:
        // Blok 'catch' akan menangkap exception yang cocok dengan tipenya[cite: 49].
        // Dalam kasus ini, ia menangkap 'ArrayIndexOutOfBoundsException'.
        catch (ArrayIndexOutOfBoundsException e) {
            // Kode di dalam blok ini dieksekusi sebagai respons terhadap exception.
            System.out.println("Exception thrown :" + e);
        }
        // Kode ini akan tetap dieksekusi karena exception sudah ditangani.
        System.out.println("Keluar dari blok try-catch.");
    }
}
