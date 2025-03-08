public class mahasiswa {//class mahasiswa
    private String nim;
    private String nama;
    private String alamat;
    private int semester;
    private int sks;
    private double ipk;

    public mahasiswa(String nim, String nama, String alamat, int semester, int sks, double ipk) {//constructor
        this.nim = nim;
        this.nama = nama;
        this.alamat = alamat;
        this.semester = semester;
        this.sks = sks;
        this.ipk = ipk;

    }

    public String getNim() {//method getter untuk nama nim ......
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public int getSemester() {
        return semester;
    }

    public int getSks() {
        return sks;
    }

    public double getIpk() {
        return ipk;
    }

    @Override
    public String toString() {//method to string
        return "mahasiswa{" +
                "nim='" + nim + '\'' +
                ", nama='" + nama + '\'' +
                ", alamat='" + alamat + '\'' +
                ", semester=" + semester +
                ", sks=" + sks +
                ", ipk=" + ipk +
                '}';
    }
}
