package task6;


class Student {
    private String name;
    private String faculty;
    Student(String name, String faculty) {
        this.name = name;
        this.faculty = faculty;
    }

    String getFaculty() {
        return faculty;
    }

    String getName() {
        return name;
    }
}
