/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TugasException;

/**
 *
 * @author DIAZ
 */
public class ExceptTest_Finally_2 {

    public static void main(String args[]) {
        System.out.println(testFinallyBlock());
    }

    private static int testFinallyBlock() {
        int a[] = new int[2];
        try {
            return 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Exception thrown :" + e);
        } finally {
            a[0] = 6;
            System.out.println("First element value: " + a[0]);
            System.out.println("The finally statement is executed");
        }
        return 0;
    }
}
