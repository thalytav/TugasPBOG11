import java.util.ArrayList;
import java.util.Scanner;

public class RentalSystem {

    private static ArrayList<Kendaraan> kendaraanList = new ArrayList<>();
    private static ArrayList<Penyewa> penyewaList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Loop utama untuk mengelola input
        boolean selesai = false;
        while (!selesai) {
            System.out.println("\n=== Sistem Rental ===");
            System.out.println("1. Tambah Kendaraan");
            System.out.println("2. Tambah Penyewa");
            System.out.println("3. Lihat Daftar Kendaraan");
            System.out.println("4. Lihat Daftar Penyewa");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Mengkonsumsi newline

            switch (pilihan) {
                case 1:
                    tambahKendaraan(scanner);
                    break;
                case 2:
                    tambahPenyewa(scanner);
                    break;
                case 3:
                    tampilkanDaftarKendaraan();
                    break;
                case 4:
                    tampilkanDaftarPenyewa();
                    break;
                case 5:
                    selesai = true;
                    break;
                default:
                    System.out.println("Opsi tidak valid.");
            }
        }
        scanner.close();
    }

    // Method untuk menambahkan kendaraan baru
    private static void tambahKendaraan(Scanner scanner) {
        System.out.println("Jenis Kendaraan (1: Mobil, 2: Motor, 3: Sepeda): ");
        int jenis = scanner.nextInt();
        scanner.nextLine(); // Mengkonsumsi newline

        System.out.print("Masukkan Merk: ");
        String merk = scanner.nextLine();

        System.out.print("Masukkan Model: ");
        String model = scanner.nextLine();

        System.out.print("Masukkan Tahun Produksi: ");
        int tahunProduksi = scanner.nextInt();
        scanner.nextLine(); // Mengkonsumsi newline

        Kendaraan kendaraan = null;
        switch (jenis) {
            case 1:
                System.out.print("Masukkan Jumlah Roda untuk Mobil: ");
                int jumlahRodaMobil = scanner.nextInt();
                scanner.nextLine(); // Mengkonsumsi newline
                kendaraan = new Mobil(merk, model, tahunProduksi, jumlahRodaMobil);
                break;
            case 2:
                System.out.print("Masukkan Jumlah Roda untuk Motor: ");
                int jumlahRodaMotor = scanner.nextInt();
                scanner.nextLine(); // Mengkonsumsi newline
                kendaraan = new Motor(merk, model, tahunProduksi, jumlahRodaMotor);
                break;
            case 3:
                System.out.print("Masukkan Jenis Sepeda (misalnya BMX, balap): ");
                String jenisSepeda = scanner.nextLine();
                kendaraan = new Sepeda(merk, model, tahunProduksi, jenisSepeda);
                break;
            default:
                System.out.println("Jenis kendaraan tidak valid.");
                return;
        }

        kendaraanList.add(kendaraan);
        System.out.println("Kendaraan berhasil ditambahkan.");
    }

    // Method untuk menambahkan penyewa baru
    private static void tambahPenyewa(Scanner scanner) {
        if (kendaraanList.isEmpty()) {
            System.out.println("Tidak ada kendaraan yang tersedia untuk disewa.");
            return;
        }

        System.out.print("Masukkan Nama Penyewa: ");
        String nama = scanner.nextLine();

        System.out.println("Pilih Kendaraan yang ingin disewa:");
        for (int i = 0; i < kendaraanList.size(); i++) {
            System.out.println((i + 1) + ". " + kendaraanList.get(i).getInfo());
        }

        System.out.print("Masukkan nomor kendaraan yang ingin disewa: ");
        int kendaraanIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Mengkonsumsi newline

        if (kendaraanIndex < 0 || kendaraanIndex >= kendaraanList.size()) {
            System.out.println("Pilihan tidak valid.");
            return;
        }

        Penyewa penyewa = new Penyewa(nama, kendaraanList.get(kendaraanIndex));
        penyewaList.add(penyewa);
        System.out.println("Penyewa berhasil ditambahkan.");
    }

    // Method untuk menampilkan daftar kendaraan
    private static void tampilkanDaftarKendaraan() {
        if (kendaraanList.isEmpty()) {
            System.out.println("Tidak ada kendaraan yang tersedia.");
        } else {
            System.out.println("\nDaftar Kendaraan Tersedia:");
            for (Kendaraan kendaraan : kendaraanList) {
                System.out.println(kendaraan.getInfo());
            }
        }
    }

    // Method untuk menampilkan daftar penyewa
    private static void tampilkanDaftarPenyewa() {
        if (penyewaList.isEmpty()) {
            System.out.println("Tidak ada penyewa yang terdaftar.");
        } else {
            System.out.println("\nDaftar Penyewa dan Kendaraan yang Disewa:");
            for (Penyewa penyewa : penyewaList) {
                System.out.println(penyewa.getDetailSewa());
            }
        }
    }
}
