package domain;

public class DireccionInfoCasas {
    private String calle;
    private double numeroPuerta;
    private double apartamento;
    private double piso;

    public DireccionInfoCasas(String calle, double numeroPuerta, double apartamento, double piso) {
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

    public double getNumeroPuerta() {
        return numeroPuerta;
    }

    public void setNumeroPuerta(double numeroPuerta) {
        this.numeroPuerta = numeroPuerta;
    }

    public double getApartamento() {
        return apartamento;
    }

    public void setApartamento(double apartamento) {
        this.apartamento = apartamento;
    }

    public double getPiso() {
        return piso;
    }

    public void setPiso(double piso) {
        this.piso = piso;
    }
}
