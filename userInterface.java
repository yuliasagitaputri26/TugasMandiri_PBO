import java.util.Scanner;

public class userInterface {

    public static void tampilkanMenu(){
        System.out.println();
        System.out.println("=================");
        System.out.println("|  Pilih Menu:  |");
        System.out.println("+---------------+");
        System.out.println("|  [C] : Create |");
        System.out.println("|  [R] : Read   |");
        System.out.println("|  [U] : Update |");
        System.out.println("|  [D] : Delete |");
        System.out.println("|  [X] : Exit   |");
        System.out.println("+---------------+");
    }

    public static void main(String[] args) {
        database db = new database();
        Scanner input = new Scanner(System.in);

        System.out.println("APLIKASI SIMPLE CRUD TEXT DATABASE");

        while (true) {
            tampilkanMenu();
            System.out.print("Pilih: ");
            String pilihan = input.nextLine().toUpperCase();

            switch (pilihan) {
                case "C":
                    System.out.println("INFO: Anda memilih menu Create");
                    System.out.println("---------------------------------------------");
                    System.out.println("INPUT DATA BARU");
                    System.out.print("NIM             : ");
                    String nim = input.nextLine();
                    System.out.print("NAMA MAHASISWA  : ");
                    String nama = input.nextLine();
                    System.out.print("ALAMAT          : ");
                    String alamat = input.nextLine();
                    System.out.print("SEMESTER        : ");
                    int semester = input.nextInt();
                    System.out.print("SKS             : ");
                    int sks = input.nextInt();
                    System.out.print("IPK             : ");
                    double ipk = input.nextDouble();
                    input.nextLine(); // Flush buffer
                    System.out.println("------------------------------------------------");

                    if (db.insert(nim, nama, alamat, semester, sks, ipk)) {
                        System.out.println("DATA BARU BERHASIL DITAMBAHKAN");
                    } else {
                        System.out.println("NIM " + nim + " sudah ada di database");
                        System.err.println("GAGAL MENAMBAHKAN DATA BARU");
                    }
                    System.out.println("------------------------------------------------");
                    break;

                case "R":
                    System.out.println("INFO: Anda memilih menu Read");
                    db.view();
                    break;

                case "U":
                    System.out.println("INFO: Anda memilih menu Update");
                    db.view();
                    System.out.print("Input Key (NIM Mahasiswa yang akan diupdate): ");
                    String key = input.nextLine();
                    int index = db.search(key);

                    if (index >= 0) {
                        System.out.println("Anda akan meng-update data: " + db.getData().get(index));
                        System.out.println("---------------------------------------------");
                        System.out.println("INPUT DATA BARU");
                        System.out.print("NIM             : ");
                        nim = input.nextLine();
                        System.out.print("NAMA MAHASISWA  : ");
                        nama = input.nextLine();
                        System.out.print("ALAMAT          : ");
                        alamat = input.nextLine();
                        System.out.print("SEMESTER        : ");
                        semester = input.nextInt();
                        System.out.print("SKS             : ");
                        sks = input.nextInt();
                        System.out.print("IPK             : ");
                        ipk = input.nextDouble();
                        input.nextLine(); // Flush buffer
                        System.out.println("------------------------------------------------");

                        if (db.update(index, nim, nama, alamat, semester, sks, ipk)) {
                            System.out.println("DATA BERHASIL DIPERBAHARUI");
                        } else {
                            System.err.println("GAGAL MEMPERBAHARUI DATA");
                        }
                        System.out.println("------------------------------------------------");
                    } else {
                        System.err.println("Mahasiswa dengan NIM: " + key + " tidak ada di database");
                    }
                    break;

                case "D":
                    System.out.println("INFO: Anda memilih menu Delete");
                    db.view();
                    System.out.print("Input Key (NIM Mahasiswa yang akan dihapus): ");
                    key = input.nextLine();
                    index = db.search(key);

                    if (index >= 0) {
                        System.out.println("APAKAH ANDA YAKIN INGIN MENGHAPUS DATA " + db.getData().get(index) + "? (Y/N)");
                        System.out.print("Pilih: ");
                        pilihan = input.nextLine();

                        if (pilihan.equalsIgnoreCase("Y")) {
                            if (db.delete(index)) {
                                System.out.println("DATA BERHASIL DIHAPUS");
                            } else {
                                System.out.println("GAGAL MENGHAPUS DATA");
                            }
                        }
                    } else {
                        System.err.println("Mahasiswa dengan NIM: " + key + " tidak ada di database");
                    }
                    break;

                case "X":
                    System.out.println("INFO: Anda memilih menu Exit");
                    System.out.print("APAKAH ANDA YAKIN INGIN KELUAR DARI APLIKASI? (Y/N): ");
                    pilihan = input.nextLine();

                    if (pilihan.equalsIgnoreCase("Y")) {
                        System.out.println("Terima kasih telah menggunakan aplikasi ini!");
                        System.exit(0);
                    }
                    break;

                default:
                    System.err.println("Pilihan tidak valid! Silakan pilih menu yang tersedia.");
            }
        }
    }
}
