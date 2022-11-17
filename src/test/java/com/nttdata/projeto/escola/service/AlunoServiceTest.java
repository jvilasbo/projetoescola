package com.nttdata.projeto.escola.service;

import com.nttdata.projeto.escola.model.AlunoEntity;
import com.nttdata.projeto.escola.repository.IAlunoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AlunoServiceTest {

    @MockBean
    IAlunoRepository alunoRepository;

    @InjectMocks
    AlunoService alunoService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("Teste Save Aluno")
    void shouldSaveNewAlunoWithCorrectAttributes() {
        //Arrange
        AlunoEntity alunoEntityDouble = mock(AlunoEntity.class);
        //Act
        alunoService.save(alunoEntityDouble);
        //Assert
        verify(alunoRepository, times(1)).save(alunoEntityDouble);
    }


    @Test
    @DisplayName("Teste Get Aluno by Matrícula(id)")
    void shouldGetAlunoByIdWithCorrectAttributes() {
        //Arrange
        AlunoEntity alunoDouble = mock(AlunoEntity.class);
        String generatedId = new Random().toString();

        when(alunoDouble.getMatricula()).thenReturn(generatedId);
        when(alunoRepository.findById(generatedId)).thenReturn(Optional.of(alunoDouble));
        //Act
        AlunoEntity alunoResult = alunoService.getAluno(generatedId);
        //Assert
        assertEquals(alunoDouble, alunoResult);
    }

    @Test
    @DisplayName("Teste Get Todos os Alunos ")
    void shouldGetAllAlunosWithCorrectAttributes() {
        //Arrange
        AlunoEntity alunoDouble1 = mock(AlunoEntity.class);
        AlunoEntity alunoDouble2 = mock(AlunoEntity.class);

        List<AlunoEntity> listAlunosDouble = new ArrayList<>();
        listAlunosDouble.add(alunoDouble1);
        listAlunosDouble.add(alunoDouble2);

        when(alunoRepository.findAll()).thenReturn(listAlunosDouble);

        //Act
        List<AlunoEntity> listAlunosResult = alunoService.listAll();

        //Assert
        assertEquals(listAlunosDouble, listAlunosResult);
    }

    @Test
    @DisplayName("Teste apagar Aluno")
    void shouldDeleteAlunoByIdAndCallRepositoryMethodOneTimeAndReturnTrue() {
        //Arrange
        String generatedId = new Random().toString();

        //Act
        boolean result = alunoService.deleteById(generatedId);

        //Assert
        verify(alunoRepository, times(1)).deleteById(generatedId);
        assertTrue(result);
    }

    @Test
    @DisplayName("Teste Apagar Aluno com Erro")
    void shouldDeleteAlunoByIdAndCallRepositoryMethodOneTimeAndReturnFalseIfException() {
        //Arrange
        String generatedId = new Random().toString();

        doThrow(new RuntimeException()).when(alunoRepository).deleteById(generatedId);

        //Act
        boolean result = alunoService.deleteById(generatedId);

        //Assert
        verify(alunoRepository, times(1)).deleteById(generatedId);
        assertFalse(result);
    }
}