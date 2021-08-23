package com.example.konversisuhu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    EditText etBil;
    TextView tvHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        load();
//        isiSpinner();
    }

    public void load(){
        spinner =findViewById(R.id.spinner);
        etBil = findViewById(R.id.etBil);
        tvHasil = findViewById(R.id.tvHasil);
    }

//    public void isiSpinner(){
//        String[] isi = {"C to F","c to R","C to K"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, isi);
//        spinner.setAdapter(adapter);
//    }

    public void btnKonversi(View view) {
        String pilihan = spinner.getSelectedItem().toString();
        if(etBil.getText().toString().equals("")){
            Toast.makeText(this, "Isikan suhu yg akan dikonversi", Toast.LENGTH_SHORT).show();
        }else if(pilihan.equals("Celcius to Reamur")){
            ctor();
        }else if(pilihan.equals("Celcius to Farenheit")){
            ctof();
        }else if(pilihan.equals("Celcius to Kelvin")){
            ctok();
        }else if(pilihan.equals("Reamur to Celcius")){
            rtoc();
        }else if(pilihan.equals("Reamur to Farenheit")){
            rtof();
        }

    }

    public void ctor(){
        double bil = Double.parseDouble( etBil.getText().toString());
        double hasil = (4.0/5.0)*bil;

        tvHasil.setText(hasil+"");
    }
    public void ctof(){
        double bil = Double.parseDouble( etBil.getText().toString());
        double hasil = (bil * 9.0/5.0)+32;

        tvHasil.setText(hasil+"");
    }
    public void ctok(){
        double bil = Double.parseDouble( etBil.getText().toString());
        double hasil = bil+273.5;

        tvHasil.setText(hasil+"");
    }
    public void rtoc(){
        double bil = Double.parseDouble( etBil.getText().toString());
        double hasil = (bil -  491.67)*5.0/9.0;

        tvHasil.setText(hasil+"");
    }
    public void rtof(){
        double bil = Double.parseDouble( etBil.getText().toString());
        double hasil = bil - 459.67;

        tvHasil.setText(hasil+"");
    }
}











