package patrones.strategy;
// 2. Estrategias Concretas - Diferentes implementaciones del algoritmo

class PagoConTarjetaCredito implements EstrategiaPago {
    private String numeroTarjeta;
    private String nombreTitular;
    private String cvv;
    private String fechaExpiracion;

    public PagoConTarjetaCredito(String numeroTarjeta, String nombreTitular,
                                 String cvv, String fechaExpiracion) {
        this.numeroTarjeta = numeroTarjeta;
        this.nombreTitular = nombreTitular;
        this.cvv = cvv;
        this.fechaExpiracion = fechaExpiracion;
    }

    @Override
    public boolean procesarPago(double monto) {
        // Lógica específica para procesar pago con tarjeta de crédito
        System.out.println("💳 Procesando pago con Tarjeta de Crédito");
        System.out.println("   Titular: " + nombreTitular);
        System.out.println("   Tarjeta: **** **** **** " +
                numeroTarjeta.substring(numeroTarjeta.length() - 4));
        System.out.println("   Monto: $" + String.format("%.2f", monto));

        // Simulación de validación
        if (validarTarjeta()) {
            System.out.println("   ✅ Pago autorizado");
            return true;
        } else {
            System.out.println("   ❌ Pago rechazado");
            return false;
        }
    }

    private boolean validarTarjeta() {
        // Simulación de validación (en realidad se conectaría a un gateway de pago)
        return numeroTarjeta.length() == 16 && !cvv.isEmpty();
    }

    @Override
    public String obtenerNombre() {
        return "Tarjeta de Crédito";
    }
}