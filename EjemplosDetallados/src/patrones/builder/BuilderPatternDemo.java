package patrones.builder;

// 4. Demo del patrón Builder
public class BuilderPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== DEMOSTRACIÓN DEL PATRÓN BUILDER ===\n");

        // Ejemplo 1: Construcción personalizada paso a paso
        System.out.println("--- EJEMPLO 1: Construcción Personalizada ---\n");

        Computadora miPC = new Computadora.ComputadoraBuilder(
                "AMD Ryzen 5 5600X",    // Procesador (obligatorio)
                "16GB DDR4",             // RAM (obligatorio)
                "512GB NVMe"             // Disco (obligatorio)
        )
                // Ahora agregamos componentes opcionales usando method chaining
                .conTarjetaGrafica("NVIDIA RTX 3060 Ti")
                .conFuentePoder("650W 80+ Bronze")
                .conSSD(true)
                .conWiFi(true)
                .conBluetooth(true)
                .conGabinete("Torre Mini ITX")
                .conVentiladores(3)
                .build(); // Finalmente construimos el objeto

        System.out.println(miPC.obtenerEspecificaciones());

        // Ejemplo 2: Construcción mínima (solo obligatorios)
        System.out.println("\n\n--- EJEMPLO 2: Construcción Mínima ---\n");

        Computadora pcBasica = new Computadora.ComputadoraBuilder(
                "Intel Pentium Gold",
                "4GB DDR4",
                "500GB HDD"
        ).build(); // Solo componentes obligatorios

        System.out.println(pcBasica.obtenerEspecificaciones());

        // Ejemplo 3: Usando el Director para configuraciones predefinidas
        System.out.println("\n\n--- EJEMPLO 3: Usando Director (PC Oficina) ---\n");
        Computadora pcOficina = DirectorComputadoras.construirPCOficina();
        System.out.println(pcOficina.obtenerEspecificaciones());

        System.out.println("\n\n--- EJEMPLO 4: Usando Director (PC Gaming) ---\n");
        Computadora pcGaming = DirectorComputadoras.construirPCGaming();
        System.out.println(pcGaming.obtenerEspecificaciones());

        System.out.println("\n\n--- EJEMPLO 5: Usando Director (Workstation) ---\n");
        Computadora workstation = DirectorComputadoras.construirWorkstation();
        System.out.println(workstation.obtenerEspecificaciones());

        // Ejemplo 6: Demostración de validación
        System.out.println("\n\n--- EJEMPLO 6: Validación de Errores ---");
        try {
            Computadora pcInvalida = new Computadora.ComputadoraBuilder(
                    null,  // Error: procesador nulo
                    "8GB",
                    "1TB"
            ).build();
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error capturado: " + e.getMessage());
        }
    }
}

/*
 * COMPARACIÓN: SIN Builder vs CON Builder
 *
 * === SIN BUILDER (Telescoping Constructor) ===
 * public Computadora(String proc, String ram, String disco, String gpu,
 *                    String fuente, boolean ssd, boolean wifi,
 *                    boolean bt, String gab, int vent) {
 *     // Constructor con 10 parámetros - difícil de leer y usar
 * }
 *
 * Uso:
 * Computadora pc = new Computadora(
 *     "i5", "16GB", "512GB", "RTX 3060", "650W",
 *     true, true, true, "ATX", 3
 * ); // ¿Qué significa cada parámetro? No es claro
 *
 *
 * === CON BUILDER ===
 * Computadora pc = new Computadora.ComputadoraBuilder("i5", "16GB", "512GB")
 *     .conTarjetaGrafica("RTX 3060")
 *     .conFuentePoder("650W")
 *     .conSSD(true)
 *     .conWiFi(true)
 *     .conBluetooth(true)
 *     .conGabinete("ATX")
 *     .conVentiladores(3)
 *     .build();
 * // Mucho más legible y mantenible
 *
 *
 * CUÁNDO USAR BUILDER:
 * - Objeto con muchos parámetros (más de 4-5)
 * - Muchos parámetros opcionales
 * - Se necesita inmutabilidad del objeto
 * - El proceso de construcción es complejo
 * - Se requiere validación durante la construcción
 */