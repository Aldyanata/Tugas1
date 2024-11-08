
import java.util.Scanner;
class Menu {
    String nama;
    int harga;
    String kategori;

    public Menu(String nama, int harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }
}

public class Tugas1 {
    static Menu[] daftarMenu = {
        new Menu("Nasi Goreng", 25000, "Makanan"),
        new Menu("Ayam Bakar", 30000, "Makanan"),
        new Menu("Sate Ayam", 20000, "Makanan"),
        new Menu("Mie Ayam", 15000, "Makanan"),
        new Menu("Teh Manis", 5000, "Minuman"),
        new Menu("Kopi Hitam", 7000, "Minuman"),
        new Menu("Jus Alpukat", 12000, "Minuman"),
        new Menu("Soda Gembira", 10000, "Minuman")
    };

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        tampilkanMenu();
        System.out.println("\nSilakan masukkan pesanan Anda (maksimal 4 menu, format: Nama Menu = Jumlah):");

        String[] pesananNama = new String[4];
        int[] pesananJumlah = new int[4];
        int totalPesanan = 0;
        
        for (int i = 0; i < 4; i++) {
            System.out.print("Pesanan " + (i+1) + ": ");
            String pesanan = input.nextLine();
            if (!pesanan.isEmpty()) {
                String[] data = pesanan.split("=");
                pesananNama[i] = data[0].trim();
                pesananJumlah[i] = Integer.parseInt(data[1].trim());
                totalPesanan++;
            }
        }

        int totalBiaya = hitungTotalBiaya(pesananNama, pesananJumlah, totalPesanan);
        cetakStruk(pesananNama, pesananJumlah, totalPesanan, totalBiaya);
        input.close();
    }

    static void tampilkanMenu() {
        System.out.println("========== Menu Restoran ==========");
        System.out.println("Makanan:");
        for (Menu menu : daftarMenu) {
            if (menu.kategori.equals("Makanan")) {
                System.out.println(menu.nama + " - Rp " + menu.harga);
            }
        }
        System.out.println("\nMinuman:");
        for (Menu menu : daftarMenu) {
            if (menu.kategori.equals("Minuman")) {
                System.out.println(menu.nama + " - Rp " + menu.harga);
            }
        }
    }

    static int hitungTotalBiaya(String[] pesananNama, int[] pesananJumlah, int totalPesanan) {
        int subtotal = 0;
        boolean diskonMinuman = false;

        for (int i = 0; i < totalPesanan; i++) {
            for (Menu menu : daftarMenu) {
                if (menu.nama.equalsIgnoreCase(pesananNama[i])) {
                    int hargaItem = menu.harga * pesananJumlah[i];
                    subtotal += hargaItem;

                    if (menu.kategori.equals("Minuman") && subtotal > 50000) {
                        diskonMinuman = true;
                    }
                }
            }
        }

        int pajak = (int) (subtotal * 0.1);
        int biayaPelayanan = 20000;
        int total = subtotal + pajak + biayaPelayanan;

        if (subtotal > 100000) {
            total -= subtotal * 0.1;  // Diskon 10%
        }
        if (diskonMinuman) {
            total -= 5000; // Diskon beli 1 gratis 1 minuman
        }

        return total;
    }

    static void cetakStruk(String[] pesananNama, int[] pesananJumlah, int totalPesanan, int totalBiaya) {
        System.out.println("\n========== Struk Pesanan ==========");
        int subtotal = 0;
        for (int i = 0; i < totalPesanan; i++) {
            for (Menu menu : daftarMenu) {
                if (menu.nama.equalsIgnoreCase(pesananNama[i])) {
                    int hargaItem = menu.harga * pesananJumlah[i];
                    subtotal += hargaItem;
                    System.out.println(menu.nama + " x " + pesananJumlah[i] + " = Rp " + hargaItem);
                }
            }
        }

        int pajak = (int) (subtotal * 0.1);
        int biayaPelayanan = 20000;
        int totalSebelumDiskon = subtotal + pajak + biayaPelayanan;

        System.out.println("Subtotal\t\t: Rp " + subtotal);
        System.out.println("Pajak (10%)\t\t: Rp " + pajak);
        System.out.println("Biaya Pelayanan\t: Rp " + biayaPelayanan);

        if (subtotal > 100000) {
            int diskon = (int) (subtotal * 0.1);
            System.out.println("Diskon (10%)\t: - Rp " + diskon);
        }

        if (subtotal > 50000) {
            System.out.println("Promo Minuman\t: - Rp 5000 (Beli 1 Gratis 1 Minuman)");
        }

        System.out.println("Total Biaya\t\t: Rp " + totalBiaya);
        System.out.println("=====================================");
    }
}
