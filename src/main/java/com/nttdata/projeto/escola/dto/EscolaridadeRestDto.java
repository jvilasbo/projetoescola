package com.nttdata.projeto.escola.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class EscolaridadeRestDto {

    private int idade;
    private String anoEscolar;

    public EscolaridadeRestDto() {
    }

    public EscolaridadeRestDto(int idade, String anoEscolar) {
        this.idade = idade;
        this.anoEscolar = anoEscolar;
    }
}
