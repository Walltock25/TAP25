package dao;
import modelo.Estudiante;
import java.sql.*;
import java.util.*;
import java.sql.*;
public class EstudianteDAOImpl implements EstudianteDAO {
    private Connection conn;
    public EstudianteDAOImpl(Connection conn) {
        this.conn = conn;
    }
    @Override
    public void insertar(Estudiante e) {
        String sql = "INSERT INTO estudiantes (nombre, correo) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, e.getNombre());
            stmt.setString(2, e.getCorreo());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public Estudiante obtenerPorId(int id) {
        String sql = "SELECT * FROM estudiantes WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("correo")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Estudiante> listar() {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("correo")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
    @Override
    public void actualizar(Estudiante e) {
        String sql = "UPDATE estudiantes SET nombre=?, correo=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, e.getNombre());
            stmt.setString(2, e.getCorreo());
            stmt.setInt(3, e.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM estudiantes WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

