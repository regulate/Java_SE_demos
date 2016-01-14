package org.baddev.learning.demos.practical_tasks.employee;

/**
 * Created by Potapchuk Ilya on 18.12.2015.
 */
public interface Marshaller<T> {

    void marshal(String path, T obj);

    EmployeesInfo unmarshal(String path);

}
