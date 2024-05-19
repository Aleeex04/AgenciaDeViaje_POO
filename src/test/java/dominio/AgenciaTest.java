package dominio;

import agencia.dominio.Agencia;
import agencia.dominio.Destino;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class AgenciaTest {

    @Test
    void agregarDestino() {

        List<Destino> destinosreservados = new ArrayList<>();
        Agencia agencia = new Agencia(destinosreservados);
        Destino vehiculo = new Destino(1, "Toyota", "Corolla", 2022, 25000.0, "Nuevo");


        agencia.agregarDestino(destinosreservados);


        assertEquals(1, agencia.getDestinosreservados().size());
        assertTrue(agencia.getDestinosreservados().contains(vehiculo));
    }
}
