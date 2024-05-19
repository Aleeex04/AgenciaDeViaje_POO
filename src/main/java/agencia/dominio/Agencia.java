package agencia.dominio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Agencia {
    private  List<Destino> destinosreservados;


    // Constructor
    public Agencia(List<Destino> destinosreservados) {
        this.destinosreservados = destinosreservados;

    }

    public List<Destino> getDestinosreservados() {
        return destinosreservados;
    }

    public void agregarDestino(Destino destino){
          for (Destino v : destinosreservados) {
              if (v.getIdDestino() == destino.getIdDestino()) {
                throw new IllegalArgumentException("El destino con ID " + destino.getIdDestino() + " ya está en el inventario.");
              }
        }
        destinosreservados.add(destino);
    }
    public void eliminarDestino(Destino destino) {
        destinosreservados.remove(destino);
    }

    public List<Destino> buscardestinosporCiudad(String ciudad){
        List<Destino> nuevaLista = new ArrayList<>();
        for(Destino destino: destinosreservados){
            if(destino.getCiudad().equalsIgnoreCase(ciudad)){
                nuevaLista.add(destino);
            }
        }
        return nuevaLista;
    }


    public void mostrarDestinos(){
        System.out.println("---Mostrando destinos disponibles---");
        for(Destino destino : destinosreservados){
            System.out.println("Ciudad: " + destino.getCiudad() + " Pais: " + destino.getPais());
        }
    }
    // Método para guardar todo el inventario en un archivo
    public void guardarInventarioEnArchivo(String nombreArchivo){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Destino destino : destinosreservados) {
                writer.write(destino.getIdDestino() + "," + destino.getCiudad() + "," + destino.getPais()+ "\n");
            }
            System.out.println("Destino guardado correctamente en el archivo: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al guardar el destino en el archivo: " + e.getMessage());
        }
    }



    // Método para cargar el archivo y convertirlo en objetos, para posteriormente añadirlos al inventario
    public void cargarInventarioDesdeArchivo(String nombreArchivo){
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Dividir la línea en partes utilizando la coma
                String[] partes = linea.split(",");
                int idDestino = Integer.parseInt(partes[0].trim()); // Convertir la cadena del id a entero
                String ciudad = partes[1].trim(); // recoge la ciudad
                String pais = partes[2].trim(); // recoge el pais
                // Crear un nuevo objeto Vehiculo y agregarlo al inventario
                Destino destino = new Destino(idDestino, ciudad, pais);
                destinosreservados.add(destino);
            }
            System.out.println("Reservas han sido cargadas correctamente desde el archivo: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al cargar las reservas desde el archivo: " + e.getMessage());
        }
    }


}




