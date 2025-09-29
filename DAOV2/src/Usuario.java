import java.time.LocalDate;

/**
 * Clase de entidad Usuario
 */
public class Usuario {
    private Long id;
    private String nombre;
    private String email;
    private String password;
    private LocalDate fechaRegistro;
    public Usuario() {
    }
    public Usuario(Long id, String nombre, String email, String password, LocalDate fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.fechaRegistro = fechaRegistro;
    }
    // Getters y setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }
    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}


