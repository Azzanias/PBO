/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudiKasusModul2;

/**
 *
 * @author DIAZ
 */
public class RekeningBankCS {

    private String nomorAkun;
    private double saldo;

    public RekeningBankCS(String nomorAkun, double saldoAwal) {
        this.nomorAkun = nomorAkun;

        if (saldoAwal > 0) {
            this.saldo = saldoAwal;
        } else {
            this.saldo = 0;
        }
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void setor(double jumlah) {
        if (jumlah > 0) {
            this.saldo += jumlah;
            System.out.println("Setoran Rp" + jumlah + " berhasil. Saldo baru: Rp" + this.saldo);
        } else {
            System.out.println("Error: Jumlah setoran harus positif.");
        }
    }

    public boolean tarik(double jumlah) {
        if (jumlah > 0 && jumlah <= this.saldo) {
            this.saldo -= jumlah;
            System.out.println("Penarikan Rp" + jumlah + " berhasil. Saldo sisa: Rp" + this.saldo);
            return true;
        } else if (jumlah > this.saldo) {
            System.out.println("Error: Saldo tidak mencukupi untuk penarikan.");
            return false;
        } else {
            System.out.println("Error: Jumlah penarikan tidak valid.");
            return false;
        }
    }
}
