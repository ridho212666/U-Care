package ib.ganz.u_care.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ib.ganz.u_care.R;

public class LoginActivity extends AppCompatActivity {
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

        tDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, DaftarActivity.class);
                startActivity(i);
            }
        });
    }
}