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

        File file = new File("E://file.txt");
        try {
            FileReader fr = new FileReader(file);
            System.out.println("File berhasil dibaca.");
        } catch (FileNotFoundException e) {
            System.out.println("Gagal membaca file: " + e.getMessage());
        }
    }
}
