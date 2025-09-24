package Models.Builders;

import Models.Cuenta; /**
 * Cuenta extendida que incluye nuevas características
 * Extiende la funcionalidad de la clase Cuenta original
 */
public class CuentaExtendida extends Cuenta {
    private final double limiteDiario;
    private final double comisionTransferencia;
    private final boolean esPremium;
    private final String tipoCuenta;
    private double retirosDiarios = 0.0;

    // Constructor que usa el Builder
    protected CuentaExtendida(CuentaBuilder builder) {
        super(builder.getNumeroCuenta(), builder.getPin(),
                builder.getSaldoInicial(), builder.getTitular());
        this.limiteDiario = builder.getLimiteDiario();
        this.comisionTransferencia = builder.getComisionTransferencia();
        this.esPremium = builder.isCuentaPremium();
        this.tipoCuenta = builder.getTipoCuenta();
    }

    // Métodos extendidos con nuevas reglas de negocio
    @Override
    public boolean m_retirar(double monto) {
        if (retirosDiarios + monto > limiteDiario) {
            System.out.println("Error: Excede el límite diario de $" + limiteDiario);
            return false;
        }

        if (super.m_retirar(monto)) {
            retirosDiarios += monto;
            return true;
        }
        return false;
    }

    public boolean transferirConComision(double monto, Cuenta cuentaDestino) {
        double montoTotal = monto + comisionTransferencia;
        if (super.m_retirar(montoTotal)) {
            cuentaDestino.m_depositar(monto);
            System.out.println("Transferencia exitosa. Comisión aplicada: $" + comisionTransferencia);
            return true;
        }
        return false;
    }

    // Resetear límite diario (se podría llamar diariamente)
    public void resetearLimiteDiario() {
        this.retirosDiarios = 0.0;
    }

    public String getInfoCuenta() {
        return String.format(
                "Cuenta: %s | Titular: %s | Tipo: %s | Saldo: $%.2f | Límite Diario: $%.2f | Premium: %s",
                getA_numCuenta(), getA_titular(), tipoCuenta, getA_saldo(), limiteDiario, esPremium ? "Sí" : "No"
        );
    }

    // Getters adicionales
    public double getLimiteDiario() { return limiteDiario; }
    public double getComisionTransferencia() { return comisionTransferencia; }
    public boolean esPremium() { return esPremium; }
    public String getTipoCuenta() { return tipoCuenta; }
    public double getRetirosDiarios() { return retirosDiarios; }
}
