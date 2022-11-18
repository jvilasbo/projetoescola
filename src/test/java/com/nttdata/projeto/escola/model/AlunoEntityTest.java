package com.nttdata.projeto.escola.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlunoEntityTest {

    @Test
    void shouldSetCorrectAtributesToAlunoEntity() {
        AlunoEntity alunoTeste = new AlunoEntity();

        alunoTeste.setMatricula("00000001");
        alunoTeste.setNome("NomeTeste");
        alunoTeste.setIdade(20);
        alunoTeste.setGenero("F");
        alunoTeste.setNif("111111111");
        alunoTeste.setEscolaridade("1º Ano");

        assertEquals("00000001", alunoTeste.getMatricula());
        assertEquals("NomeTeste", alunoTeste.getNome());
        assertEquals(20, alunoTeste.getIdade());
        assertEquals("F", alunoTeste.getGenero());
        assertEquals("111111111", alunoTeste.getNif());
        assertEquals("1º Ano", alunoTeste.getEscolaridade());
    }
}