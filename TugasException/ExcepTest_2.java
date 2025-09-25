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
            int c = 1 / b;
            System.out.println("Access element three :" + a[3]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException thrown :" + e);
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException thrown :" + e);
        } catch (Exception e) {
            System.out.println("Exception thrown :" + e);
        }
        System.out.println("Keluar dari blok.");
    }
}
