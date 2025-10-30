package org.example.controller;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import org.example.model.ClienteModel;
import org.example.view.ClienteView;
import org.json.JSONArray;
import org.json.JSONObject;
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
        vista.getBtnHistorial().setOnAction(e -> solicitarHistorial());

        vista.getStage().setOnCloseRequest(e -> {
            cerrarAplicacion();
        });
    }

    private void conectarServidor() {
        try {
            modelo.conectar();
            vista.actualizarEstado("Conectado (localhost:9090)", true);
            vista.agregarMensaje("\nCONECTADO AL SERVIDOR\n");
            vista.agregarMensaje("El servidor convertirá tus mensajes a MAYÚSCULAS");
            vista.agregarMensaje("Usa el botón 'Historial' para ver mensajes anteriores");
            vista.agregarMensaje("Usa el botón 'Salir' o escribe 'SALIR' para cerrar");
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
                    "No se pudo conectar al servidor.\nAsegúrate de que ServidorMain esté ejecutándose.",
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

                    // Verificar si es un JSON (respuesta del servidor)
                    if (mensajeFinal.startsWith("{")) {
                        procesarRespuestaJSON(mensajeFinal);
                    } else {
                        // Mensaje de texto normal
                        Platform.runLater(() -> {
                            vista.agregarMensaje("Servidor: " + mensajeFinal);

                            if (mensajeFinal.contains("finalizada")) {
                                vista.habilitarControles(false);
                                vista.actualizarEstado("Desconectado", false);
                                vista.agregarMensaje("");
                                vista.agregarMensaje("  Sesión terminada");
                            }
                        });
                    }
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

    private void procesarRespuestaJSON(String jsonStr) {
        try {
            JSONObject respuesta = new JSONObject(jsonStr);
            String tipo = respuesta.getString("tipo");

            Platform.runLater(() -> {
                switch (tipo) {
                    case "HISTORIAL":
                        mostrarHistorial(respuesta);
                        break;

                    case "HISTORIAL_REMITENTE":
                        mostrarHistorialRemitente(respuesta);
                        break;

                    case "CONTEO":
                        mostrarConteo(respuesta);
                        break;

                    case "ERROR":
                        String mensajeError = respuesta.getString("mensaje");
                        vista.agregarMensaje("Error: " + mensajeError);
                        vista.mostrarAlerta("Error", mensajeError, Alert.AlertType.ERROR);
                        break;

                    default:
                        vista.agregarMensaje("Respuesta desconocida del servidor");
                }
            });

        } catch (Exception e) {
            Platform.runLater(() -> {
                vista.agregarMensaje("Error procesando respuesta: " + e.getMessage());
            });
        }
    }

    private void mostrarHistorial(JSONObject respuesta) {
        int total = respuesta.getInt("total");
        JSONArray mensajes = respuesta.getJSONArray("mensajes");

        vista.agregarMensaje("\nHISTORIAL DE MENSAJES");
        vista.agregarMensaje("Total de mensajes: " + total+"\n");

        if (total == 0) {
            vista.agregarMensaje("No hay mensajes en el historial");
        } else {
            for (int i = 0; i < mensajes.length(); i++) {
                JSONObject msg = mensajes.getJSONObject(i);
                String fecha = msg.getString("fecha");
                String remitente = msg.getString("remitente");
                String contenido = msg.getString("contenido");

                vista.agregarMensaje(String.format("[%s] %s: %s", fecha, remitente, contenido));
            }
        }
        vista.agregarMensaje("");
    }


    private void mostrarHistorialRemitente(JSONObject respuesta) {
        String remitente = respuesta.getString("remitente");
        int total = respuesta.getInt("total");
        JSONArray mensajes = respuesta.getJSONArray("mensajes");
        vista.agregarMensaje("\nHISTORIAL DE: " + remitente);
        vista.agregarMensaje("Total de mensajes: " + total);

        if (total == 0) {
            vista.agregarMensaje("No hay mensajes de " + remitente);
        } else {
            for (int i = 0; i < mensajes.length(); i++) {
                JSONObject msg = mensajes.getJSONObject(i);
                String fecha = msg.getString("fecha");
                String contenido = msg.getString("contenido");

                vista.agregarMensaje(String.format("[%s] %s", fecha, contenido));
            }
        }
    }

    private void mostrarConteo(JSONObject respuesta) {
        int total = respuesta.getInt("total");
        vista.agregarMensaje("Total de mensajes en la base de datos: " + total);
    }

    private void solicitarHistorial() {
        if (!modelo.estaConectado()) {
            vista.mostrarAlerta(
                    "Sin conexión",
                    "No hay conexión con el servidor.",
                    Alert.AlertType.ERROR
            );
            return;
        }

        vista.agregarMensaje("Solicitando historial al servidor...");
        modelo.solicitarHistorial();
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

    private void desconectar() {
        if (modelo.estaConectado()) {
            modelo.enviarMensaje("SALIR");
            vista.agregarMensaje("Tú: SALIR");
            vista.agregarMensaje("");
            vista.agregarMensaje("Cerrando conexión...");
        }
    }

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