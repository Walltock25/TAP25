package patrones.builder;

/**
 * PATRÓN BUILDER (Constructor)
 *
 * Propósito: Permite construir objetos complejos paso a paso.
 * Produce diferentes tipos y representaciones de un objeto usando
 * el mismo código de construcción.
 *
 * Ventajas:
 * - Construye objetos paso a paso
 * - Permite crear diferentes representaciones del mismo objeto
 * - Evita constructores con muchos parámetros (telescoping constructors)
 * - El código es más legible y fácil de mantener
 * - Inmutabilidad: el objeto final no puede modificarse después de construido
 */

// 1. Clase Producto - El objeto complejo que queremos construir
class Computadora {
    // Componentes obligatorios
    private final String procesador;
    private final String ram;
    private final String discoDuro;

    // Componentes opcionales
    private final String tarjetaGrafica;
    private final String fuentePoder;
    private final boolean tieneSSD;
    private final boolean tieneWiFi;
    private final boolean tieneBluetooth;
    private final String gabinete;
    private final int ventiladores;

    /**
     * Constructor privado - Solo el Builder puede crear instancias
     * Esto asegura que solo se puedan crear objetos válidos
     */
    private Computadora(ComputadoraBuilder builder) {
        // Componentes obligatorios
        this.procesador = builder.procesador;
        this.ram = builder.ram;
        this.discoDuro = builder.discoDuro;

        // Componentes opcionales
        this.tarjetaGrafica = builder.tarjetaGrafica;
        this.fuentePoder = builder.fuentePoder;
        this.tieneSSD = builder.tieneSSD;
        this.tieneWiFi = builder.tieneWiFi;
        this.tieneBluetooth = builder.tieneBluetooth;
        this.gabinete = builder.gabinete;
        this.ventiladores = builder.ventiladores;
    }

    /**
     * Método para obtener las especificaciones completas
     */
    public String obtenerEspecificaciones() {
        StringBuilder specs = new StringBuilder();
        specs.append("╔════════════════════════════════════════╗\n");
        specs.append("║      ESPECIFICACIONES COMPUTADORA      ║\n");
        specs.append("╠════════════════════════════════════════╣\n");
        specs.append(String.format("║ Procesador:    %-23s ║\n", procesador));
        specs.append(String.format("║ RAM:           %-23s ║\n", ram));
        specs.append(String.format("║ Disco Duro:    %-23s ║\n", discoDuro));

        if (tarjetaGrafica != null) {
            specs.append(String.format("║ GPU:           %-23s ║\n", tarjetaGrafica));
        }
        if (fuentePoder != null) {
            specs.append(String.format("║ Fuente:        %-23s ║\n", fuentePoder));
        }
        if (gabinete != null) {
            specs.append(String.format("║ Gabinete:      %-23s ║\n", gabinete));
        }
        if (ventiladores > 0) {
            specs.append(String.format("║ Ventiladores:  %-23d ║\n", ventiladores));
        }

        specs.append("║ Características:                       ║\n");
        specs.append(String.format("║   SSD:         %-23s ║\n", tieneSSD ? "Sí" : "No"));
        specs.append(String.format("║   WiFi:        %-23s ║\n", tieneWiFi ? "Sí" : "No"));
        specs.append(String.format("║   Bluetooth:   %-23s ║\n", tieneBluetooth ? "Sí" : "No"));
        specs.append("╚════════════════════════════════════════╝");

        return specs.toString();
    }

    // 2. Clase Builder - Construye el objeto paso a paso
    public static class ComputadoraBuilder {
        // Componentes obligatorios
        private final String procesador;
        private final String ram;
        private final String discoDuro;

        // Componentes opcionales - con valores por defecto
        private String tarjetaGrafica = "Gráficos Integrados";
        private String fuentePoder = "500W Genérica";
        private boolean tieneSSD = false;
        private boolean tieneWiFi = false;
        private boolean tieneBluetooth = false;
        private String gabinete = "Torre ATX Estándar";
        private int ventiladores = 1;

        /**
         * Constructor del Builder - Solo requiere parámetros obligatorios
         * Esto evita tener un constructor con 10+ parámetros
         */
        public ComputadoraBuilder(String procesador, String ram, String discoDuro) {
            // Validación de parámetros obligatorios
            if (procesador == null || procesador.isEmpty()) {
                throw new IllegalArgumentException("El procesador es obligatorio");
            }
            if (ram == null || ram.isEmpty()) {
                throw new IllegalArgumentException("La RAM es obligatoria");
            }
            if (discoDuro == null || discoDuro.isEmpty()) {
                throw new IllegalArgumentException("El disco duro es obligatorio");
            }

            this.procesador = procesador;
            this.ram = ram;
            this.discoDuro = discoDuro;
        }

        // Métodos para establecer componentes opcionales
        // Cada método retorna 'this' para permitir method chaining

        public ComputadoraBuilder conTarjetaGrafica(String tarjetaGrafica) {
            this.tarjetaGrafica = tarjetaGrafica;
            return this; // Retorna el builder para encadenar métodos
        }

        public ComputadoraBuilder conFuentePoder(String fuentePoder) {
            this.fuentePoder = fuentePoder;
            return this;
        }

        public ComputadoraBuilder conSSD(boolean tieneSSD) {
            this.tieneSSD = tieneSSD;
            return this;
        }

        public ComputadoraBuilder conWiFi(boolean tieneWiFi) {
            this.tieneWiFi = tieneWiFi;
            return this;
        }

        public ComputadoraBuilder conBluetooth(boolean tieneBluetooth) {
            this.tieneBluetooth = tieneBluetooth;
            return this;
        }

        public ComputadoraBuilder conGabinete(String gabinete) {
            this.gabinete = gabinete;
            return this;
        }

        public ComputadoraBuilder conVentiladores(int ventiladores) {
            if (ventiladores < 0) {
                throw new IllegalArgumentException("Los ventiladores no pueden ser negativos");
            }
            this.ventiladores = ventiladores;
            return this;
        }

        /**
         * Método build() - Construye y retorna el objeto final
         * Este es el último paso en la construcción
         */
        public Computadora build() {
            // Aquí podríamos agregar validaciones adicionales
            // Por ejemplo, si tiene GPU potente, necesita fuente de al menos 600W
            if (tarjetaGrafica != null &&
                    tarjetaGrafica.contains("RTX") &&
                    fuentePoder.contains("500W")) {
                System.out.println("⚠️ Advertencia: GPU potente con fuente de 500W");
            }

            return new Computadora(this);
        }
    }
}