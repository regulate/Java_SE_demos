package org.baddev.learning.demos.practical_tasks.employee;

import java.io.Serializable;

/**
 * Created by Potapchuk Ilya on 18.12.2015.
 */
public enum ProgrammingLang implements Serializable{
    JAVA("Java"), C("C"), CPP("C++"), PYTHON("Python"), RUBY("Ruby"), SCALA("Scala"), CLOJURE("Clojure"), CSHARP("C#");

    private ProgrammingLang(String value){
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue();
    }
}
