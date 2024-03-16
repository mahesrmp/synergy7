import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class CatatanPembelian {
    String namaMenu;
    int hargaMenu;
    int jlhPembelian;

    boolean validateCetakCatatanPembelian = false;

    Scanner sc = new Scanner(System.in);

    public CatatanPembelian() {
    }

    public CatatanPembelian(String namaMenu, int hargaMenu, int jlhPembelian) {
        this.namaMenu = namaMenu;
        this.hargaMenu = hargaMenu;
        this.jlhPembelian = jlhPembelian;
    }

    ArrayList<CatatanPembelian> pembelian = new ArrayList();

    public void tambahPembelian(String namaMenu, int hargaMenu, int jlhPembelian) {
        pembelian.add(new CatatanPembelian(namaMenu, hargaMenu, jlhPembelian));
    }

    public void historyPembelian() {
        int i = 0;
        for (CatatanPembelian item : pembelian) {
            System.out.println((i + 1) + ". " + item.namaMenu + "  " + item.jlhPembelian + "   " + item.hargaMenu);
            i++;
        }
    }

    public boolean isEmpty() {
        return pembelian.isEmpty();
    }

    public int totalHargaPembelian() {
        int totalHargaPembelian = 0;
        for (CatatanPembelian item : pembelian) {
            totalHargaPembelian += (item.hargaMenu * item.jlhPembelian);
        }
        return totalHargaPembelian;
    }

    public int totalJumlahPembelian() {
        int totalJumlahPembelian = 0;
        for (CatatanPembelian item : pembelian) {
            totalJumlahPembelian += item.jlhPembelian;
        }
        return totalJumlahPembelian;
    }

    public void removePembelian(int index) {
        if (index >= 0 && index < pembelian.size()) {
            pembelian.remove(index);
            System.out.println("Pesanan berhasil dihapus.");
        } else {
            System.out.println("Indeks pesanan tidak valid.");
        }
    }

    public void updatePembelian(int index, String namaMenu, int hargaMenu, int jlhPembelian) {
        if (index >= 0 && index < pembelian.size()) {
            pembelian.set(index, new CatatanPembelian(namaMenu, hargaMenu, jlhPembelian));
            System.out.println("Pesanan berhasil diubah.");
        } else {
            System.out.println("Indeks pesanan tidak valid.");
        }
    }

    public void saveCatatanPembelian(String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("=================================================\nBinarFud\n================================================\n\n" +
                    "Terima Kasih sudah memesan di BinarFud\n\n" +
                    "Dibawah ini adalah pesanan anda\n\n");
            for (CatatanPembelian item : pembelian) {
                bufferedWriter.write(item.namaMenu + " " + item.jlhPembelian + " " + item.hargaMenu);
                bufferedWriter.newLine();
            }

            bufferedWriter.write("\nTotal Jumlah Pembelian: " + totalJumlahPembelian());
            bufferedWriter.newLine();
            bufferedWriter.write("Total Harga Pembelian: " + totalHargaPembelian());
            bufferedWriter.newLine();

            bufferedWriter.write("\n\nPembayaran : BinarCash\n\n" +
                    "==============================================\nSimpan struk ini sebagai bukti pembayaran\n============================================");

            bufferedWriter.close();
            validateCetakCatatanPembelian = true;
            System.out.println("Data berhasil disimpan ke " + fileName);
            if (validateCetakCatatanPembelian == true) {
                do {
                    System.out.println("Apakah anda ingin melakukan pembelian lagi? (Ya/Tidak)");
                    String pembelianLagi = sc.nextLine();
                    if (pembelianLagi.equals("Tidak") || pembelianLagi.equals("tidak") || pembelianLagi.equals("T") || pembelianLagi.equals("t")) {
                        System.out.println("Mampir Lagi");
                        System.exit(0);
                    } else if (pembelianLagi.equals("Ya") || pembelianLagi.equals("ya") || pembelianLagi.equals("Y") || pembelianLagi.equals("y")) {
                        break;
                    } else {
                        validateCetakCatatanPembelian = false;
                        System.out.println("Masukkan inputan Ya/Tidak");
                    }
                } while (validateCetakCatatanPembelian == false);

            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan data ke file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
