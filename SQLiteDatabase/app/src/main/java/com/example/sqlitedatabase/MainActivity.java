package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Database db;
    EditText etBarang, etHarga, etStok;
    TextView tvPilihan;
    RecyclerView rcvBarang;

    List<Barang> dataBarang = new ArrayList<Barang>();
    BarangAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        load();
        selectData();
    }

    public void load(){
        db = new Database(this);
        db.buatTabel();

        etBarang = findViewById(R.id.etBarang);
        etHarga = findViewById(R.id.etHarga);
        etStok = findViewById(R.id.etStok);
        tvPilihan = findViewById(R.id.tvPilihan);
        rcvBarang = findViewById(R.id.rcvBarang);

        rcvBarang.setLayoutManager(new LinearLayoutManager(this));
        rcvBarang.setHasFixedSize(true);
    }

    public void simpan(View v) {
        String barang = etBarang.getText().toString();
        String stok = etStok.getText().toString();
        String harga = etHarga.getText().toString();
        String pilihan = tvPilihan.getText().toString();

        if(barang.isEmpty() || stok.isEmpty() || harga.isEmpty()){
            pesan("Data Kosong");
        }else{
            if(pilihan.equals("insert")){
                String sql = "INSERT INTO tblbarang (barang,stok,harga) VALUES ('"+barang+"',"+stok+","+harga+")";
                if(db.runSQL(sql)){
                    pesan("insert successed");
                    selectData();
                }else{
                    pesan("insert failed");
                }
//                pesan("ins");
            }else{
                pesan("upd");
            }
        }

        etBarang.setText("");
        etHarga.setText("");
        etStok.setText("");
    }

    public void pesan(String isi){
        Toast.makeText(this, isi, Toast.LENGTH_SHORT).show();
    }

    public void selectData(){
        String sql = "SELECT * FROM tblbarang ORDER BY barang ASC";
        Cursor cursor = db.select(sql);
        pesan(cursor.getCount()+"");
        dataBarang.clear();
        if(cursor.getCount() > 0){
            while (cursor.moveToNext()){
                String idBarang = cursor.getString(cursor.getColumnIndex("idbarang"));
                String barang = cursor.getString(cursor.getColumnIndex("barang"));
                String harga = cursor.getString(cursor.getColumnIndex("harga"));
                String stok = cursor.getString(cursor.getColumnIndex("stok"));

                dataBarang.add(new Barang(idBarang,barang,stok,harga));
            }

            adapter = new BarangAdapter(this, dataBarang);
            rcvBarang.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }else{
            pesan("Data Kosong");
        }
    }

    public void deleteData(String id){
        String idBarang = id;
        String sql = "DELETE FROM tblbarang WHERE idbarang = "+idBarang+";";
        if(db.runSQL(sql)){
            pesan("Data sudah dihapus");
            selectData();
        } else{
            pesan("Data tidak bisa dihapus");
        }
    }
}