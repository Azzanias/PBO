/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbo;

/**
 *
 * @author DIAZ
 */
public class MatematikaBeraksi {
    public static void main(String[] args) {
        MatematikaCanggih mtk = new MatematikaCanggih();
        System.out.println("Pertambahan int: 23 + 34 = " + mtk.pertambahan(23, 34));
        System.out.println("Pertambahan double : 3.4 + 4.9 = " + mtk.pertambahan(3.4, 4.9));
        System.out.println("Pertambahan double 3 param: 12.5 + 28.7 + 14.2 = " + mtk.pertambahan(12.5, 28.7 , 14.2));
        System.out.println("Pertambahan int 3 param: 12 + 28 + 14 = " + mtk.pertambahan(12, 28, 14));
        
        System.out.println("Pengurangan: 20 - 7 = " + mtk.pengurangan(20, 7));
        
        System.out.println("Perkalian: 7 * 6 = " + mtk.perkalian(7, 6));
        
        System.out.println("Pembagian: 21 / 2 = " + mtk.pembagian(21, 2));
        
        System.out.println("Modulus: 21 % 4 = " + mtk.modulus(21, 4));
    }
}
