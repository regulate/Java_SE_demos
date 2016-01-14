package org.baddev.learning.demos.practical_tasks.transportsystem;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TransportSystem<E extends Station, T extends Arc<E>> {

    private List<T> arcs = new ArrayList<>();

    public TransportSystem() {}

    public TransportSystem(final List<T> arcs) {
        this.arcs = arcs;
    }

    public List<T> getArcs() {
        return arcs;
    }

    public void setArcs(List<T> arcs) {
        this.arcs = arcs;
    }

    public List<E> getStations() {
        Set<E> stations = new LinkedHashSet<>();
        for (Arc<E> arc : arcs) {
            stations.add(arc.getStart());
            stations.add(arc.getEnd());
        }
        return new ArrayList<>(stations);
    }
    //searches for nearest stations and it's neighbours
    public List<E> findNearStations(final E from) {
        Set<E> nearestStations = new LinkedHashSet<>();
        //down to finish
        findArcsWithStart(from).forEach(n -> {
            nearestStations.add(n.getEnd());
            //next up to start
            findArcsWithFinish(n.getEnd()).forEach(k -> {
                nearestStations.add(k.getStart());
            });
            //next down to finish
            findArcsWithStart(n.getEnd()).forEach(s -> {
                nearestStations.add(s.getEnd());
            });
        });
        //up to start
        findArcsWithFinish(from).forEach(n -> {
            nearestStations.add(n.getStart());
            //next down to finish
            findArcsWithStart(n.getStart()).forEach(s -> {
                nearestStations.add(s.getEnd());
            });
            //next up to start
            findArcsWithFinish(n.getStart()).forEach(s -> {
                nearestStations.add(s.getStart());
            });
        });
        //exclude value of *from* arg
        List<E> result = nearestStations.stream().filter(n -> !n.equals(from)).collect(Collectors.toList());
        return result;
    }

    private List<T> findArcsWithFinish(final E value) {
        return arcs.stream().filter(n -> n.getEnd().equals(value)).collect(Collectors.toList());
    }

    private List<T> findArcsWithStart(final E value) {
        return arcs.stream().filter(n -> n.getStart().equals(value)).collect(Collectors.toList());
    }

    public T findFastestArc() {
        return arcs.stream().max((a1, a2) -> Double.compare(a1.getApproxSpeed(), a2.getApproxSpeed())).get();
    }

    //result is sorted by number of incoming and outcoming ways
    public List<E> getStationsSorted() {
        List<E> stations = getStations();
        stations.sort((s1, s2) -> {
            Long countFirst = arcs.stream().filter(n -> {
                return n.getEnd().equals(s1) || n.getStart().equals(s1);
            }).count();
            Long countSecond = arcs.stream().filter(n -> {
                return n.getEnd().equals(s2) || n.getStart().equals(s2);
            }).count();
            return Long.compare(countFirst, countSecond);
        });
        return stations;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Arc arc : arcs) {
            sb.append(arc);
            sb.append("\n");
        }
        return sb.toString();
    }
}
