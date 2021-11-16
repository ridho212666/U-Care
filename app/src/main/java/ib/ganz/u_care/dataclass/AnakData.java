package ib.ganz.u_care.dataclass;

import com.google.gson.annotations.SerializedName;

public class AnakData {

    private String id;
    @SerializedName("ortu_id")
    private String ortuId;
    @SerializedName("nama_anak")
    private String namaAnak;
    @SerializedName("tanggal_lahir")
    private String tanggalLahir;
    @SerializedName("jenis_kelamin")
    private String jenisKelamin;
    @SerializedName("usia_kehamilan_ibu")
    private int usiaKehamilanIbu;
    @SerializedName("bb_lahir")
    private float bbLahir;
    @SerializedName("pb_lahir")
    private float pbLahir;
    @SerializedName("lk_lahir")
    private float lkLahir;
    @SerializedName("foto")
    private String foto;

    public void setNamaAnak(String namaAnak) {
        this.namaAnak = namaAnak;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public void setUsiaKehamilanIbu(int usiaKehamilanIbu) {
        this.usiaKehamilanIbu = usiaKehamilanIbu;
    }

    public void setBbLahir(float bbLahir) {
        this.bbLahir = bbLahir;
    }

    public void setPbLahir(float pbLahir) {
        this.pbLahir = pbLahir;
    }

    public void setLkLahir(float lkLahir) {
        this.lkLahir = lkLahir;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getId() {
        return id;
    }

    public String getOrtuId() {
        return ortuId;
    }

    public String getNamaAnak() {
        return namaAnak;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public int getUsiaKehamilanIbu() {
        return usiaKehamilanIbu;
    }

    public float getBbLahir() {
        return bbLahir;
    }

    public float getPbLahir() {
        return pbLahir;
    }

    public float getLkLahir() {
        return lkLahir;
    }

    public String getFoto() {
        return foto;
    }
}
