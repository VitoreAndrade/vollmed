package med.voll.api.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.http.HttpServletRequest;
import med.voll.api.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Locale;

@Service
public class TokenService {

    private final String issuer = "API Voll.med ";

    @Autowired
    MessageSource messageSource;
    @Autowired
    HttpServletRequest request;
    @Value("${api.security.token.secret}")
    private String secret;



    public String gerarToken(Usuario usuario) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create().withIssuer(issuer).
                    withSubject(usuario.getLogin()).
                    withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao enviar o token!");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException e) {
            e.printStackTrace();
          throw new RuntimeException(("Token inválido ou expirado!"));
        }
    }

    private Date dataExpiracao() throws ParseException {
    LocalDateTime localDateTime = LocalDateTime.now();
    localDateTime = localDateTime.plusHours(2);
    Date dataExpiracao = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

    return dataExpiracao;
        //        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
//        //plusHours(2) serve para definir o limite de hr
//        //toInstant para converter em objeto INSTANT
//        // dentro do toInstant passa o ZoneOffset.off () para definir o fuso horario
    }



}