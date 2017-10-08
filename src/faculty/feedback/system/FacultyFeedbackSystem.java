/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faculty.feedback.system;

import java.util.*;
import java.sql.*;



public class FacultyFeedbackSystem {
 
    /**
     * @param args the command line arguments
     */
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   //static final String DB_URL = "jdbc:mysql://localhost:8889/College_Database_v0_1?zeroDateTimeBehavior=convertToNull";

   //static final String USER = "root";
  // static final String PASS = "root";   
     static final String DB_URL = "jdbc:mysql://mysql5.gear.host/collegedatabase";
   //static final String DB_URL = "jdbc:mysql://192.168.0.102:8889/College_Database_v0_1?zeroDateTimeBehavior=convertToNull";
static final String USER = "collegedatabase";
   static final String PASS = "Rs6pI5-NHXi!";  
   static int formNumber=0;
    public static void az(String[] args) {
        // TODO code application logic here
        int ch1,ch2;
        Scanner keyin =new Scanner(System.in);
        try{
            
            Class.forName(JDBC_DRIVER);

            System.out.println("Please hold while we connect to the data base");
            System.out.println();
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);   
            Statement stmt = conn.createStatement();
         
              java.util.Date javaDate = new java.util.Date();
              long javaTime = javaDate.getTime();
              java.sql.Date sqlDate = new java.sql.Date(javaTime);
              System.out.print("Date : " + sqlDate.toString());
              java.sql.Time sqlTime = new java.sql.Time(javaTime);
              System.out.println("\t\t\tTime : " + sqlTime.toString());
      
            String sql = "Select * FROM `HODS`";
                ResultSet rs = stmt.executeQuery(sql);  
                rs.absolute(1);
                formNumber=rs.getInt(5);
            System.out.println();
             CheckPer k=new CheckPer();
            A:while(true)
            {
                System.out.println("1.Student\n2.Faculty\n3.HOD\n4.Admin\n5.Exit");
                 ch1=keyin.nextInt();
                switch(ch1)
                {
                    case 1:
                    {
                        if(formNumber>0)
                        {
                            //k.checkStudent();
                            
                        }
                        else 
                        {
                            System.out.println("Faculty Feedback hasnt been activated yet\n\nPlease contact your supervisor");
                        }
                        break;    
                            
                    }
                    case 2:
                    {
                            //k.checkFaculty();
                            break ;
                            
                    }
                    case 4:
                    {
                        
                        //k.checkAdmin();
                            /**
                             * the admin has to enter that this is the nth feedback form
                             * once you have this value, you will know which form of student you need to check if completed or not 
                             */
                            break;
                            
                    }
                    case 3:
                        //k.checkHOD();
                        break;
                    case 5:
                    {
                        System.out.println("Goodbye");  
                        System.exit(1);
                            
                    }
                    
                    default:
                    {
                        System.out.println("ERROR");
                        
                    }
                    
                }
                
            
            }
        }
        catch(Exception k)
        {
                k.printStackTrace();
        }
    }
    
    
}
