/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PBO2;

/**
 *
 * @author DIAZ
 */
public class RekeningBankMain {

    public static void main(String[] args) {
        RekeningBank akunSaya = new RekeningBank("12345678", 500000.0);

        System.out.println("Saldo saat ini: " + akunSaya.getSaldo());
        akunSaya.setor(200000.0);
        akunSaya.tarik(800000.0);
        akunSaya.tarik(150000.0);
        System.out.println("Saldo akhir: " + akunSaya.getSaldo());
    }
}
