package patrones.singleton;

// ═══════════════════════════════════════════════════════════════════
// IMPLEMENTACIÓN 2: Singleton Lazy (Inicialización Perezosa)
// ═══════════════════════════════════════════════════════════════════

/**
 * Singleton Lazy - La instancia se crea solo cuando se necesita
 *
 * PROS:
 * - La instancia se crea solo si se usa (ahorra memoria)
 * - Permite manejo de excepciones
 *
 * CONTRAS:
 * - NO es thread-safe en su forma básica
 * - Requiere sincronización para ser thread-safe
 */
class ConfiguracionLazy {
    // La instancia inicia como null
    private static ConfiguracionLazy instancia = null;

    private String rutaBaseDatos;
    private int puertoServidor;
    private int maxConexiones;

    private ConfiguracionLazy() {
        // Simulación de inicialización costosa
        System.out.println("⏳ Inicializando ConfiguracionLazy (proceso costoso)...");
        try {
            Thread.sleep(1000); // Simula carga de configuración
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.rutaBaseDatos = "jdbc:mysql://localhost:3306/midb";
        this.puertoServidor = 8080;
        this.maxConexiones = 100;
        System.out.println("✨ Instancia ConfiguracionLazy creada");
    }

    /**
     * Lazy initialization - La instancia se crea al primer acceso
     * NOTA: Esta versión NO es thread-safe
     */
    public static ConfiguracionLazy obtenerInstancia() {
        if (instancia == null) {
            instancia = new ConfiguracionLazy();
        }
        return instancia;
    }

    public void mostrarConfiguracion() {
        System.out.println("┌─────────────────────────────┐");
        System.out.println("│ CONFIGURACIÓN (LAZY)        │");
        System.out.println("├─────────────────────────────┤");
        System.out.println("│ BD:         " + rutaBaseDatos);
        System.out.println("│ Puerto:     " + puertoServidor);
        System.out.println("│ Max Conn:   " + maxConexiones);
        System.out.println("└─────────────────────────────┘");
    }
}