package Parcial2PatronStrategy;

class Suma implements OperacionMatematica {
    @Override
    public double ejecutar(double a, double b) {
        return a + b;
    }

    @Override
    public String getNombreOperacion() {
        return "Suma";
    }
}
