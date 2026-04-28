package model;

public abstract class Socio {

    protected int id;
    protected String nombre;
    protected String dni;
    protected String email;

    public Socio(int id, String nombre, String dni, String email) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }

    public abstract int getLimitePrestamos();
}