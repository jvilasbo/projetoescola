package com.nttdata.projeto.escola.dto;

import lombok.Getter;

@Getter
public class DisciplinaRestDto {

    private String titulo;
    private String area;

    protected DisciplinaRestDto() {
    }

    public DisciplinaRestDto(String titulo, String area) {
        this.titulo = titulo;
        this.area = area;
    }
}
