package gui.dialog.spasilac;

import domen.Spasilac;
import java.util.List;
import javax.swing.JOptionPane;
import kontroler.SpasilacKontroler;
import model.SpasilacTableModel;

public final class SpasilacDialog extends javax.swing.JDialog {

    private List<Spasilac> spasioci;

    public SpasilacDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);

        popuniTabelu();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSpasioci = new javax.swing.JTable();
        btnKreiraj = new javax.swing.JButton();
        btnDetalji = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Rad sa spasiocima"));

        tblSpasioci.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblSpasioci);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnKreiraj.setText("Kreiraj spasioca");
        btnKreiraj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKreirajActionPerformed(evt);
            }
        });

        btnDetalji.setText("Detalji");
        btnDetalji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetaljiActionPerformed(evt);
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
                .addGap(15, 15, 15)
                .addComponent(btnDetalji)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKreiraj)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDetaljiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetaljiActionPerformed
        ucitajSpasioca();
    }//GEN-LAST:event_btnDetaljiActionPerformed

    private void btnKreirajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKreirajActionPerformed
        kreirajSpasioca();
    }//GEN-LAST:event_btnKreirajActionPerformed

    protected void popuniTabelu() {
        spasioci = SpasilacKontroler.getInstanca().ucitajListuSpasioca(spasioci);

        SpasilacTableModel stm = new SpasilacTableModel(spasioci);
        tblSpasioci.setModel(stm);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetalji;
    private javax.swing.JButton btnKreiraj;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSpasioci;
    // End of variables declaration//GEN-END:variables

    private void ucitajSpasioca() {
        int red = tblSpasioci.getSelectedRow();
        if (red != -1) {
            String ime = (String) tblSpasioci.getValueAt(red, 0);
            String prezime = (String) tblSpasioci.getValueAt(red, 1);
            String jmbg = (String) tblSpasioci.getValueAt(red, 2);

            Spasilac spasilac = new Spasilac();
            spasilac.setIme(ime);
            spasilac.setPrezime(prezime);
            spasilac.setJmbg(jmbg);

            spasilac = SpasilacKontroler.getInstanca().ucitajSpasioca(spasilac);
            JOptionPane.showMessageDialog(this, "Sistem je ucitao spasioca", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            new DetaljiSpasiocaDialog(null, true, spasilac, this).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da ucita spasioca", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void kreirajSpasioca() {
        new KreirajSpasiocaDialog(null, true, this).setVisible(true);
    }
}
