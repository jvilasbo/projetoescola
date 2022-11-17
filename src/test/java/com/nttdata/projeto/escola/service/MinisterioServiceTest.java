package com.nttdata.projeto.escola.service;

import com.nttdata.projeto.escola.dto.DisciplinaRestDto;
import com.nttdata.projeto.escola.dto.EscolaridadeRestDto;
import com.nttdata.projeto.escola.model.DisciplinaEntity;
import com.nttdata.projeto.escola.repository.IDisciplinaRepository;
import com.nttdata.projeto.escola.repository.MinisterioWebRepository;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class MinisterioServiceTest {
    @MockBean
    MinisterioWebRepository ministerioWebRepository;
    @InjectMocks
    MinisterioService ministerioService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetAllEscolaridadesWithCorrectAttributes() {
        //Arrange
        EscolaridadeRestDto escolaridadeDouble1 = mock(EscolaridadeRestDto.class);
        EscolaridadeRestDto escolaridadeDouble2 = mock(EscolaridadeRestDto.class);

        List<EscolaridadeRestDto> listEscolaridadesDouble = new ArrayList<>();
        listEscolaridadesDouble.add(escolaridadeDouble1);
        listEscolaridadesDouble.add(escolaridadeDouble2);

        when(ministerioWebRepository.findAllEscolaridades()).thenReturn(Optional.of(listEscolaridadesDouble));

        //Act
        List<EscolaridadeRestDto> listEscolaridadesResult = ministerioService.listAllEscolaridades();

        //Assert
        assertEquals(listEscolaridadesDouble, listEscolaridadesResult);
    }

    @Test
    void shouldGetEscolaridadeByIdadeWithCorrectAttributes() {
        //Arrange
        EscolaridadeRestDto escolaridadeDouble = mock(EscolaridadeRestDto.class);

        int generatedId = new Random().nextInt();

        when(ministerioWebRepository.findEscolaridadeByIdade(generatedId)).thenReturn(Optional.of(escolaridadeDouble));

        //Act
        EscolaridadeRestDto escolaridadeResult = ministerioService.findEscolaridadeByIdade(generatedId);

        //Assert
        assertEquals(escolaridadeDouble, escolaridadeResult);
    }

    @Test
    void shouldGetAllDisciplinasWithCorrectAttributes() {
        //Arrange
        DisciplinaRestDto disciplinaDouble1 = mock(DisciplinaRestDto.class);
        DisciplinaRestDto disciplinaDouble2 = mock(DisciplinaRestDto.class);

        List<DisciplinaRestDto> listDisciplinasDouble = new ArrayList<>();
        listDisciplinasDouble.add(disciplinaDouble1);
        listDisciplinasDouble.add(disciplinaDouble2);

        when(ministerioWebRepository.findAllDisciplinas()).thenReturn(Optional.of(listDisciplinasDouble));

        //Act
        List<DisciplinaRestDto> listDisciplinasResult = ministerioService.listAllDisciplinas();

        //Assert
        assertEquals(listDisciplinasDouble, listDisciplinasResult);
    }

    @Test
    void shouldGetAllDistinctAreasWithCorrectAttributes () {
        //Arrange
        String area1 = new Random().toString();
        String area2 = new Random().toString();

        List<String> listDisciplinasDouble = new ArrayList<>();
        listDisciplinasDouble.add(area1);
        listDisciplinasDouble.add(area2);

        when(ministerioWebRepository.findAllDistinctArea()).thenReturn(listDisciplinasDouble);

        //Act
        List<String> listDisciplinasResult = ministerioService.listAllDistinctArea();

        //Assert
        assertEquals(listDisciplinasDouble, listDisciplinasResult);
        assertEquals(2, listDisciplinasResult.size());
    }
}