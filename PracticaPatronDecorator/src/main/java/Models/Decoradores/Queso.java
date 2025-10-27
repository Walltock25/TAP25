package main.java.Models.Decoradores;

public class Queso extends PizzaDecorator {
    public Queso(Pina masa) {
        super(masa);
    }

    // Sobrescribir getDescripcion: agregar " + Azúcar"
    @Override
    public String getDescripcion() {
        return masa.getDescripcion() + " + Queso";
    }

    // Sobrescribir getCosto: agregar $2.0
    @Override
    public double getCosto() {
        return masa.getCosto() + 7.0;
    }
}
