package main.java;
import main.java.Models.Decoradores.Pina;
import main.java.Models.Decoradores.Peperonni;
import main.java.Models.Decoradores.Queso;
import main.java.Models.Pizza;
import main.java.Models.Masa;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE Pizzería - PATRÓN DECORATOR ===\n");

        // Ejemplo 1: Simple
        Masa masa1 = new Pizza();
        System.out.println("1. " + masa1.getDescripcion());
        System.out.println("   Costo: $" + masa1.getCosto());
        System.out.println();

        // Ejemplo 2
        Masa masa2 = new Peperonni(new Pizza());
        System.out.println("2. " + masa2.getDescripcion());
        System.out.println("   Costo: $" + masa2.getCosto());
        System.out.println();

        // Ejemplo 3
        Masa masa3 = new Queso(new Pina(new Pizza()));
        System.out.println("3. " + masa3.getDescripcion());
        System.out.println("   Costo: $" + masa3.getCosto());
        System.out.println();

        // Ejemplo 4
        Masa masa4 = new Peperonni(new Pina(new Pizza()));
        System.out.println("4. " + masa4.getDescripcion());
        System.out.println("   Costo: $" + masa4.getCosto());
        System.out.println();

        //Ejemplo 5
        Masa masa5 =  new Peperonni(new Queso(new Pina(new Pizza())));
        System.out.println("5. " + masa5.getDescripcion());
        System.out.println("   Costo: $" + masa5.getCosto());
        System.out.println();

        System.out.println("=== ¡Gracias por usar nuestro sistema! ===");
    }
}