package com.nttdata.projeto.escola.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="ALUNO")
@Entity
public class AlunoEntity {

    @Id
    @Column(nullable=false, length = 8, columnDefinition = "CHAR")
    private String matricula;

    @Column(nullable=false)
    private String nome;

    private int idade;

    @Column(nullable=false, columnDefinition = "CHAR")
    private String genero;

    @Column(name = "nif", nullable = false, unique = true, length = 9, columnDefinition = "CHAR")
    private String nif;

    private String escolaridade;
}
