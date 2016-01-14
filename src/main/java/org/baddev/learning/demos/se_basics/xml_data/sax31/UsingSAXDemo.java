package org.baddev.learning.demos.se_basics.xml_data.sax31;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Created by Potapchuk Ilya on 08.12.2015.
 */
public class UsingSAXDemo {

    public static final String ST_GROUP_XML = "data/st_group.xml";
    public static final String SAX_REPORT = "data/sax_report-st_group.txt";

    private static class StudentsGroupHandler extends DefaultHandler {

        private PrintWriter writer;

        public StudentsGroupHandler(OutputStream out) {
            this.writer = new PrintWriter(out, true);
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            writer.printf("Entering: %s%n", qName);
            if (attributes.getLength() > 0) {
                writer.printf("Attributes: ");
                for (int i = 0; i < attributes.getLength(); i++) {
                    writer.printf("Name: %s, value: %s%n", attributes.getQName(i), attributes.getValue(i));
                }
            } else {
                //for proper formatting
                writer.println();
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            writer.printf("Leaving: %s%n%n", qName);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String data = new String(ch).substring(start, length + start).trim();
            if (data.length() > 0) {
                writer.printf("Characters found: %s%n", data);
            }
        }

    }

    public static void read(String xmlPath, OutputStream to) {
        SAXParser sax = null;
        try {
            sax = SAXParserFactory.newInstance().newSAXParser();
            sax.parse(xmlPath, new StudentsGroupHandler(to));
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //reading to console
        read(ST_GROUP_XML, System.out);
        //reading to file
        try  (FileOutputStream fos = new FileOutputStream(SAX_REPORT)){
            read(ST_GROUP_XML, fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
