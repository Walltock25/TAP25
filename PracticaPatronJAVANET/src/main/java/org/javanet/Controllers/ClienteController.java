package org.javanet.Controllers;

import org.javanet.Models.ClienteModel;
import org.javanet.Views.ClienteView;

public class ClienteController {
    private final ClienteModel modelo;
    private final ClienteView vista;

    public ClienteController(ClienteModel modelo, ClienteView vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        vista.mostrarMensaje("Conectado al servidor.");

        boolean salir = false;

        while (!salir) {
            vista.mostrarMenu();
            String opcion = vista.leerOpcion();

            String comando = "";

            switch (opcion) {
                case "1":
                    comando = "IP";
                    break;
                case "2":
                    comando = "HORA";
                    break;
                case "3":
                    comando = "API";
                    break;
                case "4":
                    comando = "SALIR";
                    break;
                default:
                    vista.mostrarMensaje("Opción inválida.");
                    break;
            }

            modelo.enviarComando(comando);

            if (comando.equals("SALIR")) {
                vista.mostrarMensaje("Cerrando conexión...");
                salir = true;
                break;
            }

            try {
                String respuesta = modelo.recibirRespuesta();
                vista.mostrarRespuesta(respuesta);
            } catch (Exception e) {
                vista.mostrarMensaje("Error leyendo respuesta: " + e.getMessage());
            }
        }

        try {
            modelo.cerrarConexion();
        } catch (Exception e) {
            vista.mostrarMensaje("Error al cerrar conexión.");
        }
    }
}