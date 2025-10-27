package org.javanet.Controllers;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import org.javanet.Models.ClienteModel;
import org.javanet.Views.ClienteView;

public class ClienteController {
    private final ClienteModel modelo;
    private final ClienteView vista;

    public ClienteController(ClienteModel modelo, ClienteView vista) {
        this.modelo = modelo;
        this.vista = vista;
        configurarEventos();
    }

    private void configurarEventos() {
        vista.getBtnEnviar().setOnAction(e -> enviarMensaje());

        vista.getCampoMensaje().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                enviarMensaje();
            }
        });
    }

    private void enviarMensaje() {
        if (!modelo.estaConectado()) {
            vista.mostrarError("No estás conectado al servidor.\n\nPor favor ejecuta primero ServidorMain.java");
            return;
        }

        String mensaje = vista.obtenerMensajeCompleto();

        if (mensaje == null || mensaje.isEmpty()) {
            vista.mostrarError("Por favor escribe un mensaje");
            return;
        }

        vista.agregarMensaje("Tú: " + mensaje);
        vista.limpiarCampo();

        // Enviar en hilo separado
        new Thread(() -> {
            try {
                modelo.enviarMensaje(mensaje);
                String respuesta = modelo.recibirRespuesta();

                Platform.runLater(() -> {
                    if (respuesta != null) {
                        vista.agregarMensaje("Servidor: " + respuesta);
                    }
                });

            } catch (Exception e) {
                Platform.runLater(() -> {
                    vista.mostrarError("Error al comunicarse con el servidor");
                    vista.actualizarEstado("Desconectado", false);
                });
            }
        }).start();
    }

    public void cerrar() {
        if (modelo.estaConectado()) {
            modelo.enviarMensaje("SALIR");
            modelo.desconectar();
        }
    }
}