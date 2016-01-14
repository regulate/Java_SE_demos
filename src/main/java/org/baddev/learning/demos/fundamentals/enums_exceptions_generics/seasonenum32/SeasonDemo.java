package org.baddev.learning.demos.fundamentals.enums_exceptions_generics.seasonenum32;

public class SeasonDemo {

    public static void main(String[] args) {
        Season season = Season.WINTER;
        System.out.println(season);
        System.out.println(season.getPrevSeason());
        System.out.println(season.getNextSeason());
    }

}
