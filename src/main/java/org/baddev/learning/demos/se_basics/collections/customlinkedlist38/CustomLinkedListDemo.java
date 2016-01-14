package org.baddev.learning.demos.se_basics.collections.customlinkedlist38;

public class CustomLinkedListDemo {

    public static void main(String[] args) {
        CustomLinkedList<String> strings = new CustomLinkedList<>();
        strings.addLast("Hello");
        System.out.println(strings.size());
        System.out.println(strings);
        strings.addFirst("World");
        System.out.println(strings.size());
        System.out.println(strings);
        strings.addLast("Test");
        System.out.println(strings.size());
        System.out.println(strings);
        strings.add(2, "Second");
        System.out.println(strings.size());
        System.out.println(strings);
        strings.removeFirst();
        System.out.println(strings.size());
        System.out.println(strings);
        strings.removeLast();
        System.out.println(strings.size());
        System.out.println(strings);
        strings.remove(1);
        System.out.println(strings.size());
        System.out.println(strings);
    }

}
