package agencia.dominio;

public class Hotel implements SolicitarInformacion{
    private int idHotel;
    private String nombre;
    private int estrellas;
    private int numeroHabitacion;
    private double precio;

    public Hotel(int idHotel, String nombre, int estrellas, int numeroHabitacion, double precio) {
        this.idHotel = idHotel;
        this.nombre = nombre;
        this.estrellas = estrellas;
        this.numeroHabitacion = numeroHabitacion;
        this.precio = precio;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public double getPrecioHotel() {
        return precio;
    }

    public void setPrecioHotel(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Vuelo{" +
                "ID: " + getIdHotel() +
                ", Nombre del hotel: " + getNombre() +
                ", Estrellas: " + getEstrellas() +
                ", Numero de habitacion: " + getNumeroHabitacion() +
                ", Precio del hotel por noche: €" + String.format("%.2f", getPrecioHotel()) +
                '}';

    }
    @Override
    public void solicitarInformacion(){
        System.out.println(this.toString());
    }

}
