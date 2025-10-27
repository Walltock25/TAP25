package patrones.strategy;
// 3. Contexto - La clase que usa la estrategia
class CarritoCompras {
    private double total;
    private EstrategiaPago estrategiaPago; // La estrategia actual

    public CarritoCompras() {
        this.total = 0.0;
    }

    /**
     * Agrega un item al carrito
     */
    public void agregarItem(String nombre, double precio) {
        total += precio;
        System.out.println("➕ Agregado: " + nombre + " - $" +
                String.format("%.2f", precio));
    }

    /**
     * Establece la estrategia de pago a usar
     * Este método permite cambiar la estrategia en tiempo de ejecución
     */
    public void establecerEstrategiaPago(EstrategiaPago estrategia) {
        this.estrategiaPago = estrategia;
        System.out.println("💼 Método de pago seleccionado: " +
                estrategia.obtenerNombre());
    }

    /**
     * Procesa el pago usando la estrategia actual
     */
    public void pagar() {
        if (estrategiaPago == null) {
            System.out.println("❌ Error: No se ha seleccionado un método de pago");
            return;
        }

        if (total <= 0) {
            System.out.println("❌ Error: El carrito está vacío");
            return;
        }

        System.out.println("\n🛒 Total a pagar: $" + String.format("%.2f", total));
        System.out.println("─────────────────────────────────────");

        // Delega el procesamiento a la estrategia seleccionada
        boolean exito = estrategiaPago.procesarPago(total);

        if (exito) {
            System.out.println("─────────────────────────────────────");
            System.out.println("🎉 ¡Compra completada exitosamente!");
            total = 0.0; // Vaciar el carrito
        }
    }

    public double obtenerTotal() {
        return total;
    }
}
