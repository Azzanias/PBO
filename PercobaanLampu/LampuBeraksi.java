/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PercobaanLampu;

/**
 *
 * @author DIAZ
 */
public class LampuBeraksi {
    public static void main(String[] args) {
        Lampu lampuKamar = new Lampu();
        
        System.out.println("Status Lampu Saat Ini: Mati ");
        
        lampuKamar.hidupkan();
        lampuKamar.matikan();
        lampuKamar.matikan();
        lampuKamar.hidupkan();
    }
}
