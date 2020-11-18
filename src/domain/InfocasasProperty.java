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
    private double cantBanios;
    private double cantDormitorios;
    private Contacto contacto;
    private int garages;

    public InfocasasProperty(Row row) {
        this.precio = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
        this.direccion = new DireccionInfoCasas(row.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue(), row.getCell(10, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue(), row.getCell(11, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue(), row.getCell(12, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue());
        this.barrio = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        this.m2 = row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
        this.titulo = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        this.descripcion = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        this.tipo = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
        this.cantBanios = row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
        this.cantDormitorios = row.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
        this.contacto = new Contacto(row.getCell(13, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue(), String.valueOf(row.getCell(14, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue()));
        this.garages = (int) row.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();

    }


    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public DireccionInfoCasas getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionInfoCasas direccion) {
        this.direccion = direccion;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public double getM2() {
        return m2;
    }

    public void setM2(double m2) {
        this.m2 = m2;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getCantBanios() {
        return cantBanios;
    }

    public void setCantBanios(double cantBanios) {
        this.cantBanios = cantBanios;
    }

    public double getCantDormitorios() {
        return cantDormitorios;
    }

    public void setCantDormitorios(double cantDormitorios) {
        this.cantDormitorios = cantDormitorios;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public int getGarages() {
        return garages;
    }

    public void setGarages(int garages) {
        this.garages = garages;
    }
}
