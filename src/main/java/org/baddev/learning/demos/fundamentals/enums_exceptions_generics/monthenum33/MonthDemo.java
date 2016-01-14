package org.baddev.learning.demos.fundamentals.enums_exceptions_generics.monthenum33;

public class MonthDemo {

    public static void main(String[] args) {
        Month month = Month.JANUARY;
        //January
        System.out.println(month);
        month.printInRussian();
        //February
        System.out.println(month.getNextMonth());
        month.getNextMonth().printInRussian();
        //December
        System.out.println(month.getPrevMonth());
        month.getPrevMonth().printInRussian();

        Month.printAllMonths();
    }

}
