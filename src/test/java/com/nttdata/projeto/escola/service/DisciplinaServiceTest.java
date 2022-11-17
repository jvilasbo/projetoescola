package com.nttdata.projeto.escola.service;

import com.nttdata.projeto.escola.dto.DisciplinaRestDto;
import com.nttdata.projeto.escola.model.AulaEntity;
import com.nttdata.projeto.escola.model.DisciplinaEntity;
import com.nttdata.projeto.escola.repository.IAulaRepository;
import com.nttdata.projeto.escola.repository.IDisciplinaRepository;
import com.nttdata.projeto.escola.repository.MinisterioWebRepository;
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
class DisciplinaServiceTest {
    @MockBean
    IDisciplinaRepository disciplinaRepository;

    @MockBean
    MinisterioWebRepository ministerioWebRepository;

    @InjectMocks
    DisciplinaService disciplinaService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveNewDisciplinaWithCorrectAttributesAndReturnOk() throws Exception {
        //Arrange
        DisciplinaEntity disciplinaEntityDouble = mock(DisciplinaEntity.class);
        DisciplinaRestDto disciplinaRestDtoDouble = mock(DisciplinaRestDto.class);
        String generatedTitulo = new Random().toString();
        String generatedArea = new Random().toString();

        when(disciplinaEntityDouble.getTitulo()).thenReturn(generatedTitulo);
        when(disciplinaRepository.existsByTitulo(generatedTitulo)).thenReturn(false);
        when(ministerioWebRepository.findDisciplinaByTitulo(generatedTitulo)).thenReturn(Optional.of(disciplinaRestDtoDouble));

        when(disciplinaEntityDouble.getArea()).thenReturn(generatedArea);
        when(disciplinaRestDtoDouble.getArea()).thenReturn(generatedArea);
        //when(disciplinaRepository.save(disciplinaEntityDouble)).thenReturn(disciplinaEntityDouble);

        //Act
        String resultString =  disciplinaService.save(disciplinaEntityDouble);

        //Assert
        verify(disciplinaRepository, times(1)).save(disciplinaEntityDouble);
        assertEquals("Ok", resultString);
    }

    @Test
    void shouldNotSaveNewDisciplinaWithCorrectAttributesAndReturnExiste() throws Exception {
        //Arrange
        DisciplinaEntity disciplinaEntityDouble = mock(DisciplinaEntity.class);
        DisciplinaRestDto disciplinaRestDtoDouble = mock(DisciplinaRestDto.class);
        String generatedTitulo = new Random().toString();

        when(disciplinaEntityDouble.getTitulo()).thenReturn(generatedTitulo);
        when(disciplinaRepository.existsByTitulo(generatedTitulo)).thenReturn(true);
        when(ministerioWebRepository.findDisciplinaByTitulo(generatedTitulo)).thenReturn(Optional.of(disciplinaRestDtoDouble));

        //Act
        String resultString =  disciplinaService.save(disciplinaEntityDouble);

        //Assert
        verify(disciplinaRepository, times(0)).save(disciplinaEntityDouble);
        assertEquals("Existe", resultString);
    }

    @Test
    void shouldNotSaveNewDisciplinaWithCorrectAttributesAndReturnArea() throws Exception {
        //Arrange
        DisciplinaEntity disciplinaEntityDouble = mock(DisciplinaEntity.class);
        DisciplinaRestDto disciplinaRestDtoDouble = mock(DisciplinaRestDto.class);

        String generatedTitulo = new Random().toString();
        String generatedArea = new Random().toString();
        String generatedArea2 = new Random().toString();

        when(disciplinaEntityDouble.getTitulo()).thenReturn(generatedTitulo);

        when(disciplinaRepository.existsByTitulo(generatedTitulo)).thenReturn(false);
        when(ministerioWebRepository.findDisciplinaByTitulo(generatedTitulo)).thenReturn(Optional.of(disciplinaRestDtoDouble));

        when(disciplinaEntityDouble.getArea()).thenReturn(generatedArea);
        when(disciplinaRestDtoDouble.getArea()).thenReturn(generatedArea2);

        //Act
        String resultString =  disciplinaService.save(disciplinaEntityDouble);

        //Assert
        verify(disciplinaRepository, times(0)).save(disciplinaEntityDouble);
        assertEquals("Area", resultString);
    }

    @Test
    void shouldGetDisciplinaByIdWithCorrectAttributes() {
        //Arrange
        DisciplinaEntity disciplinaDouble = mock(DisciplinaEntity.class);
        int generatedId = new Random().nextInt();

        when(disciplinaDouble.getId()).thenReturn(generatedId);
        when(disciplinaRepository.findById(generatedId)).thenReturn(Optional.of(disciplinaDouble));
        //Act
        DisciplinaEntity disciplinaResult = disciplinaService.getDisciplina(generatedId);
        //Assert
        assertEquals(disciplinaDouble, disciplinaResult);
    }

    @Test
    void shouldGetAllDisciplinasWithCorrectAttributes() {
        //Arrange
        DisciplinaEntity disciplinaDouble1 = mock(DisciplinaEntity.class);
        DisciplinaEntity disciplinaDouble2 = mock(DisciplinaEntity.class);

        List<DisciplinaEntity> listDisciplinasDouble = new ArrayList<>();
        listDisciplinasDouble.add(disciplinaDouble1);
        listDisciplinasDouble.add(disciplinaDouble2);

        when(disciplinaRepository.findAll()).thenReturn(listDisciplinasDouble);

        //Act
        List<DisciplinaEntity> listDisciplinasResult = disciplinaService.listAll();

        //Assert
        assertEquals(listDisciplinasDouble, listDisciplinasResult);
    }

    @Test
    void shouldDeleteDisciplinaByIdCallRepositoryMethodOneTimeAndReturnTrue() {
        //Arrange
        int generatedId = new Random().nextInt();
        //Act
        boolean result = disciplinaService.deleteById(generatedId);

        //Assert
        verify(disciplinaRepository, times(1)).deleteById(generatedId);
        assertTrue(result);
    }
    @Test
    void shouldDeleteDisciplinaByIdCallRepositoryMethodOneTimeAndReturnFalseIfException() {
        //Arrange
        int generatedId = new Random().nextInt();

        doThrow(new RuntimeException()).when(disciplinaRepository).deleteById(generatedId);

        //Act
        boolean result = disciplinaService.deleteById(generatedId);

        //Assert
        verify(disciplinaRepository, times(1)).deleteById(generatedId);
        assertFalse(result);
    }
}