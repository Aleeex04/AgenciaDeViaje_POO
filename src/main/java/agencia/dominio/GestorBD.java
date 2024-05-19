package agencia.dominio;
import java.sql.*;
import java.sql.Date;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;

public class GestorBD {
    private DataSource dataSource;

    public GestorBD(){
        this.dataSource=createDataSource();

    }
    public static DataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mariadb://localhost:3307/agenciaviaje");
        config.setUsername("root");
        config.setPassword("alex2004");

        return new HikariDataSource(config);
    }
    // CREATE
    public void agregarDestino(Destino destino) {
        String query = "INSERT INTO Destino (idDestino, ciudad, pais, descripcion) VALUES (?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1,destino.getIdDestino());
            st.setString(2, destino.getCiudad());
            st.setString(3, destino.getPais());
            st.setString(4, destino.getDescripcion());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // READ
    public Destino obtenerDestinoPorId(int id) {
        Destino destino = null;
        String query = "SELECT * FROM Destino WHERE idDestino = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                destino = new Destino(
                        rs.getInt("idDestino"),
                        rs.getString("ciudad"),
                        rs.getString("pais"),
                        rs.getString("descripcion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return destino;
    }

    public Vuelo obtenerVueloPorId(int id) {
        Vuelo vuelo = null;
        String query = "SELECT * FROM Vuelo WHERE idVuelo = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int idVuelo = rs.getInt("idVuelo");
                int numeroAsiento = rs.getInt("numeroAsiento");
                int idDestino = rs.getInt("idDestino");
                int precio = rs.getInt("precio");// Asumiendo que la tabla Vuelo tiene una columna idDestino

                // Obtener el destino correspondiente
                Destino destino = obtenerDestinoPorId(idDestino);

                // Crear el objeto Vuelo
                vuelo = new Vuelo(idVuelo, numeroAsiento, destino,precio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vuelo;
    }

    public Hotel obtenerHotelPorNombre(String nombreHotel) {
        // Implementación para obtener el hotel por nombre desde la base de datos
        String query = "SELECT * FROM Hotel WHERE nombre = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, nombreHotel);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int idHotel = rs.getInt("idHotel");
                String nombre = rs.getString("nombre");
                int estrellas = rs.getInt("estrellas");
                int numeroHabitacion = rs.getInt("Numero de Habitacion");
                double precioHotel = rs.getDouble("precioHotel");
                return new Hotel(idHotel, nombre, estrellas, numeroHabitacion, precioHotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int generarIdReserva() {
        // Implementación para generar un nuevo ID de reserva
        // Aquí se puede implementar una lógica que genere un nuevo ID único
        String query = "SELECT MAX(idReserva) AS maxId FROM Reserva";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("maxId") + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1; // Si no hay reservas, comenzamos en 1
    }

    // UPDATE

    public void actualizarDestino(int id, Destino destino) {
        String query = "UPDATE Destino SET idDestino= ?, ciudad = ?, pais = ?, descripcion = ? WHERE idDestino = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, destino.getIdDestino());
            st.setString(2, destino.getCiudad());
            st.setString(3, destino.getPais());
            st.setString(4, destino.getDescripcion());
            st.setInt(5, id); // Aquí configuramos el ID del destino
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double calcularPrecioReserva(Hotel hotel, Vuelo vuelo, Destino destino) {
        // Implementación para calcular el precio de la reserva
        // Aquí simplemente sumamos el precio del hotel y el precio del vuelo
        return hotel.getPrecioHotel() + vuelo.getPreciovuelo();
    }

    // DELETE

    public void eliminarDestinoporId(int id) {
        String query = "DELETE FROM Destino WHERE idDestino = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // METODO PARA REGISTRAR UNA VENTA

    public void registrarReserva(Reserva reserva, Hotel hotel, Vuelo vuelo, Destino destino, Cliente cliente) {
        String query = "INSERT INTO Reserva (idReserva, idCliente, idDestino, hotel, vuelo, fechaReserva, precioReserva) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, reserva.getIdReserva());
            st.setInt(2, cliente.getDNI());
            st.setInt(3, destino.getIdDestino());
            st.setString(4, hotel.getNombre());
            st.setInt(5, vuelo.getIdVuelo());
            st.setDate(6, new Date(System.currentTimeMillis()));// Suponiendo que la fecha se registra automáticamente como la fecha actual
            st.setDouble(7, reserva.getPrecioReserva());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CLIENTE

    public void agregarCliente(Cliente cliente){
        String query = "INSERT INTO clientes (idCliente, nombre, apellido, telefono, direccion, correoElectronico) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, cliente.getDNI());
            st.setString(2, cliente.getNombre());
            st.setString(3, cliente.getApellido());
            st.setString(4, cliente.getTelefono());
            st.setString(5, cliente.getDireccion());
            st.setString(6, cliente.getCorreoElectronico());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }








