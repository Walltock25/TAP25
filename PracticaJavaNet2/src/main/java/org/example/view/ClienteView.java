package org.example.view;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ClienteView {
    private Stage stage;
    private TextArea areaChat;
    private TextField campoMensaje;
    private Button btnEnviar;
    private Button btnLimpiar;
    private Button btnDesconectar;
    private Button btnHistorial;
    private Label lblEstado;

    public void inicializar(Stage stage) {
        this.stage = stage;
        stage.setTitle("Cliente Chat - JavaFX (Puerto 9090)");

        // Etiqueta de título
        Label lblTitulo = new Label("CHAT CLIENTE-SERVIDOR");
        lblTitulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        lblTitulo.setAlignment(Pos.CENTER);

        // Estado de conexión
        lblEstado = new Label("Desconectado");
        lblEstado.setStyle("-fx-font-size: 12px; -fx-text-fill: #e74c3c;");

        HBox headerBox = new HBox(20);
        headerBox.setAlignment(Pos.CENTER);
        headerBox.getChildren().addAll(lblTitulo, lblEstado);

        // Área de chat (historial)
        areaChat = new TextArea();
        areaChat.setEditable(false);
        areaChat.setWrapText(true);
        areaChat.setPrefHeight(400);
        areaChat.setStyle(
                "-fx-control-inner-background: #ecf0f1; " +
                        "-fx-font-size: 13px; " +
                        "-fx-font-family: 'Consolas', 'Monaco', monospace;"
        );

        // Campo de texto para mensajes
        campoMensaje = new TextField();
        campoMensaje.setPromptText("Escribe tu mensaje aquí...");
        campoMensaje.setPrefHeight(40);
        campoMensaje.setStyle("-fx-font-size: 14px;");

        // Botones
        btnEnviar = new Button("Enviar");
        btnEnviar.setPrefHeight(40);
        btnEnviar.setPrefWidth(120);
        btnEnviar.setStyle(
                "-fx-background-color: #27ae60; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-font-weight: bold;"
        );

        btnHistorial = new Button("Historial");
        btnHistorial.setPrefHeight(40);
        btnHistorial.setPrefWidth(120);
        btnHistorial.setStyle(
                "-fx-background-color: #9b59b6; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-font-weight: bold;"
        );

        btnLimpiar = new Button("Limpiar");
        btnLimpiar.setPrefHeight(40);
        btnLimpiar.setPrefWidth(120);
        btnLimpiar.setStyle(
                "-fx-background-color: #3498db; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px;"
        );

        btnDesconectar = new Button("Salir");
        btnDesconectar.setPrefHeight(40);
        btnDesconectar.setPrefWidth(120);
        btnDesconectar.setStyle(
                "-fx-background-color: #e74c3c; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px;"
        );

        // Layout de botones - Primera fila
        HBox layoutBotonesFila1 = new HBox(10);
        layoutBotonesFila1.setAlignment(Pos.CENTER);
        layoutBotonesFila1.getChildren().addAll(btnEnviar, btnHistorial);

        // Layout de botones - Segunda fila
        HBox layoutBotonesFila2 = new HBox(10);
        layoutBotonesFila2.setAlignment(Pos.CENTER);
        layoutBotonesFila2.getChildren().addAll(btnLimpiar, btnDesconectar);

        // Layout de todos los botones
        VBox layoutBotones = new VBox(10);
        layoutBotones.getChildren().addAll(layoutBotonesFila1, layoutBotonesFila2);

        // Layout inferior
        VBox layoutInferior = new VBox(10);
        layoutInferior.getChildren().addAll(campoMensaje, layoutBotones);

        // Layout principal
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #ffffff;");
        root.getChildren().addAll(headerBox, areaChat, layoutInferior);
        VBox.setVgrow(areaChat, Priority.ALWAYS);

        Scene scene = new Scene(root, 750, 650);
        stage.setScene(scene);
        stage.show();

        campoMensaje.requestFocus();
    }

    // Getters para el controlador
    public Button getBtnEnviar() { return btnEnviar; }
    public Button getBtnLimpiar() { return btnLimpiar; }
    public Button getBtnDesconectar() { return btnDesconectar; }
    public Button getBtnHistorial() { return btnHistorial; }
    public TextField getCampoMensaje() { return campoMensaje; }
    public TextArea getAreaChat() { return areaChat; }
    public Stage getStage() { return stage; }

    public void actualizarEstado(String estado, boolean conectado) {
        if (conectado) {
            lblEstado.setText("🟢 " + estado);
            lblEstado.setStyle("-fx-font-size: 12px; -fx-text-fill: #27ae60;");
        } else {
            lblEstado.setText("🔴 " + estado);
            lblEstado.setStyle("-fx-font-size: 12px; -fx-text-fill: #e74c3c;");
        }
    }

    public void agregarMensaje(String mensaje) {
        areaChat.appendText(mensaje + "\n");
    }

    public void limpiarChat() {
        areaChat.clear();
    }

    public void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public void habilitarControles(boolean habilitar) {
        campoMensaje.setDisable(!habilitar);
        btnEnviar.setDisable(!habilitar);
        btnHistorial.setDisable(!habilitar);
    }
}