package exception;

public class EntidadNoEncontradaException extends BibliotecaException {

  public EntidadNoEncontradaException(String entidad) {
    super(entidad + " no encontrado");
  }
}