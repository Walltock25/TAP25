package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.example.controller.ClienteController;
import org.example.model.ClienteModel;
import org.example.view.ClienteView;

public class ClienteMain extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Cliente JavaFX");
        Label label = new Label("Cliente JavaFX en ejecución...");
        StackPane root = new StackPane(label);
        Scene scene = new Scene(root, 400, 200);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        String HOST = "localhost";
        int PUERTO = 9090;
        launch(args);
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