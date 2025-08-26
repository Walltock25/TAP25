import java.util.Scanner;

public class ConversorMoneda{

    // Datos iniciales de billetes disponibles por denominación
    static int billetes500 = 10;
    static int billetes200 = 15;
    static int billetes100 = 20;
    static int billetes50 = 25;
    static int billetes20 = 30;
    static int billetes10 = 40;
    static int billetes5 = 50;
    static int billetes2 = 60;
    static int billetes1 = 100;

    public static void main(String[] args) {
        Scanner a_scanner = new Scanner(System.in);
        int a_entrada;

        System.out.println("Billetes disponibles al inicio:");
        m_mostrarBilletesDisponibles();

        // Bucle para permitir múltiples entradas
        while (true) {
            System.out.print("\nIngrese la cantidad a convertir (o 0 para salir): ");
            a_entrada = m_leerEntrada(a_scanner);

            if (a_entrada == 0) {
                System.out.println("Saliendo del programa.");
                break;
            }

            System.out.println(m_contador(a_entrada));
        }
        a_scanner.close();
    }

    /**
     * Lee la entrada de un entero desde el scanner.
     * @param a_scanner El objeto Scanner para leer la entrada.
     * @return El entero leído.
     */
    static int m_leerEntrada(Scanner a_scanner) {
        return a_scanner.nextInt();
    }

    /**
     * Realiza el conteo de billetes para una cantidad dada.
     * Muestra los billetes utilizados y actualiza el conteo de billetes disponibles.
     * @param a_entrada La cantidad total a convertir.
     * @return Una cadena con el resumen de billetes usados y un mensaje de estado.
     */
    static String m_contador(int a_entrada) {
        StringBuilder a_resultado = new StringBuilder();
        int a_modificable = a_entrada;

        // Contadores temporales para la transacción actual
        int usados500 = 0, usados200 = 0, usados100 = 0, usados50 = 0, usados20 = 0, usados10 = 0, usados5 = 0, usados2 = 0, usados1 = 0;

        // Billetes de 500
        if (a_modificable >= 500 && billetes500 > 0) {
            int cantidad = Math.min(a_modificable / 500, billetes500);
            usados500 = cantidad;
            a_modificable -= cantidad * 500;
        }

        // Billetes de 200
        if (a_modificable >= 200 && billetes200 > 0) {
            int cantidad = Math.min(a_modificable / 200, billetes200);
            usados200 = cantidad;
            a_modificable -= cantidad * 200;
        }

        // Billetes de 100
        if (a_modificable >= 100 && billetes100 > 0) {
            int cantidad = Math.min(a_modificable / 100, billetes100);
            usados100 = cantidad;
            a_modificable -= cantidad * 100;
        }

        // Billetes de 50
        if (a_modificable >= 50 && billetes50 > 0) {
            int cantidad = Math.min(a_modificable / 50, billetes50);
            usados50 = cantidad;
            a_modificable -= cantidad * 50;
        }

        // Billetes de 20
        if (a_modificable >= 20 && billetes20 > 0) {
            int cantidad = Math.min(a_modificable / 20, billetes20);
            usados20 = cantidad;
            a_modificable -= cantidad * 20;
        }

        // Billetes de 10
        if (a_modificable >= 10 && billetes10 > 0) {
            int cantidad = Math.min(a_modificable / 10, billetes10);
            usados10 = cantidad;
            a_modificable -= cantidad * 10;
        }

        // Billetes de 5
        if (a_modificable >= 5 && billetes5 > 0) {
            int cantidad = Math.min(a_modificable / 5, billetes5);
            usados5 = cantidad;
            a_modificable -= cantidad * 5;
        }

        // Billetes de 2
        if (a_modificable >= 2 && billetes2 > 0) {
            int cantidad = Math.min(a_modificable / 2, billetes2);
            usados2 = cantidad;
            a_modificable -= cantidad * 2;
        }

        // Billetes de 1
        if (a_modificable >= 1 && billetes1 > 0) {
            int cantidad = Math.min(a_modificable, billetes1);
            usados1 = cantidad;
            a_modificable -= cantidad;
        }

        a_resultado.append("--- Detalle de la transacción ---\n");
        // Se arma el resultado con los billetes usados
        if (usados500 > 0) a_resultado.append(usados500).append(" billetes de 500\n");
        if (usados200 > 0) a_resultado.append(usados200).append(" billetes de 200\n");
        if (usados100 > 0) a_resultado.append(usados100).append(" billetes de 100\n");
        if (usados50 > 0) a_resultado.append(usados50).append(" billetes de 50\n");
        if (usados20 > 0) a_resultado.append(usados20).append(" billetes de 20\n");
        if (usados10 > 0) a_resultado.append(usados10).append(" billetes de 10\n");
        if (usados5 > 0) a_resultado.append(usados5).append(" billetes de 5\n");
        if (usados2 > 0) a_resultado.append(usados2).append(" billetes de 2\n");
        if (usados1 > 0) a_resultado.append(usados1).append(" billetes de 1\n");

        // Se actualiza el conteo global de billetes
        billetes500 -= usados500;
        billetes200 -= usados200;
        billetes100 -= usados100;
        billetes50 -= usados50;
        billetes20 -= usados20;
        billetes10 -= usados10;
        billetes5 -= usados5;
        billetes2 -= usados2;
        billetes1 -= usados1;

        // Se verifica si hay un remanente no convertible
        if (a_modificable > 0) {
            a_resultado.append("Atención: No se pudo convertir la cantidad total. Resto: ").append(a_modificable);
        }

        a_resultado.append("\n--- Billetes restantes ---\n");
        a_resultado.append("500: ").append(billetes500).append("\n");
        a_resultado.append("200: ").append(billetes200).append("\n");
        a_resultado.append("100: ").append(billetes100).append("\n");
        a_resultado.append("50: ").append(billetes50).append("\n");
        a_resultado.append("20: ").append(billetes20).append("\n");
        a_resultado.append("10: ").append(billetes10).append("\n");
        a_resultado.append("5: ").append(billetes5).append("\n");
        a_resultado.append("2: ").append(billetes2).append("\n");
        a_resultado.append("1: ").append(billetes1).append("\n");

        return a_resultado.toString();
    }

    /**
     * Muestra la cantidad actual de billetes disponibles para cada denominación.
     */
    static void m_mostrarBilletesDisponibles() {
        System.out.println("500: " + billetes500);
        System.out.println("200: " + billetes200);
        System.out.println("100: " + billetes100);
        System.out.println("50: " + billetes50);
        System.out.println("20: " + billetes20);
        System.out.println("10: " + billetes10);
        System.out.println("5: " + billetes5);
        System.out.println("2: " + billetes2);
        System.out.println("1: " + billetes1);
    }
}