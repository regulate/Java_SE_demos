package org.baddev.learning.demos.se_basics.io_file_system.serializ34;

import java.io.Serializable;

/**
 * Created by Potapchuk Ilya on 06.12.2015.
 */
public class Student implements Serializable {

    private static final long serialVersionUID = 5817205676571784708L;
    private String name;
    private int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (age != student.age) return false;
        return !(name != null ? !name.equals(student.name) : student.name != null);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
