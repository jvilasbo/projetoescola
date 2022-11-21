package com.nttdata.projeto.escola.service;

import com.nttdata.projeto.escola.model.AlunoEntity;
import com.nttdata.projeto.escola.model.AulaEntity;
import com.nttdata.projeto.escola.repository.IAlunoRepository;
import com.nttdata.projeto.escola.repository.IAulaRepository;
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
class AulaServiceTest {

    @MockBean
    IAulaRepository aulaRepository;

    @InjectMocks
    AulaService aulaService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void shouldSaveNewAulaWithCorrectAttributes() {
        //Arrange
        AulaEntity aulaEntityDouble = mock(AulaEntity.class);
        //Act
        aulaService.save(aulaEntityDouble);
        //Assert
        verify(aulaRepository, times(1)).save(aulaEntityDouble);
    }

    @Test
    void shouldGetAulaByIdWithCorrectAttributes() {
        //Arrange
        AulaEntity aulaDouble = mock(AulaEntity.class);
        int generatedId = new Random().nextInt();

        when(aulaDouble.getId()).thenReturn(generatedId);
        when(aulaRepository.findById(generatedId)).thenReturn(Optional.of(aulaDouble));
        //Act
        AulaEntity aulaResult = aulaService.getAula(generatedId);
        //Assert
        assertEquals(aulaDouble, aulaResult);
    }

    @Test
    void shouldGetAllAulasWithCorrectAttributes() {
        //Arrange
        AulaEntity aulaDouble1 = mock(AulaEntity.class);
        AulaEntity aulaDouble2 = mock(AulaEntity.class);

        List<AulaEntity> listAulasDouble = new ArrayList<>();
        listAulasDouble.add(aulaDouble1);
        listAulasDouble.add(aulaDouble2);

        when(aulaRepository.findAll()).thenReturn(listAulasDouble);

        //Act
        List<AulaEntity> listAulasResult = aulaService.listAll();

        //Assert
        assertEquals(listAulasDouble, listAulasResult);
    }

    @Test
    void shouldDeleteAulaByIdAndCallRepositoryMethodOneTimeAndReturnTrue() {
        //Arrange
        int generatedId = new Random().nextInt();

        //Act
        boolean result = aulaService.deleteById(generatedId);

        //Assert
        verify(aulaRepository, times(1)).deleteById(generatedId);
        assertTrue(result);
    }

    @Test
    void shouldDeleteAulaByIdAndCallRepositoryMethodOneTimeAndReturnFalseIfException() {
        //Arrange
        int generatedId = new Random().nextInt();

        doThrow(new RuntimeException()).when(aulaRepository).deleteById(generatedId);

        //Act
        boolean result = aulaService.deleteById(generatedId);

        //Assert
        verify(aulaRepository, times(1)).deleteById(generatedId);
        assertFalse(result);
    }
}