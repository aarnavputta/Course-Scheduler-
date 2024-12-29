/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aarnavputta
 */
public class ClassEntry {
    private String semester;
    private String coursecode;
    private Integer seats;

    public ClassEntry(String semester, String coursecode, Integer seats)
    {
        this.semester = semester;
        this.coursecode = coursecode;
        this.seats = seats;
    }

    public String getSemester() 
    {
        return this.semester;
    }
    public String getCoursecode() 
    {
        return this.coursecode;
    }
    public Integer getSeats() 
    {
        return this.seats;
    }
    }
