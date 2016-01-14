package org.baddev.learning.demos.se_basics.se_basic_comp.workingwithcalendar32;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Person {

    public enum Gender {
        MAN, WOMAN;
    }

    private String name;
    private Calendar birthDate;
    private Gender gender;

    public Person() {
        name = "unknown";
        birthDate = Calendar.getInstance();
        birthDate.set(1970, 1, 1);
        gender = Gender.MAN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        if (birthDate.after(Calendar.getInstance())) {
            throw new IllegalArgumentException("Birth date can't be in future");
        }
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int calcAge() {
        return Calendar.getInstance().get(Calendar.YEAR) - this.birthDate.get(Calendar.YEAR);
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return "Person{" +
                "name=" + name +
                ", birthDate=" + dateFormat.format(birthDate.getTime()) +
                ", gender=" + gender +
                ", age=" + calcAge() +
                '}';
    }
}
