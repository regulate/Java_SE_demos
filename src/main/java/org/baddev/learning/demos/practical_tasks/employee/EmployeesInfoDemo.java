package org.baddev.learning.demos.practical_tasks.employee;

import java.util.Arrays;

/**
 * Created by Potapchuk Ilya on 18.12.2015.
 */
public class EmployeesInfoDemo {

    public static void main(String[] args) {
        Employee e1 = new Employee();
        e1.getEmails().add("test1@test.test");
        e1.getEmails().add("second@test.test");
        e1.setFirstName("John");
        e1.setLastName("Smith");
        e1.setProgrammingLangs(Arrays.asList(ProgrammingLang.JAVA, ProgrammingLang.C));

        Employee e2 = new Employee();
        e2.getEmails().add("test2@test.test");
        e2.setFirstName("Kyle");
        e2.setLastName("Walker");
        e2.setProgrammingLangs(Arrays.asList(ProgrammingLang.CPP, ProgrammingLang.JAVA));

        Employee e3 = new Employee();
        e3.getEmails().add("test3@test.test");
        e3.setFirstName("Martin");
        e3.setLastName("Lingard");
        e3.setProgrammingLangs(Arrays.asList(ProgrammingLang.CLOJURE, ProgrammingLang.SCALA));

        EmployeesInfo info = new EmployeesInfo();
        try {
            info.addEmployeeInfo(e1);
            info.addEmployeeInfo(e2);
            info.addEmployeeInfo(e3);
        } catch (EmployeesInfo.EmployeeExistException e) {
            e.printStackTrace();
        }

        EmployeesInfoService employeesInfoService = new EmployeesInfoService();
        employeesInfoService.writeToXml(info);
        EmployeesInfo infoRead = employeesInfoService.readFromXml();
        employeesInfoService.writeToTxt(infoRead);
        employeesInfoService.printByLastName(infoRead);
    }

}
