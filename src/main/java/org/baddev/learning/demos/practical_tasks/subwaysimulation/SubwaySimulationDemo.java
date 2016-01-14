package org.baddev.learning.demos.practical_tasks.subwaysimulation;

import java.util.Arrays;

/**
 * Created by Potapchuk Ilya on 19.12.2015.
 */
public class SubwaySimulationDemo {

    public static void main(String[] args) {
        SubwayStation a = new SubwayStation("A", 100);
        SubwayStation b = new SubwayStation("B", 80);
        SubwayStation c = new SubwayStation("C", 50);
        SubwayStation d = new SubwayStation("D", 90);

        SubwayLine line = new SubwayLine("ABCD", Arrays.asList(
                new SubwayArc(a, b, 4000),
                new SubwayArc(b, c, 5000),
                new SubwayArc(c, d, 6000)
        ));

        Train tr1 = new Train(1, 2000 ,line);
        Train tr2 = new Train(2, 5000 ,line);

        SubwaySimulation sim = new SubwaySimulation();
        sim.setTrains(Arrays.asList(tr1,tr2));
        sim.saveToXml();
        sim.setFromXml();
        sim.simulate();
    }

}
