package domain;

import org.apache.poi.ss.usermodel.Row;

public class InfocasasProperty {
    private double precio;
    private DireccionInfoCasas direccion;
    private String barrio;
    private double m2;
    private String titulo;
    private String descripcion;
    private String tipo; //Apartamento o casa
    private String cantBanios;
    private String cantDormitorios;
    private Contacto contacto;
    private String link;

    public InfocasasProperty(Row row) {
        this.precio = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
        this.direccion = new DireccionInfoCasas(row.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue(), row.getCell(10, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue(), row.getCell(11, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue(), row.getCell(12, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue());
        this.barrio = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        this.m2 = row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
        this.titulo = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        this.descripcion = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        this.tipo = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        this.cantBanios = row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        this.cantDormitorios = row.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        this.contacto = new Contacto(row.getCell(13, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue(), String.valueOf(row.getCell(14, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue()));
        this.link = row.getCell(15, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
    }


    public double getPrecio() {
        return precio;
    }

    public DireccionInfoCasas getDireccion() {
        return direccion;
    }

    public String getBarrio() {
        return barrio;
    }

    public double getM2() {
        return m2;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCantBanios() {
        return cantBanios;
    }

    public String getCantDormitorios() {
        return cantDormitorios;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public String getLink() {
        return link;
    }
}
