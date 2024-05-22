package agencia.dominio;

import java.util.Date;
import java.util.Scanner;

public class Cliente extends Persona {
    public Cliente(int idCliente, String nombre, String apellido, String telefono, String direccion, String correoElectronico) {
        super(idCliente, nombre, apellido, telefono, direccion, correoElectronico);

    }



    // no aplicado
    @Override
    public void hacerReserva(Destino destino, GestorBD gestorBD) {
        if (!harealizadoReserva()) {
            System.out.println("Realizando reserva a: " + destino);

            System.out.println("Ingresa el nombre del hotel:");
            Scanner scanner = new Scanner(System.in);
            String nombreHotel = scanner.nextLine();
            Hotel hotel = gestorBD.obtenerHotelPorNombre(nombreHotel);

            System.out.println("Ingresa el ID del vuelo:");
            int idVuelo = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea pendiente
            Vuelo vuelo = gestorBD.obtenerVueloPorId(idVuelo);

            if (hotel != null && vuelo != null) {
                int idReserva = gestorBD.generarIdReserva();
                Date fechaReserva = new Date(System.currentTimeMillis());
                double precioReserva = gestorBD.calcularPrecioReserva(hotel, vuelo, destino);

                Reserva reserva = new Reserva(idReserva, this, destino, hotel, vuelo, fechaReserva, precioReserva);
                gestorBD.registrarReserva(reserva, hotel, vuelo, destino, this);

                marcarReservaRealizada();
                System.out.println("Reserva registrada correctamente.");
            } else {
                System.out.println("No se pudo completar la reserva. Verifique los datos del hotel y vuelo.");
            }
        } else {
            System.out.println("El cliente " + this.getNombre() + " ya ha realizado una reserva previamente.");
        }
    }
}

