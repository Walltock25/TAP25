package Parcial2PatronFactory;

import Parcial2PatronFactory.Models.Client;
import Parcial2PatronFactory.Models.Product;
import Parcial2PatronFactory.Views.View;
import Parcial2PatronFactory.Controllers.Controller;

public class Main {
    public static void main(String[] args) {
        // Caso 1: Trabajamos con Productos
        EntityFactory<Product> productFactory = new EntityFactory<>(Product.class);
        View<Product> productView = new View<>();
        Controller<Product> productController = new Controller<>(productFactory, productView);

        productController.createAndShow("Laptop", 15000.0);
        productController.createAndShow("Smartphone", 8000.0);

        System.out.println("-----");

        // Caso 2: Trabajamos con Clientes
        EntityFactory<Client> clientFactory = new EntityFactory<>(Client.class);
        View<Client> clientView = new View<>();
        Controller<Client> clientController = new Controller<>(clientFactory, clientView);

        clientController.createAndShow("Alan", 20);
        clientController.createAndShow("María", 25);
    }
}