package com.nttdata.projeto.escola.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AulaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, columnDefinition = "INT")
    private int id;

    @Column(name="data_aula")
    private LocalDate dataAula;

    private BigDecimal duracao;

    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name="professor_nif")
    private ProfessorEntity professor;


    @ManyToOne
    @JoinColumn(name="aluno_matricula")
    private AlunoEntity aluno;



    /*private BigDecimal media;
    private String resultado;*/
}
