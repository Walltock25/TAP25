package Models.Strategies;
import Models.Cuenta;

public interface OperacionStrategy {
    boolean ejecutar(Cuenta cuentaOrigen, double monto, Cuenta cuentaDestino);
    String obtenerMensajeExito(double monto, String cuentaDestino);
    String obtenerMensajeError();
}