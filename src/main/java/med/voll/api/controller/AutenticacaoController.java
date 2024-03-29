package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.DadosAutenticacao;

import med.voll.api.dto.DadosTokenJWT;
import med.voll.api.model.Usuario;
import med.voll.api.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="/login", method = RequestMethod.POST)
@CrossOrigin(origins = "http://localhost:8080/login")
public class AutenticacaoController {


    // DISPARA O PROCESSO DE AUTENTICAÇÃO
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efeturarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var authenticationtoken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        // representa o usuario logado
        var authentication = manager.authenticate(authenticationtoken);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}

