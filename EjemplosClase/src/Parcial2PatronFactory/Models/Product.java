package Parcial2PatronFactory.Models;
import Parcial2PatronFactory.Entity;

public class Product implements Entity {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getInfo() {
        return "Producto: " + name + " - Precio: $" + price;
    }
}