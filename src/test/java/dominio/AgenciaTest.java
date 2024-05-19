package dominio;

import agencia.dominio.Agencia;
import agencia.dominio.Destino;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class AgenciaTest {

    @Test
    void agregarDestino() {
        // Creamos una lista de destinos reservados vacía
        List<Destino> destinosreservados = new ArrayList<>();

        // Creamos una instancia de la agencia con la lista vacía
        Agencia agencia = new Agencia(destinosreservados);

        // Creamos un destino de prueba
        Destino destino = new Destino(1, "Madrid", "España", "La capital de España");

        // Agregamos el destino a la agencia
        agencia.agregarDestino(destino);

        // Verificamos si el destino se ha agregado correctamente
        assertEquals(1, agencia.getDestinosreservados().size());
        assertTrue(agencia.getDestinosreservados().contains(destino));
    }
}
