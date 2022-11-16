package com.nttdata.projeto.escola.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="PROFESSOR")
@Entity
public class ProfessorEntity {

    @Id
    @Column(nullable = false, length = 9, columnDefinition = "CHAR")
    private String nif;

    @Column(nullable = false, columnDefinition = "VARCHAR")
    private String nome;

    private int idade;

    @Column(length = 1, columnDefinition = "CHAR")
    private String genero;

    @Column(name="tempo_ensino")
    private int tempoEnsino;

    private BigDecimal salario;

    @ManyToOne
    @JoinColumn(name = "disciplina_id", nullable = false)
    private DisciplinaEntity disciplina;

    //criar estado do prof - disponivel indisponivel
    /*@Enumerated(EnumType.STRING)
    private EstadoProfessor estado;*/

}
