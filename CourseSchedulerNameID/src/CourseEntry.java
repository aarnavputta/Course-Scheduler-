/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aarnavputta
 */
public class CourseEntry {
    private String courseCode;
    private String courseDescription;
    
    public CourseEntry( String courseCode, String courseDescription) {
        this.courseCode = courseCode;
        this.courseDescription = courseDescription;
    } 
    public String getCourseCode()
    {
        return this.courseCode;
    }
    public String getCourseDescription()
    {
        return this.courseDescription;
    }
    
}
