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
        String query = "INSERT INTO Destino (idDestino,ciudad, pais, hotel, precio) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1,destino.getIdDestino());
            st.setString(2, destino.getCiudad());
            st.setString(3, destino.getPais());
            st.setString(4, destino.getHotel());
            st.setDouble(5, destino.getPrecio());
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
                        rs.getString("hotel"),
                        rs.getDouble("precio")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return destino;
    }

    // UPDATE

    public void actualizarDestino(int id, Destino destino) {
        String query = "UPDATE Destino SET idDestino= ?, ciudad = ?, pais = ?, hotel = ?, precio = ? WHERE idVehiculo = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, destino.getIdDestino());
            st.setString(2, destino.getCiudad());
            st.setString(3, destino.getPais());
            st.setString(6, destino.getHotel());
            st.setDouble(5, destino.getPrecio());
            st.setInt(7, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // DELETE

    public void eliminarDestinoporId(int id) {
        String query = "DELETE FROM Destino WHERE idVehiculo = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // METODO PARA REGISTRAR UNA VENTA

    public void registrarReserva(Destino destino, Cliente cliente) {
        String query = "INSERT INTO Reserva (idDestino, idCliente, fechaReserva, precio) VALUES (?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, destino.getIdDestino());
            st.setInt(2, cliente.getDNI());
            st.setDate(3, new Date(System.currentTimeMillis()));// Suponiendo que la fecha se registra automáticamente como la fecha actual
            st.setDouble(4, destino.getPrecio()); // Obtenemos el precio del vehículo
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








