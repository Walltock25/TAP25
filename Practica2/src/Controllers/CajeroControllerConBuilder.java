package Controllers;
import Models.CajeroModelConBuilder;
import Models.ResultadoOperacion;
import Models.Builders.CuentaExtendida;
import Models.Builders.CuentaBuilder;
import Models.Builders.DirectorCuentas;
import Views.CajeroView;

/**
 * Controlador extendido que utiliza el patrón Builder
 * Incluye funcionalidades adicionales como creación de cuentas dinámicas
 */
public class CajeroControllerConBuilder {
    private CajeroModelConBuilder model;
    private CajeroView view;
    private boolean sistemaActivo;
    private DirectorCuentas directorCuentas;

    public CajeroControllerConBuilder(CajeroModelConBuilder model, CajeroView view) {
        this.model = model;
        this.view = view;
        this.sistemaActivo = true;
        this.directorCuentas = new DirectorCuentas();
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
            m_mostrarMenuExtendido();
            int opcion = view.m_leerOpcion();
            switch (opcion) {
                case 1:
                    m_consultarSaldoDetallado();
                    break;
                case 2:
                    m_retirarConLimite();
                    break;
                case 3:
                    m_depositar();
                    break;
                case 4:
                    m_transferirConComision();
                    break;
                case 5:
                    m_mostrarInfoCuenta();
                    break;
                case 6:
                    m_crearNuevaCuenta();
                    break;
                case 7:
                    // Cerrar sesión
                    sesionActiva = false;
                    view.m_mostrarMensaje("Sesión cerrada. Volviendo al inicio...");
                    return true;
                case 8:
                    // Salir completamente
                    sesionActiva = false;
                    view.m_mostrarMensaje("Saliendo del sistema...");
                    return false;
                default:
                    view.m_mostrarMensaje("Opción inválida. Intente de nuevo.");
            }
        }
        return true;
    }

    private void m_mostrarMenuExtendido() {
        CuentaExtendida cuenta = model.getCuentaActual();
        System.out.println("\n=== CAJERO AUTOMÁTICO CON PATRÓN BUILDER ===");
        System.out.println("Bienvenid@ " + cuenta.getA_titular() + " (" + cuenta.getTipoCuenta() + ")");
        System.out.println("1. Consultar Saldo Detallado");
        System.out.println("2. Retirar Dinero (con límite)");
        System.out.println("3. Depositar Dinero");
        System.out.println("4. Transferir (con comisión)");
        System.out.println("5. Ver Información de Cuenta");
        System.out.println("6. Crear Nueva Cuenta");
        System.out.println("7. Cerrar Sesión");
        System.out.println("8. Salir del Sistema");
        System.out.print("Seleccione una opción: ");
    }

    private void m_consultarSaldoDetallado() {
        CuentaExtendida cuenta = model.getCuentaActual();
        double saldo = model.m_consultarSaldo();
        System.out.println("\n=== INFORMACIÓN DE SALDO ===");
        System.out.printf("Saldo actual: $%.2f\n", saldo);
        System.out.printf("Límite diario: $%.2f\n", cuenta.getLimiteDiario());
        System.out.printf("Retiros hoy: $%.2f\n", cuenta.getRetirosDiarios());
        System.out.printf("Disponible para retiro: $%.2f\n",
                Math.min(saldo, cuenta.getLimiteDiario() - cuenta.getRetirosDiarios()));
    }

    private void m_retirarConLimite() {
        double montoRetiro = view.m_solicitarMonto("retirar");
        ResultadoOperacion resultado = model.m_retirar(montoRetiro);
        view.m_mostrarMensaje(resultado.getMensaje());
    }

    private void m_depositar() {
        double montoDeposito = view.m_solicitarMonto("depositar");
        ResultadoOperacion resultado = model.m_depositar(montoDeposito);
        view.m_mostrarMensaje(resultado.getMensaje());
    }

    private void m_transferirConComision() {
        String cuentaDestino = CajeroView.m_solicitarNumeroCuenta();
        double montoTransferencia = view.m_solicitarMonto("transferir");

        CuentaExtendida cuentaActual = model.getCuentaActual();
        System.out.printf("Comisión a aplicar: $%.2f\n", cuentaActual.getComisionTransferencia());
        System.out.printf("Total a descontar: $%.2f\n",
                montoTransferencia + cuentaActual.getComisionTransferencia());

        ResultadoOperacion resultado = model.m_transferir(cuentaDestino, montoTransferencia);
        view.m_mostrarMensaje(resultado.getMensaje());
    }

    private void m_mostrarInfoCuenta() {
        CuentaExtendida cuenta = model.getCuentaActual();
        System.out.println("\n=== INFORMACIÓN COMPLETA DE CUENTA ===");
        System.out.println(cuenta.getInfoCuenta());
        System.out.printf("Retiros realizados hoy: $%.2f\n", cuenta.getRetirosDiarios());
        System.out.println("Premium: " + (cuenta.esPremium() ? "Sí - Sin comisiones" : "No"));
    }

    private void m_crearNuevaCuenta() {
        System.out.println("\n=== CREAR NUEVA CUENTA ===");
        String numCuenta = CajeroView.m_solicitarDato("Número de cuenta nueva: ");
        String pin = CajeroView.m_solicitarDato("PIN para la cuenta: ");
        String titular = CajeroView.m_solicitarDato("Nombre del titular: ");
        double saldoInicial = view.m_solicitarMonto("saldo inicial");

        System.out.println("Tipos de cuenta disponibles:");
        System.out.println("1. Básica");
        System.out.println("2. Premium");
        System.out.println("3. Empresarial");
        System.out.println("4. Estudiante");
        System.out.println("5. Personalizada");
        System.out.print("Seleccione el tipo: ");

        int tipo = view.m_leerOpcion();
        CuentaExtendida nuevaCuenta = null;

        switch (tipo) {
            case 1:
                nuevaCuenta = directorCuentas.crearCuentaBasica(numCuenta, pin, titular, saldoInicial);
                break;
            case 2:
                nuevaCuenta = directorCuentas.crearCuentaPremium(numCuenta, pin, titular, saldoInicial);
                break;
            case 3:
                nuevaCuenta = directorCuentas.crearCuentaEmpresarial(numCuenta, pin, titular, saldoInicial);
                break;
            case 4:
                nuevaCuenta = directorCuentas.crearCuentaEstudiante(numCuenta, pin, titular, saldoInicial);
                break;
            case 5:
                double limite = view.m_solicitarMonto("límite diario");
                double comision = view.m_solicitarMonto("comisión por transferencia");
                nuevaCuenta = directorCuentas.crearCuentaPersonalizada(numCuenta, pin, titular, saldoInicial, limite, comision);
                break;
            default:
                view.m_mostrarMensaje("Tipo inválido, creando cuenta básica...");
                nuevaCuenta = directorCuentas.crearCuentaBasica(numCuenta, pin, titular, saldoInicial);
        }

        if (nuevaCuenta != null && model.m_agregarNuevaCuenta(nuevaCuenta)) {
            view.m_mostrarMensaje("Cuenta creada exitosamente: " + nuevaCuenta.getInfoCuenta());
        } else {
            view.m_mostrarMensaje("Error al crear la cuenta. Verifique que el número no exista.");
        }
    }
}