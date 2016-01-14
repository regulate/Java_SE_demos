package org.baddev.learning.demos.se_basics.io_file_system.dosfileattributes310;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributes;

/**
 * Created by Potapchuk Ilya on 13.12.2015.
 */
public class DosFileAttributesDemo {

    private static final String rootDirPath = "data";

    public static void printDosFileAttributes(String path) throws IOException {
        DosFileAttributes dfa = Files.readAttributes(Paths.get(path), DosFileAttributes.class);
        System.out.println(Paths.get(path).toRealPath()+" dos attributes:");
        System.out.printf("*system file: %s%n" +
                        "*read only: %s%n" +
                        "*directory: %s%n" +
                        "*hidden: %s%n" +
                        "*regular file: %s%n" +
                        "*created on: %s%n" +
                        "*last access on: %s%n" +
                        "*last modified on: %s%n" +
                        "*size: %s bytes%n",
                dfa.isSystem(), dfa.isReadOnly(), dfa.isDirectory(), dfa.isHidden(), dfa.isRegularFile(),
                dfa.creationTime(), dfa.lastAccessTime(), dfa.lastModifiedTime(), dfa.size());
    }

    public static void main(String[] args) {
        try {
            printDosFileAttributes(rootDirPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
