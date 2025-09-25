/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TugasException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author DIAZ
 */
public class FileNot_Found {

    public static void main(String[] args) {
        try {

            FileReader fr = new FileReader("file_yang_tidak_ada.txt");
            BufferedReader br = new BufferedReader(fr);
            System.out.println("File berhasil dibuka.");
            br.close();
        } catch (FileNotFoundException e) {

            System.err.println("Error: File tidak ditemukan.");
            System.err.println("Detail: " + e.getMessage());
        } catch (IOException e) {

            System.err.println("Error saat membaca file: " + e.getMessage());
        }
    }
}
