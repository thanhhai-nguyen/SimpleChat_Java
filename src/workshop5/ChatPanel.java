/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop5;

import java.io.*;
import java.net.Socket;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Administrator
 */
public class ChatPanel extends javax.swing.JPanel {

    Socket socket = null;
    BufferedReader bf = null;
    DataOutputStream os = null;
    OutputThread t = null;
    String sender;
    String receiver;

    PrintWriter print;

    public ChatPanel() {

    }

    public ChatPanel(Socket s, String sender, String receiver) {
        initComponents();
        socket = s;
        this.sender = sender;
        this.receiver = receiver;
        try {
            bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            os = new DataOutputStream((socket.getOutputStream()));
            t = new OutputThread(s, this.txtMessages, sender, receiver);
            t.start();

            print = new PrintWriter(os);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JTextArea getTxtMessages() {
        return this.txtMessages;
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
        btnSend = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMessage = new javax.swing.JTextArea();
        btnSendFile = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMessages = new javax.swing.JTextArea();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setDoubleBuffered(false);
        setPreferredSize(new java.awt.Dimension(726, 400));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Message", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), java.awt.Color.blue)); // NOI18N

        btnSend.setText("Send");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        txtMessage.setColumns(20);
        txtMessage.setRows(5);
        jScrollPane1.setViewportView(txtMessage);

        btnSendFile.setText("Send File");
        btnSendFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSendFile, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSendFile, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        txtMessages.setColumns(20);
        txtMessages.setRows(5);
        jScrollPane2.setViewportView(txtMessages);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        if (txtMessage.getText().trim().length() == 0) {
            return;
        }
        try {
            os.writeBytes(txtMessage.getText());
            os.write(13);
            os.flush();
            this.txtMessages.append("\n" + sender + ": " + txtMessage.getText());
            txtMessage.setText("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Connect Error !!! Send failed");
            System.exit(0);
        }
    }//GEN-LAST:event_btnSendActionPerformed

    public void saveFile(String msg) throws IOException {
        String data[] = msg.split("-");
        JFileChooser fc = new JFileChooser();
        int saveCheck = JOptionPane.showConfirmDialog(this, "Want to recieve file ????");
        if (saveCheck == JOptionPane.OK_OPTION) {
            int file = fc.showSaveDialog(ChatPanel.this);
            if (file == JFileChooser.APPROVE_OPTION) {
                PrintWriter write = new PrintWriter(new FileWriter(fc.getSelectedFile() + ".txt"));
                for (int i = 0; i < data.length; i++) {
                    write.append(data[i]);
                    write.println();
                }
                write.close();
                JOptionPane.showMessageDialog(this, "File have been save");
            } else {
                JOptionPane.showMessageDialog(this, "File canceled");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tranfer file terminate");
        }
    }

    private void btnSendFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendFileActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("Text File", "txt"));
        int fileCheck = fc.showOpenDialog(ChatPanel.this);
        if (fileCheck == JFileChooser.APPROVE_OPTION) {
            FileInputStream fileInput = null;
            try {
                File file = fc.getSelectedFile();
                fileInput = new FileInputStream(file);
                this.txtMessages.append("File is sending");
                BufferedReader read = new BufferedReader(new FileReader(file));
                String line, send = "";
                while ((line = read.readLine()) != null) {
                    send += line + "-";
                }
                os.write(64);      // 64 = @
                os.writeBytes(send);
                os.write(13);
                os.flush();
                JOptionPane.showMessageDialog(this, "Sending complete ");
            } catch (Exception e) {
                System.out.println("Error !!!");
            }
            try {
                fileInput.close();
            } catch (IOException e) {
                System.out.println("Error !!!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "File transfer canceled");
        }
    }//GEN-LAST:event_btnSendFileActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSend;
    private javax.swing.JButton btnSendFile;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtMessage;
    private javax.swing.JTextArea txtMessages;
    // End of variables declaration//GEN-END:variables

    void setText(String manager_has_terminated_your_connection_re) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
