package med.voll.api.infra;

import org.springframework.web.bind.annotation.RestControllerAdvice;


public class ValidacaoException extends RuntimeException{

        public ValidacaoException(String exception) {
            super(exception);
        }
    }

