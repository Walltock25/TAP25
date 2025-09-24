package Models;

import Models.Builders.CuentaExtendida;
import Models.Builders.DirectorCuentas;
import Models.Builders.CuentaBuilder;
import Models.Strategies.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Versión extendida del CajeroModel que utiliza el patrón Builder
 * para crear diferentes tipos de cuentas con características específicas
 */
public class CajeroModelConBuilder {
    private Map<String, CuentaExtendida> cuentasExtendidas;
    private CuentaExtendida cuentaActual;
    private ContextoOperacion contextoOperacion;
    private DirectorCuentas directorCuentas;

    public CajeroModelConBuilder() {
        cuentasExtendidas = new HashMap<>();
        contextoOperacion = new ContextoOperacion(new ConsultaSaldoStrategy());
        directorCuentas = new DirectorCuentas();
        m_inicializarCuentasConBuilder();
    }

    /**
     * Inicializa las cuentas usando el patrón Builder
     * Demuestra la creación de diferentes tipos de cuentas
     */
    private void m_inicializarCuentasConBuilder() {
        // Cuenta básica usando director
        CuentaExtendida cuentaBasica = directorCuentas.crearCuentaBasica("12345", "1111", "Juan Pérez", 5000.0);
        cuentasExtendidas.put("12345", cuentaBasica);

        // Cuenta premium usando director
        CuentaExtendida cuentaPremium = directorCuentas.crearCuentaPremium("654321", "4321", "Bob Premium", 15000.0);
        cuentasExtendidas.put("654321", cuentaPremium);

        // Cuenta empresarial usando director
        CuentaExtendida cuentaEmpresarial = directorCuentas.crearCuentaEmpresarial("111222", "1111", "Charlie Business", 50000.0);
        cuentasExtendidas.put("111222", cuentaEmpresarial);

        // Cuenta estudiante usando director
        CuentaExtendida cuentaEstudiante = directorCuentas.crearCuentaEstudiante("55555", "5555", "Alan Student", 1500.0);
        cuentasExtendidas.put("55555", cuentaEstudiante);

        // Cuenta personalizada usando builder directo
        CuentaExtendida cuentaPersonalizada = new CuentaBuilder("77777", "7777", "María Personalizada")
                .conSaldoInicial(8000.0)
                .conLimiteDiario(4000.0)
                .conComisionTransferencia(7.5)
                .build();
        cuentasExtendidas.put("77777", cuentaPersonalizada);

        System.out.println("=== CUENTAS INICIALIZADAS CON PATRÓN BUILDER ===");
        for (CuentaExtendida cuenta : cuentasExtendidas.values()) {
            System.out.println(cuenta.getInfoCuenta());
        }
        System.out.println("================================================\n");
    }

    public boolean m_autenticar(String p_numCuenta, String p_pin) {
        CuentaExtendida cuenta = cuentasExtendidas.get(p_numCuenta);
        if (cuenta != null && cuenta.m_validPin(p_pin)) {
            cuentaActual = cuenta;
            System.out.println("Autenticación exitosa para: " + cuenta.getInfoCuenta());
            return true;
        }
        return false;
    }

    public CuentaExtendida getCuentaActual() {
        return cuentaActual;
    }

    public double m_consultarSaldo() {
        return this.cuentaActual != null ? cuentaActual.getA_saldo() : 0.0;
    }

    // Métodos que aprovechan las características extendidas de Builder
    public ResultadoOperacion m_retirarConLimite(double p_monto) {
        if (cuentaActual == null) {
            return new ResultadoOperacion(false, "No hay sesión activa");
        }

        boolean exito = cuentaActual.m_retirar(p_monto);
        String mensaje = exito ?
                "Retiro exitoso de: $" + p_monto +
                        " (Retiros diarios: $" + cuentaActual.getRetirosDiarios() +
                        " / Límite: $" + cuentaActual.getLimiteDiario() + ")" :
                "Error en retiro. Verifique fondos y límite diario ($" + cuentaActual.getLimiteDiario() + ")";

        return new ResultadoOperacion(exito, mensaje);
    }

    public ResultadoOperacion m_retirar(double p_monto) {
        return m_retirarConLimite(p_monto);
    }

    public ResultadoOperacion m_depositar(double p_monto) {
        contextoOperacion.setStrategy(new DepositoStrategy());
        boolean exito = contextoOperacion.ejecutarOperacion(cuentaActual, p_monto, null);
        String mensaje = exito ?
                contextoOperacion.obtenerMensajeExito(p_monto, null) :
                contextoOperacion.obtenerMensajeError();
        return new ResultadoOperacion(exito, mensaje);
    }

    public ResultadoOperacion m_transferirConComision(String p_numCuentaDestino, double p_monto) {
        if (cuentaActual == null) {
            return new ResultadoOperacion(false, "No hay sesión activa");
        }

        if (!cuentasExtendidas.containsKey(p_numCuentaDestino)) {
            return new ResultadoOperacion(false, "Cuenta destino no encontrada");
        }

        CuentaExtendida cuentaDestino = cuentasExtendidas.get(p_numCuentaDestino);
        double comision = cuentaActual.getComisionTransferencia();

        boolean exito = cuentaActual.transferirConComision(p_monto, cuentaDestino);
        String mensaje = exito ?
                "Transferencia exitosa de: $" + p_monto + " a cuenta " + p_numCuentaDestino +
                        " (Comisión: $" + comision + ")" :
                "Error en transferencia. Verifique fondos suficientes para monto + comisión ($" + comision + ")";

        return new ResultadoOperacion(exito, mensaje);
    }

    public ResultadoOperacion m_transferir(String p_numCuentaDestino, double p_monto) {
        return m_transferirConComision(p_numCuentaDestino, p_monto);
    }

    // Método especial para agregar nuevas cuentas usando Builder
    public boolean m_agregarNuevaCuenta(CuentaExtendida cuenta) {
        if (!cuentasExtendidas.containsKey(cuenta.getA_numCuenta())) {
            cuentasExtendidas.put(cuenta.getA_numCuenta(), cuenta);
            System.out.println("Nueva cuenta agregada: " + cuenta.getInfoCuenta());
            return true;
        }
        return false;
    }

    // Método para resetear límites diarios (simulación de proceso nocturno)
    public void resetearLimitesDiarios() {
        for (CuentaExtendida cuenta : cuentasExtendidas.values()) {
            cuenta.resetearLimiteDiario();
        }
        System.out.println("");
        System.out.println("=== LÍMITES DIARIOS REINICIADOS PARA TODAS LAS CUENTAS ===");
        System.out.println("");
    }
}