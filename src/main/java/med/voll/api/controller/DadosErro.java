package med.voll.api.controller;

import lombok.extern.slf4j.Slf4j;
import med.voll.api.dto.DefaultDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


public class DadosErro extends Exception {
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity Exception(Exception exc) {
//        System.out.println("caiu no erro");
//        DefaultDto erro = new DefaultDto(HttpStatus.BAD_GATEWAY.value(), "Erro ao iniciar sua aplicação");
//        return new ResponseEntity("error", HttpStatus.BAD_GATEWAY);
//    }
    public DadosErro (String messege){
        super(messege);
    }

}
