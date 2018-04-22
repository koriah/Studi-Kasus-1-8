package com.koriah.kasus_8;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class KirimPesan extends AppCompatActivity {

    TextView NamaPemesan, Makanan, Minuman, Tanggal, Waktu, Tempat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kirim_pesan);

        NamaPemesan = (TextView) findViewById(R.id.teks5);
        Makanan = (TextView) findViewById(R.id.teks6);
        Minuman = (TextView) findViewById(R.id.teks7);
        Tanggal = (TextView) findViewById(R.id.teks8);
        Waktu   = (TextView) findViewById(R.id.teks9);
        Tempat  = (TextView) findViewById(R.id.teks10);

        NamaPemesan.setText("Nama Pemesan : \n" + getIntent().getStringExtra("NmPemesan"));
        Makanan.setText("Makanan : \n" + getIntent().getStringExtra("Makanan"));
        Minuman.setText("Minuman : \n" + getIntent().getStringExtra("Minuman"));
        Waktu.setText("Waktu : \n" + getIntent().getStringExtra("Waktu"));
        Tanggal.setText("Tanggal : \n" + getIntent().getStringExtra("Tanggal"));
        Tempat.setText("Tempat : \n" + getIntent().getStringExtra("Tempat"));

        Button tutup = (Button) findViewById(R.id.btn7);
        tutup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
            }
        });
    }

    public void onClick(View view) {
    }
}
