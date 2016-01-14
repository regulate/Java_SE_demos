package org.baddev.learning.demos.se_basics.se_basic_comp.workingwithcalendar32;

import java.util.GregorianCalendar;

public class PersonDemo {

    public static void main(String[] args) {
        Person p = new Person();
        p.setGender(Person.Gender.WOMAN);
        p.setName("Helena");
        //month arg value starts from 0, e.g. 0=January
        p.setBirthDate(new GregorianCalendar(1992, 10, 11));

        System.out.println(p);
    }

}
