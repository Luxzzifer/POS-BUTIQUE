/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package VIEW;

import CONTROL.ctrlBarang;
import MODEL.model_barang;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import CONTROL.ctrlTransaksi;
import model.model_transaksi;
import model.model_detailtransaksi;
import java.sql.SQLException;

public class TRANSAKSI extends javax.swing.JFrame {

    private final ctrlTransaksi ctrlTransaksi;

    /**
     * Creates new form TRANSAKSI
     */
    public TRANSAKSI() {
        initComponents();
        updateTable();
        ctrlTransaksi = new ctrlTransaksi();

    }

    private void tambahkanItemKeKeranjang() {
        int selectedRow = jtabelbarang.getSelectedRow();
        if (selectedRow != -1) {
            String kode = jtabelbarang.getValueAt(selectedRow, 0).toString();
            String nama = jtabelbarang.getValueAt(selectedRow, 1).toString();
            String harga = jtabelbarang.getValueAt(selectedRow, 3).toString();
            String qtyStr = txtjumlah.getText();

            // Validasi jumlah
            if (qtyStr.isEmpty() || !qtyStr.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Masukkan jumlah barang dengan benar.");
                return;
            }

            int qty = Integer.parseInt(qtyStr);
            int stok = Integer.parseInt(jtabelbarang.getValueAt(selectedRow, 2).toString());

            // Validasi stok yang cukup
            if (qty > stok) {
                JOptionPane.showMessageDialog(this, "Stok tidak mencukupi.");
                return;
            }

            // Hitung total harga
            int hargaSatuan = Integer.parseInt(harga);
            int total = hargaSatuan * qty;

            // Tambahkan ke tabel keranjang
            DefaultTableModel model = (DefaultTableModel) jtabelkerjang.getModel();
            model.addRow(new Object[]{kode, nama, qty, hargaSatuan, total});

            // Hitung ulang total harga
            hitungTotalHarga();

            // Bersihkan input jumlah
            txtjumlah.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Pilih barang terlebih dahulu.");
        }
    }

    private void hitungTotalHarga() {
        int totalHarga = 0;
        DefaultTableModel model = (DefaultTableModel) jtabelkerjang.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            int hargaItem = Integer.parseInt(model.getValueAt(i, 4).toString()); // Assuming index 4 is the column for total price
            totalHarga += hargaItem;
        }
        txttotalharga.setText(String.valueOf(totalHarga));
    }

    private void hitungKembalian() {
        try {
            int totalHarga = Integer.parseInt(txttotalharga.getText());
            int bayar = Integer.parseInt(txtbayar.getText());

            if (bayar < totalHarga) {
                JOptionPane.showMessageDialog(this, "Jumlah pembayaran tidak mencukupi.");
                return;
            }

            int kembalian = bayar - totalHarga;
            txtjumlahkembalian.setText(String.valueOf(kembalian));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan jumlah pembayaran dengan benar.");
        }
    }

   private void prosesCheckout() {
    try {
        // Mendapatkan total harga dari text field
        int totalHarga = Integer.parseInt(txttotalharga.getText());
        // Mendapatkan jumlah pembayaran dari text field
        int bayar = Integer.parseInt(txtbayar.getText());

        // Simpan transaksi dan dapatkan ID transaksi yang baru disimpan
        model_transaksi transaksi = ctrlTransaksi.simpanTransaksi(totalHarga);
        int idTransaksi = transaksi.getId();

        // Simpan detail transaksi
        DefaultTableModel model = (DefaultTableModel) jtabelkerjang.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            // Mendapatkan detail barang dari tabel
            String kodeBarang = model.getValueAt(i, 0).toString();
            String namaBarang = model.getValueAt(i, 1).toString();
            int jumlah = Integer.parseInt(model.getValueAt(i, 2).toString());
            int harga = Integer.parseInt(model.getValueAt(i, 3).toString());

            // Menyimpan detail transaksi
            ctrlTransaksi.simpanDetailTransaksi(idTransaksi, kodeBarang, namaBarang, jumlah, harga);
        }

        // Reset UI setelah checkout
        model.setRowCount(0); // Menghapus semua baris dalam tabel keranjang
        txttotalharga.setText(""); // Mengosongkan field total harga
        txtbayar.setText(""); // Mengosongkan field bayar
        txtjumlahkembalian.setText(""); // Mengosongkan field jumlah kembalian

        // Menampilkan pesan sukses
        JOptionPane.showMessageDialog(this, "Transaksi berhasil disimpan.");
        
    } catch (SQLException ex) {
        // Menampilkan pesan kesalahan jika terjadi SQLException
        JOptionPane.showMessageDialog(this, "Gagal menyimpan transaksi: " + ex.getMessage());
    } catch (NumberFormatException ex) {
        // Menampilkan pesan kesalahan jika terjadi kesalahan format angka
        JOptionPane.showMessageDialog(this, "Kesalahan dalam format angka: " + ex.getMessage());
    }
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtabelkerjang = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtabelbarang = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txttotalharga = new javax.swing.JTextField();
        txtbayar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtjumlahkembalian = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtjumlah = new javax.swing.JTextField();
        cekout = new javax.swing.JButton();
        tambah = new javax.swing.JButton();
        back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 600));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(248, 244, 225));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 600));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTO/transaksi navigator .png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setPreferredSize(new java.awt.Dimension(250, 600));

        jLabel4.setBackground(new java.awt.Color(114, 86, 69));
        jLabel4.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 30)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(114, 86, 69));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("TRANSAKSI");
        jLabel4.setToolTipText("");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtabelkerjang.setForeground(new java.awt.Color(116, 81, 45));
        jtabelkerjang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "kode", "nama", "jumlah", "harga ", "total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtabelkerjang.setGridColor(new java.awt.Color(248, 244, 225));
        jtabelkerjang.setSelectionBackground(new java.awt.Color(116, 81, 45));
        jtabelkerjang.setSelectionForeground(new java.awt.Color(248, 244, 225));
        jScrollPane2.setViewportView(jtabelkerjang);
        if (jtabelkerjang.getColumnModel().getColumnCount() > 0) {
            jtabelkerjang.getColumnModel().getColumn(0).setResizable(false);
            jtabelkerjang.getColumnModel().getColumn(1).setResizable(false);
            jtabelkerjang.getColumnModel().getColumn(2).setResizable(false);
            jtabelkerjang.getColumnModel().getColumn(3).setResizable(false);
            jtabelkerjang.getColumnModel().getColumn(4).setResizable(false);
        }

        jtabelbarang.setForeground(new java.awt.Color(116, 81, 45));
        jtabelbarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "kode", "nama", "harga", "stok", "size"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtabelbarang.setGridColor(new java.awt.Color(248, 244, 225));
        jtabelbarang.setSelectionBackground(new java.awt.Color(116, 81, 45));
        jtabelbarang.setSelectionForeground(new java.awt.Color(248, 244, 225));
        jScrollPane3.setViewportView(jtabelbarang);
        if (jtabelbarang.getColumnModel().getColumnCount() > 0) {
            jtabelbarang.getColumnModel().getColumn(0).setResizable(false);
            jtabelbarang.getColumnModel().getColumn(1).setResizable(false);
            jtabelbarang.getColumnModel().getColumn(2).setResizable(false);
            jtabelbarang.getColumnModel().getColumn(3).setResizable(false);
            jtabelbarang.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(114, 86, 69));
        jLabel2.setText("KEMBALIAN");

        txttotalharga.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txttotalharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttotalhargaActionPerformed(evt);
            }
        });

        txtbayar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbayarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(114, 86, 69));
        jLabel3.setText("TABEL STOK");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(114, 86, 69));
        jLabel5.setText("TOTAL");

        txtjumlahkembalian.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtjumlahkembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtjumlahkembalianActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(114, 86, 69));
        jLabel6.setText("BAYAR");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(114, 86, 69));
        jLabel7.setText("QTY :");

        cekout.setBackground(new java.awt.Color(114, 86, 69));
        cekout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cekout.setForeground(new java.awt.Color(248, 244, 225));
        cekout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTO/store.png"))); // NOI18N
        cekout.setText("CHEKOUT");
        cekout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekoutActionPerformed(evt);
            }
        });

        tambah.setBackground(new java.awt.Color(114, 86, 69));
        tambah.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tambah.setForeground(new java.awt.Color(248, 244, 225));
        tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTO/plus.png"))); // NOI18N
        tambah.setText("TAMBAH");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });

        back.setBackground(new java.awt.Color(248, 244, 225));
        back.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        back.setForeground(new java.awt.Color(248, 244, 225));
        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FOTO/backbuttonn.png"))); // NOI18N
        back.setBorder(null);
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(jLabel3))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cekout, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtjumlahkembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txttotalharga, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttotalharga, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtjumlahkembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cekout, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbayarActionPerformed
        hitungKembalian();

    }//GEN-LAST:event_txtbayarActionPerformed

    private void txtjumlahkembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtjumlahkembalianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtjumlahkembalianActionPerformed

    private void cekoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekoutActionPerformed
        prosesCheckout();    }//GEN-LAST:event_cekoutActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        tambahkanItemKeKeranjang();

    }//GEN-LAST:event_tambahActionPerformed

    private void txttotalhargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttotalhargaActionPerformed
        hitungTotalHarga();
    }//GEN-LAST:event_txttotalhargaActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        new DASHBOARD().setVisible(true);
        dispose();        dispose();    }//GEN-LAST:event_backActionPerformed
    public void updateTable() {
        ctrlBarang controller = new ctrlBarang();
        List<model_barang> listBarang = controller.getAllBarang();

        // Membuat model tabel baru
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Kode", "Nama", "Stok", "Harga", "Size"});

        // Memasukkan data barang ke dalam model tabel
        for (model_barang barang : listBarang) {
            model.addRow(new Object[]{barang.getKode(), barang.getNama(), barang.getStok(), barang.getHarga(), barang.getSize()});
        }

        // Menyimpan model tabel baru ke tabelbarang
        jtabelbarang.setModel(model);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TRANSAKSI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TRANSAKSI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TRANSAKSI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TRANSAKSI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TRANSAKSI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JButton cekout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jtabelbarang;
    private javax.swing.JTable jtabelkerjang;
    private javax.swing.JButton tambah;
    private javax.swing.JTextField txtbayar;
    private javax.swing.JTextField txtjumlah;
    private javax.swing.JTextField txtjumlahkembalian;
    private javax.swing.JTextField txttotalharga;
    // End of variables declaration//GEN-END:variables
}
