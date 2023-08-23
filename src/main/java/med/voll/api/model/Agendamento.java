package med.voll.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.dto.DadosCadastroAgendamentoDto;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "agendamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_medico")
    private Medico medico;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_consultorio")
    private Consultorio consultorio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_especialidade")
    private Especialidade especialidades;

    private LocalDateTime dataHoraAgendamento;


 @ManyToMany(mappedBy = "agendamentos")
 List<Consultorio>consultorios;

public Agendamento (DadosCadastroAgendamentoDto dados){


}

    public Agendamento(Consultorio consulta, Medico medicos, Paciente pacientes, LocalDateTime dataHoraAgendamento, Especialidade especialidade) {
        this.consultorio = consulta;
        this.medico = medicos;
        this.paciente = pacientes;
        this.dataHoraAgendamento = dataHoraAgendamento;
        this.especialidades = especialidade;




    }
}
