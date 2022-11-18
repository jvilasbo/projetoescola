package com.nttdata.projeto.escola.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DisciplinaRestDtoTest {

    @Test
    void shouldCreateDisciplinaRestDtoWithCorrectAttributes() {
        DisciplinaRestDto disciplinaRestDtoTeste = new DisciplinaRestDto("titulo", "area");

        assertEquals("titulo", disciplinaRestDtoTeste.getTitulo());
        assertEquals("area", disciplinaRestDtoTeste.getArea());
    }

    @Test
    void shouldCreateDisciplinaRestDtoWithEmptyAttributes() {
        DisciplinaRestDto disciplinaRestDtoTeste = new DisciplinaRestDto();

        assertNull(disciplinaRestDtoTeste.getTitulo());
        assertNull(disciplinaRestDtoTeste.getArea());
    }
}