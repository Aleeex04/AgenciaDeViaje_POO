package agencia.dominio;

import java.util.Date;

public class Reserva implements SolicitarInformacion {
    private int idReserva;
    private Cliente cliente;
    private Destino destino;
    private Hotel hotel;
    private Vuelo vuelo;
    private Date fechaReserva;
    private double precioReserva;

    // Constructor, getters y setters

    public Reserva(int idReserva, Destino destino, Cliente cliente,Hotel hotel, Vuelo vuelo, Date fechaReserva, double precioReserva) {
        this.idReserva = idReserva;
        this.destino = destino;
        this.cliente = cliente;
        this.fechaReserva = fechaReserva;
        this.precioReserva = hotel.getPrecioHotel() + vuelo.getPreciovuelo();

    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }

    public Persona getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public double getPrecioReserva() {
        return precioReserva;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "ID: " + getIdReserva() +
                ", Destino: " + getDestino().getCiudad() +
                ", Cliente: " + getCliente().getNombre() + " " + getCliente().getApellido() +
                ", Fecha de Reserva: " + getFechaReserva() +
                ", Precio de la Reserva: €" + String.format("%.2f", getPrecioReserva()) +
                '}';
    }
    @Override
    public void solicitarInformacion(){
        System.out.println(this.toString());
    }


}

