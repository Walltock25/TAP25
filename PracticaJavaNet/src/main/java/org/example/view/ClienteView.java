package org.example.view;

import java.util.Scanner;

public class ClienteView {
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("\n=== MENÚ ===");
        System.out.println("1. Ver IP del servidor");
        System.out.println("2. Ver hora actual del servidor");
        System.out.println("3. Obtener información de API");
        System.out.println("4. Salir");
        System.out.print("Opción (1-4): ");
    }

    public String leerOpcion() {
        return scanner.nextLine();
    }

    public void mostrarRespuesta(String respuesta) {
        System.out.println(">> Respuesta del servidor:");
        System.out.println(respuesta);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}