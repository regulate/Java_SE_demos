package org.baddev.learning.demos.practical_tasks.subwaysimulation;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Potapchuk Ilya on 19.12.2015.
 */
public class SubwayStation {
    private static final Logger log = Logger.getLogger(SubwayStation.class);
    private static final int maxGenTimeInterv = 5000;//ms
    private static final int maxPasGenCount = 15;//per genTimeInterval

    private String name = "";
    private int departPassCount = 100;//per hour
    private Map<String, Integer> passengers = new ConcurrentHashMap<>();

    public SubwayStation() {
    }

    public SubwayStation(String name, int departPassCount) {
        this.name = name;
        this.departPassCount = departPassCount;
    }

    public void generatePassengers(List<SubwayStation> targetStations) {
        Thread passengersGenerationDaemon = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Random rand = new Random();
                    initDepartureStations(targetStations);
                    while (true) {
                        for (SubwayStation st : targetStations) {
                            int generated = rand.nextInt(maxPasGenCount);
                            int total = passengers.get(st.getName()) + generated;
                            passengers.put(st.getName(), total);
                            log.info(Thread.currentThread().getName()
                                    + ": generated " + generated + " passenger(s) moving to " + st.getName()
                                    + ", station total: " + passengers);
                        }
                        Thread.sleep(rand.nextInt(maxGenTimeInterv));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Passengers-generation-daemon: " + name);
        passengersGenerationDaemon.setDaemon(true);
        passengersGenerationDaemon.start();
    }

    private void initDepartureStations(List<SubwayStation> targetStations) {
        for (SubwayStation st : targetStations)
            passengers.put(st.getName(), 0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDepartPassengersCount() {
        return departPassCount;
    }

    public void setDepartPassengersCount(int passengersCount) {
        this.departPassCount = passengersCount;
    }

    public Map<String, Integer> getPassengers() {
        return passengers;
    }

    public void setPassengers(Map<String, Integer> passengers) {
        this.passengers = passengers;
    }

    @Override
    public String toString() {
        return "SubwayStation{" +
                "name='" + name + '\'' +
                ", departPassCount=" + departPassCount +
                ", passengers=" + passengers +
                '}';
    }
}
