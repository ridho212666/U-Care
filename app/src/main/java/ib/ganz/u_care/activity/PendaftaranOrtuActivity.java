package ib.ganz.u_care.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.zhihu.matisse.Matisse;

import java.util.ArrayList;
import java.util.List;

import ib.ganz.u_care.R;
import ib.ganz.u_care.dataclass.AnakData;
import ib.ganz.u_care.dataclass.OrtuData;
import ib.ganz.u_care.helper.BitmapHelper;
import ib.ganz.u_care.manager.SessionManager;
import ib.ganz.u_care.network.Service;

public class PendaftaranOrtuActivity extends BaseActivity {

    private Spinner spinJenisKelamin;
    private TextInputLayout tilNamaIbu, tilNoHp, tilNamaPengguna, tilKataKunci, tilKonfirmasiKataKunci, tilTanggalLahir, tilAlamat, tilNamaAnak, tilTglLahir, tilBeratBadanLahir, tilPanjangBadanLahir, tilLingkarKepalaLahir;
    private TextInputEditText eNamaIbu, eNoHp, eNamaPengguna, eKataKunci, eKonfirmasiKataKunci, eTanggalLahir, eAlamat, eNamaAnak, eTglLahir, eBeratBadanLahir, ePanjangBadanLahir, eLingkarKepalaLahir;
    private View bPicture, bDaftar;
    private ImageView img;
    private String base64 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendaftaran_ortu);

        tilNamaIbu = findViewById(R.id.tilNamaIbu);
        tilNoHp = findViewById(R.id.tilNoHp);
        tilNamaPengguna = findViewById(R.id.tilNamaPengguna);
        tilKataKunci = findViewById(R.id.tilKataKunci);
        tilKonfirmasiKataKunci = findViewById(R.id.tilKonfirmasiKataKunci);
        tilTanggalLahir = findViewById(R.id.tilTanggalLahir);
        tilAlamat = findViewById(R.id.tilAlamat);
        tilNamaAnak = findViewById(R.id.tilNamaAnak);
        tilTglLahir = findViewById(R.id.tilTglLahir);
        tilBeratBadanLahir = findViewById(R.id.tilBeratBadanLahir);
        tilPanjangBadanLahir = findViewById(R.id.tilPanjangBadanLahir);
        tilLingkarKepalaLahir = findViewById(R.id.tilLingkarKepalaLahir);

        eNamaIbu = findViewById(R.id.eNamaIbu);
        eNoHp = findViewById(R.id.eNoHp);
        eNamaPengguna = findViewById(R.id.eNamaPengguna);
        eKataKunci = findViewById(R.id.eKataKunci);
        eKonfirmasiKataKunci = findViewById(R.id.eKonfirmasiKataKunci);
        eTanggalLahir = findViewById(R.id.eTanggalLahir);
        eAlamat = findViewById(R.id.eAlamat);
        eNamaAnak = findViewById(R.id.eNamaAnak);
        eTglLahir = findViewById(R.id.eTglLahir);
        eBeratBadanLahir = findViewById(R.id.eBeratBadanLahir);
        ePanjangBadanLahir = findViewById(R.id.ePanjangBadanLahir);
        eLingkarKepalaLahir = findViewById(R.id.eLingkarKepalaLahir);

        img = findViewById(R.id.img);
        bPicture = findViewById(R.id.bPicture);
        bDaftar = findViewById(R.id.bDaftar);

        spinJenisKelamin = findViewById(R.id.spinner);
        spinJenisKelamin = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources()
                .getStringArray(R.array.Kelamin));

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinJenisKelamin.setAdapter(adapter);

        bDaftar.setOnClickListener(x -> {
            if (valid()) {
                register();
            }
        });
        bPicture.setOnClickListener(x -> {
            chooseImage();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            try {
                List<Uri> listUri = Matisse.obtainResult(data);
                Bitmap bitmap = BitmapHelper.uriToBitmap(this, listUri.get(0));
                String imageType = BitmapHelper.getMimeType(this.getContentResolver(), listUri.get(0));
                bitmap = BitmapHelper.resize(bitmap, 800, 800);

                base64 = BitmapHelper.bitmapToBase64(bitmap, imageType);
                img.setImageBitmap(bitmap);
                img.setVisibility(View.VISIBLE);
            }
            catch (Exception e) {
                toast("gagal mendapatkan gambar");
            }
        }
    }

    private boolean valid() {
        tilNamaIbu.setError(null);
        tilNoHp.setError(null);
        tilNamaPengguna.setError(null);
        tilKataKunci.setError(null);
        tilKonfirmasiKataKunci.setError(null);
        tilTanggalLahir.setError(null);
        tilAlamat.setError(null);
        tilNamaAnak.setError(null);
        tilTglLahir.setError(null);
        tilBeratBadanLahir.setError(null);
        tilPanjangBadanLahir.setError(null);
        tilLingkarKepalaLahir.setError(null);

        if (eNamaIbu.getText().toString().isEmpty()) {
            tilNamaIbu.setError("Masukkan nama ibu dulu");
            return false;
        }
        if (eNoHp.getText().toString().isEmpty()) {
            tilNoHp.setError("Masukkan no handphone dulu");
            return false;
        }
        if (eNamaPengguna.getText().toString().isEmpty()) {
            tilNamaPengguna.setError("Masukkan nama pengguna dulu");
            return false;
        }
        if (eKataKunci.getText().toString().isEmpty()) {
            tilKataKunci.setError("Masukkan kata kunci dulu");
            return false;
        }
        if (eKonfirmasiKataKunci.getText().toString().isEmpty()) {
            tilKonfirmasiKataKunci.setError("Masukkan konfirmasi kata kunci dulu");
            return false;
        }
        if (eTanggalLahir.getText().toString().isEmpty()) {
            tilTanggalLahir.setError("Masukkan tanggal lahir dulu");
            return false;
        }
        if (eAlamat.getText().toString().isEmpty()) {
            tilAlamat.setError("Masukkan alamat dulu");
            return false;
        }
        if (eNamaAnak.getText().toString().isEmpty()) {
            tilNamaAnak.setError("Masukkan nama anak dulu");
            return false;
        }
        if (eTglLahir.getText().toString().isEmpty()) {
            tilTglLahir.setError("Masukkan tanggal lahir anak dulu");
            return false;
        }
        if (eBeratBadanLahir.getText().toString().isEmpty()) {
            tilBeratBadanLahir.setError("Masukkan berat badan lahir dulu");
            return false;
        }
        if (ePanjangBadanLahir.getText().toString().isEmpty()) {
            tilPanjangBadanLahir.setError("Masukkan panjang badan lahir dulu");
            return false;
        }
        if (eLingkarKepalaLahir.getText().toString().isEmpty()) {
            tilLingkarKepalaLahir.setError("Masukkan lingkar kepala lahir dulu");
            return false;
        }

        return true;
    }

    private void register() {
        AnakData a = new AnakData();
        a.setNamaAnak(eNamaAnak.getText().toString());
        a.setTanggalLahir(eTglLahir.getText().toString());
        a.setJenisKelamin(spinJenisKelamin.getSelectedItem().equals("Laki-Laki") ? "L" : "P");
        a.setUsiaKehamilanIbu(10);
        a.setBbLahir(Float.parseFloat(eBeratBadanLahir.getText().toString()));
        a.setPbLahir(Float.parseFloat(ePanjangBadanLahir.getText().toString()));
        a.setLkLahir(Float.parseFloat(eLingkarKepalaLahir.getText().toString()));
        a.setFoto(base64);

        List<AnakData> l = new ArrayList<>();
        l.add(a);

        OrtuData o = new OrtuData();
        o.setNamaIbu(eNamaIbu.getText().toString());
        o.setNoHp(eNoHp.getText().toString());
        o.setNamaPengguna(eNamaPengguna.getText().toString());
        o.setTanggalLahir(eTanggalLahir.getText().toString());
        o.setAlamat(eAlamat.getText().toString());
        o.setAnaks(l);

        progressDialog.show();
        compositeDisposable.add(Service.registerOrtu(o).subscribe(r -> {
            progressDialog.dismiss();
            if (r.isSuccess()) {
                SessionManager.loginOrtu(r.getData());
                toast("Register berhasil");

                Intent i = new Intent(this, OtpActivity.class);
                i.putExtra("no_hp", eNoHp.getText().toString());
                i.putExtra("id", r.getData().getId());
                i.putExtra("ortu", true);
                startActivity(i);
            }
            else {
                toast(r.getMessage());
            }
        }, e -> {
            progressDialog.dismiss();
            errorToast();
        }));
    }
}