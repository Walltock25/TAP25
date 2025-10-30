package org.example.model;

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