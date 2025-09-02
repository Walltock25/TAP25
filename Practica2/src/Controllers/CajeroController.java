package Controllers;
import Models.CajeroModel;
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
            if (m_autenticar()) {
                m_menuPrincipal();
            } else {
                view.m_mostrarMensaje("Credenciales incorrectas");
            }
        }
        view.m_mostrarMensaje("Gracias por usar nuestro cajero");
    }

    private boolean m_autenticar() {
        String numCuenta = view.m_solicitarNumeroCuenta();
        String pin = view.m_solicitarPin();
        return model.m_autenticar(numCuenta, pin);
    }
    private boolean m_menuPrincipal() {
        //usar camelcase
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
                    if (model.m_retirar(montoRetiro) && montoRetiro > 0) {
                        view.m_mostrarMensaje("Retiro exitoso de: "+montoRetiro);
                    } else {
                        view.m_mostrarMensaje("Fondos insuficientes o monto inválido");
                    }
                    break;
                case 3:
                    double montoDeposito = view.m_solicitarMonto("depositar");
                    if (model.m_depositar(montoDeposito) && montoDeposito > 0) {
                        view.m_mostrarMensaje("Depósito exitoso de: "+montoDeposito);
                    } else {
                        view.m_mostrarMensaje("Monto inválido");
                    }
                    break;
                case 4:
                    String cuentaDestino = CajeroView.m_solicitarNumeroCuenta();
                    double montoTransferencia = view.m_solicitarMonto("transferir");
                    if (model.m_transferir(cuentaDestino, montoTransferencia)) {
                        view.m_mostrarMensaje("Transferencia exitosa de: "+montoTransferencia+" a la cuenta: "+cuentaDestino);
                    } else {
                        view.m_mostrarMensaje("Error en la transferencia. Verifique los datos.");
                    }
                    break;
                case 5:
                    sesionActiva = false;
                    view.m_mostrarMensaje("Sesión cerrada.");
                    break;
                default:
                    view.m_mostrarMensaje("Opción inválida. Intente de nuevo.");
            }
        }
        return false;
    }
}
