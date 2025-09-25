/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PercobaanLampu;

/**
 *
 * @author DIAZ
 */
public class Lampu implements LampuInterface {
    int statusLampu;

    public void hidupkan() {
        if (statusLampu == KEADAAN_MATI) {
            statusLampu = KEADAAN_HIDUP;
            System.out.println("Hidupkan Lampu! -- > Lampu Hidup");
        } else {
            System.out.println("Hidupkan Lampu! --> Lampu Sudah Hidup Kok");
        }
    }
    
    public void matikan() {
        if (statusLampu == KEADAAN_HIDUP) {
            statusLampu = KEADAAN_MATI;
            System.out.println("Matikan Lampu --> Lampu Mati");
        } else {
            System.out.println("Matikan Lampu! --> Lampu Sudah Mati Kok");
        }
    }
}
