package agencia.dominio;

public class Cliente extends Persona {
    public Cliente(int idCliente, String nombre, String apellido, String telefono, String direccion, String correoElectronico) {
        super(idCliente, nombre, apellido, telefono, direccion, correoElectronico);

    }



    // no aplicado
    @Override
    public void hacerReserva(Destino destino, GestorBD gestorBD) {
        if (!harealizadoReserva()) {
            System.out.println("Realizando reserva del destino: " + destino);
            gestorBD.registrarReserva(destino,this);
            marcarReservaRealizada();

        } else {
            System.out.println("El cliente " + this.getNombre() + " ya ha realizado una reserva previamente.");
        }
    }
}
