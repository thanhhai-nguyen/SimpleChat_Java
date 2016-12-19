
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop5;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class ClientChatter extends javax.swing.JFrame {

    Socket mngSocket = null;
    String mngIP = "";
    int mngPort = 0;
    String StaffName = "";
    BufferedReader bf = null;
    DataOutputStream os = null;
    OutputThread t = null;

    public ClientChatter() {
        initComponents();
        this.setSize(900, 600);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtStaff = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtServerIP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtServerPort = new javax.swing.JTextField();
        btnConnect = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Staff and Server info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 51, 255))); // NOI18N
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jLabel1.setText("Staff :");
        jPanel1.add(jLabel1);
        jPanel1.add(txtStaff);

        jLabel2.setText("Mag IP :");
        jPanel1.add(jLabel2);

        txtServerIP.setText("127.0.0.1");
        jPanel1.add(txtServerIP);

        jLabel3.setText("Port :");
        jPanel1.add(jLabel3);

        txtServerPort.setText("12350");
        jPanel1.add(txtServerPort);

        btnConnect.setText("Connect");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });
        jPanel1.add(btnConnect);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
        mngIP = this.txtServerIP.getText();
        mngPort = Integer.parseInt(this.txtServerPort.getText());
        StaffName = this.txtStaff.getText();
        try {
            mngSocket = new Socket(mngIP, mngPort);
            if (mngSocket != null) {
                ChatPanel p = new ChatPanel(mngSocket, StaffName, "Manager ");
                this.getContentPane().add(p);
                p.getTxtMessages().append("Manager is runing\n");
                p.updateUI();

                bf = new BufferedReader(new InputStreamReader(mngSocket.getInputStream()));
                os = new DataOutputStream(mngSocket.getOutputStream());
                os.writeBytes("Staff: " + StaffName);
                os.write(13);
                os.write(10);
                os.flush();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Manager not online !!!!");
            System.exit(0);
        }
    }//GEN-LAST:event_btnConnectActionPerformed

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
            java.util.logging.Logger.getLogger(ClientChatter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientChatter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientChatter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientChatter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientChatter().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtServerIP;
    private javax.swing.JTextField txtServerPort;
    private javax.swing.JTextField txtStaff;
    // End of variables declaration//GEN-END:variables
}