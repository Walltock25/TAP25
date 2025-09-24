package Views;
import Models.CajeroModelConBuilder;
import Controllers.CajeroControllerConBuilder;
import Models.Builders.DemoBuilderCajero;

/**
 * Clase principal que demuestra la integración del patrón Builder
 * en el sistema de cajero automático
 */
public class CajeroAutomaticoConBuilder {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA CAJERO AUTOMÁTICO CON PATRÓN BUILDER ===\n");

        // Opción 1: Ejecutar demostración del patrón Builder
        System.out.println("¿Desea ver la demostración del patrón Builder? (s/n): ");
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String respuesta = scanner.nextLine().toLowerCase();

        if (respuesta.equals("s") || respuesta.equals("si")) {
            System.out.println("\n--- DEMOSTRACIÓN DEL PATRÓN BUILDER ---");
            DemoBuilderCajero.main(args);

            System.out.println("\n¿Continuar con el sistema de cajero? (s/n): ");
            respuesta = scanner.nextLine().toLowerCase();
            if (!respuesta.equals("s") && !respuesta.equals("si")) {
                return;
            }
        }

        // Opción 2: Ejecutar el sistema de cajero con Builder
        System.out.println("\n--- INICIANDO SISTEMA DE CAJERO CON BUILDER ---");
        CajeroModelConBuilder model = new CajeroModelConBuilder();
        CajeroView view = new CajeroView();
        CajeroControllerConBuilder controlador = new CajeroControllerConBuilder(model, view);

        controlador.m_inciarSistema();
    }
}