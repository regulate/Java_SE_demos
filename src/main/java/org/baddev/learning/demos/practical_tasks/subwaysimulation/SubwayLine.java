package org.baddev.learning.demos.practical_tasks.subwaysimulation;

import java.util.*;

/**
 * Created by Potapchuk Ilya on 19.12.2015.
 */
public class SubwayLine {
    private String name = "";
    private List<SubwayArc> arcs = new ArrayList<>();

    public SubwayLine() {
    }

    public SubwayLine(String name, List<SubwayArc> arcs) {
        this.name = name;
        setArcs(arcs);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubwayArc> getArcs() {
        return arcs;
    }

    public void setArcs(List<SubwayArc> arcs) {
        Set<SubwayArc> uniqueArcs = new LinkedHashSet<>(arcs);
        this.arcs = new ArrayList<>(uniqueArcs);
    }

    public List<SubwayStation> getRemainingStations(SubwayStation from) {
        List<SubwayStation> remained = new ArrayList<>();
        for (int i = 0; i < arcs.size(); i++) {
            if (arcs.get(i).getStart().equals(from)) {
                for (int j = i; j < arcs.size(); j++) {
                    remained.add(arcs.get(j).getEnd());
                }
                break;
            }
        }
        return remained;
    }

    public List<SubwayStation> getStations(SubwayStation... except) {
        List<SubwayStation> exceptions = Arrays.asList(except);
        Set<SubwayStation> stations = new HashSet<>();
        arcs.stream().forEach(a -> {
            if (!exceptions.contains(a.getStart())) {
                stations.add(a.getStart());
            }
            if (!exceptions.contains(a.getEnd())) {
                stations.add(a.getEnd());
            }
        });
        return new ArrayList<>(stations);
    }

    public void doPassengersGeneration() {
        for (SubwayStation st : getStations()) {
            st.generatePassengers(getStations(st));
        }
    }

    public SubwayLine getReverseSubwayLine() {
        List<SubwayArc> reverse = new ArrayList<>();
        for (int i = arcs.size() - 1; i >= 0; i--) {
            reverse.add(new SubwayArc(
                    arcs.get(i).getEnd(),
                    arcs.get(i).getStart(),
                    arcs.get(i).getTime()));
        }
        return new SubwayLine(name, reverse);
    }

    public SubwayStation getFirstStation() {
        return arcs.get(0).getStart();
    }

    public SubwayStation getLastStation() {
        return arcs.get(arcs.size() - 1).getEnd();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubwayLine that = (SubwayLine) o;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return arcs != null ? arcs.equals(that.arcs) : that.arcs == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (arcs != null ? arcs.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SubwayLine{" +
                "name='" + name + '\'' +
                ", arcs=" + arcs +
                '}';
    }
    
}
