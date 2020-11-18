package controllers;

import domain.InfocasasProperty;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InfoCasasController {
    private List<InfocasasProperty> infocasasProperties = new ArrayList<>();

    public void loadInfocasasProperties() {
        try {
            //Get the Excel File
            FileInputStream file = new FileInputStream(
                    new File("./PropiedadesInfocasas.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get the Desired sheet
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Removes the row with titles
            Row firstRow = sheet.getRow(0);
            sheet.removeRow(firstRow);

            //Foreach row, gets the infocasas property and add it to the list
            for (Row row : sheet) {
                infocasasProperties.add(new InfocasasProperty(row));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<InfocasasProperty> findProperties(String barrio) {
        return infocasasProperties.stream()
                .filter(infocasasProperty -> infocasasProperty.getBarrio().toLowerCase().equals(barrio.toLowerCase()))
                .collect(Collectors.toList());
    }

}
