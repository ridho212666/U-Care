package ib.ganz.u_care.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ib.ganz.u_care.R;
import ib.ganz.u_care.manager.SessionManager;
import ib.ganz.u_care.network.Service;

public class LoginActivity extends BaseActivity {

    EditText eUsername, ePassword;
    TextView tDaftar;
    TextView tLupaKataKunci;
    TextView bLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        eUsername = findViewById(R.id.eUsername);
        ePassword = findViewById(R.id.ePassword);
        tDaftar = findViewById(R.id.tDaftar);
        tLupaKataKunci = findViewById(R.id.tLupaKataKunci);
        bLogin = findViewById(R.id.bLogin);

        tDaftar.setOnClickListener(view -> {
            Intent i = new Intent(LoginActivity.this, DaftarActivity.class);
            startActivity(i);
        });

        bLogin.setOnClickListener(x -> {
//            if (eUsername.getText().toString().isEmpty()) {
//                toast("Masukkan nama pengguna dulu");
//                return;
//            }
//            if (ePassword.getText().toString().isEmpty()) {
//                toast("Masukkan kata kunci dulu");
//                return;
//            }
//
//            progressDialog.show();
//            compositeDisposable.add(Service.login(eUsername.getText().toString(), ePassword.getText().toString()).subscribe(r -> {
//                progressDialog.dismiss();
//                if (r.isSuccess()) {
//                    if (r.getRole().equals("ortu")) {
//                        SessionManager.loginOrtu(r.getOrtu());
//                    }
//                    else {
//                        SessionManager.loginNakes(r.getNakes());
//                    }
//                    toast("Berhasil login");
//
//                    Intent i = new Intent(this, MainActivity.class);
//                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(i);
//                }
//                else {
//                    toast(r.getMessage());
//                }
//            }, e -> {
//                progressDialog.dismiss();
//                errorToast();
//            }));
        });
    }
}