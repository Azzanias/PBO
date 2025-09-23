/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TugasException;

/**
 *
 * @author DIAZ
 */
public class ExcepTest_Finally {

    public static void main(String args[]) {
        try {
            int a[] = new int[2];
            System.out.println("Mencoba mengakses elemen tiga: " + a[3]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Exception thrown : " + e);
        } // Analisis:
        // Blok 'finally' akan selalu dieksekusi setelah 'try' atau 'catch' selesai.
        // Ini sangat berguna untuk membersihkan resource, seperti menutup file atau koneksi database,
        // karena kode di dalamnya dijamin berjalan[cite: 71, 78].
        finally {
            System.out.println("Blok finally dieksekusi.");
        }
        System.out.println("Selesai.");
    }
}
