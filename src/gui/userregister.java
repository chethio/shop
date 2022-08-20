/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.mysql;
import java.util.regex.Pattern;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
/**
 *
 * @author Lap.lk
 */
public class userregister extends javax.swing.JFrame {

    public void loaduser(){
        try {
            ResultSet rs= mysql.search("SELECT `user`.`id`,`user`.`name`,`user`.`username`,`user`.`password`,`user`.`contact`,`user_type`.`type`,`city`.`name` AS `city_name`,`user_status`.`name` AS `user_status` FROM `user` INNER JOIN `city` ON `user`.`city_id`=`city`.`id` INNER JOIN `user_status` ON `user`.`user_status_id`=`user_status`.`id` INNER JOIN `user_type` ON `user`.`user_type_id`=`user_type`.`id` ORDER BY `user`.`id` ASC;");
            DefaultTableModel dtm=(DefaultTableModel)jTable1.getModel();
            dtm.setRowCount(0);
            dtm.setColumnCount(0);
            
            dtm.addColumn("Id");
            dtm.addColumn("Name");
            dtm.addColumn("Username");
            dtm.addColumn("Password");
            dtm.addColumn("Mobile");
            dtm.addColumn("Type");
            dtm.addColumn("City");
            dtm.addColumn("Status");
            
            while (rs.next()) {                
                Vector v=new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("name"));
                v.add(rs.getString("username"));
                v.add(rs.getString("password"));
                v.add(rs.getString("contact"));
                v.add(rs.getString("type"));
                v.add(rs.getString("city_name"));
                v.add(rs.getString("user_status"));
                dtm.addRow(v);
            }
            jTable1.setModel(dtm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void loaduser(String text){
        try {
            ResultSet rs= mysql.search("SELECT `user`.`id`,`user`.`name`,`user`.`username`,`user`.`password`,`user`.`contact`,`user_type`.`type`,`city`.`name` AS `city_name`,`user_status`.`name` AS `user_status` FROM `user` INNER JOIN `city` ON `user`.`city_id`=`city`.`id` INNER JOIN `user_status` ON `user`.`user_status_id`=`user_status`.`id` INNER JOIN `user_type` ON `user`.`user_type_id`=`user_type`.`id` WHERE `user`.`name` LIKE '"+text+"%' OR `user`.`contact` LIKE '"+text+"%' ORDER BY `user`.`id` ASC");
            DefaultTableModel dtm=(DefaultTableModel)jTable1.getModel();
            dtm.setRowCount(0);
            dtm.setColumnCount(0);
            
            dtm.addColumn("Id");
            dtm.addColumn("Name");
            dtm.addColumn("Username");
            dtm.addColumn("Password");
            dtm.addColumn("Mobile");
            dtm.addColumn("Type");
            dtm.addColumn("City");
            dtm.addColumn("Status");
            
            while (rs.next()) {                
                Vector v=new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("name"));
                v.add(rs.getString("username"));
                v.add(rs.getString("password"));
                v.add(rs.getString("contact"));
                v.add(rs.getString("type"));
                v.add(rs.getString("city_name"));
                v.add(rs.getString("user_status"));
                dtm.addRow(v);
            }
            jTable1.setModel(dtm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void loadtype(){
        try {
            ResultSet rs= mysql.search("SELECT * FROM `user_type`");
            Vector v=new Vector();
            v.add("select");
             while (rs.next()) {                
                v.add(rs.getString("type"));
            }
             DefaultComboBoxModel dcm=new DefaultComboBoxModel(v);
             jComboBox1.setModel(dcm);
         } catch (Exception e) {
             e.printStackTrace();
         }
    }
    
     public void loadcities(){
         try {
            ResultSet rs= mysql.search("SELECT * FROM `city`");
            Vector v=new Vector();
            v.add("select");
             while (rs.next()) {                
                v.add(rs.getString("name"));
            }
             DefaultComboBoxModel dcm=new DefaultComboBoxModel(v);
             jComboBox2.setModel(dcm);
         } catch (Exception e) {
             e.printStackTrace();
         }
    }
     
     public void resetfields(){
    jTextField1.setText("");
        jTextField2.setText("");
       jPasswordField1.setText("");
        jTextField3.setText("");
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jTextField1.grabFocus();
}
     
    /**
     * Creates new form userregister
     */
    public userregister() {
        initComponents();
        
       ListSelectionListener tml=new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
            
                
        int selectedrow=jTable1.getSelectedRow();
       
       if(selectedrow!=-1){
            String id=jTable1.getValueAt(selectedrow, 0).toString();
            if(id.equals("1")){
                jButton2.setEnabled(false);
            }else{
                jButton2.setEnabled(true);
            }
       }
            
              
            }
        };
        
       jTable1.getSelectionModel().addListSelectionListener(tml);
        
        loaduser();
        loadcities();
        loadtype();
        
        
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
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Name");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Password");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("username");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Type");

        jTextField3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField3MouseClicked(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });

        jButton1.setText("Create Account");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Change Status");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Contact Number");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("City");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(102, 102, 102))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(80, 80, 80)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                            .addComponent(jTextField2)
                            .addComponent(jTextField1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
                        .addGap(101, 101, 101)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(32, 32, 32))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Search User");

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField4))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 13, Short.MAX_VALUE)
                        .addComponent(jButton4))
                    .addComponent(jTextField4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String name=jTextField1.getText();
        String username=jTextField2.getText();
        String password=jPasswordField1.getText();
        String mobile=jTextField3.getText();
        String type=jComboBox1.getSelectedItem().toString();
        String city=jComboBox2.getSelectedItem().toString();
        
        if(name.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please enter name","Warning",JOptionPane.WARNING_MESSAGE);
        }else if(username.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please enter username","Warning",JOptionPane.WARNING_MESSAGE);
        }else if(password.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please enter password","Warning",JOptionPane.WARNING_MESSAGE);
        }else if(mobile.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please enter mobile no.","Warning",JOptionPane.WARNING_MESSAGE);
        }else if(!Pattern.compile("07[01245678][0-9]{7}").matcher(mobile).matches()){
            JOptionPane.showMessageDialog(this, "Please enter valid mobile no.","Warning",JOptionPane.WARNING_MESSAGE);
        }else if(type.equals("select")){
            JOptionPane.showMessageDialog(this, "Please select user type","Warning",JOptionPane.WARNING_MESSAGE);
        }else if(city.equals("select")){
            JOptionPane.showMessageDialog(this, "Please select a city","Warning",JOptionPane.WARNING_MESSAGE);
        }else{
            try {
                ResultSet rs1=mysql.search("SELECT * FROM `city` WHERE `name`='"+city+"'");
                rs1.next();
                ResultSet rs2=mysql.search("SELECT * FROM `user_type` WHERE `type`='"+type+"'");
                rs2.next();
                
                String cityid= rs1.getString("id");
                String typeid= rs2.getString("id");
               
                 mysql.iud("INSERT INTO `user`(`name`,`username`,`password`,`contact`,`user_type_id`,`user_status_id`,`city_id`) VALUES('"+name+"','"+username+"','"+password+"','"+mobile+"','"+Integer.parseInt(typeid)+"','"+1+"','"+Integer.parseInt(cityid)+"')");
                 JOptionPane.showMessageDialog(this, "registration success","success", JOptionPane.INFORMATION_MESSAGE);
                 resetfields();
                 loaduser();
            } catch (Exception e) {
               e.printStackTrace();
            }
           
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
       String mobile=jTextField3.getText();
       String text=mobile+evt.getKeyChar();
       
           
       if(text.length()==1){
           if(!text.equals("0")){
                evt.consume();
           }
          
       }else if(text.length()==2){
           if(!text.equals("07")){
                evt.consume();
           }
       }else if(text.length()==3){
           if(!Pattern.compile("07[01245678]").matcher(text).matches()){
                evt.consume();
           }
       }else if(text.length()<=10){
           if(!Pattern.compile("07[01245678][0-9]+").matcher(text).matches()){
                evt.consume();
           }
           
       } else{
           evt.consume();
       
       }
//        if(!Pattern.compile("0?7?[01245678]?[0-9]{7}").matcher(text).matches()){
//            evt.consume();
//        }
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
      if(jTextField3.getText().length()==10){
   
          jTextField3.setEnabled(false);
        }
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jTextField3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField3MouseClicked
        if(evt.getClickCount()==2){
            jTextField3.setEnabled(true);
            jTextField3.setText("");
            jTextField3.grabFocus();
        }
    }//GEN-LAST:event_jTextField3MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       int selectedrow=jTable1.getSelectedRow();
       
       if(selectedrow==-1){
            JOptionPane.showMessageDialog(this, "Please select a user","Warning",JOptionPane.WARNING_MESSAGE);
       }else{
           String id=jTable1.getValueAt(selectedrow, 0).toString();
           String currentstate=jTable1.getValueAt(selectedrow, 7).toString();
           
           int status;
           if(currentstate.equals("active")){
               status=2;
           }else{
               status=1;
           }
      
           try {
               mysql.iud("UPDATE `user` SET `user_status_id`=" +status+ " WHERE `id`="+Integer.parseInt(id)+" ");
           } catch (Exception ex) {
               Logger.getLogger(userregister.class.getName()).log(Level.SEVERE, null, ex);
           }
                   loaduser();
                   JOptionPane.showMessageDialog(this, "success","success", JOptionPane.INFORMATION_MESSAGE);
          
               
           
       }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
//        int selectedrow=jTable1.getSelectedRow();
//       
//       if(selectedrow!=-1){
//            String id=jTable1.getValueAt(selectedrow, 0).toString();
//            if(id.equals("1")){
//                jButton2.setEnabled(false);
//            }else{
//                jButton2.setEnabled(true);
//            }
//       }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            String text=Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor).toString();
            System.out.println(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
     String text = jTextField4.getText();
        loaduser(text);
    }//GEN-LAST:event_jTextField4KeyReleased

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
            java.util.logging.Logger.getLogger(userregister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(userregister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(userregister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(userregister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new userregister().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
