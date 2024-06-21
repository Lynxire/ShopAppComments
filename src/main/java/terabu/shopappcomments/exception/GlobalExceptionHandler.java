package terabu.shopappcomments.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> validationList = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        log.error(validationList.toString(), ex);
        return new ResponseEntity<>(validationList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleResourceException(HttpClientErrorException ex) {
        log.error(ex.getMessage(), ex);
        HttpStatusCode statusCode = ex.getStatusCode();
        return ResponseEntity.status(statusCode).body(ex.getStatusText());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<String> handleResourceException(HttpServerErrorException ex) {
        log.error(ex.getMessage(), ex);
        HttpStatusCode statusCode = ex.getStatusCode();
        return ResponseEntity.status(statusCode).body(ex.getStatusText());
    }



}
