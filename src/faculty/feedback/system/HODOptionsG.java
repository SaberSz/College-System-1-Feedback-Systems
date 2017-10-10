/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faculty.feedback.system;

import static faculty.feedback.system.FacultyFeedbackSystem.DB_URL;
import static faculty.feedback.system.FacultyFeedbackSystem.USER;
import static faculty.feedback.system.FacultyFeedbackSystem.PASS;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import java.util.Timer;
import net.proteanit.sql.DbUtils;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author rudolf
 */
public class HODOptionsG extends javax.swing.JFrame {

    /**
     * Creates new form HODOptionsG
     */
    boolean otptrue=true;
    public void threadotp() {
    // Do your startup work here
    System.out.println("Started....");
    Timer timer = new Timer();

    TimerTask delayedThreadStartTask = new TimerTask() {
        @Override
        public void run() {

            //captureCDRProcess();
            //moved to TimerTask
            new Thread(new Runnable() {
                @Override
                public void run() {
try
        {
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);   
            Statement stmt = conn.createStatement();
            int r = (int) (Math.random() * (100000)) + 999999;
                        stmt.executeUpdate("UPDATE `HODS` SET `Code`='"+r+"' WHERE 1 ;");
                        String c=Integer.toString(r);
                        if(otptrue)
                        {
                            JOptionPane.showMessageDialog(null, "OTP  reset"); 
                            timer.cancel();
                            timer.purge();
                        }
                        otptrue=false;
                        
                         //OTPGEN.setText(c);
                        // threadotp();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
                    
                }
            }).start();
        }
    };

    timer.schedule(delayedThreadStartTask, 300 * 1000); //5 minute
}
    
    public void threadNotify_Students() {
    // Do your startup work here
    System.out.println("Started....");
    Timer timer = new Timer();

    TimerTask delayedThreadStartTask = new TimerTask() {
        @Override
        public void run() {

            //captureCDRProcess();
            //moved to TimerTask
            new Thread(new Runnable() {
                @Override
                public void run() {
try
        {
            ResultSet rs= null;
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);   
            Statement stmt = conn.createStatement();
            
                        rs = stmt.executeQuery("SELECT `Notify Students` FROM `HODS` WHERE `NAME`='"+hodEntered.ename+"';");
                       // rs.next();
                       rs.absolute(1);
                        int noti=rs.getInt("Notify Students");
                        
                        System.out.println(noti);
                        if(noti>0)
                        {
                             jLabel16.setText("NEW");
                             jButton7.setForeground(Color.red);
                              jButton7.setContentAreaFilled(false);
                               jButton7.setOpaque(false);
                        }
                          
                        else
                        {
                           jLabel16.setText(""); 
                           jButton7.setForeground(Color.black);
                               jButton7.setOpaque(false);
                           
                        }
                           
                        if(timercount)
                        {
                           // JOptionPane.showMessageDialog(null, "stopped");
                            timer.cancel();
                            timer.purge();
                        }
                        threadNotify_Students();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
                    
                }
            }).start();
        }
    };

    timer.schedule(delayedThreadStartTask, 5 * 1000); //1 minute
}
    
    public void threadNotify_Faculty() {
    // Do your startup work here
    System.out.println("Started....");
    Timer timer = new Timer();

    TimerTask delayedThreadStartTask = new TimerTask() {
        @Override
        public void run() {

            //captureCDRProcess();
            //moved to TimerTask
            new Thread(new Runnable() {
                @Override
                public void run() {
try
        {
            ResultSet rs= null;
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);   
            Statement stmt = conn.createStatement();
            
                        rs = stmt.executeQuery("SELECT `Notify Faculty` FROM `HODS` WHERE `NAME`='"+hodEntered.ename+"';");
                        rs.absolute(1);
                        int noti=rs.getInt("Notify Faculty");
                        if(noti>0)
                        {
                            jLabel17.setText("NEW");
                             jButton8.setForeground(Color.red);
                              jButton8.setContentAreaFilled(false);
                               jButton8.setOpaque(false);
                        }
                        
                        else
                             {
                           jLabel17.setText(""); 
                            jButton8.setForeground(Color.black);
                               jButton8.setOpaque(false);
                           
                        }
                           
                        if(timercount)
                        {
                            //JOptionPane.showMessageDialog(null, "stopped");
                            timer.cancel();
                            timer.purge();
                        }
                        threadNotify_Faculty();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
                    
                }
            }).start();
        }
    };

    timer.schedule(delayedThreadStartTask, 10 * 1000); //1 minute
}
    
    public HODOptionsG() {
        initComponents();
        PrintName.setText(hodEntered.ename);
        threadNotify_Students();
         threadNotify_Faculty();
    }
    static boolean timercount= false;
 public void diaplaytab()
 {
     try
        {
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);   
            Statement stmt = conn.createStatement();
            
             String sql = "Select `Name`,`Class`, `Subject`, `SEM`, `F1Q1`, `F1Q2`, `F1Q3`, `F1Q4`, `F1Q5`, `Form 1` FROM `"+hodEntered.dep+"`;";
            
             ResultSet rs = null;
             PreparedStatement prepstmt = null;


        prepstmt = conn.prepareStatement(sql);
        rs = prepstmt.executeQuery();
             FacTable.setModel(DbUtils.resultSetToTableModel(rs));   
             
        }
        catch(Exception e)
        {
            e.printStackTrace();
            
        }
 }
 public void part()
 { // To display only a part of the faculty table
     try
        {
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);   
            Statement stmt = conn.createStatement();
            String name =ename.getText();
            System.out.println(name);
             String sql = "Select `Name`,`Class`, `Subject`, `SEM`, `F1Q1`, `F1Q2`, `F1Q3`, `F1Q4`, `F1Q5`, `Form 1` FROM `"+hodEntered.dep+"`WHERE `Name`='"+name+"';";
             
            
             ResultSet rs = null;
             PreparedStatement prepstmt = null;


        prepstmt = conn.prepareStatement(sql);
        rs = prepstmt.executeQuery();
        
             FacTable.setModel(DbUtils.resultSetToTableModel(rs));   
             
        }
        catch(Exception e)
        {
            e.printStackTrace();
            
        }
 }
 public void close()
    {
        timercount=true;
        WindowEvent wce = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wce);
        //Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wce);
        
        
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
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        ename = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        PrintName = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        Changes = new javax.swing.JPanel();
        Pro = new javax.swing.JPanel();
        OTPGEN = new javax.swing.JLabel();
        GEN = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        newDOB = new javax.swing.JTextField();
        newDEP = new javax.swing.JTextField();
        newName = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        TABS = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        FacTable = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1180, 700));
        getContentPane().setLayout(null);

        jPanel1.setOpaque(false);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/faculty/feedback/system/Icons/Update Tag Filled-32.png"))); // NOI18N
        jButton2.setText("Update Profile");
        jButton2.setOpaque(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/faculty/feedback/system/Icons/Logout Rounded Up Filled-32.png"))); // NOI18N
        jButton4.setText("Logout");
        jButton4.setOpaque(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/faculty/feedback/system/Icons/Feedback Filled-32.png"))); // NOI18N
        jButton1.setText("Feedback Result of a Particular");
        jButton1.setOpaque(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/faculty/feedback/system/Icons/Key Filled-32.png"))); // NOI18N
        jButton3.setText("Generate admin code");
        jButton3.setOpaque(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        ename.setOpaque(false);
        ename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enameActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        jLabel12.setText("Search");

        jButton7.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jButton7.setText("Student Remarks");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jButton8.setText("Faculty Remarks");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ename, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(32, 32, 32)
                .addComponent(jButton2)
                .addGap(29, 29, 29)
                .addComponent(jButton3)
                .addGap(32, 32, 32)
                .addComponent(jButton4)
                .addGap(20, 20, 20))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(900, 70, 274, 410);

        PrintName.setFont(new java.awt.Font("Adobe Caslon Pro", 1, 18)); // NOI18N
        getContentPane().add(PrintName);
        PrintName.setBounds(62, 28, 226, 38);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(883, 62, 26, 394);

        Changes.setOpaque(false);
        Changes.setLayout(new java.awt.CardLayout());

        Pro.setOpaque(false);

        OTPGEN.setFont(new java.awt.Font("Adobe Caslon Pro", 0, 24)); // NOI18N

        javax.swing.GroupLayout ProLayout = new javax.swing.GroupLayout(Pro);
        Pro.setLayout(ProLayout);
        ProLayout.setHorizontalGroup(
            ProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(OTPGEN, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(118, Short.MAX_VALUE))
        );
        ProLayout.setVerticalGroup(
            ProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProLayout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(OTPGEN, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(558, Short.MAX_VALUE))
        );

        Changes.add(Pro, "card2");

        GEN.setOpaque(false);

        jLabel1.setText("Old Profile");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel2.setText("New Profile");

        jPanel2.setOpaque(false);

        jLabel4.setText("Date of Birth YYYY-MM-DD");

        jLabel3.setText("Name");

        jLabel5.setText("Department");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(74, 74, 74)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newDOB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newDEP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newDEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setOpaque(false);

        jLabel6.setText("Date of Birth YYYY-MM-DD");

        jLabel7.setText("Name");

        jLabel8.setText("Department");

        jButton6.setText("No Change");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addGap(74, 74, 74)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                        .addGap(22, 22, 22))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(120, 120, 120))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(87, 87, 87)
                .addComponent(jButton6)
                .addContainerGap())
        );

        jButton5.setText("Submit New");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout GENLayout = new javax.swing.GroupLayout(GEN);
        GEN.setLayout(GENLayout);
        GENLayout.setHorizontalGroup(
            GENLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GENLayout.createSequentialGroup()
                .addGroup(GENLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(GENLayout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(jLabel1))
                    .addGroup(GENLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(GENLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(GENLayout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(GENLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(GENLayout.createSequentialGroup()
                                .addGap(142, 142, 142)
                                .addComponent(jLabel2))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(GENLayout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
        );
        GENLayout.setVerticalGroup(
            GENLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GENLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(GENLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(GENLayout.createSequentialGroup()
                        .addGroup(GENLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(26, 26, 26)
                        .addGroup(GENLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(GENLayout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(78, 78, 78)
                                .addComponent(jButton5)
                                .addGap(521, 521, 521))
                            .addGroup(GENLayout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(46, 46, 46))))
                    .addGroup(GENLayout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        Changes.add(GEN, "card3");

        TABS.setOpaque(false);

        FacTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Class", "Subject", "Sem", "Question 1 ", "Question 2 ", "Question 3", "Question 4", "Question 5", "Average"
            }
        ));
        jScrollPane1.setViewportView(FacTable);

        javax.swing.GroupLayout TABSLayout = new javax.swing.GroupLayout(TABS);
        TABS.setLayout(TABSLayout);
        TABSLayout.setHorizontalGroup(
            TABSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TABSLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE)
                .addContainerGap())
        );
        TABSLayout.setVerticalGroup(
            TABSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TABSLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(507, Short.MAX_VALUE))
        );

        Changes.add(TABS, "card4");

        getContentPane().add(Changes);
        Changes.setBounds(62, 129, 815, 828);
        getContentPane().add(jLabel16);
        jLabel16.setBounds(900, 50, 60, 20);
        getContentPane().add(jLabel17);
        jLabel17.setBounds(1040, 50, 60, 0);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/faculty/feedback/system/Icons/HODOpdtions.jpg"))); // NOI18N
        getContentPane().add(jLabel13);
        jLabel13.setBounds(0, 0, 1180, 690);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        Changes.removeAll();
        Changes.repaint();
        Changes.revalidate();
        Changes.add(TABS);
        Changes.repaint();
        Changes.revalidate();
        if("".equals(ename.getText()))
        {
            diaplaytab();
        } 
        else
        {
             part();
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Changes.removeAll();
        Changes.repaint();
        Changes.revalidate();
        Changes.add(Pro);
        Changes.repaint();
        Changes.revalidate();
        try
        {
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);   
            Statement stmt = conn.createStatement();
            int r = (int) (Math.random() * (10000)) + 9999;
                        stmt.executeUpdate("UPDATE `HODS` SET `Code`='"+r+"' WHERE `NAME`='"+hodEntered.ename+"';");
                        String c=Integer.toString(r);
                        JOptionPane.showMessageDialog(null, "OTP is : "+c); 
                        otptrue=true;
                         //OTPGEN.setText(c);
                         threadotp();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
            
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        otptrue=false;
        this.setVisible(false);
        MainMenu obj1 = new MainMenu();
                obj1.setVisible(true);
           timercount=true;      
               close();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Changes.removeAll();
        Changes.repaint();
        Changes.revalidate();
        Changes.add(GEN);
        Changes.repaint();
        Changes.revalidate();
         try
        {
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);   
            Statement stmt = conn.createStatement();
            ResultSet rs=null;
                         rs =stmt.executeQuery("Select * FROM `HODS`  WHERE `NAME`='"+hodEntered.ename+"';");
                         rs.next();
                         jLabel9.setText(rs.getString(1));
                         jLabel10.setText(rs.getString(2));
                         jLabel11.setText(rs.getString(3));
                       
                         
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
         
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
       try
        {
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);   
            Statement stmt = conn.createStatement();
            ResultSet rs=null;
                          stmt.executeUpdate("UPDATE `HODS` SET `Name`='"+newName.getText()+"', `DOB`='"+newDOB.getText()+"',`Branch`='"+newDEP.getText()+"' WHERE `NAME`='"+hodEntered.ename+"';"); 
                           JOptionPane.showMessageDialog(null, "Saved"); 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
       
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "No Change"); 
    }//GEN-LAST:event_jButton6ActionPerformed

    private void enameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enameActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        //text file, should be opening in default text editor
        File file = new File("StudentComplaint"+hodEntered.dep.toUpperCase()+".txt");
        
        //first check if Desktop is supported by Platform or not
        if(!Desktop.isDesktopSupported()){
            JOptionPane.showMessageDialog(null, "Not Supported. Please contact Tech Support"); 
            return;
        }
        try{
        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) {
            desktop.open(file); 
             Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);   
            Statement stmt = conn.createStatement();
             stmt.executeUpdate("Update `HODS` SET `Notify Students`=0 WHERE `NAME`='"+hodEntered.ename+"';");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "None of the students have entered any remarks"); 
        }
        }
        catch(Exception e){JOptionPane.showMessageDialog(null, "EEEE");  }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        File file = new File("FacultyComplaint"+hodEntered.dep.toUpperCase()+".txt");
        
        //first check if Desktop is supported by Platform or not
        if(!Desktop.isDesktopSupported()){
            JOptionPane.showMessageDialog(null, "Not Supported. Please contact Tech Support"); 
            return;
        }
        try{
        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) 
        {
            desktop.open(file);
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);   
            Statement stmt = conn.createStatement();
             stmt.executeUpdate("Update `HODS` SET `Notify Faculty`=0 WHERE `NAME`='"+hodEntered.ename+"';");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "None of the faculty members have entered any remarks"); 
        }
        }
        catch(Exception e){JOptionPane.showMessageDialog(null, "EEEE");  }
    }//GEN-LAST:event_jButton8ActionPerformed

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
            java.util.logging.Logger.getLogger(HODOptionsG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HODOptionsG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HODOptionsG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HODOptionsG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HODOptionsG().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Changes;
    private javax.swing.JTable FacTable;
    private javax.swing.JPanel GEN;
    private javax.swing.JLabel OTPGEN;
    private javax.swing.JLabel PrintName;
    private javax.swing.JPanel Pro;
    private javax.swing.JPanel TABS;
    private javax.swing.JTextField ename;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField newDEP;
    private javax.swing.JTextField newDOB;
    private javax.swing.JTextField newName;
    // End of variables declaration//GEN-END:variables
}
