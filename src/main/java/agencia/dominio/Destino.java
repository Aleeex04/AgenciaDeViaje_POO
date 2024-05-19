package agencia.dominio;

public class Destino implements SolicitarInformacion {
    private int idDestino;
    private String ciudad;
    private String pais;
    private String descripcion;

    // Constructor, getters y setters

    public Destino(int idDestino, String ciudad, String pais, String descripcion) {
        this.idDestino = idDestino;
        this.ciudad = ciudad;
        this.pais = pais;
        this.descripcion= descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.pais = descripcion;
    }

    @Override
    public String toString() {
        return "Destino{" +
                "idDestino=" + idDestino +
                ", ciudad='" + ciudad + '\'' +
                ", pais='" + pais + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    // Implementación de interfaz
    @Override
    public void solicitarInformacion() {
        System.out.println(this.toString());
    }
}


