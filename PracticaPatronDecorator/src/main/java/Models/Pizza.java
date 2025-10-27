package main.java.Models;
public class Pizza implements Masa {
    @Override
    public String getDescripcion() {
        return "Pizza Simple";
    }
    @Override
    public double getCosto() {
        return 20.0;
    }
}
