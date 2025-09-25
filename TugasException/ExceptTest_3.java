/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TugasException;

/**
 *
 * @author DIAZ
 */
public class ExceptTest_3 {

    public static void main(String args[]) {
        try {
            int a[] = new int[2];
            try {
                int b = 0;
                int c = 1 / b;
            } catch (Exception e) {
                System.out.println("Exception thrown :" + e);
            }

            System.out.println("Access element three :" + a[3]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Exception thrown :" + e);
        }
        System.out.println("Out of the block");
    }
}
