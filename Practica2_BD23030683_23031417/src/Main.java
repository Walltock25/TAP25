import dao.EstudianteDAO;
import dao.EstudianteDAOImpl;
import modelo.Estudiante;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/escuela";
        String user = "root";
        String pass = "Creativo52";

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            EstudianteDAO dao = new EstudianteDAOImpl(conn);

            // Insertar
            //dao.insertar(new Estudiante(0, "Oskar", "oscar@mail.com"));


            // Listar
            for (Estudiante e : dao.listar()) {
                System.out.println(e.getId() + " - " + e.getNombre());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

