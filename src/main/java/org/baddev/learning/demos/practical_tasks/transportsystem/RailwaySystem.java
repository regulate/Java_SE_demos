package org.baddev.learning.demos.practical_tasks.transportsystem;

import java.util.ArrayList;
import java.util.List;

public class RailwaySystem {

    private TransportSystem<Station, Arc<Station>> ts = new TransportSystem<>();

    public RailwaySystem() {
    }

    public RailwaySystem(final List<Arc<Station>> arcList) {
        this.ts.setArcs(arcList);
    }

    public TransportSystem<Station, Arc<Station>> getTransportSystem() {
        return ts;
    }

    public void setTransportSystem(final TransportSystem<Station, Arc<Station>> ts) {
        this.ts = ts;
    }

    public static List<Arc<Station>> getPreparedArcs() throws Arc.EqualPointsException {
        Station novoselovka = new RailwayStation("Novoselovka");
        Station kharkov = new RailwayStation("Kharkov");
        Station lyubotin = new RailwayStation("Lyubotin");
        Station merefa = new RailwayStation("Merefa");
        Station krasnoyarsk = new RailwayStation("Krasnoyarsk");
        Station lozova = new RailwayStation("Lozova");

        List<Arc<Station>> arcs = new ArrayList<>();

        Arc<Station> a1 = new Arc<>(kharkov, novoselovka, 2, 5);
        Arc<Station> a2 = new Arc<>(novoselovka, lyubotin, 22, 35);
        Arc<Station> a3 = new Arc<>(lyubotin, merefa, 18, 35);
        Arc<Station> a4 = new Arc<>(novoselovka, merefa, 23, 30);
        Arc<Station> a6 = new Arc<>(merefa, lozova, 123, 120);
        Arc<Station> a5 = new Arc<>(merefa, krasnoyarsk, 76, 120);

        arcs.add(a1);
        arcs.add(a2);
        arcs.add(a3);
        arcs.add(a4);
        arcs.add(a5);
        arcs.add(a6);

        return arcs;
    }

    public static void main(String[] args) {
        RailwaySystem rs = null;
        try {
            rs = new RailwaySystem(RailwaySystem.getPreparedArcs());
            System.out.println("Transport system arcs:");
            rs.getTransportSystem().getArcs().forEach(System.out::println);
            System.out.println("\nAvailable stations:");
            System.out.println(rs.getTransportSystem().getStations());
            System.out.println("\nSorted stations:");
            System.out.println(rs.getTransportSystem().getStationsSorted());
            for (Station st : rs.getTransportSystem().getStations()) {
                System.out.println("\nNeighbour stations to " + st.getName() + ":");
                System.out.println(rs.getTransportSystem().findNearStations(st));
            }
            System.out.println("\nFastest arc in the system: ");
            System.out.println(rs.getTransportSystem().findFastestArc());
        } catch (Arc.EqualPointsException e) {
            e.printStackTrace();
        }
    }

}
