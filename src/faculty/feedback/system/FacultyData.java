/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faculty.feedback.system;
import java.util.*;
import java.sql.*;
/**
 *
 * @author rudolf
 */
public class FacultyData extends FacultyFeedbackSystem{
    static String eName,eDOB,eDEP;
    
    public void accessOptions(String Name,String DOB,String DEP)
    {
       eName=Name;
       eDOB=DOB;
       eDEP=DEP;
       FacultyDataG obj1 = new FacultyDataG();
                obj1.setVisible(true);
       /*String sql; 
       int columnAccess=6;
       columnAccess*=formNumber;
       String Dep[]={"BT","CS","ME","EE","EC"};
        try 
        {
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);   
            Statement stmt = conn.createStatement();
            System.out.println("1.Punctuality");
            System.out.println("2.Audibility");
            System.out.println("3.Depth in Subject");
            System.out.println("4.Control over Class");
            System.out.println("5.Availability after class hour");
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("Department\tClass\tSubject\tSemester\tQ1\tQ2\tQ3\tQ4\tQ5\tAverage");
            for(int z=0;z<5;z++)
            {
                
                sql = "Select * FROM `"+Dep[z]+"` WHERE `Name`='"+Name+"';";
                ResultSet rs = stmt.executeQuery(sql);


                while(rs.next())
                {   
                    System.out.print(Dep[z]+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5));
                    for(int i=0;i<6;i++)
                    {
                        System.out.print("\t"+rs.getString(columnAccess+i));
                    }

                }
                System.out.println();
            }
            System.out.println();
            System.out.println("\n");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }*/
    }
}
