package org.baddev.learning.demos.practical_tasks.employee;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Potapchuk Ilya on 18.12.2015.
 */
public class EmployeesInfo implements Serializable {

    public static class EmployeeExistException extends Exception {

        private static final String dftMsg = "Such employee already exists";

        public EmployeeExistException() {
            super(dftMsg);
        }

        public EmployeeExistException(String employeeInfo) {
            super(dftMsg + ": " + employeeInfo);
        }
    }

    private Map<Long, Employee> employeesInfo = new HashMap<>();
    private static long stCounter = 0;

    public EmployeesInfo() {
    }

    public Map<Long, Employee> getEmployeesInfo() {
        return employeesInfo;
    }

    public void setEmployeesInfo(Map<Long, Employee> employeesInfo) {
        this.employeesInfo = employeesInfo;
    }

    public void addEmployeeInfo(Employee e) throws EmployeeExistException {
        stCounter++;
        for(Employee emp : employeesInfo.values()){
            for(String email : e.getEmails()){
                if(emp.getEmails().contains(email)){
                    throw new EmployeeExistException(emp.toString());
                }
            }
        }
        e.setId(stCounter);
        employeesInfo.put(e.getId(), e);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        employeesInfo.entrySet().forEach(e -> sb.append(e + "\n"));
        return sb.toString();
    }
}
