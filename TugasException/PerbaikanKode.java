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
public class PerbaikanKode {

    public static void main(String[] args) {

        try (FileReader fr = new FileReader("data.txt"); BufferedReader br = new BufferedReader(fr)) {

            String line = br.readLine();
            if (line != null) {
                int angka = Integer.parseInt(line);
                System.out.println("Hasil: " + (10 / angka));
            } else {
                System.out.println("File kosong.");
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: File 'data.txt' tidak dapat ditemukan.");
        } catch (IOException e) {
            System.err.println("Error saat membaca file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error: Isi file bukan merupakan angka yang valid.");
        } catch (ArithmeticException e) {
            System.err.println("Error: Tidak bisa melakukan pembagian dengan nol.");
        } catch (Exception e) {
            System.err.println("Terjadi kesalahan yang tidak terduga: " + e.getMessage());
        }
    }
}
