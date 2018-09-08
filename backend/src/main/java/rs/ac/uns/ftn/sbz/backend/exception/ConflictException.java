package rs.ac.uns.ftn.sbz.backend.exception;

public class ConflictException extends RuntimeException
{
    public ConflictException() { }

    public ConflictException(String message) {
        super(message);
    }
}
