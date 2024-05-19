package dominio;

import agencia.dominio.Destino;
import agencia.dominio.GestorBD;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GestorBDTest {

    @Test
    void agregarVehiculo() {

        GestorBD gestorBD = new GestorBD();
        Destino destino = new Destino(15, "Paris", "Francia", "La capital de Francia");


        gestorBD.agregarDestino(destino);


        Destino destinoAgregado = gestorBD.obtenerDestinoPorId(999);
        assertNotNull(destinoAgregado);
        assertEquals(destinoAgregado.getIdDestino(), destinoAgregado.getIdDestino());
        assertEquals(destinoAgregado.getCiudad(), destinoAgregado.getCiudad());
        assertEquals(destinoAgregado.getPais(), destinoAgregado.getPais());
        assertEquals(destinoAgregado.getDescripcion(), destinoAgregado.getDescripcion());

    }

    @Test
    void eliminarDestinoporId() {

        GestorBD gestorBD = new GestorBD();
        Destino destino = new Destino(15, "Paris", "Francia", "La capital de Francia");
        gestorBD.agregarDestino(destino);


        gestorBD.eliminarDestinoporId(10000);


        assertNull(gestorBD.obtenerDestinoPorId(10000));
    }
}

