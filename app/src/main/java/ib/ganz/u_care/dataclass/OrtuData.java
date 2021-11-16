package ib.ganz.u_care.dataclass;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrtuData {

    private String id;
    @SerializedName("nama_ibu")
    private String namaIbu;
    @SerializedName("no_hp")
    private String noHp;
    @SerializedName("nama_pengguna")
    private String namaPengguna;
    @SerializedName("tanggal_lahir")
    private String tanggalLahir;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("firebase_token")
    private String firebaseToken;
    private List<AnakData> anaks;

    public String getId() {
        return id;
    }

    public String getNamaIbu() {
        return namaIbu;
    }

    public String getNoHp() {
        return noHp;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public List<AnakData> getAnaks() {
        return anaks;
    }

    public void setNamaIbu(String namaIbu) {
        this.namaIbu = namaIbu;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }

    public void setAnaks(List<AnakData> anaks) {
        this.anaks = anaks;
    }
}
