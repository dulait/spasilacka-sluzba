package gui.dialog.angazovanje;

import domen.Angazovanje;
import javax.swing.JOptionPane;
import kontroler.AngazovanjeKontroler;

/**
 * Dialog that renders the details of a selected Angazovanje instance.
 *
 * Upon loading it renders the selected Angazovanje instance from the
 * {@code AngazovanjeDialog}
 *
 * @author dulait
 */
public class DetaljiAngazovanjaDialog extends javax.swing.JDialog {

    private final Angazovanje angazovanje;
    private final AngazovanjeDialog dialog;

    public DetaljiAngazovanjaDialog(java.awt.Frame parent, boolean modal, Angazovanje angazovanje, AngazovanjeDialog dialog) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);

        this.angazovanje = angazovanje;
        this.dialog = dialog;

        popuniCb();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbSpasilac = new javax.swing.JComboBox();
        cmbSmena = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cmbRaspored = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        btnObrisi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Rad sa angažovanjima");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalji učitanog angažovanja"));

        jLabel1.setText("Spasilac:");

        cmbSpasilac.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbSpasilac.setEnabled(false);

        cmbSmena.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbSmena.setEnabled(false);

        jLabel2.setText("Smena:");

        cmbRaspored.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbRaspored.setEnabled(false);

        jLabel3.setText("Raspored:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(cmbRaspored, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cmbSmena, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(cmbSpasilac, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbSpasilac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbSmena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbRaspored, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        btnObrisi.setText("Obriši");
        btnObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addComponent(btnObrisi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnObrisi)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiActionPerformed
        obrisiAngazovanje();
    }//GEN-LAST:event_btnObrisiActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnObrisi;
    private javax.swing.JComboBox cmbRaspored;
    private javax.swing.JComboBox cmbSmena;
    private javax.swing.JComboBox cmbSpasilac;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    private void popuniCb() {
        popuniCBSpasioca();
        popuniCBSmena();
        popuniCBRasporeda();
    }

    private void popuniCBSpasioca() {
        cmbSpasilac.removeAllItems();
        cmbSpasilac.addItem(angazovanje.getSpasilac());
    }

    private void popuniCBSmena() {
        cmbSmena.removeAllItems();
        cmbSmena.addItem(angazovanje.getSmena());
    }

    private void popuniCBRasporeda() {
        cmbRaspored.removeAllItems();
        cmbRaspored.addItem(angazovanje.getRaspored());
    }

    private void obrisiAngazovanje() {
        if (AngazovanjeKontroler.getInstanca().obrisiAngazovanje(angazovanje)) {
            JOptionPane.showMessageDialog(this, "Sistem je obrisao angazovanje", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            dialog.popuniTabelu();
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da obrise angazovanje", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }
}
