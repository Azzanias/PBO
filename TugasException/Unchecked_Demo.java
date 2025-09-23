/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TugasException;

/**
 *
 * @author DIAZ
 */
public class Unchecked_Demo {

    public static void main(String args[]) {
        // Analisis:
        // Array 'num' dideklarasikan dengan 4 elemen (indeks 0 sampai 3).
        int num[] = {1, 2, 3, 4};

        // Analisis:
        // Kode ini mencoba mengakses elemen ke-5 (indeks 5) dari array 'num'.
        // Karena indeks tertinggi yang valid adalah 3, maka akan terjadi error saat program berjalan.
        // Error ini disebut 'ArrayIndexOutOfBoundsException', yang merupakan 'unchecked exception'.
        // Compiler tidak akan mendeteksi error ini, tapi program akan berhenti saat dieksekusi.
        System.out.println(num[5]);
    }
}
