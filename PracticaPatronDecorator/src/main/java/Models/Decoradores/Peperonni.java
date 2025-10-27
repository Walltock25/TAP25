package main.java.Models.Decoradores;

import main.java.Models.Masa;
public class Peperonni extends PizzaDecorator {
    // Constructor: recibe la bebida a decorar
    public Peperonni(Masa masa) {
        super(masa);  // Llama al constructor del padre
    }

    // Sobrescribir getDescripcion: agregar " + Azúcar"
    @Override
    public String getDescripcion() {
        return masa.getDescripcion() + " + Peperoni";
    }

    // Sobrescribir getCosto: agregar $2.0
    @Override
    public double getCosto() {
        return masa.getCosto() + 2.0;
    }
}
