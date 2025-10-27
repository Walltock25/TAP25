package patrones.singleton;

// ═══════════════════════════════════════════════════════════════════
// IMPLEMENTACIÓN 3: Singleton Thread-Safe con Double-Checked Locking
// ═══════════════════════════════════════════════════════════════════

/**
 * Singleton Thread-Safe - Seguro para ambientes multi-hilo
 * Usa Double-Checked Locking para optimizar el rendimiento
 *
 * PROS:
 * - Thread-safe
 * - Lazy initialization
 * - Alto rendimiento (sincronización solo en primera creación)
 *
 * CONTRAS:
 * - Implementación más compleja
 * - Requiere 'volatile' para funcionar correctamente
 */
class ConfiguracionThreadSafe {
    // volatile asegura que los cambios sean visibles en todos los hilos
    private static volatile ConfiguracionThreadSafe instancia = null;

    private String apiKey;
    private String apiSecret;
    private String urlBase;

    private ConfiguracionThreadSafe() {
        this.apiKey = "ABC123XYZ";
        this.apiSecret = "SECRET_KEY_123";
        this.urlBase = "https://api.ejemplo.com";
        System.out.println("✨ Instancia ConfiguracionThreadSafe creada");
    }

    /**
     * Double-Checked Locking
     * Verifica dos veces antes de crear la instancia para optimizar rendimiento
     */
    public static ConfiguracionThreadSafe obtenerInstancia() {
        // Primera verificación sin sincronización (rápida)
        if (instancia == null) {
            // Sincronización solo si la instancia no existe
            synchronized (ConfiguracionThreadSafe.class) {
                // Segunda verificación dentro del bloque sincronizado
                // Por si otro hilo creó la instancia mientras esperábamos
                if (instancia == null) {
                    instancia = new ConfiguracionThreadSafe();
                }
            }
        }
        return instancia;
    }

    public void mostrarConfiguracion() {
        System.out.println("┌─────────────────────────────┐");
        System.out.println("│ CONFIGURACIÓN (THREAD-SAFE) │");
        System.out.println("├─────────────────────────────┤");
        System.out.println("│ API Key:    " + apiKey);
        System.out.println("│ API Secret: " + apiSecret.substring(0, 10) + "...");
        System.out.println("│ URL Base:   " + urlBase);
        System.out.println("└─────────────────────────────┘");
    }
}

// ═══════════════════════════════════════════════════════════════════
// IMPLEMENTACIÓN 4: Singleton con Enum (La mejor práctica según Joshua Bloch)
// ═══════════════════════════════════════════════════════════════════

/**
 * Singleton usando Enum - Considerada la mejor implementación
 *
 * PROS:
 * - Thread-safe por defecto
 * - Protección contra serialización
 * - Protección contra reflexión
 * - Implementación más simple y limpia
 * - Garantizada una única instancia por la JVM
 *
 * CONTRAS:
 * - No permite lazy initialization
 * - No puede extender otras clases (pero puede implementar interfaces)
 */
enum Logger {
    INSTANCIA; // La única instancia del enum

    private int contadorMensajes;

    // Constructor del enum (se ejecuta solo una vez)
    Logger() {
        this.contadorMensajes = 0;
        System.out.println("✨ Logger (Enum Singleton) inicializado");
    }

    public void log(String mensaje) {
        contadorMensajes++;
        System.out.println("[LOG #" + contadorMensajes + "] " + mensaje);
    }

    public void info(String mensaje) {
        contadorMensajes++;
        System.out.println("[INFO #" + contadorMensajes + "] ℹ️  " + mensaje);
    }

    public void error(String mensaje) {
        contadorMensajes++;
        System.out.println("[ERROR #" + contadorMensajes + "] ❌ " + mensaje);
    }

    public void advertencia(String mensaje) {
        contadorMensajes++;
        System.out.println("[WARN #" + contadorMensajes + "] ⚠️  " + mensaje);
    }

    public int obtenerContador() {
        return contadorMensajes;
    }
}