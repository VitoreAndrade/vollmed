package med.voll.api.repositorio;

import med.voll.api.dto.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoConsultaRepository {

    void validar(DadosCancelamentoConsulta dados);
}
