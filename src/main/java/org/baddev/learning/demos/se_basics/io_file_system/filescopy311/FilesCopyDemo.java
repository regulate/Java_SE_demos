package org.baddev.learning.demos.se_basics.io_file_system.filescopy311;

import java.io.*;

/**
 * Created by Potapchuk Ilya on 13.12.2015.
 */
public class FilesCopyDemo {

    private static final String initialDir = "data/copy-files-demo_initial/";
    private static final String copiesDir = "data/copy-files-demo_with-copies/";
    private static final String file1 = "file1.rtf";
    private static final String file2 = "file2.txt";
    private static final String file3 = "file3.dat";

    private static void copyFiles(String from, String to) {
        File fromDir = new File(from);
        if (fromDir.isDirectory()) {
            for (File f : fromDir.listFiles()) {
                if (f != null) {
                    try (InputStream is = new FileInputStream(f);
                         OutputStream os = new FileOutputStream(new File(to + f.getName()))) {
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = is.read(buf)) > 0) {
                            os.write(buf, 0, len);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.printf("Error: %s is not exist or not a dir.%n", from);
        }
    }

    private static void createFiles(String... fileNames) {
        for (String f : fileNames) {
            File file = new File(initialDir + f);
            try {
                if (!file.createNewFile()) {
                    System.out.printf("Error: can't create file %s in %s directory. It may already exist.%n", f, initialDir);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void createDir(String path) {
        File dir = new File(path);
        dir.mkdir();
    }

    private static void doCleanup() {
        File dir = new File(initialDir);
        if (dir.exists() && dir.isDirectory()) {
            for (File f : dir.listFiles()) {
                if (!f.delete()) {
                    System.out.printf("Error: unable to delete file %s in %s while cleanup.%n", f.getName(), initialDir);
                }
            }
            if (!dir.delete()) {
                System.out.printf("Error: can't delete dir %s, it may contain some content.%n", initialDir);
            }
        } else {
            System.out.println("Error: " + initialDir + " doesn't exist or is not a directory.");
        }
    }

    private static void initialize() {
        createDir(initialDir);
        createFiles(file1, file2, file3);
        createDir(copiesDir);
    }

    public static void main(String[] args) {
        initialize();
        copyFiles(initialDir, copiesDir);
        doCleanup();
    }

}
