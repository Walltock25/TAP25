package org.javanet.Views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ClienteView {
    private TextArea areaChat;
    private TextField campoMensaje;
    private Button btnEnviar;
    private RadioButton rbMayusculas;
    private RadioButton rbMinusculas;
    private RadioButton rbNormal;
    private Label lblEstado;

    public VBox crearInterfaz() {
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f5f5f5;");

        // Título
        Label titulo = new Label("Cliente - Transformador de Mensajes");
        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Estado de conexión
        lblEstado = new Label("● Conectado al servidor");
        lblEstado.setStyle("-fx-text-fill: #4CAF50; -fx-font-size: 14px;");

        // Panel de opciones de transformación
        VBox panelOpciones = crearPanelOpciones();

        // Área de chat
        areaChat = new TextArea();
        areaChat.setEditable(false);
        areaChat.setPrefHeight(300);
        areaChat.setWrapText(true);
        areaChat.setStyle("-fx-font-family: 'Consolas', monospace; -fx-font-size: 12px;");

        // Panel de envío
        HBox panelEnvio = crearPanelEnvio();

        root.getChildren().addAll(titulo, lblEstado, panelOpciones,
                new Label("Conversación:"), areaChat, panelEnvio);

        return root;
    }

    private VBox crearPanelOpciones() {
        VBox panel = new VBox(10);
        panel.setPadding(new Insets(10));
        panel.setStyle("-fx-background-color: white; -fx-border-color: #ddd; -fx-border-radius: 5;");

        Label lblTitulo = new Label("Tipo de transformación:");
        lblTitulo.setStyle("-fx-font-weight: bold;");

        ToggleGroup grupo = new ToggleGroup();

        rbNormal = new RadioButton("Normal (sin transformación)");
        rbNormal.setToggleGroup(grupo);
        rbNormal.setSelected(true);

        rbMayusculas = new RadioButton("Convertir a MAYÚSCULAS");
        rbMayusculas.setToggleGroup(grupo);

        rbMinusculas = new RadioButton("Convertir a minúsculas");
        rbMinusculas.setToggleGroup(grupo);

        panel.getChildren().addAll(lblTitulo, rbNormal, rbMayusculas, rbMinusculas);

        return panel;
    }

    private HBox crearPanelEnvio() {
        HBox panel = new HBox(10);
        panel.setAlignment(Pos.CENTER);

        campoMensaje = new TextField();
        campoMensaje.setPromptText("Escribe tu mensaje aquí...");
        campoMensaje.setPrefWidth(400);
        HBox.setHgrow(campoMensaje, Priority.ALWAYS);

        btnEnviar = new Button("Enviar");
        btnEnviar.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-weight: bold;");
        btnEnviar.setPrefWidth(100);

        panel.getChildren().addAll(campoMensaje, btnEnviar);

        return panel;
    }

    public String obtenerMensajeCompleto() {
        String mensaje = campoMensaje.getText().trim();
        if (mensaje.isEmpty()) return null;

        if (rbMayusculas.isSelected()) {
            return "MAY:" + mensaje;
        } else if (rbMinusculas.isSelected()) {
            return "MIN:" + mensaje;
        } else {
            return mensaje;
        }
    }

    public void agregarMensaje(String mensaje) {
        areaChat.appendText(mensaje + "\n");
    }

    public void limpiarCampo() {
        campoMensaje.clear();
    }

    public void actualizarEstado(String estado, boolean conectado) {
        lblEstado.setText(conectado ? "● " + estado : "○ " + estado);
        lblEstado.setStyle(conectado ?
                "-fx-text-fill: #4CAF50; -fx-font-size: 14px;" :
                "-fx-text-fill: #f44336; -fx-font-size: 14px;");
    }

    public void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public Button getBtnEnviar() {
        return btnEnviar;
    }

    public TextField getCampoMensaje() {
        return campoMensaje;
    }
}