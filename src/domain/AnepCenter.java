package domain;

import org.apache.poi.ss.usermodel.Row;

public class AnepCenter {
    private String nombre;
    private String deptoNumero;
    private String deptoNombre;
    private String localidad;
    private String barrio;
    private String calle;
    private String numeroPuerta;
    private String telefono;

    public String getNombre() {
        return nombre;
    }

    public String getDeptoNumero() {
        return deptoNumero;
    }

    public String getDeptoNombre() {
        return deptoNombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getBarrio() {
        return barrio;
    }

    public String getCalle() {
        return calle;
    }

    public String getNumeroPuerta() {
        return numeroPuerta;
    }

    public String getTelefono() {
        return telefono;
    }

    public AnepCenter(Row row) {
        this.nombre = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        this.deptoNumero = row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        this.deptoNombre = row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        this.localidad = row.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        this.barrio = row.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        this.calle = row.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        this.numeroPuerta = row.getCell(10, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        this.telefono = row.getCell(11, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
    }
}
