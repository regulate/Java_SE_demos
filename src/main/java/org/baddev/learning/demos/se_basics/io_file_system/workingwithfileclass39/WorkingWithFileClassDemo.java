package org.baddev.learning.demos.se_basics.io_file_system.workingwithfileclass39;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Potapchuk Ilya on 12.12.2015.
 */
public class WorkingWithFileClassDemo {

    private static final String rootPath = "data/";
    private static final String fileName = "test.txt";

    private static boolean createFile(String fileName) throws IOException {
        File file = new File(rootPath + fileName);
        return file.createNewFile();
    }

    private static boolean removeFile(String fileName) {
        File file = new File(rootPath + fileName);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    private static void listData(String rootPath) throws IOException {
        File root = new File(rootPath);
        if (root.exists() && root.isDirectory()) {
            System.out.println("*Found in " + root.getCanonicalPath() + ":");
            Arrays.stream(root.list()).forEach(System.out::println);
        } else {
            System.out.println("*Error: " + rootPath + " is not found");
        }
    }

    public static void main(String[] args) {
        try {
            if (!createFile(fileName)) {
                System.out.printf("*Error: can't create %s, it may exist.%n", fileName);
            }
            listData(rootPath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(!removeFile(fileName)){
                System.out.printf("*Error: can't remove %s, it may not exist.%n", fileName);
            }
        }
    }


}
