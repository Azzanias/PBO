/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PBO2;

/**
 *
 * @author DIAZ
 */
public class RekeningBank {

    private String nomorRekening;
    private double saldo; // Data sensitif, dibuat private

    public RekeningBank(String nomorRekening, double saldoAwal) {
        this.nomorRekening = nomorRekening;
        if (saldoAwal >= 0) {
            this.saldo = saldoAwal;
        } else {
            this.saldo = 0;
        }
    }

    // Getter untuk melihat saldo (read-only access)
    public double getSaldo() {
        // Mungkin ada logika otentikasi di sini sebelum mengembalikan saldo
        System.out.println("Mengakses saldo untuk rekening " + this.nomorRekening);
        return this.saldo;
    }

    // Method publik untuk menyetor uang
    public void setor(double jumlah) {
        if (jumlah > 0) {
            this.saldo += jumlah;
            System.out.println("Setoran berhasil. Saldo baru: " + this.saldo);
        } else {
            System.out.println("Jumlah setoran tidak valid.");
        }
    }

    // Method publik untuk menarik uang dengan validasi
    public void tarik(double jumlah) {
        if (jumlah > this.saldo) {
            System.out.println("Saldo tidak mencukupi.");
        } else if (jumlah <= 0) {
            System.out.println("Jumlah penarikan tidak valid.");
        } else {
            this.saldo -= jumlah;
            System.out.println("Penarikan berhasil. Saldo baru: " + this.saldo);
        }
    }
}
