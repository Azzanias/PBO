/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Shapes;

/**
 *
 * @author DIAZ
 */
public class Main {

    public static void main(String[] args) {

        Shape circle = new Circle(10);

        System.out.println("--- Info Lingkaran ---");
        ((Drawable) circle).draw();
        System.out.println("Luas: " + circle.getArea());
        System.out.println();

        Shape rectangle = new Rectangle(8, 5);

        // Menampilkan informasi Persegi Panjang
        System.out.println("--- Info Persegi Panjang ---");
        ((Drawable) rectangle).draw();
        System.out.println("Luas: " + rectangle.getArea());
    }
}
