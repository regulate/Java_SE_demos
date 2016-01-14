package org.baddev.learning.demos.practical_tasks.transportsystem;

public class Arc<T> {

    public static class EqualPointsException extends Exception {

        private static final String message = "Start and end points are equal";

        public EqualPointsException() {
            super(message);
        }

        public EqualPointsException(String message) {
            super(message);
        }
    }

    private long distance;
    private long overcTime;
    private T start;
    private T end;
    private double approxSpeed;

    public Arc() {
    }

    public Arc(T start, T end, long distance, long overcTime) throws EqualPointsException {
        setStart(start);
        setEnd(end);
        this.distance = distance;
        this.overcTime = overcTime;
        calcApprSpeed();
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public long getOvercomingTime() {
        return overcTime;
    }

    public void setOvercomingTime(long overcTime) {
        this.overcTime = overcTime;
    }

    public T getStart() {
        return start;
    }

    public void setStart(T start) throws EqualPointsException {
        if (start == null) {
            throw new NullPointerException("start argument is null");
        } else if (start.equals(this.end)) {
            throw new EqualPointsException();
        }
        this.start = start;
    }

    public T getEnd() {
        return end;
    }

    public void setEnd(T end) throws EqualPointsException {
        if (end == null) {
            throw new NullPointerException("end arg is null");
        } else if (end.equals(this.start)) {
            throw new EqualPointsException();
        }
        this.end = end;
    }

    public double getApproxSpeed() {
        return approxSpeed;
    }

    public void setApproxSpeed(double apprSpeed) {
        this.approxSpeed = apprSpeed;
    }

    private void calcApprSpeed() {
        double hours = (overcTime * 60d) / 3600d;
        setApproxSpeed(distance / hours);
    }

    @Override
    public String toString() {
        return "Arc from: " + start + ", to: " + end + ", overcome time: " + overcTime +
                " minutes, distance: " + distance + " km, " +
                "approximate speed: " + approxSpeed + " km/h";
    }
}
