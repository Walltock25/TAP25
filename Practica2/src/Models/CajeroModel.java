package Models;
import Models.Strategies.*;
import java.util.HashMap;
import java.util.Map;

public class CajeroModel {
    private Map<String, Cuenta> cuentas;
    private Cuenta cuentaActual;
    private ContextoOperacion contextoOperacion;

    public CajeroModel() {
        cuentas = new HashMap<>();
        contextoOperacion = new ContextoOperacion(new ConsultaSaldoStrategy());
        m_inicializarCuentas();
    }

    private void m_inicializarCuentas() {
        cuentas.put("12345", new Cuenta("12345", "1111", 5000.0, "Juan Perez"));
        cuentas.put("654321", new Cuenta("654321", "4321", 2000.0, "Bob"));
        cuentas.put("111222", new Cuenta("111222", "1111", 1500.0, "Charlie"));
        cuentas.put("55555", new Cuenta("55555", "5555", 3000.0, "Alan"));
    }

    public boolean m_autenticar(String p_numCuenta, String p_pin) {
        Cuenta cuenta = cuentas.get(p_numCuenta);
        if (cuenta != null && cuenta.m_validPin(p_pin)) {
            cuentaActual = cuenta;
            return true;
        }
        return false;
    }

    public Cuenta getCuentaActual() {
        return cuentaActual;
    }

    public double m_consultarSaldo() {
        return this.cuentaActual != null ? cuentaActual.getA_saldo() : 0.0;
    }

    // Métodos usando el patrón Strategy
    public ResultadoOperacion m_retirar(double p_monto) {
        contextoOperacion.setStrategy(new RetiroStrategy());
        boolean exito = contextoOperacion.ejecutarOperacion(cuentaActual, p_monto, null);
        String mensaje = exito ?
                contextoOperacion.obtenerMensajeExito(p_monto, null) :
                contextoOperacion.obtenerMensajeError();
        return new ResultadoOperacion(exito, mensaje);
    }

    public ResultadoOperacion m_depositar(double p_monto) {
        contextoOperacion.setStrategy(new DepositoStrategy());
        boolean exito = contextoOperacion.ejecutarOperacion(cuentaActual, p_monto, null);
        String mensaje = exito ?
                contextoOperacion.obtenerMensajeExito(p_monto, null) :
                contextoOperacion.obtenerMensajeError();
        return new ResultadoOperacion(exito, mensaje);
    }

    public ResultadoOperacion m_transferir(String p_numCuentaDestino, double p_monto) {
        if (cuentaActual != null && cuentas.containsKey(p_numCuentaDestino)) {
            Cuenta cuentaDestino = cuentas.get(p_numCuentaDestino);
            contextoOperacion.setStrategy(new TransferenciaStrategy());
            boolean exito = contextoOperacion.ejecutarOperacion(cuentaActual, p_monto, cuentaDestino);
            String mensaje = exito ?
                    contextoOperacion.obtenerMensajeExito(p_monto, p_numCuentaDestino) :
                    contextoOperacion.obtenerMensajeError();
            return new ResultadoOperacion(exito, mensaje);
        }
        return new ResultadoOperacion(false, "Cuenta destino no encontrada");
    }

    public boolean m_cuentaExistente(String p_numCuenta) {
        return cuentas.containsKey(p_numCuenta);
    }
}