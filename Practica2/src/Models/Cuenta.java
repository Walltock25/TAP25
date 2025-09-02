package Models;
public class Cuenta {
    private final String a_numCuenta;
    private final String a_pin;
    private double a_saldo;
    private final String a_titular;

    public Cuenta(String p_numCuenta, String p_pin, Double p_saldoInicial, String p_titular) {
        this.a_numCuenta = p_numCuenta;
        this.a_pin = p_pin;
        this.a_saldo = p_saldoInicial;
        this.a_titular = p_titular;
    }

    public String getA_numCuenta() {return a_numCuenta;}
    public String getA_pin() {return a_pin;}
    public Double getA_saldo() {return a_saldo;}
    public String getA_titular() {return a_titular;}

    //Reglas de negocio
    public boolean m_validPin(String p_pinIngresado) {
        return this.a_pin.equals(p_pinIngresado);
    }
    public boolean m_retirar(double p_monto) {
        if (p_monto > 0 && p_monto <= this.a_saldo) {
            a_saldo -= p_monto;
            return true;
        }
        return false;
    }
    public void m_depositar(double p_monto) {
        if (p_monto > 0) {
            a_saldo += p_monto;
        }
    }
    //Tarea: Diseñar los comportamientos restantes como transferir, cambiar NIP, etc.
    public void m_transferir(double p_monto, Cuenta p_cuentaDestino) {
        if (p_monto > 0 && p_monto <= this.a_saldo) {
            this.m_retirar(p_monto);
            p_cuentaDestino.m_depositar(p_monto);
        }
    }
}