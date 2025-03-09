import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class database {//tiga fiil privat
    private ArrayList<mahasiswa> data = new ArrayList<>(); //dari data di simpan ke file pake perintah seve
    private String filenama = "src/data.csv";
    private Path path = Path.of(filenama);

    public database() {
        open();
    }

    public ArrayList<mahasiswa> getData() {
        return data;
    }

    public void open() {//method open di bungkus

        try {
            List<String> lines = Files.readAllLines(path);//membaca
            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] element = line.split(";");
                String nim = element[0];
                String nama = element[1];
                String alamat = element[2];
                int semester = Integer.parseInt(element[3]);
                int sks = Integer.parseInt(element[4]);
                double ipk = Double.parseDouble(element[5]);
                mahasiswa mhs = new mahasiswa(nim, nama, alamat, semester, sks, ipk);//memnggail contruktor
                data.add(mhs);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void save() {//kode untuk menyimpan/menulis
        StringBuilder sb = new StringBuilder();
        sb.append("NIM, NAMA, ALAMAT (KOTA), SEMESTER, SKS, IPK\n");
        if (!data.isEmpty()) { //cek apakah kosong atau tidak
            for (int i = 0; i < data.size(); i++) {
                mahasiswa mhs = data.get(i);
                String line = mhs.getNim() + ";" + mhs.getNama() + ";" + mhs.getAlamat() + ";" + mhs.getSemester() + ";" + mhs.getSks() + ";" + mhs.getIpk() + "\n";
                sb.append(line);

            }
        }
        try {
            Files.writeString(path, sb.toString());//menulis
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void view() {
        System.out.println("==================================================================================");
        System.out.printf("| %-8.8S |", "NIM");//agkanya menujukkan mencetak berapa karakter jika lebih tidak akan di cetak
        System.out.printf(" %-20.20S |", "NAMA");//tetapi tetap tersimpan di data base namun tidak di cetak gunanya agar rapi
        System.out.printf(" %-20.20S |", "ALAMAT");
        System.out.printf(" %8.8S |", "SEMESTER");
        System.out.printf(" %3.3S |", "SKS");
        System.out.printf(" %4.4S |%n", "IPK");
        System.out.println("----------------------------------------------------------------------------------");
        for (mahasiswa mhs : data) {
            System.out.printf("| %-8S |", mhs.getNim());
            System.out.printf(" %-20.20S |", mhs.getNama());
            System.out.printf(" %-20.20S |", mhs.getAlamat());
            System.out.printf(" %8.8S |", mhs.getSemester());
            System.out.printf(" %3.3S |", mhs.getSks());
            System.out.printf(" %4.4S |%n", mhs.getIpk());
            System.out.println();
        }
        System.out.println("----------------------------------------------------------------------------------");
    }

    public boolean insert(String nim, String nama, String alamat, int semester, int sks, double ipk) {
        boolean status = true;
        //cek primary key
        if (!data.isEmpty()) {
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getNim().equalsIgnoreCase(nim)) {
                    status = false;
                    break;
                }
            }

        }
        if (status == true){//jika statusnya msih trus maka datanya tidak memilikinduplikat jadi masih bisa di tambahkan
            mahasiswa mhs = new mahasiswa(nim, nama, alamat, semester, sks, ipk);
            data.add(mhs);
            save();
        }
        return status;
    }

    public int search(String nim){//untuk mngetahui posisis pada saat megaupdate data
        int index = -1; //jika baris data ada yanga sma dengan nim maka kita kembalikan indexnya
        if (!data.isEmpty()){
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getNim().equalsIgnoreCase(nim)){
                    index = i;
                    break;
                }
            }
        }
        return index;
    }
    public boolean update(int index, String nim, String nama, String alamat, int semester, int sks, double ipk){
        boolean status = false;
        if(!data.isEmpty()){
            //update
            if (index >= 0 && index < data.size()){
                mahasiswa mhs = new mahasiswa(nim, nama, alamat, semester, sks, ipk);
                data.set(index, mhs);
                save();
                status = true;
            }
        }
        return status;

    }
    public boolean delete(int index){
        boolean status = false;
        if (!data.isEmpty()){
            data.remove(index);
            save();
            status = true;
        }
        return false;
    }
}
