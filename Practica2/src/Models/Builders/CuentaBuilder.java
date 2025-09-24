package Models.Builders;
import Models.Cuenta;

/**
 * Builder para la creación flexible de cuentas bancarias
 * Permite configurar diferentes tipos de cuentas con distintas características
 */
public class CuentaBuilder {
    // Atributos obligatorios
    private String numeroCuenta;
    private String pin;
    private String titular;

    // Atributos opcionales con valores por defecto
    private double saldoInicial = 0.0;
    private double limiteDiario = 5000.0;
    private double comisionTransferencia = 0.0;
    private boolean cuentaPremium = false;
    private String tipoCuenta = "BÁSICA";

    // Constructor con parámetros obligatorios
    public CuentaBuilder(String numeroCuenta, String pin, String titular) {
        this.numeroCuenta = numeroCuenta;
        this.pin = pin;
        this.titular = titular;
    }

    public CuentaBuilder conSaldoInicial(double saldo) {
        this.saldoInicial = saldo;
        return this;
    }

    public CuentaBuilder conLimiteDiario(double limite) {
        this.limiteDiario = limite;
        return this;
    }

    public CuentaBuilder conComisionTransferencia(double comision) {
        this.comisionTransferencia = comision;
        return this;
    }

    public CuentaBuilder cuentaPremium() {
        this.cuentaPremium = true;
        this.tipoCuenta = "PREMIUM";
        this.limiteDiario = 10000.0; // Límite más alto
        this.comisionTransferencia = 0.0; // Sin comisiones
        return this;
    }

    public CuentaBuilder cuentaEmpresarial() {
        this.tipoCuenta = "EMPRESARIAL";
        this.limiteDiario = 50000.0;
        this.comisionTransferencia = 5.0;
        return this;
    }

    public CuentaBuilder cuentaEstudiante() {
        this.tipoCuenta = "ESTUDIANTE";
        this.limiteDiario = 1000.0;
        this.comisionTransferencia = 0.0;
        return this;
    }

    // Método para construir la cuenta extendida
    public CuentaExtendida build() {
        return new CuentaExtendida(this);
    }

    // Getters para acceso desde CuentaExtendida
    public String getNumeroCuenta() { return numeroCuenta; }
    public String getPin() { return pin; }
    public String getTitular() { return titular; }
    public double getSaldoInicial() { return saldoInicial; }
    public double getLimiteDiario() { return limiteDiario; }
    public double getComisionTransferencia() { return comisionTransferencia; }
    public boolean isCuentaPremium() { return cuentaPremium; }
    public String getTipoCuenta() { return tipoCuenta; }
}

