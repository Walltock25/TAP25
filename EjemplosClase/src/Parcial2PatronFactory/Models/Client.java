package Parcial2PatronFactory.Models;
import Parcial2PatronFactory.Entity;

public class Client implements Entity {
    private String name;
    private int age;

    public Client(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String getInfo() {
        return "Cliente: " + name + " - Edad: " + age;
    }
}