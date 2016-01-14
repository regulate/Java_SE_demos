package org.baddev.learning.demos.practical_tasks.employee;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Potapchuk Ilya on 18.12.2015.
 */
public class Employee implements Serializable {

    private long id;
    private String firstName;
    private String lastName;
    private Set<String> emails = new HashSet<>();
    private Set<ProgrammingLang> langs = new HashSet<>();

    public Employee() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<String> getEmails() {
        return emails;
    }

    public void setEmails(Set<String> email) {
        this.emails = email;
    }

    public Set<ProgrammingLang> getProgrammingLangs() {
        return langs;
    }

    public void setProgrammingLangs(List<ProgrammingLang> langs) {
        this.langs = new HashSet<>(langs);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (id != employee.id) return false;
        if (!firstName.equals(employee.firstName)) return false;
        if (!lastName.equals(employee.lastName)) return false;
        if (!emails.equals(employee.emails)) return false;
        return langs.equals(employee.langs);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + emails.hashCode();
        result = 31 * result + langs.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + emails + '\'' +
                ", langs=" + langs +
                '}';
    }
}
