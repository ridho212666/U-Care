package ib.ganz.u_care.dataclass;

import com.google.gson.annotations.SerializedName;

public class NakesData {

    private String id;
    private String nama;
    private String profesi;
    @SerializedName("no_hp")
    private String noHp;
    @SerializedName("nama_institusi")
    private String namaInstitusi;
    @SerializedName("nama_pengguna")
    private String namaPengguna;
    @SerializedName("kata_kunci")
    private String kataKunci;
    @SerializedName("firebase_token")
    private String firebaseToken;

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getProfesi() {
        return profesi;
    }

    public String getNoHp() {
        return noHp;
    }

    public String getNamaInstitusi() {
        return namaInstitusi;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public String getKataKunci() {
        return kataKunci;
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setProfesi(String profesi) {
        this.profesi = profesi;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public void setNamaInstitusi(String namaInstitusi) {
        this.namaInstitusi = namaInstitusi;
    }

    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }

    public void setKataKunci(String kataKunci) {
        this.kataKunci = kataKunci;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }
}
