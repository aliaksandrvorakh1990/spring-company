package by.vorakh.alex.spring_company.client;

public class ClientException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ClientException(String message, Throwable ex) {
        super(message, ex);
    }

    public ClientException(String message) {
        super(message);
    }
}