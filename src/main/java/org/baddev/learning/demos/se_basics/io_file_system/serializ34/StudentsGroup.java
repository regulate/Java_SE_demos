package org.baddev.learning.demos.se_basics.io_file_system.serializ34;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Potapchuk Ilya on 06.12.2015.
 */
public class StudentsGroup implements Serializable {

    private static final long serialVersionUID = -5260508422653215896L;
    private Student[] students;

    public StudentsGroup() {
    }

    public StudentsGroup(Student[] students) {
        this.students = students;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentsGroup that = (StudentsGroup) o;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(students, that.students);
    }

    @Override
    public String toString() {
        return "StudentsGroup{" +
                "students=" + Arrays.toString(students) +
                '}';
    }

}
