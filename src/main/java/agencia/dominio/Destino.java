package agencia.dominio;

public class Destino implements SolicitarInformacion {
    private int idDestino;
    private String ciudad;
    private String pais;

    // Constructor, getters y setters

    public Destino(int idDestino, String ciudad, String pais) {
        this.idDestino = idDestino;
        this.ciudad = ciudad;
        this.pais = pais;
    }

    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }


    @Override
    public String toString() {
        return "Destino{" +
                "idDestino=" + idDestino +
                ", ciudad='" + ciudad + '\'' +
                ", pais='" + pais + '\'' +
                '}';
    }

    // Implementación de interfaz
    @Override
    public void solicitarInformacion() {
        System.out.println(this.toString());
    }
}


