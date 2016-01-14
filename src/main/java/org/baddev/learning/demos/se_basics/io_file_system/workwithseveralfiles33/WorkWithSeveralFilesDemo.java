package org.baddev.learning.demos.se_basics.io_file_system.workwithseveralfiles33;

/**
 * Created by Potapchuk Ilya on 05.12.2015.
 */
public class WorkWithSeveralFilesDemo {

    //paths to files
    public static final String INTEGERS_PATH = "data/integers.txt";
    public static final String ODDS_PATH = "data/odds.dat";
    public static final String EVENS_PATH = "data/evens.dat";
    public static final String REV_ORD_PATH = "data/rev_ordered.txt";

    public static void main(String[] args) {
        //reading from the text file and writing to a separate dat files
        IntegersIOUtils.writeIntsToDat(IntegersIOUtils.readIntsFromTxt(INTEGERS_PATH), ODDS_PATH, EVENS_PATH);
        //reading from dat files and writing to text file in reverse comparing order
        IntegersIOUtils.writeIntsOrderedToTxt(IntegersIOUtils.readIntsFromDat(ODDS_PATH), IntegersIOUtils.readIntsFromDat(EVENS_PATH), REV_ORD_PATH);
    }

}
