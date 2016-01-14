package org.baddev.learning.demos.practical_tasks.subwaysimulation;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.*;

/**
 * Created by Potapchuk Ilya on 24.12.2015.
 */
public class SubwaySimulationXStreamMarshaller {

    private XStream xStream;

    public SubwaySimulationXStreamMarshaller() {
        setupXStream();
    }

    private void setupXStream() {
        xStream = new XStream(new StaxDriver());

        xStream.aliasType("trains", SubwaySimulation.class);
        xStream.addImplicitCollection(SubwaySimulation.class, "trains", "train", Train.class);
        xStream.omitField(SubwaySimulation.class, "simulationDuration");
        xStream.omitField(SubwaySimulation.class, "schedulePath");

        xStream.aliasType("train", Train.class);
        xStream.aliasAttribute(Train.class, "startTime", "start-time");
        xStream.aliasAttribute(Train.class, "number", "number");

        xStream.aliasType("line", SubwayLine.class);
        xStream.aliasAttribute(SubwayLine.class, "name", "name");
        xStream.addImplicitCollection(SubwayLine.class, "arcs", SubwayArc.class);

        xStream.aliasType("arc", SubwayArc.class);
        xStream.aliasField("start-station", SubwayArc.class, "start");
        xStream.aliasField("end-station", SubwayArc.class, "end");
        xStream.aliasAttribute(SubwayArc.class, "time", "time");

        xStream.aliasType("station", SubwayStation.class);
        xStream.aliasAttribute(SubwayStation.class, "name", "name");
        xStream.aliasAttribute(SubwayStation.class, "departPassCount", "per-hour-depart");
    }

    public void marshal(String path, SubwaySimulation simulation) {
        try (Writer writer = new FileWriter(path)) {
            xStream.marshal(simulation, new PrettyPrintWriter(writer));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SubwaySimulation unmarshal(String path) {
        SubwaySimulation simulation = null;
        try (Reader reader = new FileReader(path)) {
            simulation = (SubwaySimulation) xStream.fromXML(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return simulation;
    }

}
