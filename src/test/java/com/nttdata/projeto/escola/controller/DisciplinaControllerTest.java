package com.nttdata.projeto.escola.controller;

import com.nttdata.projeto.escola.dto.DisciplinaRestDto;
import com.nttdata.projeto.escola.model.DisciplinaEntity;
import com.nttdata.projeto.escola.model.ProfessorEntity;
import com.nttdata.projeto.escola.service.DisciplinaService;
import com.nttdata.projeto.escola.service.MinisterioService;
import com.nttdata.projeto.escola.service.ProfessorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class DisciplinaControllerTest {
    @MockBean
    DisciplinaService disciplinaService;
    @MockBean
    MinisterioService ministerioService;
    @InjectMocks
    DisciplinaController disciplinaController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void viewDisciplinaHomePageWithModel() {
        //Arrange
        Model modelDouble = mock(Model.class);

        DisciplinaEntity disciplinaDouble1 = mock(DisciplinaEntity.class);
        DisciplinaEntity disciplinaDouble2 = mock(DisciplinaEntity.class);
        List<DisciplinaEntity> listDisciplinaDouble = new ArrayList<>();
        listDisciplinaDouble.add(disciplinaDouble1);
        listDisciplinaDouble.add(disciplinaDouble2);

        when(disciplinaService.listAll()).thenReturn(listDisciplinaDouble);
        //Act
        String result = disciplinaController.viewHomePage(modelDouble);
        //Assert
        assertEquals("disciplinas/index", result);
    }

    //TODO Dúvida - este teste é unitário?? pq cria new ModelAndView
    @Test
    void viewHomePageWithModelAndView() {
        //Arrange
        ModelAndView modelAndViewDouble = mock(ModelAndView.class);

        DisciplinaEntity disciplinaDouble1 = mock(DisciplinaEntity.class);
        DisciplinaEntity disciplinaDouble2 = mock(DisciplinaEntity.class);
        List<DisciplinaEntity> listDisciplinaDouble = new ArrayList<>();
        listDisciplinaDouble.add(disciplinaDouble1);
        listDisciplinaDouble.add(disciplinaDouble2);

        when(disciplinaService.listAll()).thenReturn(listDisciplinaDouble);

        //Act
        ModelAndView modelAndViewResult = disciplinaController.viewHomePage2();
        //Assert
        verify(disciplinaService, times(1)).listAll();
        assertEquals("disciplinas/index", modelAndViewResult.getViewName());

    }

    @Test
    void showNewDisciplinaPage() {
        //Arrange
        Model modelDouble = mock(Model.class);

        DisciplinaRestDto disciplinaDouble1 = mock(DisciplinaRestDto.class);
        DisciplinaRestDto disciplinaDouble2 = mock(DisciplinaRestDto.class);
        List<DisciplinaRestDto> listDisciplinasRestDtoDouble = new ArrayList<>();
        listDisciplinasRestDtoDouble.add(disciplinaDouble1);
        listDisciplinasRestDtoDouble.add(disciplinaDouble2);
        when(ministerioService.listAllDisciplinas()).thenReturn(listDisciplinasRestDtoDouble);

        String area1 = "area1";
        String area2 = "area2";
        List<String> listStringsArea = new ArrayList<>();
        listStringsArea.add(area1);
        listStringsArea.add(area2);
        when(ministerioService.listAllDistinctArea()).thenReturn(listStringsArea);

        //Act
        String result = disciplinaController.showNewDisciplinaPage(modelDouble);
        //Assert
        assertEquals("disciplinas/new_disciplina", result);
        verify(modelDouble, times(1)).addAttribute("disciplinasMinisterio", listDisciplinasRestDtoDouble);
        verify(modelDouble, times(1)).addAttribute("areasMinisterio", listStringsArea);
    }

    @Test
    void shouldSaveDisciplinaIfSaveMethodReturnOk() throws Exception {
        //Arrange
        Model modelDouble = mock(Model.class);
        DisciplinaEntity disciplinaDouble = mock(DisciplinaEntity.class);

        when(disciplinaService.save(disciplinaDouble)).thenReturn("Ok");
        //Act
        String result = disciplinaController.saveDisciplina(disciplinaDouble, modelDouble);
        //Assert
        verify(disciplinaService, times(1)).save(disciplinaDouble);
        assertEquals("redirect:/disciplinas", result);
    }

    @Test
    void shouldNotSaveDisciplinaAndShowErrorMessageIfSaveMethodReturnExiste() throws Exception {
        //Arrange
        Model modelDouble = mock(Model.class);
        DisciplinaEntity disciplinaDouble = mock(DisciplinaEntity.class);

        when(disciplinaService.save(disciplinaDouble)).thenReturn("Existe");
        //Act
        String result = disciplinaController.saveDisciplina(disciplinaDouble, modelDouble);
        //Assert
        verify(disciplinaService, times(2)).save(disciplinaDouble);
        verify(modelDouble, times(1)).addAttribute("error1", "Username & Password Incorrectos");
        assertEquals("disciplinas/new_disciplina", result);
    }

    @Test
    void shouldNotSaveDisciplinaAndShowErrorMessageIfSaveMethodReturnArea() throws Exception {
        //Arrange
        Model modelDouble = mock(Model.class);
        DisciplinaEntity disciplinaDouble = mock(DisciplinaEntity.class);

        when(disciplinaService.save(disciplinaDouble)).thenReturn("Area");
        //Act
        String result = disciplinaController.saveDisciplina(disciplinaDouble, modelDouble);
        //Assert
        verify(disciplinaService, times(3)).save(disciplinaDouble);
        verify(modelDouble, times(1)).addAttribute("error2", "Username & Password Incorrectos");
        assertEquals("disciplinas/new_disciplina", result);
    }

    //TODO Dúvida - este teste é unitário?? pq cria new ModelAndView
    @Test
    void showEditDisciplinaPage() {
        //Arrange
        int generatedId = new Random().nextInt();

        DisciplinaEntity disciplinaDouble = mock(DisciplinaEntity.class);
        when(disciplinaService.getDisciplina(generatedId)).thenReturn(disciplinaDouble);

        DisciplinaRestDto disciplinaDouble1 = mock(DisciplinaRestDto.class);
        DisciplinaRestDto disciplinaDouble2 = mock(DisciplinaRestDto.class);
        List<DisciplinaRestDto> listDisciplinasRestDtoDouble = new ArrayList<>();
        listDisciplinasRestDtoDouble.add(disciplinaDouble1);
        listDisciplinasRestDtoDouble.add(disciplinaDouble2);
        when(ministerioService.listAllDisciplinas()).thenReturn(listDisciplinasRestDtoDouble);

        String area1 = "area1";
        String area2 = "area2";
        List<String> listStringsArea = new ArrayList<>();
        listStringsArea.add(area1);
        listStringsArea.add(area2);
        when(ministerioService.listAllDistinctArea()).thenReturn(listStringsArea);

        //Act
        ModelAndView result = disciplinaController.showEditDisciplinaPage(generatedId);
        //Assert
        verify(disciplinaService, times(1)).getDisciplina(generatedId);
        verify(ministerioService, times(1)).listAllDisciplinas();
        verify(ministerioService, times(1)).listAllDistinctArea();
        assertEquals("disciplinas/edit_disciplina", result.getViewName());
    }

    @Test
    void shouldReturnSuccessMessageWhenDeleteDisciplinaByIdIsTrue() {
        //Arrange
        int generatedId = new Random().nextInt();
        Model modelDouble = mock(Model.class);

        when(disciplinaService.deleteById(generatedId)).thenReturn(true);

        DisciplinaEntity disciplinaDouble1 = mock(DisciplinaEntity.class);
        DisciplinaEntity disciplinaDouble2 = mock(DisciplinaEntity.class);
        List<DisciplinaEntity> listDisciplinaDouble = new ArrayList<>();
        listDisciplinaDouble.add(disciplinaDouble1);
        listDisciplinaDouble.add(disciplinaDouble2);

        when(disciplinaService.listAll()).thenReturn(listDisciplinaDouble);
        //Act
        String result = disciplinaController.deleteDisciplina(generatedId, modelDouble);
        //Assert
        assertEquals("disciplinas/index", result);
        verify(modelDouble, times(1)).addAttribute("error4", "Username & Password Incorrectos");
    }

    @Test
    void shouldReturnErrorMessageWhenDeleteDisciplinaByIdIsFalse() {
        //Arrange
        int generatedId = new Random().nextInt();
        Model modelDouble = mock(Model.class);

        when(disciplinaService.deleteById(generatedId)).thenReturn(false);

        DisciplinaEntity disciplinaDouble1 = mock(DisciplinaEntity.class);
        DisciplinaEntity disciplinaDouble2 = mock(DisciplinaEntity.class);
        List<DisciplinaEntity> listDisciplinaDouble = new ArrayList<>();
        listDisciplinaDouble.add(disciplinaDouble1);
        listDisciplinaDouble.add(disciplinaDouble2);

        when(disciplinaService.listAll()).thenReturn(listDisciplinaDouble);
        //Act
        String result = disciplinaController.deleteDisciplina(generatedId, modelDouble);
        //Assert
        assertEquals("disciplinas/index", result);
        verify(modelDouble, times(1)).addAttribute("error", "Username & Password Incorrectos");
    }
}