package com.example.kalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText bil_1, bil_2;
    TextView hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void hitung(View view) {
       bil_1 = findViewById(R.id.etBil1);
       bil_2 = findViewById(R.id.etBil2);
       hasil = findViewById(R.id.tvHasil);

       if(bil_1.getText().toString().equals("")){
           bil_1.setError("Harus di isi");
       } if(bil_2.getText().toString().equals("")){
           bil_2.setError("Harus di isi");
       } else {
            double a = Double.parseDouble(bil_1.getText().toString());
            double b = Double.parseDouble(bil_2.getText().toString());

            Kalkulator calc = new Kalkulator();

            double c = calc.perkalian(a,b);

            hasil.setText(c+"");
        }


    }
}