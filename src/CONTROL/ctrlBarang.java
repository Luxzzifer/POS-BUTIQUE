package CONTROL;

import MODEL.model_barang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ctrlBarang {

    private Connection conn;

    public ctrlBarang() {
        this.conn = Control.ctrlKoneksi.getConnection();
    }

    // Method untuk menyimpan data barang baru
    public model_barang save(String kode, String nama, int stok, int harga, String size) {
        model_barang barang = null;
        PreparedStatement stmBarang = null;
        PreparedStatement stmStok = null;
        PreparedStatement stmSize = null;

        try {
            String sqlBarang = "INSERT INTO barang (kode, nama, harga) VALUES (?, ?, ?)";
            String sqlStok = "INSERT INTO stok (kode, stok) VALUES (?, ?)";
            String sqlSize = "INSERT INTO size (kode, size) VALUES (?, ?)";

            // Insert into barang
            stmBarang = conn.prepareStatement(sqlBarang);
            stmBarang.setString(1, kode);
            stmBarang.setString(2, nama);
            stmBarang.setInt(3, harga);
            stmBarang.executeUpdate();

            // Insert into stok
            stmStok = conn.prepareStatement(sqlStok);
            stmStok.setString(1, kode);
            stmStok.setInt(2, stok);
            stmStok.executeUpdate();

            // Insert into ukuran
            stmSize = conn.prepareStatement(sqlSize);
            stmSize.setString(1, kode);
            stmSize.setString(2, size);
            stmSize.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            barang = new model_barang(kode, nama, stok, harga, size);

        } catch (SQLException exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (stmBarang != null) {
                try {
                    stmBarang.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmStok != null) {
                try {
                    stmStok.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmSize != null) {
                try {
                    stmSize.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return barang;
    }

    // Method untuk mengedit data barang
    public boolean edit(String kode, String nama, int stok, int harga, String size) {
        PreparedStatement stmBarang = null;
        PreparedStatement stmStok = null;
        PreparedStatement stmSize = null;
        boolean success = false;

        try {
            String sqlBarang = "UPDATE barang SET nama=?, harga=? WHERE kode=?";
            String sqlStok = "UPDATE stok SET stok=? WHERE kode=?";
            String sqlSize = "UPDATE size SET size=? WHERE kode=?";

            // Update barang
            stmBarang = conn.prepareStatement(sqlBarang);
            stmBarang.setString(1, nama);
            stmBarang.setInt(2, harga);
            stmBarang.setString(3, kode);
            stmBarang.executeUpdate();

            // Update stok
            stmStok = conn.prepareStatement(sqlStok);
            stmStok.setInt(1, stok);
            stmStok.setString(2, kode);
            stmStok.executeUpdate();

            // Update size
            stmSize = conn.prepareStatement(sqlSize);
            stmSize.setString(1, size);
            stmSize.setString(2, kode);
            stmSize.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data berhasil diupdate!", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            success = true;
        } catch (SQLException exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (stmBarang != null) {
                try {
                    stmBarang.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmStok != null) {
                try {
                    stmStok.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmSize != null) {
                try {
                    stmSize.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return success;
    }

    // Method untuk menghapus data barang
    public boolean delete(String kode) {
        PreparedStatement stmBarang = null;
        PreparedStatement stmStok = null;
        PreparedStatement stmSize = null;
        boolean success = false;

        try {
            String sqlStok = "DELETE FROM stok WHERE kode=?";
            String sqlSize = "DELETE FROM size WHERE kode=?";
            String sqlBarang = "DELETE FROM barang WHERE kode=?";

            // Delete from stok
            stmStok = conn.prepareStatement(sqlStok);
            stmStok.setString(1, kode);
            stmStok.executeUpdate();

            // Delete from size
            stmSize = conn.prepareStatement(sqlSize);
            stmSize.setString(1, kode);
            stmSize.executeUpdate();

            // Delete from barang
            stmBarang = conn.prepareStatement(sqlBarang);
            stmBarang.setString(1, kode);
            stmBarang.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            success = true;
        } catch (SQLException exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (stmBarang != null) {
                try {
                    stmBarang.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmStok != null) {
                try {
                    stmStok.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmSize != null) {
                try {
                    stmSize.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return success;
    }

    // Method untuk mendapatkan semua barang
    public List<model_barang> getAllBarang() {
        List<model_barang> listBarang = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT b.kode, b.nama, s.stok, b.harga, u.size " +
                         "FROM barang b " +
                         "JOIN stok s ON b.kode = s.kode " +
                         "JOIN size u ON b.kode = u.kode";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                String kode = rs.getString("kode");
                String nama = rs.getString("nama");
                int stok = rs.getInt("stok");
                int harga = rs.getInt("harga");
                String size = rs.getString("size");

                model_barang barang = new model_barang(kode, nama, stok, harga, size);
                listBarang.add(barang);
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

        return listBarang;
    }
}
