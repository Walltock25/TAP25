package Models.Builders;

/**
 * Director que define recetas para diferentes tipos de cuentas bancarias
 * Implementa el patrón Builder con configuraciones predefinidas
 * Simplifica la creación de cuentas con características específicas por tipo
 */
public class DirectorCuentas {

    /**
     * Crea una cuenta básica con configuración estándar
     * - Límite diario: $2,000
     * - Comisión por transferencia: $10
     */
    public CuentaExtendida crearCuentaBasica(String numeroCuenta, String pin, String titular, double saldoInicial) {
        return new CuentaBuilder(numeroCuenta, pin, titular)
                .conSaldoInicial(saldoInicial)
                .conLimiteDiario(2000.0)
                .conComisionTransferencia(10.0)
                .build();
    }

    /**
     * Crea una cuenta premium con beneficios especiales
     * - Límite diario: $10,000
     * - Sin comisiones por transferencia
     * - Marcada como cuenta premium
     */
    public CuentaExtendida crearCuentaPremium(String numeroCuenta, String pin, String titular, double saldoInicial) {
        return new CuentaBuilder(numeroCuenta, pin, titular)
                .conSaldoInicial(saldoInicial)
                .cuentaPremium() // Configura automáticamente límites y comisiones premium
                .build();
    }

    /**
     * Crea una cuenta empresarial para negocios
     * - Límite diario: $50,000
     * - Comisión por transferencia: $5
     */
    public CuentaExtendida crearCuentaEmpresarial(String numeroCuenta, String pin, String titular, double saldoInicial) {
        return new CuentaBuilder(numeroCuenta, pin, titular)
                .conSaldoInicial(saldoInicial)
                .cuentaEmpresarial() // Configura límites y comisiones empresariales
                .build();
    }

    /**
     * Crea una cuenta para estudiantes con beneficios educativos
     * - Límite diario: $1,000
     * - Sin comisiones por transferencia
     */
    public CuentaExtendida crearCuentaEstudiante(String numeroCuenta, String pin, String titular, double saldoInicial) {
        return new CuentaBuilder(numeroCuenta, pin, titular)
                .conSaldoInicial(saldoInicial)
                .cuentaEstudiante() // Configura límites estudiantiles
                .build();
    }

    /**
     * Crea una cuenta con configuración personalizada
     * Permite especificar todos los parámetros manualmente
     */
    public CuentaExtendida crearCuentaPersonalizada(String numeroCuenta, String pin, String titular,
                                                    double saldoInicial, double limite, double comision) {
        return new CuentaBuilder(numeroCuenta, pin, titular)
                .conSaldoInicial(saldoInicial)
                .conLimiteDiario(limite)
                .conComisionTransferencia(comision)
                .build();
    }

    /**
     * Crea una cuenta VIP con máximos beneficios
     * - Límite diario: $100,000
     * - Sin comisiones
     * - Cuenta premium
     */
    public CuentaExtendida crearCuentaVIP(String numeroCuenta, String pin, String titular, double saldoInicial) {
        return new CuentaBuilder(numeroCuenta, pin, titular)
                .conSaldoInicial(saldoInicial)
                .conLimiteDiario(100000.0)
                .conComisionTransferencia(0.0)
                .cuentaPremium()
                .build();
    }

    /**
     * Crea una cuenta de ahorro con límites restrictivos
     * - Límite diario: $500
     * - Comisión alta para desincentivar muchas transacciones: $15
     */
    public CuentaExtendida crearCuentaAhorro(String numeroCuenta, String pin, String titular, double saldoInicial) {
        return new CuentaBuilder(numeroCuenta, pin, titular)
                .conSaldoInicial(saldoInicial)
                .conLimiteDiario(500.0)
                .conComisionTransferencia(15.0)
                .build();
    }
}