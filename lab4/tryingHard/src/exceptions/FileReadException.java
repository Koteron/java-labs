package exceptions;
public class FileReadException extends Exception {
    public FileReadException() {
        super();
    }

    public FileReadException(String message) {
        super(message);
    }
}