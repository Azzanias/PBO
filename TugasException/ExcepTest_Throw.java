/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TugasException;

/**
 *
 * @author DIAZ
 */
public class ExcepTest_Throw {
    // Analisis:
    // Keyword 'throws Exception' pada method signature menandakan bahwa method ini
    // mungkin akan melempar sebuah checked exception, dan tidak menanganinya sendiri[cite: 82].
    // Penanganan exception ini didelegasikan ke method yang memanggilnya.

    private static int divide(int a, int b) throws ArithmeticException {
        // Analisis:
        // Keyword 'throw' digunakan untuk secara eksplisit membuat dan melempar
        // sebuah objek exception baru[cite: 85].
        // Di sini, kita secara manual melempar exception jika pembaginya adalah nol.
        if (b == 0) {
            throw new ArithmeticException("Argumen kedua tidak boleh nol.");
        }
        return a / b;
    }

    public static void main(String args[]) {
        int a = 3;
        int b = 0;

        try {
            // Memanggil method yang berpotensi melempar exception.
            System.out.println("hasil: " + divide(a, b));
        } catch (Exception e) {
            // Menangkap exception yang dilempar dari method 'divide'.
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
