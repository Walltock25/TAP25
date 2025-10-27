package patrones.singleton;
/**
 * PATRÓN SINGLETON
 *
 * Propósito: Garantiza que una clase tenga una única instancia
 * y proporciona un punto de acceso global a ella.
 *
 * Ventajas:
 * - Una sola instancia en toda la aplicación
 * - Acceso global controlado a esa instancia
 * - Inicialización perezosa (lazy initialization) opcional
 * - Control sobre recursos compartidos
 *
 * Casos de uso comunes:
 * - Conexiones a base de datos
 * - Configuración de la aplicación
 * - Loggers
 * - Cachés
 * - Pools de hilos
 */

// ═══════════════════════════════════════════════════════════════════
// IMPLEMENTACIÓN 1: Singleton Eager (Inicialización Temprana)
// ═══════════════════════════════════════════════════════════════════

/**
 * Singleton Eager - La instancia se crea al cargar la clase
 *
 * PROS:
 * - Thread-safe por naturaleza (la JVM maneja la sincronización)
 * - Implementación simple
 * - Sin overhead de sincronización
 *
 * CONTRAS:
 * - La instancia se crea aunque no se use
 * - No permite manejo de excepciones en la inicialización
 */
class ConfiguracionEager {
    // La instancia se crea inmediatamente cuando se carga la clase
    private static final ConfiguracionEager INSTANCIA = new ConfiguracionEager();

    // Atributos de configuración
    private String nombreAplicacion;
    private String version;
    private String ambiente;

    /**
     * Constructor privado - Evita que se creen instancias desde fuera
     * Esta es la clave del patrón Singleton
     */
    private ConfiguracionEager() {
        // Inicialización de valores por defecto
        this.nombreAplicacion = "MiAplicacion";
        this.version = "1.0.0";
        this.ambiente = "DESARROLLO";
        System.out.println("✨ Instancia ConfiguracionEager creada");
    }

    /**
     * Método estático que retorna la única instancia
     * Este es el punto de acceso global
     */
    public static ConfiguracionEager obtenerInstancia() {
        return INSTANCIA;
    }

    // Métodos de negocio
    public void setNombreAplicacion(String nombre) {
        this.nombreAplicacion = nombre;
    }

    public String getNombreAplicacion() {
        return nombreAplicacion;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void mostrarConfiguracion() {
        System.out.println("┌─────────────────────────────┐");
        System.out.println("│ CONFIGURACIÓN (EAGER)       │");
        System.out.println("├─────────────────────────────┤");
        System.out.println("│ App:      " + nombreAplicacion);
        System.out.println("│ Versión:  " + version);
        System.out.println("│ Ambiente: " + ambiente);
        System.out.println("└─────────────────────────────┘");
    }
}
