package com.example.sharedprefrence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etBarang, etStok;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        load();
    }

    public void load(){
        etBarang = findViewById(R.id.etBarang);
        etStok = findViewById(R.id.etStok);
        sharedPreferences = getSharedPreferences("barang", MODE_PRIVATE);
    }

    public  void isiSharedPrefrence(String barang, float stok){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("barang", barang);
        editor.putFloat("stok",stok);
        editor.apply();


    }

    public void simpan(View view) {
        String barang = etBarang.getText().toString();
        float stok = Float.parseFloat(etStok.getText().toString());

        if(barang.isEmpty() || stok == 0.0){
            Toast.makeText(this, "Data Kosong", Toast.LENGTH_SHORT).show();
        }else{
            isiSharedPrefrence(barang,stok);
            etBarang.setText("");
            etStok.setText("");
            Toast.makeText(this, "Disimpan", Toast.LENGTH_SHORT).show();
        }
    }

    public void tampil(View view) {
        String barang = sharedPreferences.getString("barang","");
        float stok = sharedPreferences.getFloat("stok",0);

        etBarang.setText(barang);
        etStok.setText(stok+"");
    }
}