package gui.dialog.izvestaj;

import domen.Angazovanje;
import domen.Izvestaj;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import kontroler.AngazovanjeKontroler;
import kontroler.IzvestajKontroler;

public class KreirajIzvestajDialog extends javax.swing.JDialog {

    private final IzvestajDialog dialog;

    public KreirajIzvestajDialog(java.awt.Frame parent, boolean modal, IzvestajDialog dialog) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);

        this.dialog = dialog;

        popuniCBAngazovanja();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbAngazovanja = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtOpis = new javax.swing.JTextArea();
        btnSacuvaj = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Rad sa izveštajima");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Kreiranje izveštaja"));

        jLabel1.setText("Angažovanje:");

        cmbAngazovanja.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Opis:");

        txtOpis.setColumns(20);
        txtOpis.setRows(5);
        jScrollPane1.setViewportView(txtOpis);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(cmbAngazovanja, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbAngazovanja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(btnSacuvaj)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSacuvaj)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacuvajActionPerformed
        kreirajIzvestaj();
    }//GEN-LAST:event_btnSacuvajActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSacuvaj;
    private javax.swing.JComboBox cmbAngazovanja;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtOpis;
    // End of variables declaration//GEN-END:variables

    private void kreirajIzvestaj() {

        Angazovanje angazovanje = (Angazovanje) cmbAngazovanja.getSelectedItem();
        String opis = txtOpis.getText();

        Izvestaj izvestaj = new Izvestaj(angazovanje, opis);

        if (IzvestajKontroler.getInstanca().kreirajIzvestaj(izvestaj)) {
            JOptionPane.showMessageDialog(this, "Sistem je kreirao izvestaj", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            dialog.popuniTabelu();
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da kreira izvestaj", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void popuniCBAngazovanja() {
        List<Angazovanje> angazovanja = new ArrayList<>();
        angazovanja = AngazovanjeKontroler.getInstanca().ucitajListuAngazovanja(angazovanja);

        cmbAngazovanja.removeAllItems();
        for (Angazovanje a : angazovanja) {
            cmbAngazovanja.addItem(a);
        }

    }
}
