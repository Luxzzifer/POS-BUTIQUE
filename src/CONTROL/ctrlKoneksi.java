/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ctrlKoneksi {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            String DB ="jdbc:mysql://localhost/butik";
            String user="root";
            String pass="";
            conn = (Connection) DriverManager.getConnection(DB,user,pass);
            System.out.println("Berhasil");
        } catch (SQLException e) {
            System.err.println("Gagal karena " + e);
        }
        return conn;

    }
    
    public static void main(String[] args) {
        getConnection();
    }
}
