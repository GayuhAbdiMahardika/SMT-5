package com.example.kalkulator2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvHasil;
    EditText et1,et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        load();
    }

    public void load(){
        tvHasil = findViewById(R.id.tvHasil);
        et1 = findViewById(R.id.etBil_1);
        et2 = findViewById(R.id.etBil_2);
    }

    public void tambah(View view) {
        if(et1.getText().toString().equals("") || et2.getText().toString().equals("")){
            Toast.makeText(this, "Ada bilangan yg kosong", Toast.LENGTH_SHORT).show();
        }else{
            double bil1 = Double.parseDouble(et1.getText().toString());
            double bil2 = Double.parseDouble(et2.getText().toString());
            double hasil = bil1+bil2;

            tvHasil.setText(hasil+"");
        }
    }

    public void kurang(View view) {
        if(et1.getText().toString().equals("") || et2.getText().toString().equals("")){
            Toast.makeText(this, "Ada bilangan yg kosong", Toast.LENGTH_SHORT).show();
        }else{
            double bil1 = Double.parseDouble(et1.getText().toString());
            double bil2 = Double.parseDouble(et2.getText().toString());
            double hasil = bil1-bil2;

            tvHasil.setText(hasil+"");
        }
    }

    public void kali(View view) {
        if(et1.getText().toString().equals("") || et2.getText().toString().equals("")){
            Toast.makeText(this, "Ada bilangan yg kosong", Toast.LENGTH_SHORT).show();
        }else{
            double bil1 = Double.parseDouble(et1.getText().toString());
            double bil2 = Double.parseDouble(et2.getText().toString());
            double hasil = bil1*bil2;

            tvHasil.setText(hasil+"");
        }
    }

    public void bagi(View view) {
        if(et1.getText().toString().equals("") || et2.getText().toString().equals("")){
            Toast.makeText(this, "Ada bilangan yg kosong", Toast.LENGTH_SHORT).show();
        }else{
            double bil1 = Double.parseDouble(et1.getText().toString());
            double bil2 = Double.parseDouble(et2.getText().toString());
            double hasil = bil1/bil2;

            tvHasil.setText(hasil+"");
        }
    }
}