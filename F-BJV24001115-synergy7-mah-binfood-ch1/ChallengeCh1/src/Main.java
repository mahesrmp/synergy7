import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) {
        String[] menus = {"Nasi Goreng", "Mie Goreng", "Nasi + Ayam", "Es Teh Manis", "Es Jeruk"};
        int[] hargas = {15000, 13000, 18000, 3000, 5000};
        Scanner sc = new Scanner(System.in);
        String path = "D:/SYNRGY/Challenge1/";
        String fileName = path + "catatanPembelian.txt";
        CatatanPembelian catatanPembelian = new CatatanPembelian();
        do {
            System.out.println("==========================\nSelamat datang di BinarFud\n==========================");
            System.out.println("\nSilahkan pilih makanan :");
            for (int i = 0; i < menus.length; i++) {
                System.out.println(i + 1 + ". " + menus[i] + "    |   " + "" + hargas[i]);
            }
            System.out.println("99. Pesan dan bayar\n0. Keluar aplikasi\n");
            System.out.print("=> ");
            int pilihanMakanan = sc.nextInt();

            int jumlahPesanan;
            if (pilihanMakanan >= 1 && pilihanMakanan <= 5) {
                System.out.println("============================\nBerapa pesanan anda\n=========================");
                System.out.println(menus[pilihanMakanan - 1] + "    |   " + "" + hargas[pilihanMakanan - 1] + "\n(input 0 untuk kembali)\n");
                System.out.print("qty => ");
                jumlahPesanan = sc.nextInt();
                catatanPembelian.tambahPembelian(menus[pilihanMakanan - 1], hargas[pilihanMakanan - 1], jumlahPesanan);
            } else if (pilihanMakanan == 99) {
                if (catatanPembelian.isEmpty()) {
                    System.out.println("Anda Belum Melakukan Pesanan!!!");
                } else {
                    boolean konfirmasiPembayaran = true;
                    do {
                        System.out.println("============================\nKonfirmasi & Pembayaran\n=========================");
                        catatanPembelian.historyPembelian();
                        System.out.println("---------------------------------------------------------- +");
                        System.out.println("Total       " + catatanPembelian.totalJumlahPembelian() + "       " + catatanPembelian.totalHargaPembelian());
                        System.out.println("1. Konfirmasi dan Bayar\n2. Ubah Pesanan\n3. Hapus Pesanan\n4. Kembali ke menu utama\n0. Keluar Aplikasi");
                        System.out.print("=> ");
                        int confirmAndPay = sc.nextInt();
                        switch (confirmAndPay) {
                            case 1:
                                catatanPembelian.saveCatatanPembelian(fileName);
                                break;
                            case 2:
                                if (!catatanPembelian.isEmpty()) {
                                    System.out.print("Masukkan nomor pesanan yang ingin diubah: ");
                                    int indexToUpdate = sc.nextInt();
                                    sc.nextLine();
                                    if (indexToUpdate > 0 && indexToUpdate <= catatanPembelian.pembelian.size()) {
                                        CatatanPembelian pesananToUpdate = catatanPembelian.pembelian.get(indexToUpdate - 1);
                                        System.out.println("Pesanan yang ingin diubah:");
                                        System.out.println((indexToUpdate) + ". " + pesananToUpdate.namaMenu + "  " + pesananToUpdate.jlhPembelian + "   " + pesananToUpdate.hargaMenu);
                                        System.out.print("Masukkan jumlah pembelian baru: ");
                                        int newJlhPembelian = sc.nextInt();
                                        catatanPembelian.updatePembelian(indexToUpdate - 1, pesananToUpdate.namaMenu, pesananToUpdate.hargaMenu, newJlhPembelian);
                                        konfirmasiPembayaran = false;
                                    } else {
                                        System.out.println("Nomor pesanan tidak valid.");
                                    }
                                } else {
                                    System.out.println("Belum ada pesanan yang dibuat.");
                                }
                                break;
                            case 3:
                                if (!catatanPembelian.isEmpty()) {
                                    System.out.println("Pesanan yang sudah dibuat:");
                                    catatanPembelian.historyPembelian();
                                    System.out.print("Masukkan nomor pesanan yang ingin dihapus: ");
                                    int indexToRemove = sc.nextInt();
                                    sc.nextLine();
                                    catatanPembelian.removePembelian(indexToRemove - 1);
                                    konfirmasiPembayaran = false;
                                } else {
                                    System.out.println("Belum ada pesanan yang dibuat.");
                                }
                                break;
                            case 4:
                                konfirmasiPembayaran = true;
                                break;
                            case 0:
                                System.exit(0);
                                break;
                            default:
                                konfirmasiPembayaran = false;
                                System.out.println("Pilihan yang anda masukkan tidak valid");
                        }
                    } while (konfirmasiPembayaran == false);
                }
            } else if (pilihanMakanan == 0) {
                System.out.println("Mampir Lagi");
                System.exit(0);
            } else {
                System.out.println("Pilihan yang kamu berikan tidak tersedia");
            }
        } while (true);

    }

}