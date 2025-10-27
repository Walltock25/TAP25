package org.javanet.Models;

import java.io.*;
import java.net.*;

public class ClienteModel {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public ClienteModel(String host, int puerto) throws IOException {
        socket = new Socket(host, puerto);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void enviarComando(String comando) {
        out.println(comando);
    }

    public String recibirRespuesta() throws IOException {
        return in.readLine();
    }

    public void cerrarConexion() throws IOException {
        socket.close();
    }
}