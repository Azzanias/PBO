/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TugasException;

/**
 *
 * @author DIAZ
 */
public class CaseStudy {

    public static void main(String[] args) {
        try {
            int a[] = new int[5];
            System.out.println("Mencoba mengisi nilai pada indeks ke-5...");
            
            a[5] = 100;
            System.out.println("Nilai berhasil diisi.");
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Terjadi error : Indeks array di luar batas!");
            System.out.println("Pesan error sistem: " + e.getMessage());
        }
        
        System.out.println("Program selesai dan tidak crash");

    }
}
