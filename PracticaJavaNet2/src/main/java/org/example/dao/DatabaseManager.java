package org.example.dao;

import org.example.model.Mensaje;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/chat_db";
    private static final String DB_USER = "root"; // Cambia por tu usuario
    private static final String DB_PASSWORD = "Creativo52"; // Cambia por tu contraseña

    private Connection connection;

    public DatabaseManager() {
        inicializarBaseDatos();
    }

    private void inicializarBaseDatos() {
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Crear conexión
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Crear tabla si no existe
            String createTableSQL = """
                CREATE TABLE IF NOT EXISTS mensajes (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    remitente VARCHAR(100) NOT NULL,
                    contenido TEXT NOT NULL,
                    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
                """;

            try (Statement stmt = connection.createStatement()) {
                stmt.execute(createTableSQL);
                System.out.println("✓ Base de datos MySQL inicializada correctamente");
                System.out.println("✓ Conectado a: " + DB_URL);
            }

        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver MySQL no encontrado");
            System.err.println("Asegúrate de tener el conector MySQL en el pom.xml");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error al conectar con MySQL");
            System.err.println("Verifica que:");
            System.err.println("  1. MySQL esté ejecutándose");
            System.err.println("  2. La base de datos 'chat_db' existe");
            System.err.println("  3. Usuario y contraseña son correctos");
            e.printStackTrace();
        }
    }

    public boolean guardarMensaje(Mensaje mensaje) {
        String insertSQL = "INSERT INTO mensajes (remitente, contenido, fecha) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setString(1, mensaje.getRemitente());
            pstmt.setString(2, mensaje.getContenido());
            pstmt.setTimestamp(3, Timestamp.valueOf(mensaje.getFecha()));

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al guardar mensaje: " + e.getMessage());
            return false;
        }
    }

    public List<Mensaje> obtenerHistorialMensajes() {
        List<Mensaje> mensajes = new ArrayList<>();
        String selectSQL = "SELECT id, remitente, contenido, fecha FROM mensajes ORDER BY fecha ASC";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String remitente = rs.getString("remitente");
                String contenido = rs.getString("contenido");
                LocalDateTime fecha = rs.getTimestamp("fecha").toLocalDateTime();

                Mensaje mensaje = new Mensaje(id, remitente, contenido, fecha);
                mensajes.add(mensaje);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener historial: " + e.getMessage());
        }

        return mensajes;
    }

    public List<Mensaje> obtenerMensajesPorRemitente(String remitente) {
        List<Mensaje> mensajes = new ArrayList<>();
        String selectSQL = "SELECT id, remitente, contenido, fecha FROM mensajes WHERE remitente = ? ORDER BY fecha ASC";

        try (PreparedStatement pstmt = connection.prepareStatement(selectSQL)) {
            pstmt.setString(1, remitente);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String contenido = rs.getString("contenido");
                    LocalDateTime fecha = rs.getTimestamp("fecha").toLocalDateTime();

                    Mensaje mensaje = new Mensaje(id, remitente, contenido, fecha);
                    mensajes.add(mensaje);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener mensajes por remitente: " + e.getMessage());
        }

        return mensajes;
    }

    public void cerrarConexion() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("✓ Conexión a MySQL cerrada");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    public int contarMensajes() {
        String countSQL = "SELECT COUNT(*) as total FROM mensajes";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(countSQL)) {

            if (rs.next()) {
                return rs.getInt("total");
            }

        } catch (SQLException e) {
            System.err.println("Error al contar mensajes: " + e.getMessage());
        }

        return 0;
    }

    public boolean verificarConexion() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}