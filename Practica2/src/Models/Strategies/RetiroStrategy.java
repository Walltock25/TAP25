package Models.Strategies;
import Models.Cuenta;

public class RetiroStrategy implements OperacionStrategy {
    @Override
    public boolean ejecutar(Cuenta cuentaOrigen, double monto, Cuenta cuentaDestino) {
        return cuentaOrigen.m_retirar(monto);
    }

    @Override
    public String obtenerMensajeExito(double monto, String cuentaDestino) {
        return "Retiro exitoso de: $" + monto;
    }

    @Override
    public String obtenerMensajeError() {
        return "Fondos insuficientes o monto inválido";
    }
}