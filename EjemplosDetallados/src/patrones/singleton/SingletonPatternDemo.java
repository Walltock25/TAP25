package patrones.singleton;

// ═══════════════════════════════════════════════════════════════════
// DEMOSTRACIÓN
// ═══════════════════════════════════════════════════════════════════

public class SingletonPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== DEMOSTRACIÓN DEL PATRÓN SINGLETON ===\n");

        // ─────────────────────────────────────────────────────────────
        // EJEMPLO 1: Singleton Eager
        // ─────────────────────────────────────────────────────────────
        System.out.println("--- EJEMPLO 1: Singleton Eager ---");

        ConfiguracionEager config1 = ConfiguracionEager.obtenerInstancia();
        config1.setNombreAplicacion("SistemaVentas");
        config1.setVersion("2.5.0");
        config1.mostrarConfiguracion();

        // Obtenemos la "misma" instancia desde otro lugar
        ConfiguracionEager config2 = ConfiguracionEager.obtenerInstancia();

        // Verificamos que son la misma instancia
        System.out.println("\n¿config1 y config2 son la misma instancia? " +
                (config1 == config2 ? "✅ SÍ" : "❌ NO"));
        System.out.println("HashCode config1: " + config1.hashCode());
        System.out.println("HashCode config2: " + config2.hashCode());

        // Modificamos desde config2 y vemos el cambio en config1
        config2.setAmbiente("PRODUCCIÓN");
        System.out.println("\nDespués de modificar config2:");
        config1.mostrarConfiguracion();

        // ─────────────────────────────────────────────────────────────
        // EJEMPLO 2: Singleton Lazy
        // ─────────────────────────────────────────────────────────────
        System.out.println("\n\n--- EJEMPLO 2: Singleton Lazy ---");
        System.out.println("⏸️  La instancia NO se crea hasta que la necesitamos\n");

        // Primera llamada: crea la instancia
        ConfiguracionLazy configLazy1 = ConfiguracionLazy.obtenerInstancia();
        configLazy1.mostrarConfiguracion();

        // Segunda llamada: reutiliza la instancia existente
        System.out.println("\nSegunda llamada:");
        ConfiguracionLazy configLazy2 = ConfiguracionLazy.obtenerInstancia();
        System.out.println("¿Son la misma instancia? " +
                (configLazy1 == configLazy2 ? "✅ SÍ" : "❌ NO"));

        // ─────────────────────────────────────────────────────────────
        // EJEMPLO 3: Singleton Thread-Safe
        // ─────────────────────────────────────────────────────────────
        System.out.println("\n\n--- EJEMPLO 3: Singleton Thread-Safe ---");

        ConfiguracionThreadSafe configTS = ConfiguracionThreadSafe.obtenerInstancia();
        configTS.mostrarConfiguracion();

        // ─────────────────────────────────────────────────────────────
        // EJEMPLO 4: Singleton con Enum (Mejor práctica)
        // ─────────────────────────────────────────────────────────────
        System.out.println("\n\n--- EJEMPLO 4: Singleton Enum (Logger) ---");

        // Uso del Logger desde diferentes partes del código
        Logger.INSTANCIA.info("Aplicación iniciada");
        Logger.INSTANCIA.log("Usuario conectado: admin");
        Logger.INSTANCIA.advertencia("Memoria al 80%");
        Logger.INSTANCIA.error("No se pudo conectar a la BD");
        Logger.INSTANCIA.info("Aplicación finalizada");

        System.out.println("\nTotal de mensajes registrados: " +
                Logger.INSTANCIA.obtenerContador());

        // ─────────────────────────────────────────────────────────────
        // EJEMPLO 5: Demostración de unicidad en múltiples hilos
        // ─────────────────────────────────────────────────────────────
        System.out.println("\n\n--- EJEMPLO 5: Prueba Multi-Hilo ---");
        System.out.println("Creando 5 hilos que intentan obtener la instancia...\n");

        for (int i = 0; i < 5; i++) {
            final int threadNum = i + 1;
            new Thread(() -> {
                ConfiguracionThreadSafe config = ConfiguracionThreadSafe.obtenerInstancia();
                System.out.println("Hilo " + threadNum + " obtuvo instancia: " +
                        config.hashCode());
            }).start();
        }

        // Esperar a que terminen los hilos
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n✅ Todos los hilos obtuvieron la misma instancia");
    }
}

/*
 * RESUMEN DE IMPLEMENTACIONES:
 *
 * 1. EAGER: Usa cuando la instancia siempre se va a necesitar
 * 2. LAZY: Usa cuando la instancia puede no ser necesaria
 * 3. THREAD-SAFE: Usa en aplicaciones multi-hilo con lazy init
 * 4. ENUM: La mejor opción en la mayoría de los casos (recomendada)
 *
 * ANTIPATRONES A EVITAR:
 * - Hacer el Singleton mutable globalmente
 * - Usar Singleton para pasar parámetros entre objetos
 * - Abusar del patrón (no todo debe ser Singleton)
 *
 * CUÁNDO USAR SINGLETON:
 * ✅ Conexiones a base de datos
 * ✅ Loggers
 * ✅ Configuración de la aplicación
 * ✅ Cachés
 * ✅ Pools de recursos
 *
 * CUÁNDO NO USAR:
 * ❌ Solo para evitar pasar parámetros
 * ❌ Cuando necesitas múltiples instancias en el futuro
 * ❌ En pruebas unitarias (dificulta el testing)
 */