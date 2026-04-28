package model;

public class SocioDocente extends Socio {

    public SocioDocente(int id, String nombre, String dni, String email) {
        super(id, nombre, dni, email);
    }

    @Override
    public int getLimitePrestamos() {
        return 5;
    }
}