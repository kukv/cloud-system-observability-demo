package jp.kukv.order_api.domain.policy.exception;

/** Resource not found error. */
public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(String message) {
    super(message);
  }
}
