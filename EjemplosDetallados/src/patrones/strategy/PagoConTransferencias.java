package patrones.strategy;
class PagoConTransferencia implements EstrategiaPago {
    private String cuentaBancaria;
    private String banco;

    public PagoConTransferencia(String cuentaBancaria, String banco) {
        this.cuentaBancaria = cuentaBancaria;
        this.banco = banco;
    }

    @Override
    public boolean procesarPago(double monto) {
        // Lógica específica para transferencia bancaria
        System.out.println("🏦 Procesando Transferencia Bancaria");
        System.out.println("   Banco: " + banco);
        System.out.println("   Cuenta: " + cuentaBancaria);
        System.out.println("   Monto: $" + String.format("%.2f", monto));
        System.out.println("   ⏳ Procesamiento puede tardar 24-48 horas");
        System.out.println("   ✅ Transferencia iniciada");
        return true;
    }

    @Override
    public String obtenerNombre() {
        return "Transferencia Bancaria";
    }
}
