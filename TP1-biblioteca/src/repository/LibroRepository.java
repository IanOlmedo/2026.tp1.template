package repository;
import model.Libro;

public class LibroRepository extends InMemoryRepository<Libro, String> {

    @Override
    public void guardar(Libro libro) {
        getStorage().put(libro.isbn(), libro);
    }
}