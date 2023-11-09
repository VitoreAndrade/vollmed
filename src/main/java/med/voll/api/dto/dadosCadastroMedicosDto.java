package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.model.Especialidade;
import org.hibernate.validator.constraints.UniqueElements;

public record  dadosCadastroMedicosDto(

        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,

        @NotNull
        Long id_especialidade,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        @Valid
        DadosEndereco endereco)        {
}
