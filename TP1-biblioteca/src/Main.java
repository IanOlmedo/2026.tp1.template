import model.*;
import repository.*;
import service.PrestamoService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Repositorios
        LibroRepository libroRepo = new LibroRepository();
        SocioRepository socioRepo = new SocioRepository();
        PrestamoRepository prestamoRepo = new PrestamoRepository();

        // Service
        PrestamoService prestamoService = new PrestamoService(libroRepo, socioRepo, prestamoRepo);

        int opcion;

        do {
            System.out.println("\n===== BIBLIOTECH =====");
            System.out.println("1. Alta de libro");
            System.out.println("2. Alta de socio");
            System.out.println("3. Realizar préstamo");
            System.out.println("4. Devolver libro");
            System.out.println("5. Ver historial");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            try {
                switch (opcion) {

                    case 1 -> {
                        System.out.print("ISBN: ");
                        String isbn = scanner.nextLine();

                        System.out.print("Título: ");
                        String titulo = scanner.nextLine();

                        System.out.print("Autor: ");
                        String autor = scanner.nextLine();

                        System.out.print("Año: ");
                        int anio = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Categoría: PROGRAMACION, HISTORIA, CIENCIA, LITERATURA, OTROS");
                        Categoria categoria = Categoria.valueOf(scanner.nextLine().toUpperCase());

                        Libro libro = new Libro(isbn, titulo, autor, anio, categoria);
                        libroRepo.guardar(libro);

                        System.out.println("Libro agregado correctamente");
                    }

                    case 2 -> {
                        System.out.print("ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();

                        System.out.print("DNI: ");
                        String dni = scanner.nextLine();

                        System.out.print("Email: ");
                        String email = scanner.nextLine();

                        System.out.println("Tipo: 1-Estudiante / 2-Docente");
                        int tipo = scanner.nextInt();
                        scanner.nextLine();

                        Socio socio = (tipo == 1)
                                ? new SocioEstudiante(id, nombre, dni, email)
                                : new SocioDocente(id, nombre, dni, email);

                        socioRepo.guardar(socio);

                        System.out.println("Socio agregado correctamente");
                    }

                    case 3 -> {
                        System.out.print("ISBN del libro: ");
                        String isbn = scanner.nextLine();

                        System.out.print("ID del socio: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        prestamoService.realizarPrestamo(isbn, id);
                    }

                    case 4 -> {
                        System.out.print("ISBN del libro: ");
                        String isbn = scanner.nextLine();

                        System.out.print("ID del socio: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        prestamoService.devolverLibro(isbn, id);
                    }

                    case 5 -> {
                        System.out.println("\n--- HISTORIAL ---");
                        prestamoService.obtenerHistorial()
                                .forEach(p -> System.out.println(
                                        p.getLibro().titulo() + " - " +
                                                p.getSocio().getNombre() + " - " +
                                                (p.estaDevuelto() ? "Devuelto" : "Pendiente")
                                ));
                    }

                    case 0 -> System.out.println("Saliendo del sistema...");

                    default -> System.out.println("Opción inválida");

                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (opcion != 0);

        scanner.close();
    }
}