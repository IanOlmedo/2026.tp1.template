package repository;

import model.Prestamo;

public class PrestamoRepository extends InMemoryRepository<Prestamo, Integer> {

    private int idActual = 1;

    @Override
    public void guardar(Prestamo prestamo) {
        getStorage().put(idActual++, prestamo);
    }
}