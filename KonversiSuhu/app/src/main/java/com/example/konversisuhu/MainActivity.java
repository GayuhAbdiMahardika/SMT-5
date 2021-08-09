package com.example.konversisuhu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        load();
        isiSpinner();
    }

    public void load(){
        spinner =findViewById(R.id.spinner);
    }

    public void isiSpinner(){
        String[] isi = {"C to F","c to R","C to K"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, isi);
        spinner.setAdapter(adapter);
    }

    public void btnKonversi(View view) {
        String pilihan = spinner.getSelectedItem().toString();
        Toast.makeText(this, pilihan, Toast.LENGTH_SHORT).show();
    }
}











