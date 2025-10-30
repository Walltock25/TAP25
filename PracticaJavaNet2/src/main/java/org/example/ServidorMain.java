package org.example;
import org.example.dao.DatabaseManager;
import org.example.model.Mensaje;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.List;

public class ServidorMain {
    private static final int PUERTO = 9090;
    private static DatabaseManager dbManager;

    public static void main(String[] args) {
        System.out.println("\nSERVIDOR DE CHAT - PUERTO 9090\n");

        // Inicializar base de datos
        dbManager = new DatabaseManager();
        System.out.println("Esperando conexión de cliente...\n");

        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {

            while (true) {
                // Aceptar conexión del cliente
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde: " + clienteSocket.getInetAddress());

                // Manejar cliente en hilo separado
                Thread hiloCliente = new Thread(() -> manejarCliente(clienteSocket));
                hiloCliente.start();
            }

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (dbManager != null) {
                dbManager.cerrarConexion();
            }
        }
    }

    private static void manejarCliente(Socket clienteSocket) {
        String clienteId = clienteSocket.getInetAddress().toString();

        try (
                BufferedReader entrada = new BufferedReader(
                        new InputStreamReader(clienteSocket.getInputStream())
                );
                PrintWriter salida = new PrintWriter(
                        clienteSocket.getOutputStream(), true
                )
        ) {
            String mensaje;

            while ((mensaje = entrada.readLine()) != null) {
                System.out.println("Cliente " + clienteId + " dice: " + mensaje);

                // Verificar si es una petición JSON
                if (mensaje.startsWith("{")) {
                    procesarPeticionJSON(mensaje, salida, clienteId);
                } else {
                    // Mensaje normal
                    if (mensaje.equalsIgnoreCase("SALIR")) {
                        salida.println("Conexión finalizada. ¡Hasta pronto!");
                        System.out.println("Cliente " + clienteId + " desconectado.\n");
                        break;
                    }

                    // Guardar mensaje en BD
                    Mensaje msg = new Mensaje("Cliente", mensaje);
                    dbManager.guardarMensaje(msg);

                    // Convertir a mayúsculas y responder
                    String respuesta = mensaje.toUpperCase();

                    // Guardar respuesta del servidor
                    Mensaje respuestaMsg = new Mensaje("Servidor", respuesta);
                    dbManager.guardarMensaje(respuestaMsg);

                    salida.println(respuesta);
                    System.out.println("Servidor responde: " + respuesta);
                }
            }

            clienteSocket.close();

        } catch (IOException e) {
            System.err.println("Error manejando cliente: " + e.getMessage());
        }
    }

    private static void procesarPeticionJSON(String jsonStr, PrintWriter salida, String clienteId) {
        try {
            JSONObject peticion = new JSONObject(jsonStr);
            String accion = peticion.getString("accion");

            System.out.println("Cliente " + clienteId + " solicita: " + accion);

            switch (accion) {
                case "OBTENER_HISTORIAL":
                    enviarHistorial(salida);
                    break;

                case "OBTENER_HISTORIAL_POR_REMITENTE":
                    String remitente = peticion.getString("remitente");
                    enviarHistorialPorRemitente(salida, remitente);
                    break;

                case "CONTAR_MENSAJES":
                    enviarConteoMensajes(salida);
                    break;

                default:
                    JSONObject error = new JSONObject();
                    error.put("tipo", "ERROR");
                    error.put("mensaje", "Acción no reconocida: " + accion);
                    salida.println(error.toString());
            }

        } catch (Exception e) {
            System.err.println("Error procesando JSON: " + e.getMessage());
            JSONObject error = new JSONObject();
            error.put("tipo", "ERROR");
            error.put("mensaje", "Error procesando petición: " + e.getMessage());
            salida.println(error.toString());
        }
    }

    private static void enviarHistorial(PrintWriter salida) {
        List<Mensaje> mensajes = dbManager.obtenerHistorialMensajes();

        JSONObject respuesta = new JSONObject();
        respuesta.put("tipo", "HISTORIAL");
        respuesta.put("total", mensajes.size());

        JSONArray arrayMensajes = new JSONArray();
        for (Mensaje msg : mensajes) {
            JSONObject jsonMsg = new JSONObject();
            jsonMsg.put("id", msg.getId());
            jsonMsg.put("remitente", msg.getRemitente());
            jsonMsg.put("contenido", msg.getContenido());
            jsonMsg.put("fecha", msg.getFechaFormateada());
            arrayMensajes.put(jsonMsg);
        }

        respuesta.put("mensajes", arrayMensajes);
        salida.println(respuesta.toString());

        System.out.println("Historial enviado: " + mensajes.size() + " mensajes");
    }

    private static void enviarHistorialPorRemitente(PrintWriter salida, String remitente) {
        List<Mensaje> mensajes = dbManager.obtenerMensajesPorRemitente(remitente);

        JSONObject respuesta = new JSONObject();
        respuesta.put("tipo", "HISTORIAL_REMITENTE");
        respuesta.put("remitente", remitente);
        respuesta.put("total", mensajes.size());

        JSONArray arrayMensajes = new JSONArray();
        for (Mensaje msg : mensajes) {
            JSONObject jsonMsg = new JSONObject();
            jsonMsg.put("id", msg.getId());
            jsonMsg.put("contenido", msg.getContenido());
            jsonMsg.put("fecha", msg.getFechaFormateada());
            arrayMensajes.put(jsonMsg);
        }

        respuesta.put("mensajes", arrayMensajes);
        salida.println(respuesta.toString());

        System.out.println("Historial de '" + remitente + "' enviado: " + mensajes.size() + " mensajes");
    }

    private static void enviarConteoMensajes(PrintWriter salida) {
        int total = dbManager.contarMensajes();

        JSONObject respuesta = new JSONObject();
        respuesta.put("tipo", "CONTEO");
        respuesta.put("total", total);
        salida.println(respuesta.toString());

        System.out.println("Conteo enviado: " + total + " mensajes");
    }
}