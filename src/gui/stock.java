/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.IntelliJTheme;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.mysql;

/**
 *
 * @author Lap.lk
 */
public class stock extends javax.swing.JFrame {

    /**
     * Creates new form stock
     */
    public stock() {
        initComponents();
        loadcategory();
        loadbrand();
        loadstock();
    }

    public void reset() {
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        jDateChooser3.setDate(null);
        jDateChooser4.setDate(null);
        jComboBox4.setSelectedIndex(0);
        jTextField1.setText("");
        jLabel1.setText("0.00");
        jTable1.clearSelection();
        loadstock();
    }

    public void loadcategory() {

        try {
            ResultSet rs = mysql.search("SELECT `name` FROM `category`");

            Vector v = new Vector();
            v.add("select");
            while (rs.next()) {

                v.add(rs.getString("name"));
            }
            jComboBox1.setModel(new DefaultComboBoxModel(v));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadbrand() {

        try {
            ResultSet rs = mysql.search("SELECT `name` FROM `brand`");

            Vector v = new Vector();
            v.add("select");
            while (rs.next()) {

                v.add(rs.getString("name"));
            }
            jComboBox2.setModel(new DefaultComboBoxModel(v));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadstock() {
        try {
            ResultSet rs = mysql.search("SELECT DISTINCT `stock`.`id`,`product`.`id`,`category`.`name`,`brand`.`name`,`product`.`name`,`stock`.`qty`,`grn_item`.`buy_price`,`stock`.`selling_price`,`stock`.`mfd`,`stock`.`exp` FROM `stock` INNER JOIN `grn_item` ON `grn_item`.`stock_id`=`stock`.`id` INNER JOIN `product` ON `stock`.`product_id`=`product`.`id` INNER JOIN `brand` ON `product`.`brand_id` =`brand`.id INNER JOIN `category` ON `product`.`category_id`=`category`.`id` ORDER BY `product`.`name` ASC;");
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("stock.id"));
                v.add(rs.getString("product.id"));
                v.add(rs.getString("category.name"));
                v.add(rs.getString("brand.name"));
                v.add(rs.getString("product.name"));
                v.add(rs.getString("stock.qty"));
                v.add(rs.getString("grn_item.buy_price"));
                v.add(rs.getString("stock.selling_price"));
                v.add(rs.getString("stock.mfd"));
                v.add(rs.getString("stock.exp"));

                dtm.addRow(v);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchstock() {
        try {
            String category = jComboBox1.getSelectedItem().toString();
            String brand = jComboBox2.getSelectedItem().toString();
            String name = jTextField4.getText();
            String spmin = jTextField2.getText();
            String spmax = jTextField3.getText();
              
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date mfd_fr = jDateChooser1.getDate();
            Date mfd_to = jDateChooser2.getDate();
            Date exp_fr = jDateChooser3.getDate();
            Date exp_to = jDateChooser4.getDate();
            int sort = jComboBox4.getSelectedIndex();

            Vector queryvector = new Vector();

            //category
            if (category.equals("select")) {

            } else {
                queryvector.add("`category`.`name`='" + category + "' ");
            }

            //brand
            if (brand.equals("select")) {

            } else {
                queryvector.add("`brand`.`name`='" + brand + "' ");
            }

            //name
            if (name.isEmpty()) {

            } else {
                queryvector.add("`product`.`name` LIKE '%" + name + "%' ");
            }

            //selling price min
            if (!spmin.isEmpty()) {

                //min empty
                if (spmax.isEmpty()) {
                    //min not empty and max not empty
                    queryvector.add("`stock`.`selling_price`>='" + spmin + "' ");

                } else {
                    //min  not empty and max not empty
                    queryvector.add("`stock`.`selling_price`>='" + spmin + "' AND `stock`.`selling_price`<='" + spmax + "'");
                }
            }

            //selling price max
            if (!spmax.isEmpty()) {
       
                if (spmin.isEmpty()) {
                    //max mot empty and min empty
                    queryvector.add("`stock`.`selling_price`<='" + spmax + "' ");
                } 
            }

            
            
            
            //manufacture date from
            if (mfd_fr!=null) {

                //min empty
                if (mfd_to==null) {
                    //min not empty and max not empty
                    queryvector.add("`stock`.`mfd`>='" + mfd_fr + "' ");

                } else {
                    //min  not empty and max not empty
                    queryvector.add("`stock`.`mfd`>='" + mfd_fr + "' AND `stock`.`mfd`<='" + mfd_to + "'");
                }
            }

           
            if (mfd_to!=null) {
       
                if (mfd_fr==null) {
                    //max mot empty and min empty
                    queryvector.add("`stock`.`mfd`<='" + mfd_to + "' ");
                } 
            }
            
            
            
            

            //exp date from
             if (exp_fr!=null) {

                //min empty
                if (exp_to==null) {
                    //min not empty and max not empty
                    queryvector.add("`stock`.`exp`>='" + exp_fr + "' ");

                } else {
                    //min  not empty and max not empty
                    queryvector.add("`stock`.`exp`>='" + exp_fr + "' AND `stock`.`mfd`<='" + exp_to + "'");
                }
            }

           
            if (exp_to!=null) {
       
                if (exp_fr==null) {
                    //max mot empty and min empty
                    queryvector.add("`stock`.`exp`<='" + exp_to + "' ");
                } 
            }

            String wherequery="WHERE";
            
            for (int i = 0; i < queryvector.size(); i++) {
                wherequery+=" ";
                wherequery+= queryvector.get(i);
                wherequery+=" ";
                if(i!=queryvector.size()-1){
                    wherequery+= "AND";
                }
 }
            
//             ORDER BY QUERY
            String sortquery;
            if (sort == 0) {
                sortquery = "`product`.`name` ASC";
            } else if (sort == 1) {
                sortquery = "`product`.`name` DESC";
            } else if (sort == 2) {
                sortquery = "`stock`.`selling_price` ASC";
            } else if (sort == 3) {
                sortquery = "`stock`.`selling_price` DESC";
            } else if (sort == 4) {
                sortquery = "`stock`.`qty` ASC";
            } else if (sort == 5) {
                sortquery = "`stock`.`qty` DESC";
            } else if (sort == 6) {
                sortquery = "`stock`.`exp` ASC";
            } else {
                sortquery = "`stock`.`exp` DESC";
            }
//             ORDER BY QUERY   

            ResultSet rs = mysql.search("SELECT DISTINCT `stock`.`id`,`product`.`id`,`category`.`name`,`brand`.`name`,`product`.`name`,`stock`.`qty`,`grn_item`.`buy_price`,`stock`.`selling_price`,`stock`.`mfd`,`stock`.`exp` FROM `stock` INNER JOIN `grn_item` ON `grn_item`.`stock_id`=`stock`.`id` INNER JOIN `product` ON `stock`.`product_id`=`product`.`id` INNER JOIN `brand` ON `product`.`brand_id` =`brand`.id INNER JOIN `category` ON `product`.`category_id`=`category`.`id` "+wherequery+" ORDER BY " + sortquery + " ");
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("stock.id"));
                v.add(rs.getString("product.id"));
                v.add(rs.getString("category.name"));
                v.add(rs.getString("brand.name"));
                v.add(rs.getString("product.name"));
                v.add(rs.getString("stock.qty"));
                v.add(rs.getString("grn_item.buy_price"));
                v.add(rs.getString("stock.selling_price"));
                v.add(rs.getString("stock.mfd"));
                v.add(rs.getString("stock.exp"));

                dtm.addRow(v);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jTextField4 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setText("Category");

        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel5.setText("Brand");

        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jLabel6.setText("Name");

        jLabel7.setText("Selling Price");

        jLabel8.setText("Min");

        jLabel9.setText("Max");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
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

        jLabel10.setText("MFD");

        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });

        jDateChooser2.setDateFormatString("yyyy-MM-dd");
        jDateChooser2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser2PropertyChange(evt);
            }
        });

        jLabel11.setText("To");

        jLabel12.setText("EXP");

        jDateChooser3.setDateFormatString("yyyy-MM-dd");
        jDateChooser3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser3PropertyChange(evt);
            }
        });

        jLabel13.setText("To");

        jDateChooser4.setDateFormatString("yyyy-MM-dd");
        jDateChooser4.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser4PropertyChange(evt);
            }
        });

        jLabel14.setText("Sort Product By");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name ASC", "Name DESC", "Price ASC", "Price DESC", "Qty ASC", "Qty DESC", "EXP ASC", "EXP DESC" }));
        jComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox4ItemStateChanged(evt);
            }
        });

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(30, 30, 30)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(30, 30, 30)
                                .addComponent(jDateChooser4, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(30, 30, 30)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(17, 17, 17))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(126, 126, 126))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(176, 176, 176))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8)
                        .addComponent(jLabel9)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12))
                    .addComponent(jLabel14)
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stock_ID", "Prouct_ID", "Category", "Brand", "Name", "Quantity", "Buying Price", "Selling Price", "MFD", "EXP"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("0.00");

        jLabel2.setText("Buying_Price");

        jLabel3.setText("New Price");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jButton1.setText("Update Stock");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(31, 31, 31)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //reset
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
      
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        jDateChooser3.setDate(null);
        jDateChooser4.setDate(null);
        jComboBox4.setSelectedIndex(0);
        jTextField1.setText("");
        jLabel1.setText("0.00");
        loadstock();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        String price = jTextField1.getText();
        String text = price + evt.getKeyChar();

        if (!Pattern.compile("(0|0[.]0[.][0-9]*)|[1-9]|[1-9][0-9]*|[1-9][0-9]*[.][0-9]*").matcher(text).matches()) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        String price = jTextField2.getText();
        String text = price + evt.getKeyChar();

        if (!Pattern.compile("(0|0[.]0[.][0-9]*)|[1-9]|[1-9][0-9]*|[1-9][0-9]*[.][0-9]*").matcher(text).matches()) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField2KeyTyped

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        String price = jTextField3.getText();
        String text = price + evt.getKeyChar();

        if (!Pattern.compile("(0|0[.]0[.][0-9]*)|[1-9]|[1-9][0-9]*|[1-9][0-9]*[.][0-9]*").matcher(text).matches()) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String buyingprice = jLabel1.getText();
        String newprice = jTextField1.getText();
        int selectedrow = jTable1.getSelectedRow();

        if (selectedrow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a stock first", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (!Pattern.compile("([1-9][0-9]*)|(([1-9][0-9]*)[.]([0]*[1-9][0-9]*))|([0][.]([0]*[1-9][0-9]*))").matcher(newprice).matches()) {
            JOptionPane.showMessageDialog(this, "Invalid selling price", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            String stockid = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();

            if (Double.parseDouble(newprice) <= Double.parseDouble(buyingprice)) {

                int x = JOptionPane.showConfirmDialog(this, "New price is <= to the buying price, Do you want to continue", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (x == JOptionPane.YES_OPTION) {

                    try {
                        mysql.iud("UPDATE `stock` SET `selling_price`='" + newprice + "' WHERE `id`='" + stockid + "'");
                        loadstock();
                        reset();
                    } catch (Exception ex) {
                        Logger.getLogger(stock.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    reset();
                }
            } else {

                try {
                    mysql.iud("UPDATE `stock` SET `selling_price`='" + newprice + "' WHERE `id`='" + stockid + "'");
                } catch (Exception ex) {
                    Logger.getLogger(stock.class.getName()).log(Level.SEVERE, null, ex);
                }

                loadstock();
                reset();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int selectedrow = jTable1.getSelectedRow();
        if (selectedrow != -1) {
            String buyingprice = jTable1.getValueAt(selectedrow, 6).toString();
            jLabel1.setText(buyingprice);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        searchstock();
//        
//         try {
//             
//             String category=jComboBox1.getSelectedItem().toString();
//             String brand=jComboBox2.getSelectedItem().toString();
//             String name=jTextField4.getText();
//             String spmin=jTextField2.getText();
//             String spmax=jTextField3.getText();
//             Date mfd_fr=jDateChooser1.getDate();
//             Date mfd_to=jDateChooser2.getDate();
//             Date exp_fr=jDateChooser3.getDate();
//             Date exp_to=jDateChooser4.getDate();
//             String sort=jComboBox4.getSelectedItem().toString();
//
//
//             String x="";
//             
//             if(!category.equals("select")){
//                 x="WHERE `category`.`name`='"+category+"'";
//             }else if(!brand.equals("select")){
//                 x+= " AND `brand`.`name`='"+brand+"'";
//             }
//            ResultSet rs = mysql.search("SELECT DISTINCT `stock`.`id`,`product`.`id`,`category`.`name`,`brand`.`name`,`product`.`name`,`stock`.`qty`,`grn_item`.`buy_price`,`stock`.`selling_price`,`stock`.`mfd`,`stock`.`exp` FROM `stock` INNER JOIN `grn_item` ON `grn_item`.`stock_id`=`stock`.`id` INNER JOIN `product` ON `stock`.`product_id`=`product`.`id` INNER JOIN `brand` ON `product`.`brand_id` =`brand`.id INNER JOIN `category` ON `product`.`category_id`=`category`.`id` "+x+"  ORDER BY `product`.`name` ASC;");
//            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
//            dtm.setRowCount(0);
//
//            while (rs.next()) {
//                Vector v = new Vector();
//                v.add(rs.getString("stock.id"));
//                v.add(rs.getString("product.id"));
//                v.add(rs.getString("category.name"));
//                v.add(rs.getString("brand.name"));
//                v.add(rs.getString("product.name"));
//                v.add(rs.getString("stock.qty"));
//                v.add(rs.getString("grn_item.buy_price"));
//                v.add(rs.getString("stock.selling_price"));
//                v.add(rs.getString("stock.mfd"));
//                v.add(rs.getString("stock.exp"));
//
//                dtm.addRow(v);
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        searchstock();        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        searchstock();
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        searchstock();         // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        searchstock();
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox4ItemStateChanged
        searchstock();
    }//GEN-LAST:event_jComboBox4ItemStateChanged

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        searchstock();
    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void jDateChooser2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser2PropertyChange
        searchstock();
    }//GEN-LAST:event_jDateChooser2PropertyChange

    private void jDateChooser3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser3PropertyChange
        searchstock();
    }//GEN-LAST:event_jDateChooser3PropertyChange

    private void jDateChooser4PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser4PropertyChange
        searchstock();
    }//GEN-LAST:event_jDateChooser4PropertyChange

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            IntelliJTheme.setup(grn.class.getResourceAsStream("../resources/Gradianto_deep_ocean.theme.json"));
        } catch (Exception e) {
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new stock().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
