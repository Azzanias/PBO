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

    private static int divide(int a, int b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Argumen kedua tidak boleh nol.");
        }
        return a / b;
    }

    public static void main(String args[]) {
        int a = 3;
        int b = 0;

        try {
            System.out.println("hasil: " + divide(a, b));
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
