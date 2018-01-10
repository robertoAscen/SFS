//    uniCenta oPOS  - Touch Friendly Point Of Sale
//    Copyright (c) 2009-2018 uniCenta
//    https://unicenta.com
//
//    This file is part of uniCenta oPOS
//
//    uniCenta oPOS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//   uniCenta oPOS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with uniCenta oPOS.  If not, see <http://www.gnu.org/licenses/>.

package com.openbravo.pos.config;

import com.openbravo.data.user.DirtyManager;
import java.awt.Component;
import javax.swing.SpinnerNumberModel;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import com.openbravo.data.loader.Session;
import com.openbravo.pos.forms.AppConfig;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppProperties;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.forms.AppViewConnection;
import com.openbravo.pos.util.AltEncrypter;
import javax.swing.JOptionPane;

/**
 *
 * @author JG uniCenta
 */
public class JPanelTicketSetup extends javax.swing.JPanel implements PanelConfig {
    
    private final DirtyManager dirty = new DirtyManager();
    private String receipt="1";
    private Integer x = 0;
    private String receiptSize;
    private String pickupSize;
    private final Integer ps = 0;

    private Connection conn;
    private String sdbmanager;
    private String SQL;
    private Statement stmt;  

    /**
     *
     * @param oApp
     */
    public JPanelTicketSetup() {
        
        initComponents();
        
        jReceiptSize.addChangeListener(dirty);
        jPickupSize.addChangeListener(dirty);
        jTextReceiptPrefix.getDocument().addDocumentListener(dirty);
        m_jReceiptPrintOff.addActionListener(dirty);

        jbtnReset.setVisible(true);
    }


    /**
     *
     * @return
     */
    @Override
    public boolean hasChanged() {
        return dirty.isDirty();
    }
    
    /**
     *
     * @return
     */
    @Override
    public Component getConfigComponent() {
        return this;
    }
   
    /**
     *
     * @param config
     */
    @Override
    public void loadProperties(AppConfig config) {

        receiptSize =(config.getProperty("till.receiptsize"));
        if (receiptSize == null || "".equals(receiptSize)){
            jReceiptSize.setModel(new SpinnerNumberModel(1,1,20,1));
        } else {            
            jReceiptSize.setModel(new SpinnerNumberModel(Integer.parseInt(receiptSize),1,20,1));
        }                

        pickupSize =(config.getProperty("till.pickupsize"));
        if (pickupSize == null || "".equals(pickupSize)){
            jPickupSize.setModel(new SpinnerNumberModel(1,1,20,1));
        } else {            
            jPickupSize.setModel(new SpinnerNumberModel(Integer.parseInt(pickupSize),1,20,1));
        }        
        
        jTextReceiptPrefix.setText(config.getProperty("till.receiptprefix"));        
// build the example receipt using the loaded details        
        receipt="";
        x=1;
        while (x < (Integer)jReceiptSize.getValue()){
            receipt += "0";
        x++; 
    }
         
        receipt += "1";
         jTicketExample.setText(jTextReceiptPrefix.getText()+receipt);  
         m_jReceiptPrintOff.setSelected(Boolean.parseBoolean(config.getProperty("till.receiptprintoff"))); 
        
        dirty.setDirty(false);

        
    }
    
    /*
     * JG Oct 2017 
     * This block to be used for internal SETS/RESETS and external ORDERS sync's  
    */    
    public void loadUp() throws ClassNotFoundException, SQLException {    

/* Add external received order reset block here - 
 * Get connex to secondary or external system's DB + [params]
 * Pref' use is JSON/REST rather than PreparedStatement
*/        
    }
    
    /**
     *
     * @param config
     */
    @Override
    public void saveProperties(AppConfig config) {
        
        config.setProperty("till.receiptprefix", jTextReceiptPrefix.getText());
        config.setProperty("till.receiptsize", jReceiptSize.getValue().toString());
        config.setProperty("till.pickupsize", jPickupSize.getValue().toString());        
        config.setProperty("till.receiptprintoff",Boolean.toString(m_jReceiptPrintOff.isSelected()));
        
        dirty.setDirty(false);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jReceiptSize = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jTextReceiptPrefix = new javax.swing.JTextField();
        jTicketExample = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPickupSize = new javax.swing.JSpinner();
        m_jReceiptPrintOff = new javax.swing.JCheckBox();
        jbtnReset = new javax.swing.JButton();

        jTextField2.setText("jTextField2");

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(700, 500));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("pos_messages"); // NOI18N
        jLabel1.setText(bundle.getString("label.ticketsetupnumber")); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(190, 30));

        jReceiptSize.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jReceiptSize.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        jReceiptSize.setPreferredSize(new java.awt.Dimension(50, 30));
        jReceiptSize.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jReceiptSizeStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText(bundle.getString("label.ticketsetupprefix")); // NOI18N

        jTextReceiptPrefix.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jTextReceiptPrefix.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextReceiptPrefix.setPreferredSize(new java.awt.Dimension(100, 30));
        jTextReceiptPrefix.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextReceiptPrefixKeyReleased(evt);
            }
        });

        jTicketExample.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jTicketExample.setText("1");
        jTicketExample.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTicketExample.setEnabled(false);
        jTicketExample.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText(bundle.getString("label.pickupcodesize")); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(190, 30));

        jPickupSize.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jPickupSize.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        jPickupSize.setToolTipText("");
        jPickupSize.setPreferredSize(new java.awt.Dimension(50, 30));
        jPickupSize.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jPickupSizeStateChanged(evt);
            }
        });

        m_jReceiptPrintOff.setBackground(new java.awt.Color(255, 255, 255));
        m_jReceiptPrintOff.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        m_jReceiptPrintOff.setText(bundle.getString("label.receiptprint")); // NOI18N
        m_jReceiptPrintOff.setOpaque(false);
        m_jReceiptPrintOff.setPreferredSize(new java.awt.Dimension(180, 30));
        m_jReceiptPrintOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jReceiptPrintOffActionPerformed(evt);
            }
        });

        jbtnReset.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jbtnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/reload.png"))); // NOI18N
        jbtnReset.setText(AppLocal.getIntString("label.resetpickup")); // NOI18N
        jbtnReset.setMaximumSize(new java.awt.Dimension(70, 33));
        jbtnReset.setMinimumSize(new java.awt.Dimension(70, 33));
        jbtnReset.setPreferredSize(new java.awt.Dimension(100, 45));
        jbtnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(m_jReceiptPrintOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPickupSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jbtnReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jReceiptSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jTextReceiptPrefix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTicketExample, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jReceiptSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextReceiptPrefix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTicketExample, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(m_jReceiptPrintOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPickupSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(295, 295, 295))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextReceiptPrefixKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextReceiptPrefixKeyReleased

        jTicketExample.setText(jTextReceiptPrefix.getText()+ receipt);
    }//GEN-LAST:event_jTextReceiptPrefixKeyReleased

    private void jReceiptSizeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jReceiptSizeStateChanged

        receipt="";
        x=1;
        while (x < (Integer)jReceiptSize.getValue()){
            receipt += "0";
        x++; 
    }
        receipt += "1";
         jTicketExample.setText(jTextReceiptPrefix.getText()+receipt);
         
    }//GEN-LAST:event_jReceiptSizeStateChanged

    private void jPickupSizeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jPickupSizeStateChanged

    }//GEN-LAST:event_jPickupSizeStateChanged

    private void m_jReceiptPrintOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jReceiptPrintOffActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_m_jReceiptPrintOffActionPerformed

    private void jbtnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnResetActionPerformed
        int response = JOptionPane.showOptionDialog(null,
            AppLocal.getIntString("message.resetpickup"),
                    "Reset",
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE,
                    null, null, null);
        if (response == JOptionPane.YES_OPTION) {
        try {
            String db_user =(AppConfig.getInstance().getProperty("db.user"));
            String db_url = (AppConfig.getInstance().getProperty("db.URL"));
            String db_password = (AppConfig.getInstance().getProperty("db.password"));
            
            if (db_user != null && db_password != null && db_password.startsWith("crypt:")) {
                AltEncrypter cypher = new AltEncrypter("cypherkey" + db_user);
                db_password = cypher.decrypt(db_password.substring(6));
            }
            
            conn = DriverManager.getConnection(db_url,db_user,db_password);
            sdbmanager = conn.getMetaData().getDatabaseProductName();
            stmt = (Statement) conn.createStatement();
            
            if ("MySQL".equals(sdbmanager)) {
                SQL = "UPDATE pickup_number SET id = 0";
                try {
                    stmt.executeUpdate(SQL);
                } catch (SQLException e){
                    System.out.println(e.getMessage()); 
                }
            } else if ("PostgreSQL".equals(sdbmanager)) {
                SQL = "ALTER SEQUENCE pickup_number RESTART WITH 1";
                try {
                    stmt.executeUpdate(SQL);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(JPanelTicketSetup.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }        
    }//GEN-LAST:event_jbtnResetActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSpinner jPickupSize;
    private javax.swing.JSpinner jReceiptSize;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextReceiptPrefix;
    private javax.swing.JTextField jTicketExample;
    private javax.swing.JButton jbtnReset;
    private javax.swing.JCheckBox m_jReceiptPrintOff;
    // End of variables declaration//GEN-END:variables
    
}
