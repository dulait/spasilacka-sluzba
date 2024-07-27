package gui.dialog.angazovanje;

import domen.Angazovanje;
import domen.Raspored;
import domen.Smena;
import domen.Spasilac;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import kontroler.AngazovanjeKontroler;
import kontroler.RasporedKontroler;
import kontroler.SmenaKontroler;
import kontroler.SpasilacKontroler;

/**
 * Dialog that allows users to create a new Angazovanje instance.
 *
 * Upon loading it renders the selected Angazovanje instance from the
 * {@code AngazovanjeDialog}
 *
 * @author dulait
 */
public class KreirajAngazovanjeDialog extends javax.swing.JDialog {

    private final AngazovanjeDialog dialog;

    public KreirajAngazovanjeDialog(java.awt.Frame parent, boolean modal, AngazovanjeDialog dialog) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);

        this.dialog = dialog;

        popuniCb();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbSpasioci = new javax.swing.JComboBox();
        cmbSmene = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cmbRasporedi = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        btnSacuvaj = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Rad sa angažovanjima");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalji učitanog angažovanja"));

        jLabel1.setText("Spasilac:");

        cmbSpasioci.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbSmene.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Smena:");

        cmbRasporedi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Raspored:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(cmbRasporedi, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cmbSmene, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(cmbSpasioci, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbSpasioci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbSmene, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbRasporedi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        btnSacuvaj.setText("Sačuvaj");
        btnSacuvaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacuvajActionPerformed(evt);
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
                .addComponent(btnSacuvaj)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSacuvaj)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacuvajActionPerformed
        kreirajAngazovanje();
    }//GEN-LAST:event_btnSacuvajActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSacuvaj;
    private javax.swing.JComboBox cmbRasporedi;
    private javax.swing.JComboBox cmbSmene;
    private javax.swing.JComboBox cmbSpasioci;
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
        List<Spasilac> spasioci = new ArrayList<>();
        spasioci = SpasilacKontroler.getInstanca().ucitajListuSpasioca(spasioci);

        cmbSpasioci.removeAllItems();
        for (Spasilac s : spasioci) {
            cmbSpasioci.addItem(s);
        }

    }

    private void popuniCBSmena() {
        List<Smena> smene = new ArrayList<>();
        smene = SmenaKontroler.getInstanca().ucitajListuSmena(smene);

        cmbSmene.removeAllItems();
        for (Smena s : smene) {
            cmbSmene.addItem(s);
        }
    }

    private void popuniCBRasporeda() {
        List<Raspored> rasporedi = new ArrayList<>();
        rasporedi = RasporedKontroler.getInstanca().ucitajListuRasporeda(rasporedi);

        cmbRasporedi.removeAllItems();
        for (Raspored r : rasporedi) {
            cmbRasporedi.addItem(r);
        }
    }

    private void kreirajAngazovanje() {
        Spasilac spasilac = (Spasilac) cmbSpasioci.getSelectedItem();
        Smena smena = (Smena) cmbSmene.getSelectedItem();
        Raspored raspored = (Raspored) cmbRasporedi.getSelectedItem();

        Angazovanje angazovanje = new Angazovanje(spasilac, smena, raspored);

        if (AngazovanjeKontroler.getInstanca().kreirajAngazovanje(angazovanje)) {
            JOptionPane.showMessageDialog(this, "Sistem je kreirao angazovanje", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            dialog.popuniTabelu();
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da kreira angazovanje", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }
}
