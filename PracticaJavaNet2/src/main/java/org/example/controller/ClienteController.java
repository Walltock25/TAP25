package org.example.controller;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import org.example.model.ClienteModel;
import org.example.view.ClienteView;
import java.io.IOException;
public class ClienteController {
    private final ClienteModel modelo;
    private final ClienteView vista;
    private Thread hiloEscucha;

    public ClienteController(ClienteModel modelo, ClienteView vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void inicializar() {
        // Conectar al servidor
        conectarServidor();

        // Configurar eventos de la vista
        vista.getBtnEnviar().setOnAction(e -> enviarMensaje());
        vista.getCampoMensaje().setOnAction(e -> enviarMensaje());
        vista.getBtnLimpiar().setOnAction(e -> vista.limpiarChat());
        vista.getBtnDesconectar().setOnAction(e -> desconectar());

        vista.getStage().setOnCloseRequest(e -> {
            cerrarAplicacion();
        });
    }

    private void conectarServidor() {
        try {
            modelo.conectar();
            vista.actualizarEstado("Conectado (localhost:9090)", true);
            vista.agregarMensaje("╔════════════════════════════════════════╗");
            vista.agregarMensaje("║       CONECTADO AL SERVIDOR            ║");
            vista.agregarMensaje("╚════════════════════════════════════════╝");
            vista.agregarMensaje("🔹 El servidor convertirá tus mensajes a MAYÚSCULAS");
            vista.agregarMensaje("🔹 Usa el botón 'Salir' o escribe 'SALIR' para cerrar");
            vista.agregarMensaje("");

            // Iniciar hilo para escuchar respuestas del servidor
            iniciarHiloEscucha();

        } catch (IOException e) {
            vista.actualizarEstado("Error de conexión", false);
            vista.agregarMensaje("ERROR: No se pudo conectar al servidor");
            vista.agregarMensaje("   Verifica que el servidor esté ejecutándose en el puerto 9090");
            vista.habilitarControles(false);
            vista.mostrarAlerta(
                    "Error de Conexión",
                    "No se pudo conectar al servidor.\nAsegúrate de que ServidorChatMain esté ejecutándose.",
                    Alert.AlertType.ERROR
            );
        }
    }

    private void iniciarHiloEscucha() {
        hiloEscucha = new Thread(() -> {
            try {
                String respuesta;
                while ((respuesta = modelo.recibirMensaje()) != null) {
                    String mensajeFinal = respuesta;
                    Platform.runLater(() -> {
                        vista.agregarMensaje("Servidor: " + mensajeFinal);

                        if (mensajeFinal.contains("finalizada")) {
                            vista.habilitarControles(false);
                            vista.actualizarEstado("Desconectado", false);
                            vista.agregarMensaje("");
                            vista.agregarMensaje("═══ Sesión terminada ═══");
                        }
                    });
                }
            } catch (IOException e) {
                Platform.runLater(() -> {
                    vista.agregarMensaje("");
                    vista.agregarMensaje("Conexión perdida con el servidor");
                    vista.habilitarControles(false);
                    vista.actualizarEstado("Desconectado", false);
                });
            }
        });
        hiloEscucha.setDaemon(true);
        hiloEscucha.start();
    }
    private void enviarMensaje() {
        String mensaje = vista.getCampoMensaje().getText().trim();

        if (mensaje.isEmpty()) {
            vista.mostrarAlerta(
                    "Mensaje vacío",
                    "Por favor escribe un mensaje antes de enviar.",
                    Alert.AlertType.WARNING
            );
            return;
        }

        if (!modelo.estaConectado()) {
            vista.mostrarAlerta(
                    "Sin conexión",
                    "No hay conexión con el servidor.",
                    Alert.AlertType.ERROR
            );
            return;
        }

        // Enviar mensaje al servidor
        modelo.enviarMensaje(mensaje);
        vista.agregarMensaje("Tú: " + mensaje);
        vista.getCampoMensaje().clear();

        // Si es SALIR, deshabilitar controles
        if (mensaje.equalsIgnoreCase("SALIR")) {
            vista.habilitarControles(false);
            vista.agregarMensaje("");
            vista.agregarMensaje("Cerrando conexión...");
        }
    }

    /**
     * Desconecta del servidor
     */
    private void desconectar() {
        if (modelo.estaConectado()) {
            modelo.enviarMensaje("SALIR");
            vista.agregarMensaje("Tú: SALIR");
            vista.agregarMensaje("");
            vista.agregarMensaje("Cerrando conexión...");
        }
    }

    /**
     * Cierra la aplicación correctamente
     */
    private void cerrarAplicacion() {
        try {
            if (modelo.estaConectado()) {
                modelo.enviarMensaje("SALIR");
            }
            modelo.cerrarConexion();
        } catch (IOException e) {
            System.err.println("Error al cerrar: " + e.getMessage());
        }
        Platform.exit();
        System.exit(0);
    }
}