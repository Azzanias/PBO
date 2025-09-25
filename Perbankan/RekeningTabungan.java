/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Perbankan;

/**
 *
 * @author DIAZ
 */
public class RekeningTabungan extends Rekening {

    private double saldoMinimum = 50000;

    public RekeningTabungan(String nomorRekening, double saldoAwal) {
        super(nomorRekening, saldoAwal);
    }

    @Override
    public void tarik(double jumlah) {
        if (jumlah > 0 && (saldo - jumlah) >= saldoMinimum) {
            saldo -= jumlah;
            System.out.println("Tarik dari Rek. Tabungan Rp" + jumlah + " berhasil. Saldo baru: Rp" + saldo);
        } else {
            System.out.println("Tarik gagal. Saldo tidak mencukupi atau di bawah saldo minimum.");
        }
    }
}
