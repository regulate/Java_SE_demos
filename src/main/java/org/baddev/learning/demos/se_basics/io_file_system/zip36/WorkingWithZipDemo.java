package org.baddev.learning.demos.se_basics.io_file_system.zip36;

import org.baddev.learning.demos.se_basics.io_file_system.serializ34.SerializationDemo;
import org.baddev.learning.demos.se_basics.io_file_system.serializ34.Student;
import org.baddev.learning.demos.se_basics.io_file_system.serializ34.StudentsGroup;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by Potapchuk Ilya on 06.12.2015.
 */
public class WorkingWithZipDemo {

    public static final String ZIP_PATH = "data/sts_group.zip";

    public static void writeToZip(String path, StudentsGroup sg) {
        try (ZipOutputStream zOut = new ZipOutputStream(new FileOutputStream(path))) {
            for (Student st : sg.getStudents()) {
                ZipEntry entry = new ZipEntry(st.getName() + ".dat");
                zOut.putNextEntry(entry);
                zOut.write(toBytes(st));
                zOut.closeEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static StudentsGroup readFromZip(String path) {
        StudentsGroup sg = new StudentsGroup();
        ArrayList<Student> sts = new ArrayList<>();
        try (ZipInputStream zIn = new ZipInputStream(new FileInputStream(path))) {
            ZipEntry entry = null;
            byte[] buff = new byte[1024];
            while ((entry = zIn.getNextEntry()) != null) {
                zIn.read(buff);
                zIn.closeEntry();
                sts.add((Student)fromBytes(buff));
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        sg.setStudents(sts.toArray(new Student[sts.size()]));
        return sg;
    }

    public static byte[] toBytes(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }

    public static Object fromBytes(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }

    public static void main(String[] args) {
        writeToZip(ZIP_PATH, SerializationDemo.getFakeGroup());
        StudentsGroup sg = readFromZip(ZIP_PATH);
        System.out.println(sg);
    }

}
