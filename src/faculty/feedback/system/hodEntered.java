/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faculty.feedback.system;
import java.sql.*;
import java.util.*;
/**
 *
 * @author rudolf
 */

public class hodEntered extends FacultyFeedbackSystem{
    static String ename,dep;
    public void hodOptions(String Dep,String Name)
    {
        ename=Name;
        dep=Dep;
        HODOptionsG obj1 = new HODOptionsG();
                obj1.setVisible(true);
        //Scanner keyin=new Scanner(System.in);
      //  int c;
       /* try
        {
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);   
            Statement stmt = conn.createStatement();
            A:while(true)
            {
                System.out.println("1.Feedback Result of Department\n2.Update Profile\n3.Generate admin code\n4.Log Out ");
                c=keyin.nextInt();
                switch(c)
                {
                    case 2:
                        //Update Profile
                        updateInfo(Name);
                        break;
                    case 1:
                        depReport(Dep);
                        break;
                    case 4:
                        
                        break A;
                    case 3:
                        int r = (int) (Math.random() * (10000)) + 9999;
                        stmt.executeUpdate("UPDATE `HODS` SET `Code`='"+r+"' WHERE `NAME`='"+Name+"';");
                         System.out.println("Code :"+r);
                         break;
                    default:
                        System.out.println("Error\n Try again ");
                        
                }
            }
        }
        catch(Exception e)
        {
            
        }*/
        
    }
    
    public void depReport(String Dep)
    {
         Scanner keyin=new Scanner(System.in);
        int c;
        String sql,Name;
        try {
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);   
            Statement stmt = conn.createStatement();
            
            Z:while(true)
            {
                System.out.println("1.List of all Faculty\n2.Search for particular Faculty member\n3.Go Back ");
                c=keyin.nextInt();
                System.out.println(c);
                switch(c)
                {
                    case 1:
                        //list of all faculty
                         sql = "Select * FROM `"+Dep+"`;";
                         facultyReport(sql,Dep);
                        break;
                    case 2:
                        //particular faculty member
                        System.out.println("Enter the name of the faculty member ");
                        Name=keyin.next();
                         String kp=keyin.next();
                         Name+=" "+kp;
                         sql = "Select * FROM `"+Dep+"` WHERE `Name`='"+Name+"';";
                         ResultSet rs = stmt.executeQuery(sql);
                         System.out.println(Name);
                         if(rs.absolute(1))
                         {
                             facultyReport(sql,Dep);
                         }
                         else {
                             System.out.println("No such faculty exists");
                         }
                        break;
                    case 3:
                       // System.out.println("hi");
                        break Z;
                    default:
                        System.out.println("Error\n Try again ");
                       
                }
                //System.out.println("hii"); 
            }
            //System.out.println("hii2"); 
        }
        catch(Exception e)
        {
            
        }
    }
    void facultyReport(String sql,String Dep)
    {
        Scanner keyin=new Scanner(System.in);
        int columnAccess=6;
        columnAccess*=formNumber;
        int c;
        try {
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);   
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("1.Punctuality");
            System.out.println("2.Audibility");
            System.out.println("3.Depth in Subject");
            System.out.println("4.Control over Class");
            System.out.println("5.Availability after class hour");
            System.out.println();
            System.out.println();
            System.out.println();
                System.out.println("Name\tClass\tSubject\tSemester\tQ1\tQ2\tQ3\tQ4\tQ5\tAverage");
                while(rs.next())
                {   
                    System.out.print(rs.getString(1)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5));
                    for(int i=0;i<6;i++)
                    {
                        System.out.print("\t"+rs.getString(columnAccess+i));
                    }
                    System.out.println();
                }
                
            
            System.out.println();
            System.out.println("\n");
        }
         catch(Exception e)
        {
             e.printStackTrace();
        }
    }
    void updateInfo(String Name)
    {
        Scanner keyin=new Scanner(System.in);
        int c;
        try
        {
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);   
            Statement stmt = conn.createStatement();
            String Dob,DOB,newData;
             String sql = "Select * FROM `HODS` WHERE `Name`='"+Name+"';";
            ResultSet rs = stmt.executeQuery(sql); 
            rs.absolute(1);
            DOB=rs.getString(2);
            System.out.println("Enter your current Date of Birth");
                Dob=keyin.next();
            if(DOB.equals(Dob))
                    {
                        
            X:while(true)
            {
                System.out.println("1.Update Name \n2.Update Date of Birth\n3.Update Branch\n4.Go Back");
                c=keyin.nextInt();
                
                {
                    
                        switch(c)
                        {
                            case 1:
                                System.out.println("Enter the new Name");
                                newData=keyin.next();
                                String kp=keyin.next();
                                newData+=" "+kp;
                                String sql1="UPDATE `HODS` SET `Name`='"+newData+"' WHERE `NAME`='"+Name+"';";
                                stmt.executeUpdate(sql1); 
                               
                                System.out.println("Name changed to : "+newData);
                                break;
                            case 2:
                                System.out.println("Enter the coorect Date of Birth");
                                newData=keyin.next();
                                String sql2="UPDATE `HODS` SET `DOB`='"+newData+"' WHERE `DOB`='"+DOB+"';";
                                stmt.executeUpdate(sql2); 
                                System.out.println("Date of Birth has been corrected");
                                break;
                            case 3:
                                System.out.println("Enter the correct Branch");
                                newData=keyin.next();
                                String sql3="UPDATE `HODS` SET `Branch`='"+newData+"' WHERE `DOB`='"+DOB+"';";
                                stmt.executeUpdate(sql3); 
                              
                                System.out.println("Branch has been changed to : "+newData);
                                break;
                            case 4:
                                break X;
                            default:
                                System.out.println("Error\n Try again ");
                       }
                    }
                   
                }
            
                
            }
             else 
                    {
                        System.out.println("The entered Date of Birth is incorrect");
                            
                    }
        }
        catch(Exception e)
        {
            
        }
    }
            
}
