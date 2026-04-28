import model.*;
import repository.*;
import service.PrestamoService;

public class Main {
    public static void main(String[] args) {

        //  Repositorios
        LibroRepository libroRepo = new LibroRepository();
        SocioRepository socioRepo = new SocioRepository();
        PrestamoRepository prestamoRepo = new PrestamoRepository();

        //  Crear datos
        Libro libro1 = new Libro("123", "Clean Code", "Robert C. Martin", 2008, Categoria.PROGRAMACION);
        Libro libro2 = new Libro("456", "El Principito", "Saint-Exupéry", 1943, Categoria.LITERATURA);

        Socio socio1 = new SocioEstudiante(1, "Juan", "12345678", "juan@mail.com");
        Socio socio2 = new SocioDocente(2, "Ana", "87654321", "ana@mail.com");

        // 🔹 Guardar datos
        libroRepo.guardar(libro1);
        libroRepo.guardar(libro2);

        socioRepo.guardar(socio1);
        socioRepo.guardar(socio2);

        // 🔹 Crear service
        PrestamoService prestamoService = new PrestamoService(libroRepo, socioRepo, prestamoRepo);

        // 🔹 Probar préstamo
        System.out.println("\n--- PRUEBA DE PRÉSTAMO ---");
        prestamoService.realizarPrestamo("123", 1);

        // 🔹 Intentar repetir (debería fallar)
        try {
            prestamoService.realizarPrestamo("123", 1);
        } catch (Exception e) {
            System.out.println("Error esperado: " + e.getMessage());
        }

        // 🔹 Buscar libro
        System.out.println("\nBuscar libro por ISBN 123:");
        libroRepo.buscarPorId("123")
                .ifPresentOrElse(
                        libro -> System.out.println("Encontrado: " + libro.titulo()),
                        () -> System.out.println("No encontrado")
                );

        // 🔹 Listar libros
        System.out.println("\nLista de libros:");
        libroRepo.buscarTodos().forEach(libro ->
                System.out.println(libro.titulo())
        );

        // 🔹 Buscar socio
        System.out.println("\nBuscar socio ID 1:");
        socioRepo.buscarPorId(1)
                .ifPresent(s -> System.out.println("Socio: " + s.getNombre()));

        // 🔹 Límites (polimorfismo)
        System.out.println("\nLímites:");
        System.out.println(socio1.getNombre() + ": " + socio1.getLimitePrestamos());
        System.out.println(socio2.getNombre() + ": " + socio2.getLimitePrestamos());

        // 🔹 Búsquedas
        System.out.println("\nBuscar por título 'clean':");
        libroRepo.buscarPorTitulo("clean")
                .forEach(l -> System.out.println(l.titulo()));

        System.out.println("\nBuscar por autor 'martin':");
        libroRepo.buscarPorAutor("martin")
                .forEach(l -> System.out.println(l.titulo()));

        System.out.println("\nBuscar por categoría PROGRAMACION:");
        libroRepo.buscarPorCategoria(Categoria.PROGRAMACION)
                .forEach(l -> System.out.println(l.titulo()));

        System.out.println("\n--- DEVOLUCIÓN ---");

        prestamoService.devolverLibro("123", 1);

        System.out.println("\n--- HISTORIAL ---");

        prestamoService.obtenerHistorial()
                .forEach(p -> System.out.println(
                        p.getLibro().titulo() + " - " +
                                p.getSocio().getNombre() + " - " +
                                (p.estaDevuelto() ? "Devuelto" : "Pendiente")
                ));
    }
}