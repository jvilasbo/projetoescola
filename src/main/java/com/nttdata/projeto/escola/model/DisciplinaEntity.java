package com.nttdata.projeto.escola.model;

import lombok.*;
import javax.persistence.*;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="DISCIPLINA")
@Entity
public class DisciplinaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, columnDefinition = "INT")
    private int id;

    @Column(nullable = false, columnDefinition = "VARCHAR")
    private String titulo;

    @Column(nullable = false, columnDefinition = "VARCHAR")
    private String area;

    public DisciplinaEntity(String titulo, String area) {
        this.titulo = titulo;
        this.area = area;
    }
}
