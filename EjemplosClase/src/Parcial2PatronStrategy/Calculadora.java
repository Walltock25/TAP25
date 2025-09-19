package Parcial2PatronStrategy;

class Calculadora {
    private OperacionMatematica estrategia;

    // Constructor que recibe la estrategia inicial
    public Calculadora(OperacionMatematica estrategia) {
        this.estrategia = estrategia;
    }

    // Método para cambiar la estrategia en tiempo de ejecución
    public void setEstrategia(OperacionMatematica estrategia) {
        this.estrategia = estrategia;
    }

    // Método que usa la estrategia actual
    public double calcular(double a, double b) {
        System.out.println("Ejecutando: " + estrategia.getNombreOperacion());
        return estrategia.ejecutar(a, b);
    }
}