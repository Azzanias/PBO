/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Perbankan;

/**
 *
 * @author DIAZ
 */
public class RekeningDeposito extends Rekening {

    public RekeningDeposito(String nomorRekening, double saldoAwal) {
        super(nomorRekening, saldoAwal);
    }

    @Override
    public void tarik(double jumlah) {
        System.out.println("Penarikan tidak diizinkan dari Rekening Deposito sebelum jatuh tempo.");
    }
}
