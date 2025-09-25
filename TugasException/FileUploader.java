/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TugasException;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author DIAZ
 */
public class FileUploader {

    public void uploadFile(String filePath, String serverHost, int serverPort) {
        File file = new File(filePath);

        // Memeriksa apakah file ada sebelum mencoba membukanya
        if (!file.exists()) {
            System.err.println("Error: File sumber di '" + filePath + "' tidak ditemukan.");
            return;
        }

        // try-with-resources untuk memastikan semua resource (Socket, Streams) ditutup secara otomatis
        try (
                // 1. Membaca file dari komputer lokal [cite: 18]
                FileInputStream fileInputStream = new FileInputStream(file); BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream); // 2. Mengirimkan data ke server dengan socket [cite: 19]
                 Socket socket = new Socket(serverHost, serverPort); OutputStream outputStream = socket.getOutputStream()) {
            System.out.println("Terhubung ke server " + serverHost + "...");
            byte[] buffer = new byte[4096];
            int bytesRead;

            // Membaca dari file dan menulis ke output stream socket
            while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush(); // Memastikan semua data terkirim
            System.out.println("File berhasil diunggah.");

            // 3. Menangani berbagai kemungkinan error jaringan dan file secara tepat [cite: 20]
        } catch (FileNotFoundException e) {
            // Sebenarnya sudah ditangani dengan pengecekan file.exists(), tapi baik untuk ada
            System.err.println("Error kritis: File tidak ditemukan saat proses baca. " + e.getMessage());
        } catch (UnknownHostException e) {
            System.err.println("Error jaringan: Host server '" + serverHost + "' tidak ditemukan.");
        } catch (IOException e) {
            System.err.println("Error I/O atau jaringan: Gagal terhubung atau mengirim data. Pesan: " + e.getMessage());
        }
        // 4. Selalu menutup resource setelah selesai (dilakukan otomatis oleh try-with-resources) 
    }

    public static void main(String[] args) {
        FileUploader uploader = new FileUploader();
        // Contoh pemanggilan:
        // Ganti "C:\\path\\to\\your\\file.txt", "localhost", dan 12345 dengan nilai sebenarnya
        uploader.uploadFile("dokumen_penting.txt", "server-tujuan.com", 12345);
    }
}
