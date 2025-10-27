package org.javanet;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.javanet.Controllers.ClienteController;
import org.javanet.Models.ClienteModel;
import org.javanet.Views.ClienteView;

public class ClienteApp extends Application {
    private ClienteController controlador;

    @Override
    public void start(Stage stage) {
        ClienteModel modelo = new ClienteModel();
        ClienteView vista = new ClienteView();
        controlador = new ClienteController(modelo, vista);

        // Configurar escena primero
        Scene scene = new Scene(vista.crearInterfaz(), 600, 550);
        stage.setTitle("Cliente - Transformador de Mensajes");
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> controlador.cerrar());
        stage.show();

        // Intentar conectar después de mostrar la ventana
        try {
            modelo.conectar("localhost", 9090);
            vista.agregarMensaje("=== Conectado al servidor ===\n");
            vista.actualizarEstado("Conectado al servidor", true);
        } catch (Exception e) {
            vista.actualizarEstado("Error: No se pudo conectar al servidor", false);
            vista.agregarMensaje("ERROR: No se pudo conectar al servidor\n");
            vista.agregarMensaje("Por favor, asegúrate de que ServidorMain esté ejecutándose.\n");
            vista.mostrarError("No se pudo conectar al servidor en localhost:9090\n\n" +
                    "Pasos:\n" +
                    "1. Ejecuta primero ServidorMain.java\n" +
                    "2. Luego ejecuta el cliente");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}