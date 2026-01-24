package jp.kukv.user_api.domain.policy.exception;

/** リソースが存在しないエラー */
public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(String message) {
    super(message);
  }
}
