/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbo;

/**
 *
 * @author DIAZ
 */
public class Bank {

    private int saldo;

    public Bank(int saldoAwal) {
        this.saldo = saldoAwal;
    }

    public void simpanUang(int jumlah) {
        saldo += jumlah;
        System.out.println("Simpan uang : Rp. " + jumlah);
        System.out.println("Saldo awal saat ini : Rp. " + saldo);
        System.out.println();
    }

    public void ambilUang(int jumlah) {
        if (jumlah <= saldo) {
            saldo -= jumlah;
            System.out.println("Ambil uang : Rp. " + jumlah);
        } else {
            System.out.println("Saldo tidak cukup untuk ambil uang : Rp. " + jumlah);
        }
        System.out.println("Saldo saat ini : Rp. " + saldo);
        System.out.println("");
    }

    public int getSaldo() {
        return saldo;
    }
}
