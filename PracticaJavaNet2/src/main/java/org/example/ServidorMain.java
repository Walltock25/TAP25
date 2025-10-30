package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class ServidorMain {
    private static final int PUERTO = 9090;

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║      SERVIDOR DE CHAT - PUERTO 9090    ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Esperando conexión de cliente...\n");

        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {

            while (true) {
                // Aceptar conexión del cliente
                Socket clienteSocket = serverSocket.accept();
                System.out.println("✓ Cliente conectado desde: " + clienteSocket.getInetAddress());

                // Manejar cliente en hilo separado (para múltiples clientes)
                Thread hiloCliente = new Thread(() -> manejarCliente(clienteSocket));
                hiloCliente.start();
            }

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void manejarCliente(Socket clienteSocket) {
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
                System.out.println("Cliente dice: " + mensaje);

                // Si el cliente envía SALIR, terminar la conexión
                if (mensaje.equalsIgnoreCase("SALIR")) {
                    salida.println("Conexión finalizada. ¡Hasta pronto!");
                    System.out.println("Cliente desconectado.\n");
                    break;
                }

                // REGLA: Convertir mensaje a MAYÚSCULAS
                String respuesta = mensaje.toUpperCase();
                salida.println(respuesta);
                System.out.println("Servidor responde: " + respuesta);
            }

            clienteSocket.close();

        } catch (IOException e) {
            System.err.println("Error manejando cliente: " + e.getMessage());
        }
    }
}