package org.baddev.learning.demos.practical_tasks.filessearcher;

import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by Potapchuk Ilya on 26.12.2015.
 */
public class FilesSearcherDemo {
    public static final String DIR = "src";

    public static void main(String[] args) {
        try {
            FilesSearcher fs = new FilesSearcher();
            fs.doFilesSearch("*.java", DIR);
            Map<String, BasicFileAttributes> filteredBySize = fs.filterFiles(false, 500);
            Map<String, BasicFileAttributes> filteredByText = fs.filterFiles("String name");
            System.out.println("Found files count: " + fs.getFiles().size()
                    + ", filtered by size count: " + filteredBySize.size()
                    + ", filtered by text count: " + filteredByText.size());
            System.out.println("Filtered by size: " + filteredBySize);
            System.out.println("Filtered by text: " + filteredByText);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
