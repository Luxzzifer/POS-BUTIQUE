package CONTROL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import model.model_transaksi;
import model.model_detailtransaksi;
import Control.ctrlKoneksi;
import java.util.ArrayList;
import java.util.List;

public class ctrlTransaksi {

    private Connection conn;

    public ctrlTransaksi() {
        this.conn = ctrlKoneksi.getConnection();
    }

public model_transaksi simpanTransaksi(int totalHarga) throws SQLException {
    model_transaksi modeltransaksi =null;
    String sql = "INSERT INTO transaksi (total_harga) VALUES (?)";
    int idTransaksi = -1;
    try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        ps.setInt(1, totalHarga);
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            idTransaksi = rs.getInt(1); // Ambil ID yang di-generate secara otomatis
        } else {
            throw new SQLException("Gagal mendapatkan ID transaksi.");
        }
    }

    // Kembalikan objek model_transaksi dengan ID yang baru dibuat
    return modeltransaksi;
}


    public model_detailtransaksi simpanDetailTransaksi(int idTransaksi, String kodeBarang, String namaBarang, int jumlah, int harga) throws SQLException {
       model_detailtransaksi modeldetailtransaksi = null;
        String sql = "INSERT INTO detail_transaksi (id_transaksi, kode_barang, nama_barang, jumlah, harga) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idTransaksi);
            ps.setString(2, kodeBarang);
            ps.setString(3, namaBarang);
            ps.setInt(4, jumlah);
            ps.setInt(5, harga);
            ps.executeUpdate();
        }
        return modeldetailtransaksi;
        
    }

    public List<model_transaksi> getAlltransaksi() {
        List<model_transaksi> listtransaksi = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM transaksi";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_transaksi");
                int totalHarga = rs.getInt("total_harga");

                model_transaksi transaksi = new model_transaksi(id, totalHarga);
                listtransaksi.add(transaksi);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return listtransaksi;
    }

}
