package patrones.strategy;
class PagoConPayPal implements EstrategiaPago {
    private String email;
    private String password;

    public PagoConPayPal(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean procesarPago(double monto) {
        // Lógica específica para procesar pago con PayPal
        System.out.println("🅿️ Procesando pago con PayPal");
        System.out.println("   Cuenta: " + email);
        System.out.println("   Monto: $" + String.format("%.2f", monto));

        // Simulación de autenticación
        if (autenticarCuenta()) {
            System.out.println("   ✅ Pago completado");
            return true;
        } else {
            System.out.println("   ❌ Error de autenticación");
            return false;
        }
    }

    private boolean autenticarCuenta() {
        // Simulación (en realidad se conectaría a la API de PayPal)
        return email.contains("@") && password.length() >= 6;
    }

    @Override
    public String obtenerNombre() {
        return "PayPal";
    }
}
