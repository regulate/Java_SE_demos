package org.baddev.learning.demos.se_basics.text_data.iterstringreverse33;

import java.text.BreakIterator;

public class UsingBreakIteratorDemo {

    public static String getBackwardFromIdx(String orig, final int fromIdx) {
        StringBuilder sb = new StringBuilder();
        BreakIterator br = BreakIterator.getCharacterInstance();
        br.setText(orig);
        int end = br.next(fromIdx);
        for (int start = br.previous();
             start != BreakIterator.DONE;
             end = start, start = br.previous()) {
            sb.append(orig.substring(start, end));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getBackwardFromIdx("What a hack is going on", 20));
    }

}
