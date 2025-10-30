package org.example.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mensaje {
    private int id;
    private String remitente;
    private String contenido;
    private LocalDateTime fecha;

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Mensaje(String remitente, String contenido) {
        this.remitente = remitente;
        this.contenido = contenido;
        this.fecha = LocalDateTime.now();
    }

    public Mensaje(int id, String remitente, String contenido, LocalDateTime fecha) {
        this.id = id;
        this.remitente = remitente;
        this.contenido = contenido;
        this.fecha = fecha;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getFechaFormateada() {
        return fecha.format(FORMATTER);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s: %s",
                getFechaFormateada(), remitente, contenido);
    }
}