/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Perbankan;

/**
 *
 * @author DIAZ
 */
public class RekeningGiro extends Rekening {

    public RekeningGiro(String nomorRekening, double saldoAwal) {
        super(nomorRekening, saldoAwal);
    }

    @Override
    public void tarik(double jumlah) {
        if (jumlah > 0) {
            saldo -= jumlah; // Giro boleh minus (overdraft)
            System.out.println("Tarik dari Rek. Giro Rp" + jumlah + " berhasil. Saldo baru: Rp" + saldo);
        } else {
            System.out.println("Jumlah penarikan tidak valid.");
        }
    }
}
