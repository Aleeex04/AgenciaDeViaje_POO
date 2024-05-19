package agencia.dominio;

public class Vuelo implements SolicitarInformacion {
    private int idVuelo;
    private int numeroDeAsiento;
    private Destino destino;
    private double precio;

    public Vuelo(int idVuelo, int numeroDeAsiento, Destino destino, double precio) {
        this.idVuelo = idVuelo;
        this.numeroDeAsiento = numeroDeAsiento;
        this.destino = destino;
        this.precio = precio;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public int getNumeroDeAsiento() {
        return numeroDeAsiento;
    }

    public void setNumeroDeAsiento(int numeroDeAsiento) {
        this.numeroDeAsiento = numeroDeAsiento;
    }

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }

    public double getPreciovuelo() {
        return precio;
    }


    @Override
    public String toString() {
        return "Vuelo{" +
                "ID: " + getIdVuelo() +
                ", Destino: " + getDestino().getCiudad() + " " + getDestino().getPais() +
                ", Numero de asiento: " + getNumeroDeAsiento() +
                ", Precio del vuelo: €" + String.format("%.2f", getPreciovuelo()) +
                '}';

    }
    @Override
    public void solicitarInformacion(){
        System.out.println(this.toString());
    }

}
