package org.baddev.learning.demos.se_basics.io_file_system.workwithseveralfiles33;

import java.io.*;
import java.util.*;

/**
 * Created by Potapchuk Ilya on 05.12.2015.
 */
public final class IntegersIOUtils {

    //no instances allowed
    private IntegersIOUtils() {
    }

    /**
     * Reads integers from text file
     *
     * @param txtPath path to file
     * @return integers read from file
     */
    public static Integer[] readIntsFromTxt(String txtPath) {
        List<Integer> ints = new ArrayList<>();
        try (Reader in = new BufferedReader(new FileReader(txtPath));
             Scanner sc = new Scanner(in)) {
            while (sc.hasNextInt()) {
                ints.add(sc.nextInt());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ints.toArray(new Integer[ints.size()]);
    }

    /**
     * Reads integers from binary file
     *
     * @param datPath path to file
     * @return integers read from file
     */
    public static Integer[] readIntsFromDat(String datPath) {
        List<Integer> ints = new ArrayList<>();
        try (DataInputStream in = new DataInputStream(new FileInputStream(datPath))) {
            while (true) {
                ints.add(in.readInt());
            }
        } catch (EOFException e) {
            //nothing to do
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ints.toArray(new Integer[ints.size()]);
    }

    /**
     * Gets integers, separates it to odds and evens and writes them to separate binary files
     *
     * @param ints      integers to write
     * @param oddsPath  path to binary file with odd integers
     * @param evensPath path to binary file with even integers
     */
    public static void writeIntsToDat(Integer[] ints, String oddsPath, String evensPath) {
        Integer[] odds = filter(true, ints);
        Integer[] evens = filter(false, ints);

        try (DataOutputStream oddsOut = new DataOutputStream(new FileOutputStream(oddsPath));
             DataOutputStream evensOut = new DataOutputStream(new FileOutputStream(evensPath))) {
            for (Integer odd : odds) {
                oddsOut.writeInt(odd);
                System.out.printf("*Written to %s: %d%n", oddsPath, odd);
            }
            for (Integer even : evens) {
                evensOut.writeInt(even);
                System.out.printf("*Written to %s: %d%n", evensPath, even);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets odd and even integers and writes it in reverse comparing order to text file
     *
     * @param odds    odd integers
     * @param evens   even integers
     * @param txtPath path to text file
     */
    public static void writeIntsOrderedToTxt(Integer[] odds, Integer[] evens, String txtPath) {
        Queue<Integer> ints = new PriorityQueue<>(Comparator.reverseOrder());
        Arrays.stream(odds).forEach(ints::offer);
        Arrays.stream(evens).forEach(ints::offer);
        try (PrintWriter rev = new PrintWriter(txtPath)) {
            while (ints.size() > 0) {
                int i = ints.poll();
                rev.print(i + " ");
                System.out.printf("*Written to %s: %d%n", txtPath, i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Filters odds or evens from integers
     *
     * @param isOdd true if need to filter only odd values, otherwise false
     * @param ints  integers
     * @return odd or even integers
     */
    private static Integer[] filter(boolean isOdd, Integer[] ints) {
        return Arrays.asList(ints)
                .stream()
                .filter(n -> isOdd ? n % 2 == 1 : n % 2 == 0)
                .toArray(Integer[]::new);
    }

}
