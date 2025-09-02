package Views;
import java.util.Scanner;
public class CajeroView {
    private static Scanner scanner;
    public CajeroView() { scanner = new Scanner(System.in); }
    public void m_mostrarBienvenida() {
        System.out.println("===================================");
        System.out.println("   Bienvenido al Cajero Automático  ");
        System.out.println("===================================");
    }
    public static String m_solicitarDato(String mensaje) {
        System.out.println(mensaje);
        return scanner.nextLine();
    }
    public static String m_solicitarNumeroCuenta() {
        return m_solicitarDato("Ingresar número de cuenta: ");
    }
    public static String m_solicitarPin() {
        return m_solicitarDato("Ingrese su PIN: ");
    }
    public void m_mostrarMenu(String p_titular){
        System.out.println("\nMenú Principal:\nBienvenid@"+p_titular+"\n1. Consultar Saldo\n2. Retirar Dinero\n3. Depositar Dinero\n4. Transferir\n5. Salir");
        System.out.print("Seleccione una opción: ");
    }
    public int m_leerOpcion() {
        try { return Integer.parseInt(scanner.nextLine());} catch (NumberFormatException e) {return -1;}
    }
    public void m_mostrarSaldo(double p_saldo) { System.out.printf("Su saldo actual es: $%.2f\n", p_saldo); }
    public double m_solicitarMonto(String p_operacion) {
        System.out.printf("Ingrese el monto a %s: ", p_operacion);
        try { return Double.parseDouble(scanner.nextLine());} catch (NumberFormatException e) {return -1;}
    }
    public void m_mostrarMensaje(String p_mensaje) { System.out.println("====="+p_mensaje);}
}
