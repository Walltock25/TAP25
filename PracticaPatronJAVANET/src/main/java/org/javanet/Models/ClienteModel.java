package org.javanet.Models;

import java.io.*;
import java.net.*;

public class ClienteModel {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public void conectar(String host, int puerto) throws IOException {
        socket = new Socket(host, puerto);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void enviarMensaje(String mensaje) {
        if (out != null) {
            out.println(mensaje);
        }
    }

    public String recibirRespuesta() throws IOException {
        return in != null ? in.readLine() : null;
    }

    public boolean estaConectado() {
        return socket != null && socket.isConnected() && !socket.isClosed();
    }

    public void desconectar() {
        try {
            if (out != null) out.close();
            if (in != null) in.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}