package exception;

public class LibroNoDisponibleException extends BibliotecaException {

  public LibroNoDisponibleException() {
    super("El libro no está disponible");
  }
}