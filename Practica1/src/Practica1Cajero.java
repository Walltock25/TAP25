import java.util.Scanner;
public class Practica1Cajero {
    String[] a_usuarios = {"1234", "5678"}, a_nombres = {"Juan", "Maria"};
    double[] a_saldos = {1000.0, 2500.0};
    boolean a_salir = false;
    int a_usuarioActual = -1, a_intentos = 0;
    Scanner a_scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Practica1Cajero v_objeto = new Practica1Cajero();
        v_objeto.m_ingrDatos(); v_objeto.m_OpcionesCajero();
    }
    void m_ingrDatos() {
        do {
            System.out.print("=== Bienvenido al Cajero ===\nIngrese su PIN: ");
            String v_pin = a_scanner.nextLine();
            for (int i = 0; i < a_usuarios.length; i++) {
                if (a_usuarios[i].equals(v_pin)) {
                    a_usuarioActual = i;
                    break;
                }
            }
            if (a_usuarioActual == -1) {
                System.out.println("PIN incorrecto.");
                a_intentos++;
            }
        } while(a_intentos < 3 && a_usuarioActual == -1);
        if (a_usuarioActual == -1) {
            System.out.println("Demasiados a_intentos fallidos. Adiós.");
        }
    }

    void m_OpcionesCajero() {
        System.out.println("Bienvenido, " + a_nombres[a_usuarioActual]);
        while (!a_salir) {
            System.out.println("\n1. Ver saldo\n2. Retirar dinero\n3. Depositar dinero\n4. Salir");
            System.out.print("Seleccione una opción: ");
            int v_opcion = a_scanner.nextInt();
            a_scanner.nextLine();

            switch (v_opcion) {
                case 1: System.out.println("Su saldo es: $" + a_saldos[a_usuarioActual]); break;
                case 2:
                    System.out.print("Ingrese cantidad a retirar: ");
                    double v_retiro = a_scanner.nextDouble();
                    if (v_retiro <= a_saldos[a_usuarioActual]) {
                        a_saldos[a_usuarioActual] -= v_retiro;
                        System.out.println("Retiro exitoso. Nuevo saldo: $" + a_saldos[a_usuarioActual]);
                    } else {
                        System.out.println("Fondos insuficientes.");
                    } break;
                case 3: System.out.print("Ingrese cantidad a depositar: ");
                    double deposito = a_scanner.nextDouble();
                    a_saldos[a_usuarioActual] += deposito;
                    System.out.println("Depósito exitoso. Nuevo saldo: $" + a_saldos[a_usuarioActual]); break;
                case 4: a_salir = true;
                    System.out.println("Gracias por usar el cajero."); break;
                default: System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }
}