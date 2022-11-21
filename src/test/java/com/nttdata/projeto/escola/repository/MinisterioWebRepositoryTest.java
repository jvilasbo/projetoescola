package com.nttdata.projeto.escola.repository;

import com.nttdata.projeto.escola.dto.DisciplinaRestDto;
import com.nttdata.projeto.escola.dto.EscolaridadeRestDto;
import com.nttdata.projeto.escola.model.AlunoEntity;
import com.nttdata.projeto.escola.repository.rest.MinisterioRestRepository;
import com.nttdata.projeto.escola.service.AlunoService;
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
class MinisterioWebRepositoryTest {
    @MockBean
    MinisterioRestRepository ministerioRestRepository;
    @InjectMocks
    MinisterioWebRepository ministerioWebRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllDisciplinas() {
        //Arrange
        List<DisciplinaRestDto> listDouble = new ArrayList<>();
        DisciplinaRestDto disciplinaDouble1 = mock(DisciplinaRestDto.class);
        DisciplinaRestDto disciplinaDouble2 = mock(DisciplinaRestDto.class);
        listDouble.add(disciplinaDouble1);
        listDouble.add(disciplinaDouble2);

        Optional<List<DisciplinaRestDto>> listOptionalDouble = Optional.of(listDouble);
        when(ministerioRestRepository.findAllDisciplinas()).thenReturn(listOptionalDouble);
        //Act
        Optional<List<DisciplinaRestDto>> listOptionalResult = ministerioWebRepository.findAllDisciplinas();
        //Assert
        assertEquals(listOptionalDouble, listOptionalResult);
        assertEquals(listOptionalDouble.get().size(), listOptionalResult.get().size());
        verify(ministerioRestRepository, times(1)).findAllDisciplinas();
    }

    @Test
    void findAllEscolaridades() {
        //Arrange
        List<EscolaridadeRestDto> listDouble = new ArrayList<>();
        EscolaridadeRestDto escolaridadeDouble1 = mock(EscolaridadeRestDto.class);
        EscolaridadeRestDto escolaridadeDouble2 = mock(EscolaridadeRestDto.class);
        listDouble.add(escolaridadeDouble1);
        listDouble.add(escolaridadeDouble2);

        Optional<List<EscolaridadeRestDto>> listOptionalDouble = Optional.of(listDouble);
        when(ministerioRestRepository.findAllEscolaridades()).thenReturn(listOptionalDouble);
        //Act
        Optional<List<EscolaridadeRestDto>> listOptionalResult = ministerioWebRepository.findAllEscolaridades();
        //Assert
        assertEquals(listOptionalDouble, listOptionalResult);
        assertEquals(listOptionalDouble.get().size(), listOptionalResult.get().size());
        verify(ministerioRestRepository, times(1)).findAllEscolaridades();
    }

    @Test
    void findEscolaridadeByIdade() {
        //Arrange
        int generatedIdade = new Random().nextInt();
        EscolaridadeRestDto escolaridadeDouble = mock(EscolaridadeRestDto.class);
        Optional<EscolaridadeRestDto> optionalDouble = Optional.of(escolaridadeDouble);

        when(ministerioRestRepository.findEscolaridadeByIdade(generatedIdade)).thenReturn(optionalDouble);
        //Act
        Optional<EscolaridadeRestDto> optionalResult = ministerioWebRepository.findEscolaridadeByIdade(generatedIdade);
        //Assert
        assertEquals(optionalDouble, optionalResult);
        verify(ministerioRestRepository, times(1)).findEscolaridadeByIdade(generatedIdade);
    }

    @Test
    void findDisciplinaByTitulo() {
        //Arrange
        String generatedTitulo = new Random().toString();
        DisciplinaRestDto disciplinaDouble = mock(DisciplinaRestDto.class);
        Optional<DisciplinaRestDto> optionalDouble = Optional.of(disciplinaDouble);

        when(ministerioRestRepository.findDisciplinaByTitulo(generatedTitulo)).thenReturn(optionalDouble);
        //Act
        Optional<DisciplinaRestDto> optionalResult = ministerioWebRepository.findDisciplinaByTitulo(generatedTitulo);
        //Assert
        assertEquals(optionalDouble, optionalResult);
        verify(ministerioRestRepository, times(1)).findDisciplinaByTitulo(generatedTitulo);

    }

    @Test
    void findAllDistinctArea() {
        //Arrange
        List<String> listDouble = new ArrayList<>();
        String areaDouble1 = "area1";
        String areaDouble2 = "area2";
        listDouble.add(areaDouble1);
        listDouble.add(areaDouble2);

        when(ministerioRestRepository.findAllDistinctArea()).thenReturn(listDouble);
        //Act
        List<String> listOptionalResult = ministerioWebRepository.findAllDistinctArea();
        //Assert
        assertEquals(listDouble, listOptionalResult);
        assertEquals(listDouble.size(), listOptionalResult.size());
        verify(ministerioRestRepository, times(1)).findAllDistinctArea();
    }
}