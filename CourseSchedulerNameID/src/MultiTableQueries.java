//app6000@psu.edu
//ORIGINAL WORK DONE BY AARNAV PUTTA 
//CMPSC 221 FINAL Project part 1 
//PSU ID APP6000
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//@author aarnavputta
//done in office hours 

public class MultiTableQueries {
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement addScheduleEntry;
    private static PreparedStatement getScheduleByStudent;
    private static PreparedStatement getScheduleByStudentCount;
    private static ResultSet resultSet;

    
    public static ArrayList<String> getAllClassDescriptions(String semester) {
        ArrayList<String> classDescriptions = new ArrayList<>();
        String query = "SELECT app.class.courseCode, description, seats " +
                       "FROM app.class, app.course " +
                       "WHERE semester = ? AND app.class.courseCode = app.course.courseCode " +
                       "ORDER BY app.class.courseCode";

        try {
            Connection connection = DBConnection.getConnection();
        
             PreparedStatement preparedStatement = connection.prepareStatement(query) ;

            preparedStatement.setString(1, semester);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String courseCode = resultSet.getString("courseCode");
                String description = resultSet.getString("description");
                int seats = resultSet.getInt("seats");

                classDescriptions.add(courseCode + "\t" + description + "\t" + seats);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return classDescriptions;
    }
    
    public static ArrayList<StudentEntry> getScheduledStudentsByClass(String semester, String courseCode) {
    connection = DBConnection.getConnection();
    ArrayList<StudentEntry> retList = new ArrayList<StudentEntry>();
    try {
       //this is the multitabke query to retrieve the list of students scheduled in a course however returns it as an instance of the Studententry class
        getScheduleByStudent = connection.prepareStatement(
            "SELECT s.studentid, st.firstName, st.lastName " + 
            "FROM app.schedule s " +
            "INNER JOIN app.student st ON s.studentid = st.studentid " +
            "WHERE s.semester = ? AND s.courseCode = ? AND s.status = 's'"
        );
        getScheduleByStudent.setString(1, semester);
        getScheduleByStudent.setString(2, courseCode);
        resultSet = getScheduleByStudent.executeQuery();

        while(resultSet.next()) {
            // Creating StudentEntry instead of ScheduleEntry
            retList.add(new StudentEntry(
                resultSet.getString("studentid"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName")
            ));
        }
    } catch(SQLException sqlException) {
        sqlException.printStackTrace();
    }
    return retList;
}

    
    public static ArrayList<StudentEntry> getWaitlistedStudentsByClass(String semester, String courseCode) {
    connection = DBConnection.getConnection();
    ArrayList<StudentEntry> retList = new ArrayList<StudentEntry>();
    try {
        // Updated query to join schedule and student tables
        getScheduleByStudent = connection.prepareStatement(
            "SELECT s.studentid, st.firstName, st.lastName " +
            "FROM app.schedule s " +
            "INNER JOIN app.student st ON s.studentid = st.studentid " +
            "WHERE s.semester = ? AND s.courseCode = ? AND s.status = 'w'"
        );
        getScheduleByStudent.setString(1, semester);
        getScheduleByStudent.setString(2, courseCode);
        resultSet = getScheduleByStudent.executeQuery();

        while(resultSet.next()) {
            // Creating StudentEntry instead of ScheduleEntry
            retList.add(new StudentEntry(
                resultSet.getString("studentid"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName")
            ));
        }
    } catch(SQLException sqlException) {
        sqlException.printStackTrace();
    }
    return retList;
}


}
