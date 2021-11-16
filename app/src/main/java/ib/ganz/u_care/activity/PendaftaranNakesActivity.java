package ib.ganz.u_care.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import ib.ganz.u_care.R;
import ib.ganz.u_care.dataclass.NakesData;
import ib.ganz.u_care.manager.SessionManager;
import ib.ganz.u_care.network.Service;

public class PendaftaranNakesActivity extends BaseActivity {

    private Spinner spinProfesi;
    private TextInputLayout tilNama, tilNoHp, tilNamaInstitusi, tilNamaPengguna, tilKataKunci, tilKonfirmasiKataKunci;
    private TextInputEditText eNama, eNoHp, eNamaInstitusi, eNamaPengguna, eKataKunci, eKonfirmasiKataKunci;
    private View bDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendaftaran_nakes);

        tilNama = findViewById(R.id.tilNama);
        tilNoHp = findViewById(R.id.tilNoHp);
        tilNamaInstitusi = findViewById(R.id.tilNamaInstitusi);
        tilNamaPengguna = findViewById(R.id.tilNamaPengguna);
        tilKataKunci = findViewById(R.id.tilKataKunci);
        tilKonfirmasiKataKunci = findViewById(R.id.tilKonfirmasiKataKunci);
        eNama = findViewById(R.id.eNama);
        eNoHp = findViewById(R.id.eNoHp);
        eNamaInstitusi = findViewById(R.id.eNamaInstitusi);
        eNamaPengguna = findViewById(R.id.eNamaPengguna);
        eKataKunci = findViewById(R.id.eKataKunci);
        eKonfirmasiKataKunci = findViewById(R.id.eKonfirmasiKataKunci);
        bDaftar = findViewById(R.id.bDaftar);

        spinProfesi = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, getResources()
                .getStringArray(R.array.Profesi));

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinProfesi.setAdapter(adapter);

        bDaftar.setOnClickListener(x -> {
            if (valid()) {
                register();
            }
        });
    }

    private boolean valid() {
        tilNama.setError(null);
        tilNoHp.setError(null);
        tilNamaInstitusi.setError(null);
        tilNamaPengguna.setError(null);
        tilKataKunci.setError(null);
        tilKonfirmasiKataKunci.setError(null);

        if (eNama.getText().toString().isEmpty()) {
            tilNama.setError("Masukkan nama dulu");
            return false;
        }
        if (eNoHp.getText().toString().isEmpty()) {
            tilNoHp.setError("Masukkan no handphone dulu");
            return false;
        }
        if (eNamaInstitusi.getText().toString().isEmpty()) {
            tilNamaInstitusi.setError("Masukkan nama institusi dulu");
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
        if (eKataKunci.getText().toString().length() < 6) {
            tilKataKunci.setError("Kata kunci minimal 6 karakter");
            return false;
        }
        if (eKonfirmasiKataKunci.getText().toString().isEmpty()) {
            tilKonfirmasiKataKunci.setError("Masukkan konfirmasi kata kunci dulu");
            return false;
        }
        if (!eKonfirmasiKataKunci.getText().toString().equals(eKataKunci.getText().toString())) {
            tilKonfirmasiKataKunci.setError("Konfirmasi kata kunci tidak cocok");
            return false;
        }

        return true;
    }

    private void register() {
        NakesData n = new NakesData();
        n.setNama(eNama.getText().toString());
        n.setProfesi((String) spinProfesi.getSelectedItem());
        n.setNoHp(eNoHp.getText().toString());
        n.setNamaInstitusi(eNamaInstitusi.getText().toString());
        n.setNamaPengguna(eNamaPengguna.getText().toString());
        n.setKataKunci(eKataKunci.getText().toString());
        n.setFirebaseToken(SessionManager.getToken());

        progressDialog.show();
        compositeDisposable.add(Service.registerNakes(n).subscribe(r -> {
            progressDialog.dismiss();
            if (r.isSuccess()) {
                SessionManager.loginNakes(r.getData());
                toast("Register berhasil");

                Intent i = new Intent(this, OtpActivity.class);
                i.putExtra("no_hp", eNoHp.getText().toString());
                i.putExtra("id", r.getData().getId());
                i.putExtra("ortu", false);
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