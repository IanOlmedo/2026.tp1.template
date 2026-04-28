package repository;

import model.Socio;

public class SocioRepository extends InMemoryRepository<Socio, Integer> {

    @Override
    public void guardar(Socio socio) {
        getStorage().put(socio.getId(), socio);
    }
}