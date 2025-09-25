/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InterfaceAC;

/**
 *
 * @author DIAZ
 */
public class LatihanInterfaceAC {
    public static void main(String[] args) {
        ACBeraksi acSaya = new ACBeraksi();
        
        System.out.println("Menyalakan AC");
        acSaya.hidupkanAC();
        
        System.out.println("Coba hidupkan lagi");
        acSaya.hidupkanAC();
        
        System.out.println("Mendinginkan suhu 2x");
        acSaya.dinginkanAC();
        acSaya.dinginkanAC();
        
        System.out.println("Memanaskan suhu 1x");
        acSaya.panaskanAC();
        
        System.out.println("Mematikan AC");
        acSaya.matikanAC();
        
        System.out.println("Coba mendinginkan suhu saat AC mati");
        acSaya.dinginkanAC();
    }
}
