package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Integer count = 0;
    TextView hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        load();
    }

    public void load(){
        hasil = findViewById(R.id.tvHasil);
    }

    public void btnDown(View view) {
        count--;
        hasil.setText(count+"");
    }

    public void btnUp(View view) {
        count++;
        hasil.setText(count+"");
    }
}