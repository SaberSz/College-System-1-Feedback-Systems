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
import static faculty.feedback.system.FacultyFeedbackSystem.USER;
import static faculty.feedback.system.FacultyFeedbackSystem.PASS;
import static faculty.feedback.system.FacultyFeedbackSystem.formNumber;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


import java.sql.*;
import java.util.TimerTask;
import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.*;
import javax.swing.text.*;
import javax.swing.event.*;
import javax.swing.table.*;
import net.proteanit.sql.DbUtils;



public class FeedBackEntryG extends javax.swing.JFrame {

    /**
     * Creates new form FeedBackEntryG
     */
   static int rowss=0;
    static boolean validated=true;
    public FeedBackEntryG() {
        initComponents();
      
        PrintUSN.setText("USN : "+FeedbackEntry.Usn );
        PrintDep.setText("Department : "+FeedbackEntry.dep );
        SEM.setText("Semester : "+FeedbackEntry.sem );
        Sec.setText("Section : "+FeedbackEntry.sec);
        //each cell
        
        fetch();
        
        
        
    }  
       public void threadtock() {
   
    System.out.println("Started....");
    java.util.Timer timer = new java.util.Timer();

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
             feedbackTable.setPreferredScrollableViewportSize(feedbackTable.getPreferredSize());

        TableCellEditor fce = new validMarksEditor();
        feedbackTable.setDefaultEditor(Object.class, fce);
           
                        if(true)
                        {
                            threadtock();
                        }
                         
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
                    
                }
            }).start();
        }
    };

    timer.schedule(delayedThreadStartTask, 1 * 100); //1 second
}
class validMarksEditor extends DefaultCellEditor
    {
        validMarksEditor()
        {
            super( new JTextField() );
        }

        public boolean stopCellEditing()
        {
            try
            {
               Integer marks = (Integer)getCellEditorValue();

                if(marks>10 ||marks<0)
                {
                    JTextField textField = (JTextField)getComponent();
                    textField.setBorder(new LineBorder(Color.red));
                    textField.selectAll();
                    textField.requestFocusInWindow();

                    JOptionPane.showMessageDialog(
                        null,
                        "Points given should range between 0 and 10",
                        "Alert!",JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            catch(ClassCastException exception)
            {
                return false;
            }

            return super.stopCellEditing();
        }

        public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column)
        {
            Component c = super.getTableCellEditorComponent(
                table, value, isSelected, row, column);
            ((JComponent)c).setBorder(new LineBorder(Color.black));

            return c;
        }

    }



  public void WFile()
{
    BufferedWriter bw = null;
		FileWriter fw = null;
                try {

			//String data = " This is new content";
                        String k=FACComplaint.getText();
            k=FeedbackEntry.dep+"\t"+FeedbackEntry.sem+"\t"+FeedbackEntry.sec+"\n"+Date.Date()+"\t"+Time.Time()+"\n"+k+"\n\n";

			File file = new File("StudentComplaint"+FeedbackEntry.dep+".txt");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// true = append file
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);

			bw.write(k);

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();
                }finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
                }
                        

   /* try {
         String k=FacComplaint.getText();
            k=FacultyData.eName+"\n"+Time.Time()+k+"\n\n";
            
            char buffer[]=new char [k.length()];
            k.getChars(0,k.length(),buffer,0);
            FileWriter f = new FileWriter ("FacultyComplaint.docx");
            for(int i=0;i<buffer.length;i+=2)
            {
                f.write(buffer[i]);
            }
    }
    
     catch(Exception e)
        {
            e.printStackTrace();
        }*/
    
}   
    public boolean attended(String Sub,String Name,String no)
    {
        try
        {
              Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);   
            Statement stm = conn.createStatement();
            // String sql = "Select `"+no+"` FROM `studentAttendace` WHERE `USN`='"+FeedbackEntry.Usn+"';";
             String sql = "Select * FROM `studentAttendace` WHERE `USN`='"+FeedbackEntry.Usn+"';";
              System.out.println("YOS");
             ResultSet rs = null;
             rs =stm.executeQuery(sql);
              System.out.println("YOS");
              System.out.println(FeedbackEntry.Usn);
              if(rs.absolute(1))
              {
                  System.out.println(rs.getString("Sub 1")+"  "+rs.getString("Sub 2"));
             int k1=Integer.valueOf(rs.getString(no));
             System.out.println("k1="+k1);
             if(k1>=80)
                return true;
             else 
                 return false;
              }
              else
              {
                   JOptionPane.showMessageDialog(null, FeedbackEntry.Usn+" attendance not updated.");

                  return false;
              }    
                  

        }
        catch(Exception e)
        {
             
            e.printStackTrace();
        }   
        return false;
    }
    public void fetch()
    {
        
        try
        {
            DefaultTableModel del= (DefaultTableModel) NameTable.getModel();
            
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);   
            Statement stmt = conn.createStatement();
            
             String sql = "Select `Subject`,`Name`,`subNumber` FROM `"+FeedbackEntry.dep+"` WHERE `Class`='"+FeedbackEntry.sec+"' AND `SEM`='"+FeedbackEntry.sem+"';";
            
             ResultSet rs = null;
             rs = stmt.executeQuery(sql);
             while(rs.next())
             {
                 String Sub = rs.getString("Subject");
                 String Name = rs.getString("Name");
                  String no = rs.getString("subNumber");
                   System.out.println(Sub+Name+no);
                 if(attended(Sub,Name,no))
                 {
                     del.addRow(new Object[]{Sub, Name});
                     rowss++;
                 }
             }
            /* PreparedStatement prepstmt = null;


        prepstmt = conn.prepareStatement(sql);
        rs = prepstmt.executeQuery();
             NameTable.setModel(DbUtils.resultSetToTableModel(rs));  */ 
            for(int i=0;i<rowss;i++)
            {
                feedbackTable.setEditingRow(i);
            }
            if(NameTable.getRowCount()<6)
            {
                JOptionPane.showMessageDialog(null, "You are allowed to give feedback for subjects in which your attendance is above or equal to 80% ");
            }
            
              
             
        }
        catch(Exception e)
        {
            e.printStackTrace();
            
        }
        
    }
    public float validMarks(int r,int c) throws ArrayIndexOutOfBoundsException
    {
        float x;
        //while(true)
        {
            System.out.println("entered"+feedbackTable.getValueAt(r,c));
            if(feedbackTable.getValueAt(r,c)!=null){
            x= Float.valueOf(feedbackTable.getValueAt(r,c).toString());
            System.out.println("x=" + x);
            if(x<=10 && x>=0)
             {
                return x;
             }
            else
            {
                JOptionPane.showMessageDialog(null, "Invalid Feedback. Input should be between 0 and 10");
                validated=false;
                return 0;
            }
            }
            else if(r==NameTable.getRowCount()-1 && c==4)
            {
                System.out.println("~~~~~~~~~~~~~~~~~~~COMPENSATION~~~~~~~~~~~~~~~~~~~~~~~~~");
                return Integer.valueOf(feedbackTable.getValueAt(r-1,c).toString());
            }
            else{
                 System.out.println("Invalid : "+r+" "+c);
             JOptionPane.showMessageDialog(null, "Invalid Feedback. You have missed an entry. Make sure no fields are still in editing state");
                validated=false;
                return 0;
            
            }
        }
        
    }
    public  float Average(float a,float b)
   {
       float avg = (a+b)/2;
       System.out.println("Avg is "+a+" "+b+" "+avg);
       return avg;   
   }
    
    
        

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        feedbackTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        NameTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        FACComplaint = new javax.swing.JTextArea();
        PrintUSN = new javax.swing.JLabel();
        PrintDep = new javax.swing.JLabel();
        SEM = new javax.swing.JLabel();
        Sec = new javax.swing.JLabel();
        Wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setOpaque(false);

        feedbackTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Question 1", "Question 2", "Question 3", "Question 4", "Question 5"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        feedbackTable.setColumnSelectionAllowed(true);
        feedbackTable.setOpaque(false);
        feedbackTable.setRowHeight(25);
        jScrollPane1.setViewportView(feedbackTable);
        feedbackTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        NameTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Subject", "Name"
            }
        ));
        NameTable.setOpaque(false);
        NameTable.setRowHeight(25);
        jScrollPane2.setViewportView(NameTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/faculty/feedback/system/Icons/Enter-32.png"))); // NOI18N
        jButton1.setText("Submit");
        jButton1.setOpaque(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/faculty/feedback/system/Icons/Back-32.png"))); // NOI18N
        jButton2.setText("Back to Main Menu ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel2.setOpaque(false);

        jLabel9.setText("Question 3 :Depth in Subject");

        jLabel10.setText("Question 4 :Control over Class ");

        jLabel8.setText("Question 2 :Audibility ");

        jLabel11.setText("Question 5 :Availability after class hours");

        jLabel7.setText("Question 1 :Punctuality");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jSeparator1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addContainerGap())
        );

        FACComplaint.setColumns(20);
        FACComplaint.setRows(5);
        FACComplaint.setToolTipText("");
        jScrollPane3.setViewportView(FACComplaint);

        PrintUSN.setText("USN");

        PrintDep.setText("DEP");

        Wallpaper.setFont(new java.awt.Font("Copperplate", 0, 24)); // NOI18N
        Wallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/faculty/feedback/system/Icons/StudentLogin.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(101, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(PrintUSN, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(117, 117, 117)
                                .addComponent(PrintDep, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(SEM, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Sec, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(179, 179, 179)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2)
                                .addGap(31, 31, 31)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Wallpaper, javax.swing.GroupLayout.PREFERRED_SIZE, 1080, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PrintUSN, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(Sec)
                    .addComponent(PrintDep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SEM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)))
                .addGap(53, 53, 53)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Wallpaper, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int temprow=0;
        float marksUpdate[]=new float[5];
        float oldMarks[]=new float[5];
       float formAverage=0,temp;
       int columnAccess=6;
        try{
            validated=true;
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);   
            Statement stmt = conn.createStatement();
           //String sql = "Select * FROM `"+FeedbackEntry.dep+"` WHERE `Class`='"+FeedbackEntry.sec+"' AND `SEM`='"+FeedbackEntry.sem+"';";
            ResultSet rs=null;
           // ResultSet rs = stmt.executeQuery(sql);
       Statement stmt2=conn.createStatement();
           
       columnAccess*=formNumber;
       String specificRow,sql;
       
       {
           
           for (int i=0;i<NameTable.getRowCount();i++ , temprow++)
           {
               formAverage=0;
               sql = "Select * FROM `"+FeedbackEntry.dep+"` WHERE `Class`='"+FeedbackEntry.sec+"' AND `SEM`='"+FeedbackEntry.sem+"' AND `SUBJECT`='"+NameTable.getValueAt(temprow,0).toString()+"';";
            System.out.println(NameTable.getValueAt(temprow,0).toString()) ;
            rs= stmt.executeQuery(sql);

              if(rs.next())
              {
                  for (int h=0;(h<=4) && validated ;h++)
                  {
                      System.out.println(marksUpdate[h]=validMarks(i,h)) ;
                  }
              
               if(validated){               
               for(int j=0;j<5;j++)
                {
                    oldMarks[j]=rs.getFloat(j+6);
                      System.out.println("j="+j);
                }
                
               specificRow=" WHERE `Class`='"+FeedbackEntry.sec+"' AND `SEM`='"+FeedbackEntry.sem+"' AND `Name` ='"+rs.getString(1)+"';";
               // stmt.executeUpdate("Insert into `"+DEP+"`("+columnAccess+","+(columnAccess+1)+","+(columnAccess+2)+","+(columnAccess+3)+","+(columnAccess+4)+") VALUES ('"+marksUpdate[0]+"','"+marksUpdate[1]+"','"+marksUpdate[2]+"','"+marksUpdate[3]+"','"+marksUpdate[4]+"')"+specificRow); 
                
                for(int k=0;k<5;k++)
                {
                   temp=Average(marksUpdate[k],oldMarks[k]); 
                   formAverage+= temp;
                    System.out.println("SUM="+formAverage);
                    stmt2.executeUpdate("Update `"+FeedbackEntry.dep+"` SET `F"+formNumber+"Q"+(1+k)+"`='"+temp+"'"+specificRow);
                   if(k==4)
                   {
                       
                       formAverage/=5;
                        System.out.println("final avg="+formAverage);
                       stmt2.executeUpdate("Update `"+FeedbackEntry.dep+"` SET `Form "+formNumber+"`='"+formAverage+"'"+specificRow);
                   }
                   System.out.println("k="+k);
                }
                
               }
              
              System.out.println("i="+i);
       } 
              
        }
           if(validated){   
       stmt.executeUpdate("Update `Student Records` SET `Form "+formNumber+"`='1' WHERE `USN`='"+FeedbackEntry.Usn+"';");
       JOptionPane.showMessageDialog(null, "Thank you for your feedback.");
       String ab=FACComplaint.getText();
       ab=ab.replaceAll(" ","");
       ab=ab.replaceAll("\n","");
      ab= ab.replaceAll("\t","");
       if(!("".equals(ab)))
       {
           WFile();
           sql="SELECT `Notify Students` FROM `HODS` WHERE `Branch`='"+FeedbackEntry.dep+"';";
           rs = stmt.executeQuery(sql);
           rs.next();
           int noti=rs.getInt("Notify Students");
           noti ++;
           stmt.executeUpdate("Update `HODS` SET `Notify Students`='"+noti+"' WHERE `Branch`='"+FeedbackEntry.dep+"';");
       
       }
       
       
       
       MainMenu obj1 = new MainMenu();
                obj1.setVisible(true);
                close();
                
           }
       }
        }
        
       catch(Exception e)
        {
           e.printStackTrace();
           
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed
public void close()
    {
        WindowEvent wce = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wce);
        //Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wce);
        
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        MainMenu obj1 = new MainMenu();
                obj1.setVisible(true);
                
                close();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]){
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
            java.util.logging.Logger.getLogger(FeedBackEntryG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FeedBackEntryG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FeedBackEntryG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FeedBackEntryG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FeedBackEntryG().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea FACComplaint;
    private javax.swing.JTable NameTable;
    private javax.swing.JLabel PrintDep;
    private javax.swing.JLabel PrintUSN;
    private javax.swing.JLabel SEM;
    private javax.swing.JLabel Sec;
    private javax.swing.JLabel Wallpaper;
    private javax.swing.JTable feedbackTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
