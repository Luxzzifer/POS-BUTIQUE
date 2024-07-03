package model;

import java.util.Date;
import java.util.List;

public class model_transaksi {

    private int id;
    private int totalHarga;
    private List<model_detailtransaksi> detailTransaksiList;

    public model_transaksi(int id, int totalHarga) {
        this.id = id;
        this.totalHarga = totalHarga;
        this.detailTransaksiList = detailTransaksiList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(int totalHarga) {
        this.totalHarga = totalHarga;
    }

    public List<model_detailtransaksi> getDetailTransaksiList() {
        return detailTransaksiList;
    }

    public void setDetailTransaksiList(List<model_detailtransaksi> detailTransaksiList) {
        this.detailTransaksiList = detailTransaksiList;
    }
    
    
    

}
