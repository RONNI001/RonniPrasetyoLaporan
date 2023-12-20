package javaMail;

import java.util.Properties;
import javax.mail.*;
import javax.mail.Flags.Flag;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.search.FlagTerm;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class GetMail extends javax.swing.JFrame {

    /**
     * Creates new form GetMail
     */
    public GetMail() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnInbox = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInbox = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMessage = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RONNI PRASETYO - Java Get Mail");
        setResizable(false);

        btnInbox.setText("Inbox");
        btnInbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInboxActionPerformed(evt);
            }
        });

        tblInbox.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No", "Subyek", "Dari", "Pesan", "Waktu"
            }
        ));
        tblInbox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblInboxMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblInbox);

        txtMessage.setColumns(20);
        txtMessage.setRows(5);
        jScrollPane2.setViewportView(txtMessage);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnInbox)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnInbox)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   private void getMail(String host, String storeType, String user, String password) {
        DefaultTableModel mod = new DefaultTableModel();
        mod.addColumn("No");
        mod.addColumn("Subyek");
        mod.addColumn("Dari");
        mod.addColumn("Pesan");
        mod.addColumn("Waktu");

        try {
            Properties properties = new Properties();
            properties.put("mail.imap.host", host);
            properties.put("mail.imap.port", "993");
            properties.put("mail.imap.starttls.enable", "true");
            properties.put("mail.imap.ssl.trust", host);

            Session emailSession = Session.getDefaultInstance(properties);
            Store store = emailSession.getStore("imaps");

            store.connect(host, user, password);

            Folder inbox = store.getFolder("Inbox");
            inbox.open(Folder.READ_WRITE);

            Message[] messages = inbox.search(new FlagTerm(new Flags(Flag.SEEN), false));
            int mlength = messages.length;
            if (mlength > 20) {
                mlength = 20;
            }
            for (int i = 0, n = mlength; i < n; i++) {
                Message message = messages[i];
                message.setFlag(Flag.SEEN, true);
                mod.addRow(new Object[]{
                    i + 1, message.getSubject(),
                    message.getFrom()[0], message.getContent().toString(), message.getSentDate()
                });
            }
            tblInbox.setModel(mod);
            inbox.close();
            store.close();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void tblInboxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInboxMouseClicked
        // TODO add your handling code here:
        int row = tblInbox.getSelectedRow();
        txtMessage.setText("No : " + tblInbox.getModel().getValueAt(row, 0).toString()
                + "\nSubyek : " + tblInbox.getModel().getValueAt(row, 1).toString()
                + "\nDari : " + tblInbox.getModel().getValueAt(row, 2).toString()
                + "\nIsi : " + tblInbox.getModel().getValueAt(row, 3).toString()
                + "\nWaktu : " + tblInbox.getModel().getValueAt(row, 4).toString()
        );
    }//GEN-LAST:event_tblInboxMouseClicked

    private void btnInboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInboxActionPerformed
        // TODO add your handling code here:
        String host = "imap.gmail.com";
        String mailStoreType = "imap";
        String username = "xiaoboyz17@gmail.com";
        String password = "gguc vwbe jxzl rsvp";
        getMail(host, mailStoreType, username, password);
    }//GEN-LAST:event_btnInboxActionPerformed

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
            java.util.logging.Logger.getLogger(GetMail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GetMail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GetMail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GetMail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GetMail().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInbox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblInbox;
    private javax.swing.JTextArea txtMessage;
    // End of variables declaration//GEN-END:variables
}