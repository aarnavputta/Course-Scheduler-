//app6000@psu.edu
//ORIGINAL WORK DONE BY AARNAV PUTTA 
//CMPSC 221 FINAL Project part 1 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aarnavputta
 */
public class ClassQueries {
    private static Connection connection;
    
    private static PreparedStatement addClass;
    private static PreparedStatement getClassList;
    private static ResultSet resultSet;


    public static void addClass(ClassEntry classes)
    {
        connection = DBConnection.getConnection();
        try
        {
            addClass = connection.prepareStatement("insert into app.class (  semester,courseCode, seats) values (?, ?, ?)");
            addClass.setString(1, classes.getSemester());
            addClass.setString(2, classes.getCoursecode());
            addClass.setInt(3, classes.getSeats());

            addClass.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }

    public static ArrayList<String> getAllCourseCodes(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<String> courseCodes = new ArrayList<String>();
        try
        {
            getClassList = connection.prepareStatement("select coursecode from app.class where semester = ?");
            getClassList.setString(1, semester);
            resultSet = getClassList.executeQuery();
            
            while(resultSet.next())
            {
                courseCodes.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return courseCodes;
    }

    public static int getClassSeats(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        int count = 0;
        try
        {
            getClassList = connection.prepareStatement("select seats from app.class where semester = ? and coursecode = ?");
            getClassList.setString(1, semester);
            getClassList.setString(2, courseCode);
            resultSet = getClassList.executeQuery();
            
            while(resultSet.next())
                count = resultSet.getInt(1);
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return count;
    }
    
    public static void dropClass(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        try
        {
            getClassList = connection.prepareStatement("delete from app.class where semester = ? and coursecode = ?");
            getClassList.setString(1, semester);
            getClassList.setString(2, courseCode);
            getClassList.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}