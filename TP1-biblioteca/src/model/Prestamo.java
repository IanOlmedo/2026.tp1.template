package model;

import java.time.LocalDate;

public class Prestamo {

    private final Libro libro;
    private final Socio socio;
    private final LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    public Prestamo(Libro libro, Socio socio) {
        this.libro = libro;
        this.socio = socio;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucion = null;
    }

    public Libro getLibro() {
        return libro;
    }

    public Socio getSocio() {
        return socio;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void registrarDevolucion() {
        this.fechaDevolucion = LocalDate.now();
    }

    public boolean estaDevuelto() {
        return fechaDevolucion != null;
    }

    public long calcularDiasRetraso() {
        if (fechaDevolucion == null) return 0;

        long diasPrestamo = java.time.temporal.ChronoUnit.DAYS.between(fechaPrestamo, fechaDevolucion);

        int diasPermitidos = 7; // podés cambiarlo

        return Math.max(0, diasPrestamo - diasPermitidos);
    }
}