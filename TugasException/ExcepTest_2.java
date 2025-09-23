package TugasException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DIAZ
 */
public class ExcepTest_2 {

    public static void main(String args[]) {
        try {
            int a[] = new int[2];
            int b = 0;
            // Baris ini akan menyebabkan ArithmeticException (division by zero).
            int c = 1 / b;
            System.out.println("Access element three :" + a[3]);
        } // Analisis:
        // Urutan blok catch sangat penting[cite: 56].
        // Harus dimulai dari exception yang paling spesifik (turunan) ke yang paling umum (induk).
        // Jika 'catch (Exception e)' ditaruh di atas, akan terjadi compile error
        // karena blok catch di bawahnya tidak akan pernah tercapai[cite: 57].
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException thrown :" + e);
        } // Karena exception yang pertama terjadi adalah ArithmeticException,
        // blok catch inilah yang akan dieksekusi.
        catch (ArithmeticException e) {
            System.out.println("ArithmeticException thrown :" + e);
        } // Ini adalah catch block umum yang akan menangkap semua jenis exception lain
        // yang tidak tertangkap oleh blok catch di atasnya.
        catch (Exception e) {
            System.out.println("Exception thrown :" + e);
        }
        System.out.println("Keluar dari blok.");
    }
}
