package org.baddev.learning.demos.practical_tasks.filessearcher;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;

/**
 * Created by Potapchuk Ilya on 26.12.2015.
 */
public class FilesSearcher {

    private volatile Map<Path, BasicFileAttributes> files = new ConcurrentSkipListMap<>();
    private Thread filesSearcher;

    public Map<Path, BasicFileAttributes> getFiles() {
        return files;
    }

    public void doFilesSearch(String glob, String path) {
        filesSearcher = new Thread(() -> {
            try {
                Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        try {
                            PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + glob);
                            if (matcher.matches(file.getFileName())) {
                                System.out.printf("%s: file found: %s%n",
                                        Thread.currentThread().getName(), file.getFileName());
                                files.put(file, attrs);
                            }
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "Files-searcher");
        filesSearcher.start();
    }

    public Map<String, BasicFileAttributes> filterFiles(boolean greaterThan, long size)
            throws ExecutionException, InterruptedException {
        FutureTask<Map<String, BasicFileAttributes>> sizeFilteringTask =
                new FutureTask<Map<String, BasicFileAttributes>>(
                        new Callable<Map<String, BasicFileAttributes>>() {
                            private Map<String, BasicFileAttributes> filterBySize(boolean greaterThan, long length) {
                                System.out.printf("%s: filtering by size%n", Thread.currentThread().getName());
                                return files.entrySet()
                                        .stream()
                                        .filter(e -> greaterThan ?
                                                e.getValue().size() > length : e.getValue().size() < length)
                                        .collect(Collectors.toMap(e -> e.getKey().getFileName().toString(),
                                                Map.Entry::getValue));
                            }

                            @Override
                            public Map<String, BasicFileAttributes> call() throws Exception {
                                Map<String, BasicFileAttributes> filesMap;
                                while (filesSearcher.isAlive()) {
                                    filesMap = filterBySize(greaterThan, size);
                                    Thread.sleep(2);
                                }
                                System.out.printf("%s: %s has finished. Preparing final filtering...%n",
                                        Thread.currentThread().getName(), filesSearcher.getName());
                                filesMap = filterBySize(greaterThan, size);
                                return filesMap;
                            }
                        });
        new Thread(sizeFilteringTask, "File-size-filter").start();
        return sizeFilteringTask.get();
    }

    public Map<String, BasicFileAttributes> filterFiles(String text)
            throws ExecutionException, InterruptedException {
        FutureTask<Map<String, BasicFileAttributes>> textFilteringTask =
                new FutureTask<Map<String, BasicFileAttributes>>(
                        new Callable<Map<String, BasicFileAttributes>>() {
                            private Map<String, BasicFileAttributes> filterByText(String text) {
                                System.out.printf("%s: filtering by text%n",
                                        Thread.currentThread().getName());
                                return files.entrySet()
                                        .stream()
                                        .filter(e -> {
                                            try (Scanner scanner = new Scanner(new FileReader(e.getKey().toFile()))) {
                                                while (scanner.hasNextLine()) {
                                                    if (scanner.nextLine().contains(text)) return true;
                                                }
                                            } catch (FileNotFoundException e1) {
                                                e1.printStackTrace();
                                            }
                                            return false;
                                        }).collect(Collectors.toMap(e -> e.getKey().getFileName().toString(),
                                                Map.Entry::getValue));
                            }

                            @Override
                            public Map<String, BasicFileAttributes> call() throws Exception {
                                Map<String, BasicFileAttributes> filesMap;
                                while (filesSearcher.isAlive()) {
                                    filesMap = filterByText(text);
                                    Thread.sleep(2);
                                }
                                System.out.printf("%s: %s has finished. Preparing final filtering...%n",
                                        Thread.currentThread().getName(), filesSearcher.getName());
                                filesMap = filterByText(text);
                                return filesMap;
                            }
                        }
                );
        new Thread(textFilteringTask, "File-text-filter").start();
        return textFilteringTask.get();
    }

}
