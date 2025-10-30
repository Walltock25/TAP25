package org.example;
import javafx.application.Application;
import javafx.stage.Stage;
import org.example.controller.ClienteController;
import org.example.model.ClienteModel;
import org.example.view.ClienteView;

public class ClienteMain extends Application {
    private static final String HOST = "localhost";
    private static final int PUERTO = 9090;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        try {
            // Crear componentes MVC
            ClienteModel modelo = new ClienteModel(HOST, PUERTO);
            ClienteView vista = new ClienteView();
            ClienteController controlador = new ClienteController(modelo, vista);

            // Inicializar vista
            vista.inicializar(primaryStage);

            // Inicializar controlador
            controlador.inicializar();

        } catch (Exception e) {
            System.err.println("Error al iniciar la aplicación: " + e.getMessage());
            e.printStackTrace();
        }
    }
}