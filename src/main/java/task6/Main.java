package task6;

import java.util.*;

public class Main {
    private static void printStudent(List<Student> studentList){
        studentList.stream()
                .sorted(Comparator.comparing(Student::getName))
                .forEach(student
                        -> System.out.println(student.getName() + " : " + student.getFaculty()));
    }

    private static void printFaculties(List<Student> studentList){
        Map<String, Integer> facultyMap = new HashMap<>();

        studentList.forEach((student) -> {
            if (facultyMap.containsKey(student.getFaculty()))
                facultyMap.put(student.getFaculty(),facultyMap.get(student.getFaculty()) + 1);
            else
                facultyMap.put(student.getFaculty(), 1);
        });
        System.out.print("Top 3:");

        facultyMap.entrySet().stream()
                .sorted((e1, e2) -> e1.getValue() > e2.getValue() ? e1.getValue() : e2.getValue())
                .limit(3)
                .forEach(faculty -> System.out.print(" " + faculty.getKey()));
    }

    public static void main(String[] args) {
        List<Student> studentList= new ArrayList<>();

        Student st1 = new Student("a","bb");
        Student st2 = new Student("c","bb");
        Student st3 = new Student("b","aa");
        Student st4 = new Student("aa","bb");

        studentList.add(st1);
        studentList.add(st2);
        studentList.add(st3);
        studentList.add(st4);

        printStudent(studentList);
        printFaculties(studentList);


    }
}
