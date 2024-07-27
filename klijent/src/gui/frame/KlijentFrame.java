package gui.frame;

import gui.dialog.angazovanje.AngazovanjeDialog;
import gui.dialog.izvestaj.IzvestajDialog;
import gui.dialog.raspored.RasporedDialog;
import gui.dialog.smena.SmenaDialog;
import gui.dialog.spasilac.SpasilacDialog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import konstante.Operacija;
import kontroler.ServerKontroler;
import niti.KlijentskaNit;
import transfer.Zahtev;

/**
 * Frame that displays after a User successfully logs into his account.
 *
 * Represents the main frame from which the users will be able to perform CRUD
 * operations.
 *
 * @author drask
 */
public class KlijentFrame extends javax.swing.JFrame {

    private final KlijentskaNit klijentskaNit;

    public KlijentFrame(KlijentskaNit klijentskaNit) {
        initComponents();
        setLocationRelativeTo(null);

        this.klijentskaNit = klijentskaNit;

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                zatvoriKonekciju();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmiSpasioci = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jmiSmene = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jmiRasporedi = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jmiAngazovanja = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jmiIzvestaji = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("Spasioci");

        jmiSpasioci.setText("Rad sa spasiocima");
        jmiSpasioci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSpasiociActionPerformed(evt);
            }
        });
        jMenu1.add(jmiSpasioci);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Smene");

        jmiSmene.setText("Rad sa smenama");
        jmiSmene.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSmeneActionPerformed(evt);
            }
        });
        jMenu2.add(jmiSmene);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Rasporedi");

        jmiRasporedi.setText("Rad sa rasporedima");
        jmiRasporedi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRasporediActionPerformed(evt);
            }
        });
        jMenu3.add(jmiRasporedi);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Angažovanja");

        jmiAngazovanja.setText("Rad sa angažovanjima");
        jmiAngazovanja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAngazovanjaActionPerformed(evt);
            }
        });
        jMenu4.add(jmiAngazovanja);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Izveštaji");

        jmiIzvestaji.setText("Rad sa izveštajima");
        jmiIzvestaji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiIzvestajiActionPerformed(evt);
            }
        });
        jMenu5.add(jmiIzvestaji);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiSpasiociActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSpasiociActionPerformed
        new SpasilacDialog(this, true).setVisible(true);
    }//GEN-LAST:event_jmiSpasiociActionPerformed

    private void jmiSmeneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSmeneActionPerformed
        new SmenaDialog(this, true).setVisible(true);
    }//GEN-LAST:event_jmiSmeneActionPerformed

    private void jmiRasporediActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRasporediActionPerformed
        new RasporedDialog(this, true).setVisible(true);
    }//GEN-LAST:event_jmiRasporediActionPerformed

    private void jmiAngazovanjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAngazovanjaActionPerformed
        new AngazovanjeDialog(this, true).setVisible(true);
    }//GEN-LAST:event_jmiAngazovanjaActionPerformed

    private void jmiIzvestajiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiIzvestajiActionPerformed
        new IzvestajDialog(this, true).setVisible(true);
    }//GEN-LAST:event_jmiIzvestajiActionPerformed

    private void zatvoriKonekciju() {
        try {
            ServerKontroler.getInstanca().posaljiZahtev(new Zahtev(null, Operacija.ZATVORI_KONEKCIJU));
            klijentskaNit.zatvoriKonekciju(this);
        } catch (Exception ex) {
            System.out.println("Greska: " + ex.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jmiAngazovanja;
    private javax.swing.JMenuItem jmiIzvestaji;
    private javax.swing.JMenuItem jmiRasporedi;
    private javax.swing.JMenuItem jmiSmene;
    private javax.swing.JMenuItem jmiSpasioci;
    // End of variables declaration//GEN-END:variables
}
