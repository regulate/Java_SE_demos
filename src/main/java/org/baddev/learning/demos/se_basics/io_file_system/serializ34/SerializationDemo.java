package org.baddev.learning.demos.se_basics.io_file_system.serializ34;

import java.io.*;

/**
 * Created by Potapchuk Ilya on 06.12.2015.
 */
public final class SerializationDemo {

    public static final String GROUP_PATH = "data/group.dat";

    public static <T extends Serializable> void writeObject(final T obj, final String path) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
            out.writeObject(obj);
            System.out.printf("Object has been written: %s%n", obj.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //it's ok
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T readObject(final String path, Class<T> type) {
        T obj = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
            obj = (T) in.readObject();
            System.out.printf("Object has been read: %s%n", obj.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static StudentsGroup getFakeGroup() {
        return new StudentsGroup(new Student[]{
                new Student("Ivan Petrov", 19),
                new Student("Vladislav Ivanov", 20),
                new Student("Oleg Maksimov", 18),
                new Student("Alex Mamaev", 21)
        });
    }

    public static void main(String[] args) {
        StudentsGroup sg = getFakeGroup();
        writeObject(sg, GROUP_PATH);
        StudentsGroup readSG = readObject(GROUP_PATH, StudentsGroup.class);
        System.out.printf("Objects are equal: %s%n", sg.equals(readSG));
    }

}
