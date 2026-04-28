package service;

import model.Libro;
import model.Prestamo;
import model.Socio;
import repository.LibroRepository;
import repository.PrestamoRepository;
import repository.SocioRepository;

import java.util.List;

public class PrestamoService {

    private final LibroRepository libroRepo;
    private final SocioRepository socioRepo;
    private final PrestamoRepository prestamoRepo;

    public PrestamoService(LibroRepository libroRepo,
                           SocioRepository socioRepo,
                           PrestamoRepository prestamoRepo) {
        this.libroRepo = libroRepo;
        this.socioRepo = socioRepo;
        this.prestamoRepo = prestamoRepo;
    }

    public void realizarPrestamo(String isbn, int socioId) {

        Libro libro = libroRepo.buscarPorId(isbn)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        Socio socio = socioRepo.buscarPorId(socioId)
                .orElseThrow(() -> new RuntimeException("Socio no encontrado"));

        // Validar si el libro ya está prestado
        boolean libroPrestado = prestamoRepo.buscarTodos().stream()
                .anyMatch(p -> p.getLibro().isbn().equals(isbn) && !p.estaDevuelto());

        if (libroPrestado) {
            throw new RuntimeException("Libro no disponible");
        }

        // Validar límite de préstamos
        long prestamosActivos = prestamoRepo.buscarTodos().stream()
                .filter(p -> p.getSocio().getId() == socioId && !p.estaDevuelto())
                .count();

        if (prestamosActivos >= socio.getLimitePrestamos()) {
            throw new RuntimeException("Límite de préstamos alcanzado");
        }

        // Crear préstamo
        Prestamo prestamo = new Prestamo(libro, socio);
        prestamoRepo.guardar(prestamo);

        System.out.println("Préstamo realizado con éxito");
    }
}