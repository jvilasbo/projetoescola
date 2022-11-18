package com.nttdata.projeto.escola.controller;

import com.nttdata.projeto.escola.dto.EscolaridadeRestDto;
import com.nttdata.projeto.escola.model.AlunoEntity;
import com.nttdata.projeto.escola.model.DisciplinaEntity;
import com.nttdata.projeto.escola.model.ProfessorEntity;
import com.nttdata.projeto.escola.service.AlunoService;
import com.nttdata.projeto.escola.service.DisciplinaService;
import com.nttdata.projeto.escola.service.MinisterioService;
import com.nttdata.projeto.escola.service.AlunoService;
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
class AlunoControllerTest {

    @MockBean
    MinisterioService ministerioService;
    @MockBean
    AlunoService alunoService;

    @InjectMocks
    AlunoController alunoController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void viewAlunosHomePageWithModel() {
        //Arrange
        Model modelDouble = mock(Model.class);

        AlunoEntity alunoDouble1 = mock(AlunoEntity.class);
        AlunoEntity alunoDouble2 = mock(AlunoEntity.class);

        List<AlunoEntity> listAlunosDouble = new ArrayList<>();
        listAlunosDouble.add(alunoDouble1);
        listAlunosDouble.add(alunoDouble2);

        when(alunoService.listAll()).thenReturn(listAlunosDouble);
        //Act
        String result = alunoController.viewAlunosHomePage(modelDouble);
        //Assert
        assertEquals("alunos/index", result);

    }

    //TODO Dúvida - este teste é unitário?? pq cria new ModelAndView
    @Test
    void viewAlunosHomePageWithModelAndView() {
        //Arrange
        ModelAndView modelAndViewDouble = mock(ModelAndView.class);

        List<AlunoEntity> listAlunosDouble = new ArrayList<>();

        when(alunoService.listAll()).thenReturn(listAlunosDouble);

        //Act
        ModelAndView modelAndViewResult = alunoController.viewAlunosHomePage2();
        //Assert
        verify(alunoService, times(1)).listAll();
        assertEquals("alunos/index", modelAndViewResult.getViewName());

    }

    @Test
    void showNewAlunoPage() {
        //Arrange
        Model modelDouble = mock(Model.class);

        List<EscolaridadeRestDto> listEscolaridadesDouble = new ArrayList<>();

        when(ministerioService.listAllEscolaridades()).thenReturn(listEscolaridadesDouble);

        //Act
        String result = alunoController.showNewAlunoPage(modelDouble);
        //Assert
        verify(modelDouble, times(1)).addAttribute("escolaridades", listEscolaridadesDouble);
        assertEquals("alunos/new_aluno", result);
    }

    @Test
    void shouldSaveAluno() {
        //Arrange
        Model modelDouble = mock(Model.class);
        AlunoEntity alunoDouble = mock(AlunoEntity.class);
        when(alunoDouble.getEscolaridade()).thenReturn("11º Ano");
        when(alunoDouble.getIdade()).thenReturn(17);
        EscolaridadeRestDto escolaridadeDouble = mock(EscolaridadeRestDto.class);

        when(ministerioService.findEscolaridadeByIdade(17)).thenReturn(escolaridadeDouble);
        when(escolaridadeDouble.getAnoEscolar()).thenReturn("12º Ano");

        //Act
        String result = alunoController.saveAluno(alunoDouble, modelDouble);
        //Assert
        verify(alunoService, times(1)).save(alunoDouble);
        assertEquals("redirect:/alunos", result);
    }

    @Test
    void shouldNotSaveAlunoAndReturnErrorMessage() {
        //Arrange
        Model modelDouble = mock(Model.class);
        AlunoEntity alunoDouble = mock(AlunoEntity.class);
        when(alunoDouble.getEscolaridade()).thenReturn("12º Ano");
        when(alunoDouble.getIdade()).thenReturn(17);
        EscolaridadeRestDto escolaridadeDouble = mock(EscolaridadeRestDto.class);

        when(ministerioService.findEscolaridadeByIdade(17)).thenReturn(escolaridadeDouble);

        when(escolaridadeDouble.getAnoEscolar()).thenReturn("11º Ano");

        //Act
        String result = alunoController.saveAluno(alunoDouble, modelDouble);
        //Assert
        verify(modelDouble, times(1)).addAttribute("error", "Username & Password Incorrectos");
        assertEquals("alunos/new_aluno", result);
    }

    @Test
    void showEditAlunoPage() {
        //Arrange
        String generatedId = new Random().toString();

        AlunoEntity alunoDouble = mock(AlunoEntity.class);
        when(alunoService.getAluno(generatedId)).thenReturn(alunoDouble);

        List<EscolaridadeRestDto> listEscolaridadesDouble = new ArrayList<>();

        when(ministerioService.listAllEscolaridades()).thenReturn(listEscolaridadesDouble);

        //Act
        ModelAndView result = alunoController.showEditAlunoPage(generatedId);
        //Assert
        verify(alunoService, times(1)).getAluno(generatedId);
        verify(ministerioService, times(1)).listAllEscolaridades();
        assertEquals("alunos/edit_aluno", result.getViewName());

    }

    @Test
    void saveAfterEditAluno() {
        //Arrange
        Model modelDouble = mock(Model.class);
        AlunoEntity alunoDouble = mock(AlunoEntity.class);
        when(alunoDouble.getEscolaridade()).thenReturn("11º Ano");
        when(alunoDouble.getIdade()).thenReturn(17);
        EscolaridadeRestDto escolaridadeDouble = mock(EscolaridadeRestDto.class);

        when(ministerioService.findEscolaridadeByIdade(17)).thenReturn(escolaridadeDouble);
        when(escolaridadeDouble.getAnoEscolar()).thenReturn("12º Ano");

        //Act
        String result = alunoController.saveAfterEditAluno(alunoDouble, modelDouble);
        //Assert
        verify(alunoService, times(1)).save(alunoDouble);
        assertEquals("redirect:/alunos", result);

    }

    @Test
    void shouldNotSaveAfterEditAlunoAndReturnErrorMessage() {
        //Arrange
        Model modelDouble = mock(Model.class);
        AlunoEntity alunoDouble = mock(AlunoEntity.class);
        when(alunoDouble.getEscolaridade()).thenReturn("12º Ano");
        when(alunoDouble.getIdade()).thenReturn(17);
        EscolaridadeRestDto escolaridadeDouble = mock(EscolaridadeRestDto.class);

        when(ministerioService.findEscolaridadeByIdade(17)).thenReturn(escolaridadeDouble);

        when(escolaridadeDouble.getAnoEscolar()).thenReturn("11º Ano");

        //Act
        String result = alunoController.saveAfterEditAluno(alunoDouble, modelDouble);
        //Assert
        verify(modelDouble, times(1)).addAttribute("error", "Username & Password Incorrectos");
        assertEquals("alunos/edit_aluno", result);
    }

    @Test
    void shouldReturnSuccessMessageWhenDeleteAlunoByIdIsTrue() {
        //Arrange
        String generatedId = new Random().toString();
        Model modelDouble = mock(Model.class);

        when(alunoService.deleteById(generatedId)).thenReturn(true);

        List<AlunoEntity> listAlunosDouble = new ArrayList<>();

        when(alunoService.listAll()).thenReturn(listAlunosDouble);
        //Act
        String result = alunoController.deleteAluno(generatedId, modelDouble);
        //Assert
        assertEquals("alunos/index", result);
        verify(modelDouble, times(1)).addAttribute("error4", "Username & Password Incorrectos");
    }

    @Test
    void shouldReturnErrorMessageWhenDeleteAlunoByIdIsFalse() {
        //Arrange
        String generatedId = new Random().toString();
        Model modelDouble = mock(Model.class);

        when(alunoService.deleteById(generatedId)).thenReturn(false);
        List<AlunoEntity> listAlunosDouble = new ArrayList<>();
        when(alunoService.listAll()).thenReturn(listAlunosDouble);
        //Act
        String result = alunoController.deleteAluno(generatedId, modelDouble);
        //Assert
        assertEquals("alunos/index", result);
        verify(modelDouble, times(1)).addAttribute("error", "Username & Password Incorrectos");
    }
}