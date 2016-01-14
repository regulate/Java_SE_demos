package org.baddev.learning.demos.fundamentals.enums_exceptions_generics.seasonenum32;

public enum Season {
    WINTER(1, "Winter"), SPRING(2, "Spring"), SUMMER(3, "Summer"), AUTUMN(4, "Autumn");

    private Season(int number, String name) {
        this.number = number;
        this.name = name;
    }

    private int number;
    private String name;

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public Season getNextSeason() {
        Season season = values()[(ordinal() + 1) % values().length];
        return season;
    }

    public Season getPrevSeason() {
        if (this.equals(values()[0])) {
            return values()[values().length - 1];
        } else if (this.equals(values()[values().length - 1])) {
            return values()[0];
        }
        return values()[ordinal() - 1];
    }

    @Override
    public String toString() {
        return "Season{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }
}
