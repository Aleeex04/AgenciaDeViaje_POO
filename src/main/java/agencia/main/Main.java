package agencia.main;
import agencia.dominio.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Creacion objetos prueba
        List<Destino> destinosdisponibles = new ArrayList<>();
        Agencia agencia = new Agencia(destinosdisponibles);
        GestorBD gestorBD = new GestorBD();
        Scanner scanner = new Scanner(System.in);
        String nombreArchivo = "destinos.txt"; // Writer
        String nombreArchivo2 = "destinosImport.txt"; // Reader


        int opcion;

        do {
            System.out.println("***** MENÚ *****");
            System.out.println("1. CRUD de Viajes (Gestor BD)");
            System.out.println("2. Métodos de la agencia");
            System.out.println("3. Mostrar información");
            System.out.println("0. Salir");
            System.out.print("Ingresa la opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    menuCRUDVehiculos(gestorBD, scanner);
                    break;
                case 2:
                    menumetodosAgencia(agencia, nombreArchivo, nombreArchivo2);
                    break;
                case 3:
                    menuMostrarInformacion(agencia);
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingresa una opción válida.");
                    break;
            }
        } while (opcion != 0);
    }

    public static void menuCRUDVehiculos(GestorBD gestorBD, Scanner scanner) {
        int opcion;
        Cliente nuevoCliente = null;
        do {
            System.out.println("\n***** CRUD DE VIAJES *****");
            System.out.println("1. Agregar destino");
            System.out.println("2. Obtener destino por ID");
            System.out.println("3. Actualizar destino");
            System.out.println("4. Eliminar destino por ID");
            System.out.println("5. Agregar cliente");
            System.out.println("6. Registrar reserva");
            System.out.println("0. Volver al menú principal");
            System.out.print("Ingresa una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    Destino nuevoDestino = crearDestino(scanner);
                    gestorBD.agregarDestino(nuevoDestino);
                    break;
                case 2:
                    System.out.println("Ingresa el ID del destino:");
                    int idReserva = scanner.nextInt();
                    Destino destinoBuscado = gestorBD.obtenerDestinoPorId(idReserva);
                    if (destinoBuscado != null) {
                        System.out.println("Destino encontrado: " + destinoBuscado);
                    } else {
                        System.out.println("No se encontró ningún destino con el ID proporcionado.");
                    }
                    break;
                case 3:
                    System.out.println("Ingresa el ID del destino que desea actualizar:");
                    int idActualizar = scanner.nextInt();
                    scanner.nextLine();
                    Destino destinoActualizar = crearDestino(scanner);
                    gestorBD.actualizarDestino(idActualizar, destinoActualizar);
                    break;
                case 4:
                    System.out.println("Ingresa el ID del destino que desea eliminar:");
                    int idEliminar = scanner.nextInt();
                    gestorBD.eliminarDestinoporId(idEliminar);
                    break;
                case 5:
                    nuevoCliente = crearCliente(scanner);
                    gestorBD.agregarCliente(nuevoCliente);
                    break;
                case 6:
                    System.out.println("Ingresa el ID del destino a reservar:");
                    int idDestinoReservado = scanner.nextInt();
                    scanner.nextLine(); // Consumir la nueva línea pendiente

                    Destino reservaDestino = gestorBD.obtenerDestinoPorId(idDestinoReservado);

                    if (reservaDestino != null && nuevoCliente != null) {
                        System.out.println("Ingresa el nombre del hotel:");
                        String nombreHotelReservado = scanner.nextLine();

                        // Obtener el hotel por su nombre
                        Hotel hotelReservado = gestorBD.obtenerHotelPorNombre(nombreHotelReservado);

                        // Obtener el vuelo
                        System.out.println("Ingresa el ID del vuelo:");
                        int idVuelo = scanner.nextInt();
                        scanner.nextLine(); // Consumir la nueva línea pendiente
                        Vuelo vueloReservado = gestorBD.obtenerVueloPorId(idVuelo);

                        if (hotelReservado != null && vueloReservado != null) {
                            // Crear una nueva instancia de Reserva
                            int idReserva1 = gestorBD.generarIdReserva();
                            Date fechaReserva = new Date(System.currentTimeMillis()); // Fecha actual
                            double precioReserva = gestorBD.calcularPrecioReserva(hotelReservado, vueloReservado, reservaDestino);

                            Reserva nuevaReserva = new Reserva(idReserva1, nuevoCliente, reservaDestino, hotelReservado, vueloReservado, fechaReserva, precioReserva);

                            // Registrar la reserva en la base de datos
                            gestorBD.registrarReserva(nuevaReserva, hotelReservado, vueloReservado, reservaDestino, nuevoCliente);
                            System.out.println("Reserva registrada correctamente.");
                        } else {
                            System.out.println("No se pudo registrar la reserva. Verifique el nombre del hotel y el ID del vuelo proporcionados.");
                        }
                    } else {
                        System.out.println("No se pudo registrar el destino. Verifique el ID del destino proporcionado.");
                    }
                    break;


                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingresa una opción válida.");
                    break;
            }
        } while (opcion != 0);
    }

    public static void menumetodosAgencia(Agencia agencia, String nombreArchivo, String nombreArchivo2) {
        Scanner scanner = new Scanner(System.in);
        GestorBD gestorBD = new GestorBD();

        int opcion;

        do {
            System.out.println("\n***** MÉTODOS DE LA AGENCIA *****");
            System.out.println("1. Agregar destino");
            System.out.println("2. Eliminar destino");
            System.out.println("3. Buscar destino por ciudad");
            System.out.println("4. Calcular precio del destino (hotel+vuelo)");
            System.out.println("5. Mostrar destinos disponibles");
            System.out.println("6. Guardar destinos en archivo");
            System.out.println("7. Cargar destinos desde archivo");
            System.out.println("0. Volver al menú principal");
            System.out.print("Ingresa una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Ingresa el ID del destino que deseas agregar:");
                    Destino nuevoDestino = crearDestino(scanner);
                    try {
                        agencia.agregarDestino(nuevoDestino);
                        System.out.println("Destino agregado correctamente.");
                    } catch (IllegalArgumentException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Ingresa el ID del destino que deseas eliminar:");
                    int idEliminar = scanner.nextInt();
                    Destino destinoEliminar = null;
                    for (Destino destino : agencia.getDestinosreservados()) {
                        if (destino.getIdDestino() == idEliminar) {
                            destinoEliminar = destino;
                            break;
                        }
                    }
                    if (destinoEliminar != null) {
                        try {
                            agencia.eliminarDestino(destinoEliminar);
                            System.out.println("Destino eliminado correctamente.");
                        } catch (IllegalArgumentException e) {
                            System.err.println("Error: " + e.getMessage());
                        }
                    } else {
                        System.out.println("No se encontró el destino con esa ID.");
                    }
                    break;
                case 3:
                    System.out.println("Ingresa la ciudad a buscar:");
                    String ciudadBuscar = scanner.next();
                    List<Destino> destinosporCiudad = agencia.buscardestinosporCiudad(ciudadBuscar);
                    if (!destinosporCiudad.isEmpty()) {
                        System.out.println("Destinos encontrados:");
                        for (Destino destino : destinosporCiudad) {
                            System.out.println(destino);
                        }
                    } else {
                        System.out.println("No se encontraron destinos con la ciudad especificada.");
                    }
                    break;
                case 4:
                    System.out.println("Ingrese el nombre del hotel:");
                    String nombreHotel = scanner.nextLine();
                    Hotel hotel = gestorBD.obtenerHotelPorNombre(nombreHotel);

                    if (hotel == null) {
                        System.out.println("No se encontró el hotel.");
                        return;
                    }

                    System.out.println("Ingrese el ID del vuelo:");
                    int idVuelo = scanner.nextInt();
                    Vuelo vuelo = gestorBD.obtenerVueloPorId(idVuelo);

                    if (vuelo == null) {
                        System.out.println("No se encontró el vuelo.");
                        return;
                    }

                    double precioReserva = gestorBD.calcularPrecioReserva(hotel, vuelo, vuelo.getDestino());
                    System.out.println("El precio total de la reserva es de €" + String.format("%.2f", precioReserva)+"para el destino "+vuelo.getDestino());
                    break;
                case 5:
                    agencia.mostrarDestinos();
                    break;
                case 6:
                    agencia.guardarInventarioEnArchivo(nombreArchivo);
                    break;
                case 7:
                    agencia.cargarInventarioDesdeArchivo(nombreArchivo2);
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, Ingresa una opción válida.");
                    break;
            }
        } while (opcion != 0);
    }

    public static void menuMostrarInformacion(Agencia agencia) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n***** MOSTRAR INFORMACIÓN *****");
            System.out.println("1. Mostrar información de un destino");
            System.out.println("0. Volver al menú principal");
            System.out.print("Ingresa una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("De que destino de la lista quieres sacar información? Introduce la ID: ");
                    int id = scanner.nextInt();
                    boolean encontrado = false;
                    for(Destino destino : agencia.getDestinosreservados()){
                        if (id == destino.getIdDestino()){
                            System.out.println(destino.toString());
                            encontrado = true;
                            break;
                        }
                    }
                    if(!encontrado){
                        System.out.println("El destino con la ID especificada no se encuentra en la agencia.");
                    }
                    break;

                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingresa una opción válida.");
                    break;
            }
        } while (opcion != 0);
    }

    // Método para crear un vehículo ingresando datos por consola
    public static Destino crearDestino(Scanner scanner) {
        System.out.println("Ingrese los detalles del destino:");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ciudad: ");
        String ciudad = scanner.nextLine();
        System.out.print("Pais: ");
        String pais = scanner.nextLine();
        System.out.print("Descripcion: ");
        String descripcion = scanner.nextLine();

        scanner.nextLine();

        return new Destino(id, ciudad, pais, descripcion);
    }

    // Método para crear un cliente ingresando datos por consola
    public static Cliente crearCliente(Scanner scanner) {
        System.out.println("Ingresa los detalles del cliente:");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Correo electrónico: ");
        String correo = scanner.nextLine();

        return new Cliente(id, nombre, apellido, dni, direccion, correo);
    }

}





