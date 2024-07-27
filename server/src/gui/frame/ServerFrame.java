package gui.frame;

import domen.Koordinator;
import java.awt.Color;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.KoordinatorTableModel;
import niti.ServerskaNit;

/**
 * Frame that displays after a Koordinator successfully logs into his account.
 *
 * @author drask
 */
public class ServerFrame extends javax.swing.JFrame {

    private ServerskaNit serverskaNit;
    private final Map<Socket, Koordinator> prijavljeniKoordinatori = new HashMap<>();

    public ServerFrame() {
        initComponents();
        setLocationRelativeTo(null);

        btnZaustaviServer.setEnabled(false);

        srediStatus();
        popuniTabelu();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKoordinatori = new javax.swing.JTable();
        btnPokreniServer = new javax.swing.JButton();
        btnZaustaviServer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server");

        jLabel1.setText("Status servera:");

        lblStatus.setText("STATUS");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Prijavljeni koordinatori"));

        tblKoordinatori.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblKoordinatori);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnPokreniServer.setText("Pokreni server");
        btnPokreniServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPokreniServerActionPerformed(evt);
            }
        });

        btnZaustaviServer.setText("Zaustavi server");
        btnZaustaviServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZaustaviServerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblStatus))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnPokreniServer)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnZaustaviServer)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPokreniServer)
                    .addComponent(btnZaustaviServer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnZaustaviServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZaustaviServerActionPerformed
        zaustaviServerskuNit();
    }//GEN-LAST:event_btnZaustaviServerActionPerformed

    private void btnPokreniServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPokreniServerActionPerformed
        pokreniServerskuNit();
    }//GEN-LAST:event_btnPokreniServerActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPokreniServer;
    private javax.swing.JButton btnZaustaviServer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTable tblKoordinatori;
    // End of variables declaration//GEN-END:variables

    private void pokreniServerskuNit() {
        serverskaNit = new ServerskaNit(this);
        serverskaNit.start();
        lblStatus.setText("Server pokrenut");
        lblStatus.setForeground(Color.green);
        btnPokreniServer.setEnabled(false);
        btnZaustaviServer.setEnabled(true);
    }

    private void zaustaviServerskuNit() {
        prijavljeniKoordinatori.clear();
        popuniTabelu();

        serverskaNit.zaustaviServer();
        lblStatus.setText("Server nije pokrenut");
        lblStatus.setForeground(Color.red);
        btnPokreniServer.setEnabled(true);
        btnZaustaviServer.setEnabled(false);
    }

    private void srediStatus() {
        lblStatus.setText("Server nije pokrenut");
        lblStatus.setForeground(Color.red);
    }

    private void popuniTabelu() {
        List<Koordinator> koordinatori = new ArrayList<>(prijavljeniKoordinatori.values());

        KoordinatorTableModel ktm = new KoordinatorTableModel(koordinatori);
        tblKoordinatori.setModel(ktm);
    }

    public void dodajKoordinatora(Koordinator koordinator, Socket klijentskiSocket) {
        prijavljeniKoordinatori.put(klijentskiSocket, koordinator);
        popuniTabelu();
    }

    public void ukloniKoordinatora(Socket klijentskiSocket) {
        prijavljeniKoordinatori.remove(klijentskiSocket);
        popuniTabelu();
    }

}
