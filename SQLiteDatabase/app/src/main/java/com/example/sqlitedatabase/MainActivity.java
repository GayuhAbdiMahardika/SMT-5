package com.example.sqlitedatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
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
    String idBarang;
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
//                pesan("upd");
                String sql = "UPDATE tblbarang SET barang = '"+barang+"', stok = "+stok+", harga = "+harga+" WHERE idbarang = "+idBarang+";";
                if(db.runSQL(sql)){
                    pesan("Update berhasil");
                    selectData();
                }else{
                    pesan("Update gagal");
                }

            }
        }

        etBarang.setText("");
        etHarga.setText("");
        etStok.setText("");
        tvPilihan.setText("insert");
    }

    public void pesan(String isi){
        Toast.makeText(this, isi, Toast.LENGTH_SHORT).show();
    }

    public void selectData(){
        String sql = "SELECT * FROM tblbarang ORDER BY barang ASC";
        Cursor cursor = db.select(sql);
//        pesan(cursor.getCount()+"");
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
        idBarang = id;


        AlertDialog.Builder al = new AlertDialog.Builder(this);
        al.setTitle("Peringatan");
        al.setMessage("Yakin ingin menghapus data?");
        al.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String sql = "DELETE FROM tblbarang WHERE idbarang = "+idBarang+";";
                if(db.runSQL(sql)){
                    pesan("Data sudah dihapus");
                    selectData();
                } else{
                    pesan("Data tidak bisa dihapus");
                }
            }
        });

        al.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        al.show();


    }

    public void selectUpdate(String id){
        idBarang = id;
        String sql = "SELECT * FROM tblbarang WHERE idbarang = "+id+" ;";
//        pesan(sql);
        Cursor cursor = db.select(sql);
        cursor.moveToNext();
//        pesan(cursor.getString(cursor.getColumnIndex("barang")));
        etBarang.setText(cursor.getString(cursor.getColumnIndex("barang")));
        etHarga.setText(cursor.getString(cursor.getColumnIndex("harga")));
        etStok.setText(cursor.getString(cursor.getColumnIndex("stok")));

        tvPilihan.setText("update");
    }
}