package Models;
import Models.Strategies.OperacionStrategy;
import Models.Builders.CuentaExtendida;

/**
 * Versión extendida del ContextoOperacion que puede trabajar tanto
 * con Cuenta como con CuentaExtendida
 */
public class ContextoOperacion {
    private OperacionStrategy strategy;

    public ContextoOperacion(OperacionStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(OperacionStrategy strategy) {
        this.strategy = strategy;
    }

    // Método original para compatibilidad
    public boolean ejecutarOperacion(Cuenta cuentaOrigen, double monto, Cuenta cuentaDestino) {
        return strategy.ejecutar(cuentaOrigen, monto, cuentaDestino);
    }

    // Método sobrecargado para CuentaExtendida
    public boolean ejecutarOperacion(CuentaExtendida cuentaOrigen, double monto, CuentaExtendida cuentaDestino) {
        return strategy.ejecutar(cuentaOrigen, monto, cuentaDestino);
    }

    public String obtenerMensajeExito(double monto, String cuentaDestino) {
        return strategy.obtenerMensajeExito(monto, cuentaDestino);
    }

    public String obtenerMensajeError() {
        return strategy.obtenerMensajeError();
    }
}