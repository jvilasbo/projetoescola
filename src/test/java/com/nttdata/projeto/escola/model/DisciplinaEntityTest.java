package com.nttdata.projeto.escola.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class DisciplinaEntityTest {

    @Test
    void shouldSetCorrectAtributesToDisciplinaEntity() {
        DisciplinaEntity disciplinaTeste = new DisciplinaEntity();

        disciplinaTeste.setTitulo("TituloTeste");
        disciplinaTeste.setArea("AreaTeste");

        assertEquals("TituloTeste", disciplinaTeste.getTitulo());
        assertEquals("AreaTeste", disciplinaTeste.getArea());
    }

    @Test
    void shouldCreateDisciplinaWithCorrectAttributes() {
        DisciplinaEntity disciplinaTeste = new DisciplinaEntity("titulo", "area");

        assertEquals("titulo", disciplinaTeste.getTitulo());
        assertEquals("area", disciplinaTeste.getArea());
    }
}