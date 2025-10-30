package org.example.model;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteModel {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private final String host;
    private final int puerto;

    public ClienteModel(String host, int puerto) {
        this.host = host;
        this.puerto = puerto;
    }

    public void conectar() throws IOException {
        socket = new Socket(host, puerto);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void enviarMensaje(String mensaje) {
        if (out != null) {
            out.println(mensaje);
        }
    }

    public void solicitarHistorial() {
        if (out != null) {
            JSONObject peticion = new JSONObject();
            peticion.put("accion", "OBTENER_HISTORIAL");
            out.println(peticion.toString());
        }
    }

    public void solicitarHistorialPorRemitente(String remitente) {
        if (out != null) {
            JSONObject peticion = new JSONObject();
            peticion.put("accion", "OBTENER_HISTORIAL_POR_REMITENTE");
            peticion.put("remitente", remitente);
            out.println(peticion.toString());
        }
    }

    /**
     * Solicita el conteo de mensajes
     */
    public void solicitarConteoMensajes() {
        if (out != null) {
            JSONObject peticion = new JSONObject();
            peticion.put("accion", "CONTAR_MENSAJES");
            out.println(peticion.toString());
        }
    }

    public String recibirMensaje() throws IOException {
        if (in != null) {
            return in.readLine();
        }
        return null;
    }

    public boolean estaConectado() {
        return socket != null && socket.isConnected() && !socket.isClosed();
    }

    public void cerrarConexion() throws IOException {
        if (in != null) in.close();
        if (out != null) out.close();
        if (socket != null) socket.close();
    }

    public String getHost() {
        return host;
    }

    public int getPuerto() {
        return puerto;
    }
}