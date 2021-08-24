package com.example.sqlitedatabase;

public class Barang {
    private String idBarang, barang, stok, harga;

    public Barang(String idbarang, String barang, String stok, String harga) {
        this.idBarang = idbarang;
        this.barang = barang;
        this.stok = stok;
        this.harga = harga;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getBarang() {
        return barang;
    }

    public void setBarang(String barang) {
        this.barang = barang;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
