package Parcial2Ejemplo2;

public class Main {
    public static void main(String[] args) {
        Figura figura1 = FiguraFactory.getFigura("circulo");
        figura1.dibujar();
        Figura figura2 = FiguraFactory.getFigura("cuadrado");
        figura2.dibujar();

    }
}
