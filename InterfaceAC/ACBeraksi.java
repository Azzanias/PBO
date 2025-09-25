/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InterfaceAC;

/**
 *
 * @author DIAZ
 */
public class ACBeraksi implements InterfaceAC {

    private boolean statusAC;
    private int suhu;

    public void hidupkanAC() {
        if (statusAC) {
            statusAC = true;
            suhu = 24;
            System.out.println("AC berhasil dinyalakan!");
            System.out.println("Suhu saat ini: " + suhu + "C");
        } else {
            System.out.println("AC sudah dalam keadaan menyala.");
        }
    }

    public void matikanAC() {
        if (statusAC) {
            statusAC = false;
            System.out.println("AC berhasil dimatikan.");
        } else {
            System.out.println("Ac sudah dalam keadaan mati.");
        }
    }

    public void panaskanAC() {
        if (statusAC) {
            this.suhu++;
            System.out.println("Suhu dinaikkan. Suhu saat ini: " + this.suhu + "C");
        } else {
            System.out.println("AC belum dinyalakan.");
        }
    }

    public void dinginkanAC() {
        if (statusAC) {
            this.suhu--;
            System.out.println("AC belum dinyalakan");
        }
    }
}
