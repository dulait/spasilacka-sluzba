package gui.dialog.angazovanje;

import domen.Angazovanje;
import domen.Raspored;
import domen.Smena;
import domen.Spasilac;
import java.util.List;
import javax.swing.JOptionPane;
import kontroler.AngazovanjeKontroler;
import model.AngazovanjeTableModel;

public final class AngazovanjeDialog extends javax.swing.JDialog {

    private List<Angazovanje> angazovanja;

    public AngazovanjeDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);

        popuniTabelu();
    }

    private void ucitajAngazovanje() {
        int red = tblAngazovanja.getSelectedRow();
        if (red != -1) {
            AngazovanjeTableModel atm = (AngazovanjeTableModel) tblAngazovanja.getModel();
            Spasilac spasilac = (Spasilac) atm.getValueAt(red, 0);
            Smena smena = (Smena) atm.getValueAt(red, 1);
            Raspored raspored = (Raspored) atm.getValueAt(red, 2);

            Angazovanje angazovanje = new Angazovanje(spasilac, smena, raspored);
            angazovanje = AngazovanjeKontroler.getInstanca().ucitajAngazovanje(angazovanje);
            JOptionPane.showMessageDialog(this, "Sistem je ucitao angazovanje", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            new DetaljiAngazovanjaDialog(null, true, angazovanje, this).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da ucita angazovanje", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void kreirajAngazovanje() {
        new KreirajAngazovanjeDialog(null, true, this).setVisible(true);
    }

    protected void popuniTabelu() {
        angazovanja = AngazovanjeKontroler.getInstanca().ucitajListuAngazovanja(angazovanja);

        AngazovanjeTableModel atm = new AngazovanjeTableModel(angazovanja);
        tblAngazovanja.setModel(atm);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAngazovanja = new javax.swing.JTable();
        btnDetalji = new javax.swing.JButton();
        btnKreiraj = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Rad sa angažovanjima");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Angažovanja"));

        tblAngazovanja.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblAngazovanja);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnDetalji.setText("Detalji");
        btnDetalji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetaljiActionPerformed(evt);
            }
        });

        btnKreiraj.setText("Kreiraj angažovanje");
        btnKreiraj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKreirajActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnKreiraj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDetalji, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnDetalji)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKreiraj)
                .addContainerGap(288, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDetaljiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetaljiActionPerformed
        ucitajAngazovanje();
    }//GEN-LAST:event_btnDetaljiActionPerformed

    private void btnKreirajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKreirajActionPerformed
        kreirajAngazovanje();
    }//GEN-LAST:event_btnKreirajActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetalji;
    private javax.swing.JButton btnKreiraj;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAngazovanja;
    // End of variables declaration//GEN-END:variables
}
