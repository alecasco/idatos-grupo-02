package domain;

public class DireccionInfoCasas {
    private String calle;
    private String numeroPuerta;
    private String apartamento;
    private double piso;

    public DireccionInfoCasas(String calle, String numeroPuerta, String apartamento, double piso) {
        this.calle = calle;
        this.numeroPuerta = numeroPuerta;
        this.apartamento = apartamento;
        this.piso = piso;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumeroPuerta() {
        return numeroPuerta;
    }

    public void setNumeroPuerta(String numeroPuerta) {
        this.numeroPuerta = numeroPuerta;
    }

    public String getApartamento() {
        return apartamento;
    }

    public void setApartamento(String apartamento) {
        this.apartamento = apartamento;
    }

    public double getPiso() {
        return piso;
    }

    public void setPiso(double piso) {
        this.piso = piso;
    }
}
