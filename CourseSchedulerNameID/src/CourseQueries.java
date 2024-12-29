//app6000@psu.edu
//ORIGINAL WORK DONE BY AARNAV PUTTA 
//CMPSC 221 FINAL Project part 1 
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author aarnavputta
 */
public class CourseQueries {
    private static Connection connection;
    
    private static PreparedStatement addCourse;
    private static PreparedStatement getCourseList;
    private static ResultSet resultSet;


    public static void addCourse(CourseEntry course)
    {
        connection = DBConnection.getConnection();
        try
        {
            addCourse = connection.prepareStatement("insert into app.course ( courseCode, description ) values (?, ?)");

            addCourse.setString(1, course.getCourseCode());
            addCourse.setString(2, course.getCourseDescription());
            addCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }

    public static ArrayList<String> getAllCourseCodes(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<String> course = new ArrayList<String>();
        try
        {
            getCourseList = connection.prepareStatement("select coursecode from app.course order by coursecode");
            resultSet = getCourseList.executeQuery();
            
            while(resultSet.next())
            {
                course.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return course;
    }
    
//    //method added in order for the course comboboxes since i was unable to view the list of the courses with getallcoursecodes methods 
//    //stated in template of final prject part 1 : There may be other methods needed in your classes. This list is not all inclusive.
//     public static ArrayList<String> getCourseLists()
//    {
//        connection = DBConnection.getConnection();
//        ArrayList<String> course = new ArrayList<String>();
//        try
//        {
//            getCourseList = connection.prepareStatement("select coursecode from app.course order by coursecode");
//            resultSet = getCourseList.executeQuery();
//            
//            while(resultSet.next())
//            {
//                course.add(resultSet.getString(1));
//            }
//        }
//        catch(SQLException sqlException)
//        {
//            sqlException.printStackTrace();
//        }
//        return course;
//        
//    }

}
   

