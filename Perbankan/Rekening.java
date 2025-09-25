/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Perbankan;

/**
 *
 * @author DIAZ
 */
public abstract class Rekening {

    protected String nomorRekening;
    protected double saldo;

    public Rekening(String nomorRekening, double saldoAwal) {
        this.nomorRekening = nomorRekening;
        this.saldo = saldoAwal;
    }

    // Metode konkret, sama untuk semua rekening
    public void setor(double jumlah) {
        if (jumlah > 0) {
            saldo += jumlah;
            System.out.println("Setor Rp" + jumlah + " berhasil. Saldo baru: Rp" + saldo);
        } else {
            System.out.println("Jumlah setoran tidak valid.");
        }
    }

    // Metode abstrak, implementasi berbeda di setiap subkelas
    public abstract void tarik(double jumlah);

    public double getSaldo() {
        return saldo;
    }
}
