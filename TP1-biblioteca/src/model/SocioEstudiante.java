package model;

public class SocioEstudiante extends Socio {

    public SocioEstudiante(int id, String nombre, String dni, String email) {
        super(id, nombre, dni, email);
    }

    @Override
    public int getLimitePrestamos() {
        return 3;
    }
}