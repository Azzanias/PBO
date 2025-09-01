/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbo;

/**
 *
 * @author DIAZ
 */
public class BankBeraksi {

    public static void main(String[] args) {
        System.out.println("Selamat datang di Bank ABC");
        Bank bank = new Bank(100000);
        System.out.println("Saldo saat ini : Rp. " + bank.getSaldo());
        System.out.println();
        
        bank.simpanUang(500000);
        bank.ambilUang(150000);
    }    
}
