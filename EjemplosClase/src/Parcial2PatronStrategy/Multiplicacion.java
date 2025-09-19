package Parcial2PatronStrategy;

class Multiplicacion implements OperacionMatematica {
    @Override
    public double ejecutar(double a, double b) {
        return a * b;
    }

    @Override
    public String getNombreOperacion() {
        return "Multiplicación";
    }
}