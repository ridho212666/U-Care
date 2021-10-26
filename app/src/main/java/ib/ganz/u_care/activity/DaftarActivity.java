package ib.ganz.u_care.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import ib.ganz.u_care.R;

public class DaftarActivity extends AppCompatActivity {

    TextView bOrtu, bTenagaKesehatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        bOrtu = findViewById(R.id.bOrtu);
        bTenagaKesehatan = findViewById(R.id.bTenagaKesehatan);

        bOrtu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DaftarActivity.this, PendaftaranOrtuActivity.class);
                startActivity(i);
            }
        });

        bTenagaKesehatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DaftarActivity.this, PendaftaranNakesActivity.class);
                startActivity(i);
            }
        });
    }
}