import java.util.Scanner;
public class ConversorMoneda {
    static int[] a_valores = {500, 200, 100, 50, 20, 10, 5, 2, 1};
    public static void main(String[] args) {
        Scanner a_scanner = new Scanner(System.in);
        int a_entrada;
        while ((a_entrada = m_leerEntrada(a_scanner)) != 0) {
            System.out.println(m_contador(a_entrada));
        }
        a_scanner.close();
    }
    static int m_leerEntrada(Scanner a_scanner) {
        return a_scanner.nextInt();
    }
    static String m_contador(int a_entrada) {
        StringBuilder a_resultado = new StringBuilder();
        int a_modificable = a_entrada;

        for (int a_valor : a_valores) {
            int a_cantidad = a_modificable / a_valor;
            if (a_cantidad > 0) {
                a_resultado.append(a_cantidad)
                        .append(" de ")
                        .append(a_valor)
                        .append("\n");
                a_modificable %= a_valor;
            }
        }
        return a_resultado.toString();
    }
}