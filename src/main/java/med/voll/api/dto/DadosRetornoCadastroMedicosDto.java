
package med.voll.api.dto;

import lombok.Getter;
import lombok.Setter;
import med.voll.api.model.Medico;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Getter
@Setter
public class DadosRetornoCadastroMedicosDto {
        public Medico medico;
        public URI uri;
}