package Controllers;
import Models.CajeroModel;
import Models.ResultadoOperacion;
import Views.CajeroView;

public class CajeroController {
    private CajeroModel model;
    private CajeroView view;
    private boolean sistemaActivo;

    public CajeroController(CajeroModel model, CajeroView view) {
        this.model = model;
        this.view = view;
        this.sistemaActivo = true;
    }

    public void m_inciarSistema() {
        view.m_mostrarBienvenida();
        while (sistemaActivo) {
            String numCuenta = CajeroView.m_solicitarNumeroCuenta();
            String pin = CajeroView.m_solicitarPin();
            if (model.m_autenticar(numCuenta, pin)) {
                boolean continuarSistema = m_menuPrincipal();
                if (!continuarSistema) {
                    sistemaActivo = false;
                }
            } else {
                view.m_mostrarMensaje("Credenciales incorrectas");
            }
        }
        view.m_mostrarMensaje("Gracias por usar nuestro cajero");
    }

    private boolean m_menuPrincipal() {
        boolean sesionActiva = true;
        while (sesionActiva) {
            view.m_mostrarMenu(model.getCuentaActual().getA_titular());
            int opcion = view.m_leerOpcion();
            switch (opcion) {
                case 1:
                    double saldo = model.m_consultarSaldo();
                    view.m_mostrarSaldo(saldo);
                    break;
                case 2:
                    double montoRetiro = view.m_solicitarMonto("retirar");
                    ResultadoOperacion resultadoRetiro = model.m_retirar(montoRetiro);
                    view.m_mostrarMensaje(resultadoRetiro.getMensaje());
                    break;
                case 3:
                    double montoDeposito = view.m_solicitarMonto("depositar");
                    ResultadoOperacion resultadoDeposito = model.m_depositar(montoDeposito);
                    view.m_mostrarMensaje(resultadoDeposito.getMensaje());
                    break;
                case 4:
                    String cuentaDestino = CajeroView.m_solicitarNumeroCuenta();
                    double montoTransferencia = view.m_solicitarMonto("transferir");
                    ResultadoOperacion resultadoTransferencia = model.m_transferir(cuentaDestino, montoTransferencia);
                    view.m_mostrarMensaje(resultadoTransferencia.getMensaje());
                    break;
                case 5:
                    // Cerrar sesión (volver al inicio)
                    sesionActiva = false;
                    view.m_mostrarMensaje("Sesión cerrada. Volviendo al inicio...");
                    return true; // Continuar el sistema, permitir nuevo login
                case 6:
                    // Salir completamente del sistema
                    sesionActiva = false;
                    view.m_mostrarMensaje("Saliendo del sistema...");
                    return false; // Terminar el programa completamente
                default:
                    view.m_mostrarMensaje("Opción inválida. Intente de nuevo.");
            }
        }
        return true;
    }
}