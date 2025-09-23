/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TugasException;

import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;

/**
 *
 * @author DIAZ
 */
public class FileNotFound_Demo {

    public static void main(String args[]) {
        // Analisis:
        // Baris kode di bawah ini mencoba membuat objek File.
        File file = new File("E://file.txt");

        // Analisis:
        // 'FileReader' adalah sebuah 'checked exception'. Artinya, compiler Java
        // akan memeriksa dan memaksa programmer untuk menangani potensi error
        // (seperti file tidak ditemukan) saat kompilasi.
        // Jika tidak ditangani dengan 'try-catch' atau 'throws', program akan error saat kompilasi.
        // Pesan errornya adalah "unreported exception FileNotFoundException; must be caught or declared to be thrown".
        try {
            FileReader fr = new FileReader(file);
            System.out.println("File berhasil dibaca.");
        } catch (FileNotFoundException e) {
            // Blok ini akan dieksekusi jika file tidak ditemukan,
            // sehingga program tidak crash dan memberikan pesan yang jelas.
            System.out.println("Gagal membaca file: " + e.getMessage());
        }
    }
}
