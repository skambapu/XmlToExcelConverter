package com.personal.converters;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class JaxBTest {
    public static void main(String[] args) {

        try {

            File file = new File("src/main/resources/ResultsTest.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Results.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Results results = (Results) jaxbUnmarshaller.unmarshal(file);

            System.out.println("Xml Details Fetched - Version = " + results.getVersion()+ "; Records Found = " +results.getReportNode().size());

            //Create blank workbook
            XSSFWorkbook workbook = new XSSFWorkbook();

            //Create a blank sheet
            XSSFSheet spreadsheet = workbook.createSheet( " Results Info ");

            //Create row object
            XSSFRow row;

            int rowid = 0;

            row = spreadsheet.createRow(rowid);
            Cell cell0 = row.createCell(0);
            cell0.setCellValue(" Name ");

            Cell cell1 = row.createCell(1);
            cell1.setCellValue(" Description ");

            Cell cell2 = row.createCell(2);
            cell2.setCellValue(" Result ");

            for (ReportNode reportNode : results.getReportNode()) {
                row = spreadsheet.createRow(++rowid);
                Cell cell3 = row.createCell(0);
                cell3.setCellValue((String)reportNode.getData().getName());

                Cell cell4 = row.createCell(1);
                cell4.setCellValue((String)reportNode.getData().getDescription());

                Cell cell5 = row.createCell(2);
                cell5.setCellValue((String)reportNode.getData().getResult());
            }
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(
                    new File("src/main/resources/Writesheet.xlsx"));

            workbook.write(out);
            out.close();
            System.out.println("Writesheet.xlsx written successfully");
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }

    }
}
