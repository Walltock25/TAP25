package Models.Strategies;
import Models.Cuenta;

public class TransferenciaStrategy implements OperacionStrategy {
    @Override
    public boolean ejecutar(Cuenta cuentaOrigen, double monto, Cuenta cuentaDestino) {
        if (cuentaDestino != null && cuentaOrigen.m_retirar(monto)) {
            cuentaDestino.m_depositar(monto);
            return true;
        }
        return false;
    }

    @Override
    public String obtenerMensajeExito(double monto, String cuentaDestino) {
        return "Transferencia exitosa de: $" + monto + " a la cuenta: " + cuentaDestino;
    }

    @Override
    public String obtenerMensajeError() {
        return "Error en la transferencia. Verifique los datos.";
    }
}