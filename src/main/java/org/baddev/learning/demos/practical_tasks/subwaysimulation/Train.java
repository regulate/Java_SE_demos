package org.baddev.learning.demos.practical_tasks.subwaysimulation;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Potapchuk Ilya on 19.12.2015.
 */
public class Train {
    private static final Logger log = Logger.getLogger(Train.class);
    public static final int MAX_CAPACITY = 200;
    public static final int STATION_BREAK = 1000;//ms

    private int number;
    private long startTime;
    private Map<String, Integer> passengers = new ConcurrentHashMap<>();
    private SubwayLine line;
    private SubwayStation currentStation;

    public Train() {
    }

    public Train(int number, long startTime, SubwayLine line) {
        this.number = number;
        this.line = line;
        this.startTime = startTime;
        currentStation = line.getFirstStation();
        initPassengers();
    }

    private void initPassengers() {
        for (SubwayStation st : line.getStations()) {
            passengers.put(st.getName(), 0);
        }
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public Map<String, Integer> getPassengers() {
        return passengers;
    }

    public void setPassengers(Map<String, Integer> passengers) {
        this.passengers = passengers;
    }

    public SubwayLine getLine() {
        return line;
    }

    public void setLine(SubwayLine line) {
        this.line = line;
    }

    public SubwayStation getCurrentStation() {
        return currentStation;
    }

    public void setCurrentStation(SubwayStation currentStation) {
        this.currentStation = currentStation;
    }

    public void moveOverLine() {
        //is an implicit daemon thread because instantiated by daemon
        Thread movingDaemon = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info(Thread.currentThread().getName() + ": starting from station " + currentStation);
                while (true) {
                    for (SubwayArc arc : line.getArcs()) {
                        try {
                            log.info(Thread.currentThread().getName() + ": getting " + STATION_BREAK + " ms break...");
                            Thread.sleep(STATION_BREAK);
                            landPassengers();
                            boardPassengers();
                            log.info(Thread.currentThread().getName() + ": " + arc.getTime() + " ms in transit...");
                            Thread.sleep(arc.getTime());
                            currentStation = arc.getEnd();
                            log.info(Thread.currentThread().getName() + ": arrived to " + currentStation);
                            if (isLastStation()) {
                                landPassengers();
                                log.info(Thread.currentThread().getName() + ": arrived to the last station,"
                                        + " reversing the driving direction...");
                                line = line.getReverseSubwayLine();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "Train-" + number);
        movingDaemon.start();
    }

    private boolean isFirstStation() {
        return currentStation.equals(line.getFirstStation());
    }

    private boolean isLastStation() {
        return currentStation.equals(line.getLastStation());
    }

    private void boardPassengers() {
        log.info(Thread.currentThread().getName() + ": starting to board passengers...");
        boolean canBoard = !isLastStation();
        if (canBoard) {
            for (SubwayStation st : line.getRemainingStations(currentStation)) {
                //take all available
                int boardingCount = currentStation.getPassengers().get(st.getName());
                int curPassCount = passengers.values().stream().mapToInt(i -> i).sum();
                //TODO: think about how to improve code below
                if(boardingCount+curPassCount<=MAX_CAPACITY) {
                    //subtract them from station
                    currentStation.getPassengers().put(st.getName(), 0);
                    log.info(Thread.currentThread().getName() + ": "
                            + "removed " + boardingCount + " passenger(s) from the station"
                            + " moving to station " + st.getName()
                            + ", station total: " + currentStation);
                    //board on the train
                    passengers.put(st.getName(), passengers.get(st.getName()) + boardingCount);
                    log.info(Thread.currentThread().getName() + ": "
                            + "boarded " + boardingCount + " passenger(s)"
                            + " on station " + currentStation.getName()
                            + " moving to station " + st.getName()
                            + ", train passengers total: " + passengers);
                } else {
                    int freePlacesCount =  MAX_CAPACITY - curPassCount;
                    int each = (int)freePlacesCount/currentStation.getPassengers().keySet().size();
                    int eachStationCur = boardingCount-each;
                    if(eachStationCur>=0) {
                        currentStation.getPassengers().put(st.getName(), eachStationCur);
                        passengers.put(st.getName(), each);
                    }
                }
            }
        } else {
            log.info(Thread.currentThread().getName() + ": it's not allowed to board passengers here: "
                    + currentStation);
        }
    }

    private void landPassengers() {
        log.info(Thread.currentThread().getName() + ": starting to land passengers...");
        boolean canLand = !isFirstStation();
        if (canLand) {
            int landCount = passengers.get(currentStation.getName());
            passengers.put(currentStation.getName(), 0);
            log.info(Thread.currentThread().getName() + ": "
                    + landCount
                    + " passengers have got off the train"
                    + " on the station " + currentStation.getName()
                    + ", train passengers total: " + passengers);
        } else {
            log.info(Thread.currentThread().getName() + ": it's not allowed to land passengers here: " + currentStation);
        }
    }

    @Override
    public String toString() {
        return "Train{" +
                "number=" + number +
                ", startTime=" + startTime +
                ", passengers=" + passengers +
                ", line=" + line +
                ", currentStation=" + currentStation +
                '}';
    }

}
