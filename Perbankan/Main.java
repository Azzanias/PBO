/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Perbankan;

/**
 *
 * @author DIAZ
 */
public class Main {
    public static void main(String[] args) {
        Rekening tabungan = new RekeningTabungan("RT001", 200000);
        Rekening giro = new RekeningGiro("RG001", 500000);
        Rekening deposito = new RekeningDeposito("RD001", 1000000);

        System.out.println("--- Aksi Rekening Tabungan ---");
        tabungan.setor(100000);
        tabungan.tarik(120000); // Berhasil
        tabungan.tarik(150000); // Gagal, saldo sisa < 50rb
        
        System.out.println("\n--- Aksi Rekening Giro ---");
        giro.tarik(700000); // Berhasil, saldo menjadi minus
        
        System.out.println("\n--- Aksi Rekening Deposito ---");
        deposito.tarik(100000); // Gagal
    }
}
