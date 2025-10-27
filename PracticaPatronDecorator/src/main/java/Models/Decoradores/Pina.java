package main.java.Models.Decoradores;

import main.java.Models.Masa;

public class Pina extends PizzaDecorator {
    public Pina(Masa pizza) {
        super(pizza);
    }
    // Sobrescribir getDescripcion: agregar " + Azúcar"
    @Override
    public String getDescripcion() {
        return masa.getDescripcion() + " + Piña";
    }

    // Sobrescribir getCosto: agregar $2.0
    @Override
    public double getCosto() {
        return masa.getCosto() + 3.0;
    }
}
