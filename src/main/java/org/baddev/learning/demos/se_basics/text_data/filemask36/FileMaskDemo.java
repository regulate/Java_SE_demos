package org.baddev.learning.demos.se_basics.text_data.filemask36;

import java.util.Arrays;
import java.util.List;

public class FileMaskDemo {

    public static void printAllMatches(List<String> fileNames, String pattern) {
        fileNames.stream().filter(fName -> fName.matches(pattern)).forEach(System.out::println);
    }

    public static void main(String[] args) {
        // File path starts from "images/", than is followed by "i" or "p", and than by one or many non-space
        // characters. File extension checking is case insensitive.
        String regexp = "(^(images/[ip])[^\\s]+(\\.(?i)(jpg|png|bmp))$)";
        String file1 = "images/image2131jasd.gif";
        String file2 = "images/pictureasdkla.jpg";
        String file3 = "photo_sfaja-fsaf.bmp";
        String file4 = "images/notaphoto.mp3";
        String file5 = "images/img_321231.PNG";
        List<String> files = Arrays.asList(file1, file2, file3, file4, file5);
        printAllMatches(files, regexp);
    }

}
