package org.baddev.learning.demos.practical_tasks.employee;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by Potapchuk Ilya on 18.12.2015.
 */
public class EmployeesInfoService {
    private static final String xmlPath = "data/emp_inf.xml";
    private static final String txtPath = "data/emp_inf.txt";

    public final Marshaller<EmployeesInfo> marsh = new EmployeesInfoXStreamMarshaller();

    public void writeToXml(EmployeesInfo e) {
        marsh.marshal(xmlPath, e);
        System.out.printf("Written to xml: %n%s%n", e);
    }

    public EmployeesInfo readFromXml() {
        EmployeesInfo empInf = marsh.unmarshal(xmlPath);
        System.out.printf("Read from xml: %n%s%n", empInf);
        return empInf;
    }

    public void writeToTxt(EmployeesInfo e) {
        try (PrintWriter out = new PrintWriter(txtPath)) {
            out.print(e + "\n");
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public void printByLastName(EmployeesInfo e) {
        System.out.println("Sorted by last name:");
        e.getEmployeesInfo().entrySet()
                .stream()
                .sorted((e1, e2) -> e1.getValue().getLastName().compareTo(e2.getValue().getLastName()))
                .forEach(System.out::println);
    }
}
