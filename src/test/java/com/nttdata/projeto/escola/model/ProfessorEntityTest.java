package com.nttdata.projeto.escola.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
class ProfessorEntityTest {
    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSetCorrectAtributesToProfessorEntity() {
        DisciplinaEntity disciplinaDouble = mock(DisciplinaEntity.class);
        ProfessorEntity professorTeste = new ProfessorEntity();

        professorTeste.setNif("111111111");
        professorTeste.setNome("NomeTeste");
        professorTeste.setIdade(20);
        professorTeste.setGenero("F");
        professorTeste.setTempoEnsino(10);
        professorTeste.setSalario(BigDecimal.valueOf(200.0));
        professorTeste.setDisciplina(disciplinaDouble);

        assertEquals("111111111", professorTeste.getNif());
        assertEquals("NomeTeste", professorTeste.getNome());
        assertEquals(20, professorTeste.getIdade());
        assertEquals("F", professorTeste.getGenero());
        assertEquals(10, professorTeste.getTempoEnsino());
        assertEquals(BigDecimal.valueOf(200.0), professorTeste.getSalario());
        assertEquals(disciplinaDouble, professorTeste.getDisciplina());
    }
}