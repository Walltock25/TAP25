package org.javanet;

import org.javanet.Controllers.ClienteController;
import org.javanet.Models.ClienteModel;
import org.javanet.Views.ClienteView;

public class ClienteMain {
    public static void main(String[] args) {
        String HOST = "localhost";
        int PUERTO = 9090;

        try {
            ClienteModel modelo = new ClienteModel(HOST, PUERTO);
            ClienteView vista = new ClienteView();
            ClienteController controlador = new ClienteController(modelo, vista);

            controlador.iniciar();

        } catch (Exception e) {
            System.out.println("No se pudo conectar al servidor: " + e.getMessage());
        }
    }
}