package main.java.Models.Decoradores;
import main.java.Models.Masa;

public class PizzaDecorator implements Masa{
    protected Masa masa;

    public PizzaDecorator(Masa masa) {
        this.masa = masa;
    }


    @Override
    public String getDescripcion() {
        return masa.getDescripcion();
    }

    @Override
    public double getCosto() {
        return masa.getCosto();
    }
}