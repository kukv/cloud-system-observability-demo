package jp.kukv.product_api.presentation;

import io.swagger.v3.oas.annotations.Hidden;
import jp.kukv.product_api.domain.policy.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Hidden
@RestControllerAdvice
class ExceptionAdvice {

  Logger log = LoggerFactory.getLogger(ExceptionAdvice.class);

  @ExceptionHandler({
    IllegalArgumentException.class,
    IllegalStateException.class,
    HttpMessageNotReadableException.class,
    MethodArgumentTypeMismatchException.class,
    MissingServletRequestParameterException.class
  })
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ProblemDetail badRequest(Exception exception) {
    log.warn(exception.getLocalizedMessage());
    return ProblemDetail.forStatusAndDetail(
        HttpStatus.BAD_REQUEST, exception.getLocalizedMessage());
  }

  @ExceptionHandler({ResourceNotFoundException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  ProblemDetail notFound(Exception exception) {
    log.warn(exception.getLocalizedMessage());
    return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getLocalizedMessage());
  }

  @ExceptionHandler({Exception.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  ProblemDetail internalServerError(Exception exception) {
    log.error(exception.getLocalizedMessage(), exception);
    return ProblemDetail.forStatusAndDetail(
        HttpStatus.INTERNAL_SERVER_ERROR, exception.getLocalizedMessage());
  }

  ExceptionAdvice() {}
}
