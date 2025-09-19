package Models.Strategies;
import Models.Cuenta;

public class DepositoStrategy implements OperacionStrategy {
    @Override
    public boolean ejecutar(Cuenta cuentaOrigen, double monto, Cuenta cuentaDestino) {
        if (monto > 0) {
            cuentaOrigen.m_depositar(monto);
            return true;
        }
        return false;
    }

    @Override
    public String obtenerMensajeExito(double monto, String cuentaDestino) {
        return "Depósito exitoso de: $" + monto;
    }

    @Override
    public String obtenerMensajeError() {
        return "Monto inválido para depósito";
    }
}