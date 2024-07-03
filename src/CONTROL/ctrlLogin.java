/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROL;

import MODEL.model_login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Steven
 */
public class ctrlLogin {
    Connection conn;

    public ctrlLogin(Connection conn) {
        this.conn = Control.ctrlKoneksi.getConnection();
    }
    
     public model_login Login(String username, String password) {
        model_login user = null;
        try {
            conn = Control.ctrlKoneksi.getConnection();
            PreparedStatement stm = conn.prepareStatement("SELECT username, password FROM login WHERE username=? AND password=?");
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                user = new model_login(rs.getString("username"), rs.getString("password"));
            }
            rs.close();
            stm.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan " + ex, "error", JOptionPane.ERROR_MESSAGE);
        }
        return user;
    }
     
     
}
