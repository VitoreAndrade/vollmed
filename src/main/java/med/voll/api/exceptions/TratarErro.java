package med.voll.api.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratarErro {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity <?> trataErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity <?> trataErro400(MethodArgumentNotValidException ex){
        var erro = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erro.stream().map(DadosErroValidacao::new).toList());
    }

    private record DadosErroValidacao(String campo, String mensagem){
        public DadosErroValidacao (FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
