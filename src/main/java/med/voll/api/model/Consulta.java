package med.voll.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.MotivoCancelamento;

import java.time.LocalDateTime;

@Table(name = "consultas")
    @Entity(name = "Consulta")
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(of = "id")
    public class Consulta {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "id_medico")
        private Medico medico;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "id_paciente")
        private Paciente paciente;
        private LocalDateTime data;

        @Column(name = "motivo_cancelamento")
        @Enumerated(EnumType.STRING)
        private MotivoCancelamento motivoCancelamento;

    public Consulta(Object o, Medico medico, Paciente paciente, LocalDateTime data) {
    }

    public void cancelar (MotivoCancelamento motivo){
            this.motivoCancelamento = motivo;
        }

    }

