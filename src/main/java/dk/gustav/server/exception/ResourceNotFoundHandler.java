package dk.gustav.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ResourceNotFoundHandler {
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<?> handleApiRequestException(ResourceNotFoundException exception, HttpServletRequest request) {

        ExceptionDetail exceptionDetail = new ExceptionDetail(404, exception.getMessage(), request.getServletPath());

        return new ResponseEntity<>(exceptionDetail, HttpStatus.NOT_FOUND);
    }
}
