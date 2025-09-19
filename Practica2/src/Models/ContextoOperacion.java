package Models;
import Models.Strategies.OperacionStrategy;

public class ContextoOperacion {
    private OperacionStrategy strategy;

    public ContextoOperacion(OperacionStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(OperacionStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean ejecutarOperacion(Cuenta cuentaOrigen, double monto, Cuenta cuentaDestino) {
        return strategy.ejecutar(cuentaOrigen, monto, cuentaDestino);
    }

    public String obtenerMensajeExito(double monto, String cuentaDestino) {
        return strategy.obtenerMensajeExito(monto, cuentaDestino);
    }

    public String obtenerMensajeError() {
        return strategy.obtenerMensajeError();
    }
}