package org.baddev.learning.demos.practical_tasks.subwaysimulation;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Potapchuk Ilya on 25.12.2015.
 */
public class SubwaySimulation {
    private static final Logger log = Logger.getLogger(SubwaySimulation.class);

    private String schedulePath = "data/subway_schedule.xml";
    private long simulationDuration = 60000;
    private List<Train> trains = new ArrayList<>();

    public SubwaySimulation() {
    }

    public SubwaySimulation(List<Train> trains) {
        this.trains = trains;
    }

    public SubwaySimulation(List<Train> trains, String schedulePath, long duration) {
        this.trains = trains;
        this.schedulePath = schedulePath;
        this.simulationDuration = duration;
    }

    public void setSchedulePath(String schedulePath) {
        this.schedulePath = schedulePath;
    }

    public String getSchedulePath() {
        return schedulePath;
    }

    public void setSimulationDuration(long simulationDuration) {
        this.simulationDuration = simulationDuration;
    }

    public long getSimulationDuration() {
        return simulationDuration;
    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }

    public void setFromXml() {
        SubwaySimulationXStreamMarshaller marsh = new SubwaySimulationXStreamMarshaller();
        this.trains = marsh.unmarshal(schedulePath).trains;
    }

    public void saveToXml() {
        SubwaySimulationXStreamMarshaller marsh = new SubwaySimulationXStreamMarshaller();
        marsh.marshal(schedulePath, this);
    }

    public List<Train> getTrains() {
        return trains;
    }

    private void generatePassengers() {
        trains.stream().map(Train::getLine).distinct().forEach(SubwayLine::doPassengersGeneration);
    }

    private void runTrains() {
        trains.forEach(t -> {
            Thread trainDaemon = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        log.debug(Thread.currentThread().getName() + ": starting train in " + t.getStartTime() + " ms");
                        Thread.sleep(t.getStartTime());
                        t.moveOverLine();
                        log.debug(Thread.currentThread().getName() + ": train " + t.getNumber() + " has started");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "Train-" + t.getNumber() + "-timer");
            trainDaemon.setDaemon(true);
            trainDaemon.start();
        });
    }

    public void simulate() {
        try {
            generatePassengers();
            runTrains();
            log.debug(Thread.currentThread().getName() + ": starting simulation...");
            Thread.sleep(simulationDuration);
            log.debug(Thread.currentThread().getName() + ": stopping simulation...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
