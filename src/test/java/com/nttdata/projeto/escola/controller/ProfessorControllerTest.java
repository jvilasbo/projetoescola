package com.nttdata.projeto.escola.controller;

import com.nttdata.projeto.escola.model.DisciplinaEntity;
import com.nttdata.projeto.escola.model.ProfessorEntity;
import com.nttdata.projeto.escola.service.DisciplinaService;
import com.nttdata.projeto.escola.service.ProfessorService;
import com.nttdata.projeto.escola.service.UserService;
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
class ProfessorControllerTest {
    @MockBean
    DisciplinaService disciplinaService;
    @MockBean
    ProfessorService professorService;

    @InjectMocks
    ProfessorController professorController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void viewProfessoresHomePage() {
        //Arrange
        Model modelDouble = mock(Model.class);

        ProfessorEntity professorDouble1 = mock(ProfessorEntity.class);
        ProfessorEntity professorDouble2 = mock(ProfessorEntity.class);

        List<ProfessorEntity> listProfessorDouble = new ArrayList<>();
        listProfessorDouble.add(professorDouble1);
        listProfessorDouble.add(professorDouble2);

        when(professorService.listAll()).thenReturn(listProfessorDouble);
        //Act
        String result = professorController.viewProfessoresHomePage(modelDouble);
        //Assert
        assertEquals("professores/index", result);

    }

    //TODO Dúvida - este teste é unitário?? pq cria new ModelAndView
    @Test
    void testViewProfessoresHomePage() {
        //Arrange
        ModelAndView modelAndViewDouble = mock(ModelAndView.class);

        ProfessorEntity professorDouble1 = mock(ProfessorEntity.class);
        ProfessorEntity professorDouble2 = mock(ProfessorEntity.class);

        List<ProfessorEntity> listProfessorDouble = new ArrayList<>();
        listProfessorDouble.add(professorDouble1);
        listProfessorDouble.add(professorDouble2);

        when(professorService.listAll()).thenReturn(listProfessorDouble);

        //Act
        ModelAndView modelAndViewResult = professorController.viewProfessoresHomePage();
        //Assert
        verify(professorService, times(1)).listAll();
        assertEquals("professores/index", modelAndViewResult.getViewName());

    }

    @Test
    void showNewProfessorPage() {
        //Arrange
        Model modelDouble = mock(Model.class);

        DisciplinaEntity disciplinaDouble1 = mock(DisciplinaEntity.class);
        DisciplinaEntity disciplinaDouble2 = mock(DisciplinaEntity.class);
        List<DisciplinaEntity> listDisciplinasDouble = new ArrayList<>();
        listDisciplinasDouble.add(disciplinaDouble1);
        listDisciplinasDouble.add(disciplinaDouble2);

        when(disciplinaService.listAll()).thenReturn(listDisciplinasDouble);

        ProfessorEntity professorDouble = mock(ProfessorEntity.class);
        modelDouble.addAttribute("professor", professorDouble);
        //Act
        String result = professorController.showNewProfessorPage(modelDouble);
        //Assert
        assertEquals("professores/new_professor", result);
    }

    @Test
    void saveProfessor() {
        //Arrange
        ProfessorEntity professorDouble = mock(ProfessorEntity.class);
        //Act
        String result = professorController.saveProfessor(professorDouble);
        //Assert
        verify(professorService, times(1)).save(professorDouble);
        assertEquals("redirect:/professores", result);
    }

    //TODO Dúvida - este teste é unitário?? pq cria new ModelAndView
    @Test
    void showEditProfessorPage() {
        //Arrange
        String generatedId = new Random().toString();

        ProfessorEntity professorDouble = mock(ProfessorEntity.class);
        when(professorService.getProfessor(generatedId)).thenReturn(professorDouble);

        DisciplinaEntity disciplinaDouble1 = mock(DisciplinaEntity.class);
        DisciplinaEntity disciplinaDouble2 = mock(DisciplinaEntity.class);
        List<DisciplinaEntity> listDisciplinasDouble = new ArrayList<>();
        listDisciplinasDouble.add(disciplinaDouble1);
        listDisciplinasDouble.add(disciplinaDouble2);
        when(disciplinaService.listAll()).thenReturn(listDisciplinasDouble);

        //Act
        ModelAndView result = professorController.showEditProfessorPage(generatedId);
        //Assert
        verify(professorService, times(1)).getProfessor(generatedId);
        verify(disciplinaService, times(1)).listAll();
    }

    @Test
    void shouldReturnSuccessMessageWhenDeleteProfessorByIdIsTrue() {
        //Arrange
        String generatedId = new Random().toString();
        Model modelDouble = mock(Model.class);

        when(professorService.deleteById(generatedId)).thenReturn(true);

        ProfessorEntity professorDouble1 = mock(ProfessorEntity.class);
        ProfessorEntity professorDouble2 = mock(ProfessorEntity.class);
        List<ProfessorEntity> listProfessorDouble = new ArrayList<>();
        listProfessorDouble.add(professorDouble1);
        listProfessorDouble.add(professorDouble2);

        when(professorService.listAll()).thenReturn(listProfessorDouble);
        //Act
        String result = professorController.deleteProfessor(generatedId, modelDouble);
        //Assert
        assertEquals("professores/index", result);
        verify(modelDouble, times(1)).addAttribute("error4", "Username & Password Incorrectos");

    }

    @Test
    void shouldReturnErrorMessageWhenDeleteProfessorByIdIsFalse() {
        //Arrange
        String generatedId = new Random().toString();
        Model modelDouble = mock(Model.class);

        when(professorService.deleteById(generatedId)).thenReturn(false);

        ProfessorEntity professorDouble1 = mock(ProfessorEntity.class);
        ProfessorEntity professorDouble2 = mock(ProfessorEntity.class);
        List<ProfessorEntity> listProfessorDouble = new ArrayList<>();
        listProfessorDouble.add(professorDouble1);
        listProfessorDouble.add(professorDouble2);

        when(professorService.listAll()).thenReturn(listProfessorDouble);
        //Act
        String result = professorController.deleteProfessor(generatedId, modelDouble);
        //Assert
        assertEquals("professores/index", result);
        verify(modelDouble, times(1)).addAttribute("error", "Username & Password Incorrectos");
    }
}