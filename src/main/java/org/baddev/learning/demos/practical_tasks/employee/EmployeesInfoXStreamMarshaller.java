package org.baddev.learning.demos.practical_tasks.employee;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.*;
import java.util.Map;
import java.util.Set;

/**
 * Created by Potapchuk Ilya on 18.12.2015.
 */
public class EmployeesInfoXStreamMarshaller implements Marshaller<EmployeesInfo> {

    private XStream xStream;

    public EmployeesInfoXStreamMarshaller() {
        setupXStream();
    }

    private void setupXStream(){
        xStream = new XStream(new StaxDriver());
        xStream.alias("empInf", EmployeesInfo.class);
        xStream.alias("employeesInfo", Map.class);
        xStream.alias("employee", Employee.class);
        xStream.alias("emails", Set.class);
        xStream.alias("langs", Set.class);
        xStream.omitField(EmployeesInfo.class, "stCounter");
    }

    @Override
    public void marshal(String path, EmployeesInfo empInf) {
        try (Writer writer = new FileWriter(path)){
            xStream.marshal(empInf, new PrettyPrintWriter(writer));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public EmployeesInfo unmarshal(String path) {
        EmployeesInfo empInf = null;
        try (Reader reader = new FileReader(path)) {
            empInf = (EmployeesInfo) xStream.fromXML(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return empInf;
    }
}
