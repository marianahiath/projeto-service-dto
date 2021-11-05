package br.org.serratec.servicedto.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(EmailException.class) //qual classe o método vai tratar
    public ResponseEntity<Object> handleEmailException(EmailException ex) {

        EmailException emailException = new EmailException(ex.getMessage());

        return ResponseEntity.unprocessableEntity().body(emailException); //código 422 (poderia ser 400)
    }

}
