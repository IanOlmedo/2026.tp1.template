package repository;

import model.Libro;
import model.Categoria;

import java.util.List;
import java.util.stream.Collectors;

public class LibroRepository extends InMemoryRepository<Libro, String> {

    @Override
    public void guardar(Libro libro) {
        getStorage().put(libro.isbn(), libro);
    }

    public List<Libro> buscarPorTitulo(String titulo) {
        return getStorage().values().stream()
                .filter(libro -> libro.titulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Libro> buscarPorAutor(String autor) {
        return getStorage().values().stream()
                .filter(libro -> libro.autor().toLowerCase().contains(autor.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Libro> buscarPorCategoria(Categoria categoria) {
        return getStorage().values().stream()
                .filter(libro -> libro.categoria() == categoria)
                .collect(Collectors.toList());
    }
}