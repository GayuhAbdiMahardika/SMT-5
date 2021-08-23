package com.example.massagedialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void btnToast(View view) {
        Toast.makeText(this, "Oe", Toast.LENGTH_SHORT).show();
    }

    public void alertDialog(View view) {
        AlertDialog.Builder buatAlert = new AlertDialog.Builder(this);
        buatAlert.setTitle("Warning!");
        buatAlert.setMessage("Wiuwiuwi");
        buatAlert.show();
    }

    public void btnAlertDialog(View view) {
        AlertDialog.Builder buatAlert = new AlertDialog.Builder(this);
        buatAlert.setTitle("Warning 2");
        buatAlert.setMessage("Wiuwiuwi");
        buatAlert.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Tidak", Toast.LENGTH_SHORT).show();
            }
        });
        buatAlert.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Ya", Toast.LENGTH_SHORT).show();
            }
        });
        buatAlert.show();
    }
}