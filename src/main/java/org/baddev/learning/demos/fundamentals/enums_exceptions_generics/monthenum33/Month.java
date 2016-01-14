package org.baddev.learning.demos.fundamentals.enums_exceptions_generics.monthenum33;

import org.baddev.learning.demos.fundamentals.enums_exceptions_generics.seasonenum32.Season;

public enum Month {
    JANUARY(1, "January", 31), FEBRUARY(2, "February", 28), MARCH(3, "March", 31), APRIL(4, "April", 30),
    MAY(5, "May", 31), JUNE(6, "June", 30), JULY(7, "July", 31), AUGUST(8, "August", 31),
    SEPTEMBER(9, "September", 30), OCTOBER(10, "October", 31), NOVEMBER(11, "October", 30),
    DECEMBER(12, "December", 31);

    private Month(int seqNum, String name, int daysCount) {
        this.seqNum = seqNum;
        this.name = name;
        this.daysCount = daysCount;
        determineSeason();
    }

    private static final String[] rusNames = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август",
            "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};

    private int seqNum;
    private int daysCount;
    private String name;
    private Season season;

    public int getYearSequenceNumber() {
        return seqNum;
    }

    public String getName() {
        return name;
    }

    public String getRussianName() {
        return rusNames[seqNum - 1];
    }

    public int getDaysCount() {
        return daysCount;
    }

    public Season getSeason() {
        return season;
    }

    public Month getNextMonth() {
        return values()[(ordinal() + 1) % values().length];
    }

    public Month getPrevMonth() {
        if (this.equals(values()[0])) {
            return values()[values().length - 1];
        } else if (this.equals(values()[values().length - 1])) {
            return values()[0];
        }
        return values()[ordinal() - 1];
    }

    public static void printAllMonths() {
        for (Month month : values()) {
            System.out.println(month);
        }
    }

    public void printInRussian() {
        System.out.println(getRussianName());
    }

    @Override
    public String toString() {
        return "Month{" +
                "seqNum=" + seqNum +
                ", daysCount=" + daysCount +
                ", name='" + name + '\'' +
                ", season=" + season +
                '}';
    }

    private void determineSeason() {
        if (seqNum == 12 || seqNum < 3) {
            this.season = Season.WINTER;
        } else if (seqNum > 2 && seqNum < 6) {
            this.season = Season.SPRING;
        } else if (seqNum > 5 && seqNum < 9) {
            this.season = Season.SUMMER;
        } else {
            this.season = Season.AUTUMN;
        }
    }

}
