package org.baddev.learning.demos.practical_tasks.subwaysimulation;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAliasType;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Created by Potapchuk Ilya on 19.12.2015.
 */
@XStreamAliasType("arc")
public class SubwayArc {
    @XStreamAlias("start-station")
    private SubwayStation start;
    @XStreamAlias("end-station")
    private SubwayStation end;
    @XStreamAsAttribute
    private long time;

    public SubwayArc() {
    }

    public SubwayArc(SubwayStation start, SubwayStation end, long time) {
        this.start = start;
        this.end = end;
        this.time = time;
    }

    public SubwayStation getStart() {
        return start;
    }

    public void setStart(SubwayStation start) {
        this.start = start;
    }

    public SubwayStation getEnd() {
        return end;
    }

    public void setEnd(SubwayStation end) {
        this.end = end;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubwayArc subwayArc = (SubwayArc) o;

        if (time != subwayArc.time) return false;
        if (!start.equals(subwayArc.start)) return false;
        return end.equals(subwayArc.end);

    }

    @Override
    public int hashCode() {
        int result = start.hashCode();
        result = 31 * result + end.hashCode();
        result = 31 * result + (int) (time ^ (time >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "SubwayArc{" +
                "start=" + start +
                ", end=" + end +
                ", time=" + time +
                '}';
    }
}
