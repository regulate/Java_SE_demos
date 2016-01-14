package org.baddev.learning.demos.se_basics.multithreading.workingwithfilesystem33;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by Potapchuk Ilya on 13.12.2015.
 */
public class FilesSizeCounter {

    private static final String srcDir = "src";
    private volatile Map<String, Long> files = new ConcurrentSkipListMap<>();
    private Thread counter, finder;
    private boolean canRead;

    //counts and shows total size of files
    private synchronized void countSize() throws InterruptedException {
        while (finder.isAlive()) {
            while (!canRead) {
                wait();
            }
            //count size of available files
            long sizeOfAvailable = files.values().stream().mapToLong(l -> l).sum();
            System.out.printf("%s: total size of files found: %d bytes%n", Thread.currentThread().getName(),
                    sizeOfAvailable);
            //can't count now, so will wait
            canRead = false;
            //notify that another files size calculation is done and it's not allowed to read now
            notifyAll();
        }
    }

    //finds filenames and their size from specified directory, then adds it to shared resource
    private synchronized void addAnotherFile(Path file) throws InterruptedException {
        while (canRead) {
            wait();
        }
        System.out.printf("%s: file found: %s %d bytes%n", Thread.currentThread().getName(),
                file.getFileName(), file.toFile().length());
        //add another file
        files.put(file.getFileName().toString(), file.toFile().length());
        //it's allowed to read now, so will wait
        canRead = true;
        //notify that another file is added and it's allowed to perform reading
        notifyAll();
    }

    //starts executing of application tasks in separate threads
    public void doWork() {
        finder = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    Files.walkFileTree(Paths.get(srcDir), new SimpleFileVisitor<Path>() {
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                            try {
                                addAnotherFile(file);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return FileVisitResult.CONTINUE;
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        counter = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    countSize();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        finder.setName("Finder");
        counter.setName("Counter");
        finder.start();
        counter.start();
    }

    public static void main(String[] args) {
        FilesSizeCounter fsc = new FilesSizeCounter();
        fsc.doWork();
    }

}

