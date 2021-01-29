package com.personal.converters;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JaxBTest {
    public static void main(String[] args) {

        try {

            File file = new File("src/main/resources/ResultsTest.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Results.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Results results = (Results) jaxbUnmarshaller.unmarshal(file);

            System.out.println(results.getVersion()+" "+results.getReportNode().size());


        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
