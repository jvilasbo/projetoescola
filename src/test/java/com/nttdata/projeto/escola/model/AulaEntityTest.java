package com.nttdata.projeto.escola.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
class AulaEntityTest {

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSetCorrectAtributesToAulaEntity() {
        ProfessorEntity professorDouble = mock(ProfessorEntity.class);
        AlunoEntity alunoDouble = mock(AlunoEntity.class);
        Date myDate = new Date(2022, 9, 24);

        AulaEntity aulaTeste = new AulaEntity();

        aulaTeste.setDataAula(myDate);
        aulaTeste.setDuracao(BigDecimal.valueOf(45.0));
        aulaTeste.setValor(BigDecimal.valueOf(35));
        aulaTeste.setProfessor(professorDouble);
        aulaTeste.setAluno(alunoDouble);

        assertEquals(myDate, aulaTeste.getDataAula());
        assertEquals(BigDecimal.valueOf(45.0), aulaTeste.getDuracao());
        assertEquals(BigDecimal.valueOf(35), aulaTeste.getValor());
        assertEquals(professorDouble, aulaTeste.getProfessor());
        assertEquals(alunoDouble, aulaTeste.getAluno());
    }

    @Test
    void shouldCreateAulaWithCorrectAttributes() {
        ProfessorEntity professorDouble = mock(ProfessorEntity.class);
        AlunoEntity alunoDouble = mock(AlunoEntity.class);
        Date myDate = new Date(2022, 9, 24);

        AulaEntity aulaTeste = new AulaEntity(myDate, BigDecimal.valueOf(45.0), BigDecimal.valueOf(35), professorDouble, alunoDouble);
        System.out.println(aulaTeste.toString());
        assertEquals(myDate, aulaTeste.getDataAula());
        assertEquals(BigDecimal.valueOf(45.0), aulaTeste.getDuracao());
        assertEquals(BigDecimal.valueOf(35), aulaTeste.getValor());
        assertEquals(professorDouble, aulaTeste.getProfessor());
        assertEquals(alunoDouble, aulaTeste.getAluno());
    }

}