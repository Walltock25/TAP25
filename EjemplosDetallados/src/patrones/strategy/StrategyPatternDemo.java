package patrones.strategy;
// 4. Demo del patrón Strategy
public class StrategyPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== DEMOSTRACIÓN DEL PATRÓN STRATEGY ===\n");

        // Crear carrito de compras
        CarritoCompras carrito = new CarritoCompras();

        // Agregar items al carrito
        System.out.println("--- Agregando items al carrito ---");
        carrito.agregarItem("Laptop", 15000.00);
        carrito.agregarItem("Mouse", 350.00);
        carrito.agregarItem("Teclado", 800.00);

        // Escenario 1: Pagar con Tarjeta de Crédito
        System.out.println("\n\n=== ESCENARIO 1: Pago con Tarjeta ===");
        EstrategiaPago pagoTarjeta = new PagoConTarjetaCredito(
                "1234567890123456",
                "Juan Pérez",
                "123",
                "12/2026"
        );
        carrito.establecerEstrategiaPago(pagoTarjeta);
        carrito.pagar();

        // Escenario 2: Nuevo carrito, pagar con PayPal
        System.out.println("\n\n=== ESCENARIO 2: Pago con PayPal ===");
        CarritoCompras carrito2 = new CarritoCompras();
        carrito2.agregarItem("Audífonos", 1200.00);
        carrito2.agregarItem("Cargador", 450.00);

        EstrategiaPago pagoPayPal = new PagoConPayPal(
                "usuario@ejemplo.com",
                "password123"
        );
        carrito2.establecerEstrategiaPago(pagoPayPal);
        carrito2.pagar();

        // Escenario 3: Cambiar de estrategia en tiempo de ejecución
        System.out.println("\n\n=== ESCENARIO 3: Cambio de Estrategia ===");
        CarritoCompras carrito3 = new CarritoCompras();
        carrito3.agregarItem("Monitor", 5000.00);

        // Primero seleccionamos PayPal
        carrito3.establecerEstrategiaPago(pagoPayPal);

        // Pero luego cambiamos a Transferencia Bancaria
        System.out.println("🔄 Cambio de método de pago...");
        EstrategiaPago pagoTransferencia = new PagoConTransferencia(
                "012345678901234567",
                "Banco Nacional"
        );
        carrito3.establecerEstrategiaPago(pagoTransferencia);
        carrito3.pagar();

        // Escenario 4: Intentar pagar sin estrategia
        System.out.println("\n\n=== ESCENARIO 4: Error sin estrategia ===");
        CarritoCompras carrito4 = new CarritoCompras();
        carrito4.agregarItem("Webcam", 800.00);
        carrito4.pagar(); // No se estableció estrategia
    }
}

/*
 * VENTAJAS DEMOSTRADAS:
 *
 * 1. Sin condicionales: No necesitamos if/else o switch para decidir
 *    qué algoritmo de pago usar.
 *
 * 2. Extensible: Podemos agregar nuevas estrategias (Bitcoin, Apple Pay)
 *    sin modificar el código existente.
 *
 * 3. Flexible: Podemos cambiar la estrategia en tiempo de ejecución.
 *
 * 4. Testeable: Cada estrategia puede probarse independientemente.
 *
 * 5. Mantenible: Cada estrategia está en su propia clase, fácil de mantener.
 */