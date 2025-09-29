import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
/**
 * Clase genérica DAO que proporciona operaciones CRUD básicas
 * @param <T> Tipo de entidad a manejar
 */
public abstract class GenericDAO<T> {
    protected Connection connection;
    public GenericDAO(Connection connection) {
        this.connection = connection;
    }
    /**
     * Guarda una entidad en la base de datos
     * @param entity Entidad a guardar
     * @return true si la operación fue exitosa
     */
    public abstract boolean save(T entity);
    /**
     * Actualiza una entidad en la base de datos
     * @param entity Entidad a actualizar
     * @return true si la operación fue exitosa
     */
    public abstract boolean update(T entity);
    /**
     * Elimina una entidad de la base de datos
     * @param id Identificador de la entidad
     * @return true si la operación fue exitosa
     */
    public abstract boolean delete(Long id);
    /**
     * Busca una entidad por su ID
     * @param id Identificador de la entidad
     * @return La entidad encontrada o null
     */
    public abstract T findById(Long id);
    /**
     * Obtiene todas las entidades
     * @return Lista de entidades
     */
    public abstract List<T> findAll();
    /**
     * Convierte un ResultSet en una entidad
     * @param rs ResultSet a convertir
     * @return Entidad creada
     * @throws SQLException Si ocurre un error de SQL
     */
    protected abstract T mapResultSetToEntity(ResultSet rs) throws SQLException;
}
