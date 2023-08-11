package med.voll.api.repositorio;

import med.voll.api.model.Consultorio;
import med.voll.api.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConsultorioRepository extends JpaRepository<Consultorio, Long> {
    Page<Consultorio> findAllByAtivoTrue(Pageable paginacao);


//    //delete from medico_atende where medicoId = {variavel} and consultiroId = {vairiavel}
//    @Query("delete " +
//            "FROM Consultorio " +
//            " WHERE  c.medicoId = :medicoId " +
//            "and consultorioId = :consultorioID")
//    Page<Consultorio> deleteMedicoDoConsultorio(String medicoId, Pageable consultorioID);
//
}
