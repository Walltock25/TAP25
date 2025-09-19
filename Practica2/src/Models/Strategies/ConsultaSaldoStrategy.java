package Models.Strategies;
import Models.Cuenta;

public class ConsultaSaldoStrategy implements OperacionStrategy {
    @Override
    public boolean ejecutar(Cuenta cuentaOrigen, double monto, Cuenta cuentaDestino) {
        return true; // La consulta de saldo siempre es exitosa
    }

    @Override
    public String obtenerMensajeExito(double monto, String cuentaDestino) {
        return "Consulta de saldo realizada";
    }

    @Override
    public String obtenerMensajeError() {
        return "Error al consultar saldo";
    }
}