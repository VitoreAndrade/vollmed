package med.voll.api.repositorio;

import med.voll.api.model.Consultorio;
import med.voll.api.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultorioRepository extends JpaRepository<Consultorio, Long> {
    Page<Consultorio> findAllByAtivoTrue(Pageable paginacao);

}
