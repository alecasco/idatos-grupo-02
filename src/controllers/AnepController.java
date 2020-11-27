package controllers;

import domain.AnepCenter;
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

public class AnepController {
    private List<AnepCenter> anepCenters = new ArrayList<>();

    public void LoadCentros() {
        try {
            //Get the Excel File
            FileInputStream file = new FileInputStream(
                    new File("./CENTROS_ANEP_2020.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get the Desired sheet
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Removes the row with titles
            Row firstRow = sheet.getRow(0);
            sheet.removeRow(firstRow);

            //Foreach row, gets the ANEP center and add it to the list
            for (Row row : sheet) {
                anepCenters.add(new AnepCenter(row));
            }

            RDFModelController rdfModelController = new RDFModelController();
            rdfModelController.LoadANEPRDF(anepCenters);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Busca los centros de ANEP ubicados en un cierto barrio de un cierto departamento/localidad.
    public List<AnepCenter> findCenters(String deptoNombre, String localidad, String barrio) {
        return anepCenters.stream()
                .filter(anepCenter -> anepCenter.getDeptoNombre().toLowerCase().equals(deptoNombre.toLowerCase()) &&
                        anepCenter.getLocalidad().toLowerCase().equals(localidad.toLowerCase()) &&
                        anepCenter.getBarrio().toLowerCase().equals(barrio.toLowerCase()))
                .collect(Collectors.toList());
    }
}
