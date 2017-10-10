/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faculty.feedback.system;

/**
 *
 * @author rudolf
 */
import static faculty.feedback.system.FacultyFeedbackSystem.DB_URL;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.sql.*;

    //ds
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.TimerTask;
import javax.swing.*;
public class MainMenu extends javax.swing.JFrame {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://mysql5.gear.host/collegedatabase";
   //static final String DB_URL = "jdbc:mysql://192.168.0.102:8889/College_Database_v0_1?zeroDateTimeBehavior=convertToNull";
static final String USER = "collegedatabase";
   static final String PASS = "Rs6pI5-NHXi!";  
   //static final String USER = "root";
  // static final String PASS = "root";   
    Connection conn =null;
    ResultSet rs=null;
     Statement stmt = null;
    /**
     * Creates new form MainMenu
     */
    /*public void windowClosing(WindowEvent e) {
         frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
frame.addWindowListener(new java.awt.event.WindowAdapter() {
    @Override
    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        if (JOptionPane.showConfirmDialog(frame, 
            "Are you sure to close this window?", "Really Closing?", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }
});
    }*/
     
    

    static volatile boolean tick=true;
   static boolean don=false;
   static int h=-1;
   public void ads()
   {
       String ada[]={"ad1.jpg","ad2.jpg","ad3.jpg","ad4.jpg","ad5.jpg","ad6.jpg"};
       if (h==-1)
       {
           
                   Object[] option={"I'm Good","AD Free please"};
            h=JOptionPane.showOptionDialog(null, "If you want an ad free software, then please give your donation to the supervisor in charge","AD FREE!!!!", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
       }
            if(h==1)don=true;
            if(!don)            
            {

                   addMouseListener(new MouseAdapter(){
                       int mouseX=0,mouseY=0;
                       public void mouseExited(MouseEvent e)
                       {
                           //for(int i=0;i<200;i++)
                           int x,y;
                          // mouseX=e.getX();mouseX=e.getY();
                          // System.out.println(mouseX);
                          // System.out.println(mouseY);
                           //if((mouseX>=50 && mouseY>=350)&&(mouseX<=250 && mouseY<=550))
                           {
                              // for(int j=0;j<200;j++)
                               {
                                    //mouseX=50;//+j;
                                    //mouseY=350;//+i;
                                     int r = (int) (Math.random() * (6));
                                    AD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/faculty/feedback/system/Icons/"+ada[r])));
                                    //AD2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/faculty/feedback/system/Icons/ad2.jpg")));
                                    repaint();
                               }
                              
                           }    
                           
                           
                       }
                      
                       
                   }); 
               }   
   }

   public void threadtock() {
   
    System.out.println("Started....");
    
final java.util.Timer timer = new java.util.Timer();
    final TimerTask delayedThreadStartTask = new TimerTask() {
        

        public void run() {
System.out.println("Started..123131..");
            //captureCDRProcess();
            //moved to TimerTask
            //new Thread(new Runnable() {
               
             //   public void run() {
try
        {
            if(tick){
            tickTock.setText(Time.Time());
            
            }
            else{
                System.out.println("Hello");
                timer.cancel();  // Terminates this timer, discarding any currently scheduled tasks.
timer.purge();   // Removes all cancelled tasks from this timer's task queue.
            }
                      // if(tick)
                      //  {
                      //      threadtock();
                      //  }
                         
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
                    
                }
       //     }).start();
       // }
    };

    timer.schedule(delayedThreadStartTask, 1 * 1000);//1 second
}
   
    public MainMenu() {
        try
                {
                    initComponents();
                if(true){conn =Connect.ConnectDb();System.out.println("Started....1");}
                if(don)ads();//to get ads() to run add change to (!don)
                String sql = "Select * FROM `HODS`";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);  
                rs.absolute(1);
                FacultyFeedbackSystem.formNumber=rs.getInt(5);
                System.out.println(FacultyFeedbackSystem.formNumber+5);
                if(FacultyFeedbackSystem.formNumber>0)
                {
                    java.sql.Date date1 = new java.sql.Date(System.currentTimeMillis()) ;//current date
                    String s2=rs.getString(8);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = sdf.parse(s2);
                long millis = date.getTime();
       
                System.out.println( millis);
                java.util.Date date2 = new java.sql.Date( millis);
                if((date1.after(date2))){
                    stmt.executeUpdate("Update `HODS` SET `Done Date`='0' WHERE 1");  
                    FacultyFeedbackSystem.formNumber=0;
                }
            
                }
                tickTock.setText(Time.Time());
                tick=true;
                threadtock();
                }
        catch(Exception e )
        {
            e.printStackTrace();
        }
    }
    
    public void close1()
    {
        tick=false;
        WindowEvent wce = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wce);
        
        //Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wce);
        
    }
    public void close()
    {
        tick=false;
        WindowEvent wce = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wce);
        
        
        //timer.cancel();  // Terminates this timer, discarding any currently scheduled tasks.
       // timer.purge();   // Removes all cancelled tasks from this timer's task queue.
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

        tickTock = new javax.swing.JLabel();
        AD = new javax.swing.JLabel();
        AD2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        HODButton = new javax.swing.JButton();
        AdminButton = new javax.swing.JButton();
        FacultyButton = new javax.swing.JButton();
        StudentButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1200, 675));
        getContentPane().setLayout(null);

        tickTock.setFont(new java.awt.Font("Copperplate", 0, 36)); // NOI18N
        tickTock.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(tickTock);
        tickTock.setBounds(1020, 20, 160, 60);
        getContentPane().add(AD);
        AD.setBounds(10, 320, 420, 350);

        AD2.setSize(new java.awt.Dimension(307, 260));
        getContentPane().add(AD2);
        AD2.setBounds(880, 410, 307, 260);

        jPanel1.setOpaque(false);

        HODButton.setBackground(new java.awt.Color(51, 51, 51));
        HODButton.setText("Head of Department");
        HODButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HODButtonActionPerformed(evt);
            }
        });

        AdminButton.setBackground(new java.awt.Color(51, 51, 51));
        AdminButton.setText("Admin");
        AdminButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminButtonActionPerformed(evt);
            }
        });

        FacultyButton.setBackground(new java.awt.Color(51, 51, 51));
        FacultyButton.setText("Faculty");
        FacultyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FacultyButtonActionPerformed(evt);
            }
        });

        StudentButton.setBackground(new java.awt.Color(51, 51, 51));
        StudentButton.setText("Student");
        StudentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StudentButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(StudentButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FacultyButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(HODButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AdminButton, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(7, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(StudentButton)
                .addGap(96, 96, 96)
                .addComponent(FacultyButton)
                .addGap(94, 94, 94)
                .addComponent(HODButton)
                .addGap(102, 102, 102)
                .addComponent(AdminButton)
                .addContainerGap())
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(710, 130, 170, 426);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/faculty/feedback/system/mainmenu.jpg"))); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(1100, 675));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, -90, 1290, 860);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void StudentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StudentButtonActionPerformed
        // TODO add your handling code here:
        try
        {
            if(FacultyFeedbackSystem.formNumber>0)
            {

                StudentLogin obj = new StudentLogin();
                obj.setVisible(true);
                tick=false;
                this.setVisible(false);

                //close();

            }
            else
            {
                JOptionPane.showMessageDialog(null, "The Faculty feedback hasn't been activated. Please inform your supervisor");
            }
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_StudentButtonActionPerformed

    private void FacultyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FacultyButtonActionPerformed
        // TODO add your handling code here:
         FacultyLogin obj = new FacultyLogin();
                obj.setVisible(true);
                tick=false;
                 this.setVisible(false);

                //close();

    }//GEN-LAST:event_FacultyButtonActionPerformed

    private void AdminButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminButtonActionPerformed
        // TODO add your handling code here:
         AdminLogin obj = new AdminLogin();
                obj.setVisible(true);
                tick=false;
                 this.setVisible(false);

                //close();

    }//GEN-LAST:event_AdminButtonActionPerformed

    private void HODButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HODButtonActionPerformed
        // TODO add your handling code here:
        HODLogin obj = new HODLogin();
                obj.setVisible(true);
                tick=false;
                 this.setVisible(false);

                //close();

    }//GEN-LAST:event_HODButtonActionPerformed

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
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AD;
    private javax.swing.JLabel AD2;
    private javax.swing.JButton AdminButton;
    private javax.swing.JButton FacultyButton;
    private javax.swing.JButton HODButton;
    private javax.swing.JButton StudentButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel tickTock;
    // End of variables declaration//GEN-END:variables
}
