package gui.frame;

import domen.Koordinator;
import javax.swing.JOptionPane;
import kontroler.KoordinatorKontroler;
import niti.KlijentskaNit;

/**
 * Frame that displays when a User opens the Client application.
 *
 * From here users will be able to login into their accounts and proceed with
 * using the application.
 *
 * @author dulait
 */
public class LoginFrame extends javax.swing.JFrame {

    private KlijentskaNit kn;

    public LoginFrame(KlijentskaNit kn) {
        initComponents();
        setLocationRelativeTo(null);

        pokreniKlijentskuNit();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtKorisnickoIme = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtLozinka = new javax.swing.JPasswordField();
        btnPrijaviSe = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Prijava koordinatora"));

        jLabel1.setText("Korisničko ime:");

        jLabel2.setText("Lozinka:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(txtKorisnickoIme, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(txtLozinka))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKorisnickoIme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLozinka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        btnPrijaviSe.setText("Prijavi se");
        btnPrijaviSe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrijaviSeActionPerformed(evt);
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
                .addGap(152, 152, 152)
                .addComponent(btnPrijaviSe)
                .addContainerGap(172, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPrijaviSe)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrijaviSeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrijaviSeActionPerformed
        String korisnickoIme = txtKorisnickoIme.getText();
        String lozinka = String.valueOf(txtLozinka.getPassword());

        Koordinator koordinator = new Koordinator();
        koordinator.setKorisnickoIme(korisnickoIme);
        koordinator.setLozinka(lozinka);

        if (KoordinatorKontroler.getInstanca().prijaviKoordinatora(koordinator)) {
            JOptionPane.showMessageDialog(this, "Uspesno prijavljivanje", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            new KlijentFrame(kn).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Neuspesno prijavljivanje", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnPrijaviSeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrijaviSe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtKorisnickoIme;
    private javax.swing.JPasswordField txtLozinka;
    // End of variables declaration//GEN-END:variables

    private void pokreniKlijentskuNit() {
        kn = new KlijentskaNit(9000);
        kn.start();
    }
}
