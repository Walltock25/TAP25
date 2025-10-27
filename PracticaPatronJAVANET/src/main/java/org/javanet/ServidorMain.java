package org.javanet;

import java.net.*;
import java.io.*;

public class ServidorMain {
    private static final int PUERTO = 9090;

    public static void main(String[] args) {
        System.out.println("=== SERVIDOR INICIADO ===");
        System.out.println("Esperando conexiones en puerto " + PUERTO + "...\n");

        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            while (true) {
                Socket clienteSocket = serverSocket.accept();
                System.out.println("✓ Cliente conectado: " + clienteSocket.getInetAddress());
                new Thread(() -> atenderCliente(clienteSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }

    private static void atenderCliente(Socket socket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String mensaje;
            while ((mensaje = in.readLine()) != null) {
                System.out.println("Recibido: " + mensaje);

                if (mensaje.equalsIgnoreCase("SALIR")) {
                    out.println("Desconectado");
                    break;
                }

                String respuesta = procesarMensaje(mensaje);
                out.println(respuesta);
                System.out.println("Enviado: " + respuesta + "\n");
            }

        } catch (IOException e) {
            System.err.println("Error con cliente: " + e.getMessage());
        } finally {
            try {
                socket.close();
                System.out.println("✗ Cliente desconectado\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String procesarMensaje(String mensaje) {
        if (mensaje.startsWith("MAY:")) {
            return mensaje.substring(4).toUpperCase();
        } else if (mensaje.startsWith("MIN:")) {
            return mensaje.substring(4).toLowerCase();
        } else {
            return "Mensaje recibido: " + mensaje;
        }
    }
}