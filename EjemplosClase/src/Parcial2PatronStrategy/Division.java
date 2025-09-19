package Parcial2PatronStrategy;

class Division implements OperacionMatematica {
    @Override
    public double ejecutar(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("No se puede dividir por cero");
        }
        return a / b;
    }

    @Override
    public String getNombreOperacion() {
        return "División";
    }
}