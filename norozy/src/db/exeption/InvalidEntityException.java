package db.exeption;

public class InvalidEntityException extends RuntimeException {
  public InvalidEntityException(String message) {
    super(message);
  }
}
