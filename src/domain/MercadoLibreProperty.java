package domain;

public class MercadoLibreProperty {
    private String titulo;
    private Integer precio;
    private String permalink;
    private String direccion;
    private String barrio;

    public MercadoLibreProperty(String title, Integer precio, String permalink, String direccion, String barrio) {
        this.titulo = title;
        this.precio = precio;
        this.permalink = permalink;
        this.direccion = direccion;
        this.barrio = barrio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public void printProperty() {
        System.out.println("Propiedad: "+ this.titulo +" / "+ this.precio +" / "+ this.barrio +" / "+ this.direccion +" / "+ this.permalink);
    }
}
