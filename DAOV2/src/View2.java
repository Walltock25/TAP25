import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
public class View2 {
    public static void main(String[] args) {
        try {
            // Obtener conexión a la base de datos
            Connection connection = DatabaseConnection.getConnection();
            // Crear instancia del DAO de Usuario
            UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
            // Crear un nuevo usuario
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre("Juan Pérez");
            nuevoUsuario.setEmail("sjuan@example.com");
            nuevoUsuario.setPassword("contraseña123");
            nuevoUsuario.setFechaRegistro(LocalDate.now());
            // Guardar el usuario en la base de datos
            if (usuarioDAO.save(nuevoUsuario)) {
                System.out.println("Usuario guardado con éxito. ID: " + nuevoUsuario.getId());
            } else {
                System.out.println("Error al guardar el usuario");
            }
            // Buscar usuario por ID
            Usuario usuarioEncontrado = usuarioDAO.findById(nuevoUsuario.getId());
            if (usuarioEncontrado != null) {
                System.out.println("Usuario encontrado: " + usuarioEncontrado);
                // Actualizar usuario
                usuarioEncontrado.setNombre("Juan Manuel Pérez");
                if (usuarioDAO.update(usuarioEncontrado)) {
                    System.out.println("Usuario actualizado con éxito");
                } else {
                    System.out.println("Error al actualizar el usuario");
                }
            }
            // Obtener todos los usuarios
            List<Usuario> usuarios = usuarioDAO.findAll();
            System.out.println("Total de usuarios: " + usuarios.size());
            for (Usuario u : usuarios) {
                System.out.println(u);
            }
            // Buscar usuarios por nombre
            List<Usuario> usuariosPorNombre = usuarioDAO.findByNombre("Juan");
            System.out.println("Usuarios con nombre 'Juan': " + usuariosPorNombre.size());
            for (Usuario u : usuariosPorNombre) {
                System.out.println(u);
            }

            // Cerrar conexión
            DatabaseConnection.closeConnection();

        } catch (SQLException e) {
            System.err.println("Error de base de datos: " + e.getMessage());
        }
    }
}