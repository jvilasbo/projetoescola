package com.nttdata.projeto.escola.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EscolaridadeRestDtoTest {

    @Test
    void shouldCreateEscolaridadeRestDtoWithCorrectAttributes() {
        EscolaridadeRestDto escolaridadeRestDtoTeste = new EscolaridadeRestDto(6, "1º Ano");

        assertEquals(6, escolaridadeRestDtoTeste.getIdade());
        assertEquals("1º Ano", escolaridadeRestDtoTeste.getAnoEscolar());
    }

    @Test
    void shouldCreateEscolaridadeRestDtoWithEmptyAttributes() {
        EscolaridadeRestDto escolaridadeRestDtoTeste = new EscolaridadeRestDto();

        assertEquals(0, escolaridadeRestDtoTeste.getIdade());
        assertEquals(null, escolaridadeRestDtoTeste.getAnoEscolar());
    }

}