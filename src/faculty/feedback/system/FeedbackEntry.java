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
public class FeedbackEntry extends FacultyFeedbackSystem {
    
   int marksUpdate[]=new int[5];
   Scanner keyin =new Scanner(System.in);
   static String Usn,dep,sem,sec;
   public void feedIn(String USN, String DEP,String Sem, String Sec)
   {
       
       String sql;
       Usn=USN;
       dep=DEP;
       sem=Sem;
       sec=Sec;
       
       float oldMarks[]=new float[5];
       float formAverage=0,temp;
       int columnAccess=6;
       String specificRow;
       try
       {
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);   
            Statement stmt = conn.createStatement();
            Statement stmt2=conn.createStatement();
            sql = "Select * FROM `"+DEP+"` WHERE `Class`='"+Sec+"' AND `SEM`='"+Sem+"';";
            
            ResultSet rs = stmt.executeQuery(sql);
            columnAccess*=formNumber;
            FeedBackEntryG obj1 = new FeedBackEntryG();
                obj1.setVisible(true);
           /* while(rs.next())
            {
                
                System.out.println(rs.getString(1));
                System.out.print("1.Punctuality : ");marksUpdate[0]=validMarks();
                System.out.print("2.Audibility : ");marksUpdate[1]=validMarks();
                System.out.print("3.Depth in Subject : ");marksUpdate[2]=validMarks();
                System.out.print("4.Control over Class : ");marksUpdate[3]=validMarks();
                System.out.print("5.Availability after class hours : ");marksUpdate[4]=validMarks();
                for(int i=0;i<5;i++)
                {
                    oldMarks[i]=rs.getFloat(i+6);
                }
                specificRow=" WHERE `Class`='"+Sec+"' AND `SEM`='"+Sem+"' AND `Name` ='"+rs.getString(1)+"';";
               // stmt.executeUpdate("Insert into `"+DEP+"`("+columnAccess+","+(columnAccess+1)+","+(columnAccess+2)+","+(columnAccess+3)+","+(columnAccess+4)+") VALUES ('"+marksUpdate[0]+"','"+marksUpdate[1]+"','"+marksUpdate[2]+"','"+marksUpdate[3]+"','"+marksUpdate[4]+"')"+specificRow); 
                System.out.println();
                for(int i=0;i<5;i++)
                {
                   temp=Average(marksUpdate[i],oldMarks[i]); 
                   formAverage+= temp;
                    stmt2.executeUpdate("Update `"+DEP+"` SET `F"+formNumber+"Q"+(1+i)+"`='"+temp+"'"+specificRow);
                   if(i==4)
                   {
                       formAverage/=5;
                       stmt2.executeUpdate("Update `"+DEP+"` SET `Form "+formNumber+"`='"+formAverage+"'"+specificRow);
                   }
                }
            }
            //Accept Remarks
            System.out.println("Thank You for your feedback");
            stmt.executeUpdate("Update `Student Records` SET `Form "+formNumber+"`='1' WHERE `USN`='"+USN+"';");*/
       }
       catch(Exception e)
       {
             e.printStackTrace(); 
       }
   }
    /*float Average(int a,float b)
   {
       float avg = (a+b)/2;
       return avg;   
   }
    int validMarks()
    {
        int x;
        while(true)
        {
            x=keyin.nextInt();
            if(x<=10 && x>=0)
             {
                return x;
             }
            else
            {
                System.out.println("Invalid Input ( 0 to 10)");  
            }
        }
        
    }*/
   
}

