package domain;

import java.util.List;

public class MercadoLibreProperty {
    private double precio;
    private String direccion;
    private String barrio;
    private String m2;
    private String titulo;
    private String tipo; //Apartamento o casa
    private String cantBanios;
    private String cantDormitorios;
    private Contacto contacto;

    public MercadoLibreProperty(double precio,
                                String direccion,
                                String barrio,
                                String m2,
                                String titulo,
                                String tipo,
                                String cantBanios,
                                String cantDormitorios,
                                Contacto contacto) {
        this.precio = precio;
        this.direccion = direccion;
        this.barrio = barrio;
        this.m2 = m2;
        this.titulo = titulo;
        this.tipo = tipo;
        this.cantBanios = cantBanios;
        this.cantDormitorios = cantDormitorios;
        this.contacto = contacto;
    }

    public double getPrecio() {
        return precio;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getBarrio() {
        return barrio;
    }

    public String getM2() {
        return m2;
    }

    public String getTitulo() {
        return titulo;
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
}
