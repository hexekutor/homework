package main;

public class Student {
    private String name;
    private String faculty;
    private Integer course;

    public String getName() {
        return name;
    }

    public String getFaculty() {
        return faculty;
    }

    public Integer getCourse() {
        return course;
    }

    public Student(String name, String faculty, Integer course) {
        this.name = name;
        this.faculty = faculty;
        this.course = course;
    }



}
