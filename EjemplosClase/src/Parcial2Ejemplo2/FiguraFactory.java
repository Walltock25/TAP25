package Parcial2Ejemplo2;

public class FiguraFactory {
    public static Figura getFigura(String tipo) {
        if (tipo.equalsIgnoreCase("circulo")) {
            return new Circulo();
        } else if (tipo.equalsIgnoreCase("cuadrado")) {
            return new Cuadrado();
        }
        return null;
    }
}
