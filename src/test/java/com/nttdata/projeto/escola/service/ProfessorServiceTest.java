package com.nttdata.projeto.escola.service;

import com.nttdata.projeto.escola.model.AlunoEntity;
import com.nttdata.projeto.escola.model.ProfessorEntity;
import com.nttdata.projeto.escola.repository.IDisciplinaRepository;
import com.nttdata.projeto.escola.repository.IProfessorRepository;
import org.junit.jupiter.api.BeforeEach;
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
class ProfessorServiceTest {
    @MockBean
    IProfessorRepository professorRepository;

    @InjectMocks
    ProfessorService professorService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveNewProfessorWithCorrectAttributes() {
        //Arrange
        ProfessorEntity professorEntityDouble = mock(ProfessorEntity.class);
        //Act
        professorService.save(professorEntityDouble);
        //Assert
        verify(professorRepository, times(1)).save(professorEntityDouble);
    }

    @Test
    void shouldGetProfessorByIdWithCorrectAttributes() {
        //Arrange
        ProfessorEntity professorDouble = mock(ProfessorEntity.class);
        String generatedId = new Random().toString();

        when(professorDouble.getNif()).thenReturn(generatedId);
        when(professorRepository.findById(generatedId)).thenReturn(Optional.of(professorDouble));
        //Act
        ProfessorEntity professorResult = professorService.getProfessor(generatedId);
        //Assert
        assertEquals(professorDouble, professorResult);

    }

    @Test
    void shouldGetAllProfessoresWithCorrectAttributes() {
        //Arrange
        ProfessorEntity professorDouble1 = mock(ProfessorEntity.class);
        ProfessorEntity professorDouble2 = mock(ProfessorEntity.class);

        List<ProfessorEntity> listProfessoresDouble = new ArrayList<>();
        listProfessoresDouble.add(professorDouble1);
        listProfessoresDouble.add(professorDouble2);

        when(professorRepository.findAll()).thenReturn(listProfessoresDouble);

        //Act
        List<ProfessorEntity> listProfessoresResult = professorService.listAll();

        //Assert
        assertEquals(listProfessoresDouble, listProfessoresResult);

    }

    @Test
    void shouldDeleteProfessorByIdAndCallRepositoryMethodOneTimeAndReturnTrue() {
        //Arrange
        String generatedId = new Random().toString();

        //Act
        boolean result = professorService.deleteById(generatedId);

        //Assert
        verify(professorRepository, times(1)).deleteById(generatedId);
        assertTrue(result);
    }

    @Test
    void shouldDeleteProfessorByIdAndCallRepositoryMethodOneTimeAndReturnFalseIfException() {
        //Arrange
        String generatedId = new Random().toString();

        doThrow(new RuntimeException()).when(professorRepository).deleteById(generatedId);

        //Act
        boolean result = professorService.deleteById(generatedId);

        //Assert
        verify(professorRepository, times(1)).deleteById(generatedId);
        assertFalse(result);
    }
}