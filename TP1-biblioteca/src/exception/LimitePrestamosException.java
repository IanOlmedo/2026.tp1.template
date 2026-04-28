package exception;

public class LimitePrestamosException extends BibliotecaException {

    public LimitePrestamosException() {
        super("El socio alcanzó el límite de préstamos");
    }
}