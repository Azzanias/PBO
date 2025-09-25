/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudiKasusModul2;

/**
 *
 * @author DIAZ
 */
public class RekeningBankMainCS {

    public static void main(String[] args) {
        RekeningBankCS akunBudi = new RekeningBankCS("ACC123", 1000000.0);

        System.out.println("Saldo awal Budi: Rp" + akunBudi.getSaldo());

        akunBudi.setor(500000.0);
        akunBudi.tarik(200000.0);
        akunBudi.tarik(1500000.0);

        System.out.println("Saldo akhir Budi: Rp" + akunBudi.getSaldo());
    }
}
