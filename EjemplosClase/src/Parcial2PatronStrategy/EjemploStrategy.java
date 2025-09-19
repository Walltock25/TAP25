package Parcial2PatronStrategy;

public class EjemploStrategy {
    public static void main(String[] args) {
        // Crear la calculadora con una estrategia inicial
        Calculadora calculadora = new Calculadora(new Suma());

        System.out.println("=== EJEMPLO DEL PATRÓN STRATEGY ===\n");

        // Usar diferentes estrategias
        double a = 10;
        double b = 3;

        // 1. Suma
        double resultado = calculadora.calcular(a, b);
        System.out.println("Resultado: " + a + " + " + b + " = " + resultado + "\n");

        // 2. Cambiar a resta
        calculadora.setEstrategia(new Resta());
        resultado = calculadora.calcular(a, b);
        System.out.println("Resultado: " + a + " - " + b + " = " + resultado + "\n");

        // 3. Cambiar a multiplicación
        calculadora.setEstrategia(new Multiplicacion());
        resultado = calculadora.calcular(a, b);
        System.out.println("Resultado: " + a + " * " + b + " = " + resultado + "\n");

        // 4. Cambiar a división
        calculadora.setEstrategia(new Division());
        resultado = calculadora.calcular(a, b);
        System.out.printf("Resultado: %.2f / %.2f = %.2f\n\n", a, b, resultado);

        // 5. Demostrar polimorfismo - usando diferentes estrategias en un array
        System.out.println("=== DEMO CON MÚLTIPLES ESTRATEGIAS ===");
        OperacionMatematica[] operaciones = {
                new Suma(),
                new Resta(),
                new Multiplicacion(),
                new Division()
        };

        for (OperacionMatematica op : operaciones) {
            calculadora.setEstrategia(op);
            try {
                double res = calculadora.calcular(15, 5);
                System.out.println("15 y 5 con " + op.getNombreOperacion() + " = " + res);
            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}