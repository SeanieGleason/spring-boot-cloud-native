package gleason.tech.boot.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static gleason.tech.boot.constants.ErrorConstants.NOT_FOUND_EXCEPTION;

@ControllerAdvice
@Slf4j
public class GlobalResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(Exception ex, ServletWebRequest servletWebRequest) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity(new ErrorResponse(NOT_FOUND_EXCEPTION, HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }
}
