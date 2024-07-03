package model;

public class model_detailtransaksi {
    private int id;
    private int idTransaksi;
    private String kodeBarang;
    private String namaBarang;
    private int jumlah;
    private int harga;

    public model_detailtransaksi(int id, int idTransaksi, String kodeBarang, String namaBarang, int jumlah, int harga) {
        this.id = id;
        this.idTransaksi = idTransaksi;
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.jumlah = jumlah;
        this.harga = harga;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

  
}
