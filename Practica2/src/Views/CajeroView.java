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
    public static String m_solicitarNumeroCuenta() {
        System.out.println("Ingresar número de cuenta: ");
        return scanner.nextLine();
    }
    public static String m_solicitarPin() {
        System.out.println("Ingrese su PIN: ");
        return scanner.nextLine();
    }
    public void m_mostrarMenu(String p_titular){
        System.out.println("\nMenú Principal:\nBienvenid@"+p_titular+"\n1. Consultar Saldo\n2. Retirar Dinero\n3. Depositar Dinero\n4. Transferir\n5. Salir");
        //Definir las opciones faltantes del menu
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
    /*Tarea: mostrar mensajes de error y de éxito
    * tarea método para salir cerrar scanner
    * */
}
