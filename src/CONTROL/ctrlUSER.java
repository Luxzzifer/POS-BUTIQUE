/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROL;

import MODEL.model_user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author steven
 */
public class ctrlUSER {

    private Connection conn;

    public ctrlUSER() {
        this.conn = Control.ctrlKoneksi.getConnection();
    }

    public model_user save(String username, String Password) {
        model_user user = null;
        PreparedStatement stm = null;

        try {
            String sql = "INSERT INTO LOGIN (username,password)  VALUES (?,?)";
            stm = conn.prepareStatement(sql);

            stm.setString(1, username);
            stm.setString(2, Password);

            int rowinserted = stm.executeUpdate();

            if (rowinserted > 0) {
                JOptionPane.showMessageDialog(null, "Data Berhasil disimpan!", "informasi", JOptionPane.INFORMATION_MESSAGE);
                user = new model_user(username, Password);

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan : " + exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
        return user;
    }

    public boolean delete(String username) {
        PreparedStatement stm = null;
        boolean success = false;

        try {
            String sql = "DELETE FROM login WHERE username=?";
            stm = conn.prepareStatement(sql);

            stm.setString(1, username);

            int rowsdeleted = stm.executeUpdate();

            if (rowsdeleted > 0) {
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus!", "informasi", JOptionPane.INFORMATION_MESSAGE);
                success = true;

            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan : " + exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return success;
    }
    
        public List<model_user> getAllUser() {
        List<model_user> listuser = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT username,password FROM login";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");

                model_user user = new model_user(username,password);
                listuser.add(user);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Menutup ResultSet, PreparedStatement, dan tidak mencakup Connection di sini
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

        return listuser;
    }

}
