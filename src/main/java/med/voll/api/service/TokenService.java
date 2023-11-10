package med.voll.api.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import med.voll.api.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

        public String gerarToken(Usuario usuario){

            try {
              var algoritimo = Algorithm.HMAC256(secret);
               return JWT.create()
                        .withIssuer("API Voll.med")
                       // para saber qual usuario esta relacionado ao token,
                       //antes passa como parametro da cladsse quem vai ser o responsavel
                       .withSubject(usuario.getLogin())
                       .withExpiresAt(dataExpiracao())
                        .sign(algoritimo);
            } catch (JWTCreationException exception){
                throw new RuntimeException("Erro ao gerar o token jwt", exception);
            }
        }
// para devolver o tempo de expiração do token
    private Instant dataExpiracao() {
            return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    //plusHours(2) serve para definir o limite de hr
    //toInstant para converter em objeto INSTANT
    // dentro do toInstant passa o ZoneOffset.off () para definir o fuso horario

        }

}

