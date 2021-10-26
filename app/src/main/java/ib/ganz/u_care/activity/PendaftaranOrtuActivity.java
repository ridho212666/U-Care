package ib.ganz.u_care.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import ib.ganz.u_care.R;

public class PendaftaranOrtuActivity extends AppCompatActivity {
    private Spinner spinJenisKelamin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendaftaran_ortu);
        spinJenisKelamin = findViewById(R.id.spinner);

        spinJenisKelamin = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, getResources()
                .getStringArray(R.array.Kelamin));

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinJenisKelamin.setAdapter(adapter);

        spinJenisKelamin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

    }
}