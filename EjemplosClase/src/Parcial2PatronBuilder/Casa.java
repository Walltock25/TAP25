package Parcial2PatronBuilder;
// Producto complejo que queremos construir
public class Casa {
    private String cimientos;
    private String paredes;
    private String techo;
    private String ventanas;
    private String puertas;
    private boolean tieneGaraje;
    private boolean tienePiscina;
    private boolean tieneJardin;

    // Constructor privado - solo el Builder puede crear la casa
    private Casa(CasaBuilder builder) {
        this.cimientos = builder.cimientos;
        this.paredes = builder.paredes;
        this.techo = builder.techo;
        this.ventanas = builder.ventanas;
        this.puertas = builder.puertas;
        this.tieneGaraje = builder.tieneGaraje;
        this.tienePiscina = builder.tienePiscina;
        this.tieneJardin = builder.tieneJardin;
    }

    @Override
    public String toString() {
        StringBuilder descripcion = new StringBuilder();
        descripcion.append(" CASA CONSTRUIDA:\n");
        descripcion.append("• Cimientos: ").append(cimientos).append("\n");
        descripcion.append("• Paredes: ").append(paredes).append("\n");
        descripcion.append("• Techo: ").append(techo).append("\n");
        descripcion.append("• Ventanas: ").append(ventanas).append("\n");
        descripcion.append("• Puertas: ").append(puertas).append("\n");
        descripcion.append("• Garaje: ").append(tieneGaraje ? "Sí" : "No").append("\n");
        descripcion.append("• Piscina: ").append(tienePiscina ? "Sí" : "No").append("\n");
        descripcion.append("• Jardín: ").append(tieneJardin ? "Sí" : "No").append("\n");
        return descripcion.toString();
    }

    // Builder interno estático
    public static class CasaBuilder {
        // Atributos obligatorios
        private String cimientos;
        private String paredes;
        private String techo;

        // Atributos opcionales con valores por defecto
        private String ventanas = "Ventanas estándar";
        private String puertas = "Puertas estándar";
        private boolean tieneGaraje = false;
        private boolean tienePiscina = false;
        private boolean tieneJardin = false;

        // Constructor con parámetros obligatorios
        public CasaBuilder(String cimientos, String paredes, String techo) {
            this.cimientos = cimientos;
            this.paredes = paredes;
            this.techo = techo;
        }

        // Métodos para configurar características opcionales
        public CasaBuilder conVentanas(String ventanas) {
            this.ventanas = ventanas;
            return this;
        }

        public CasaBuilder conPuertas(String puertas) {
            this.puertas = puertas;
            return this;
        }

        public CasaBuilder conGaraje() {
            this.tieneGaraje = true;
            return this;
        }

        public CasaBuilder conPiscina() {
            this.tienePiscina = true;
            return this;
        }

        public CasaBuilder conJardin() {
            this.tieneJardin = true;
            return this;
        }

        // Método para construir el objeto final
        public Casa build() {
            return new Casa(this);
        }
    }
}