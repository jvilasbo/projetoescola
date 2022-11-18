package com.nttdata.projeto.escola.controller;

import com.nttdata.projeto.escola.dto.EscolaridadeRestDto;
import com.nttdata.projeto.escola.model.AlunoEntity;
import com.nttdata.projeto.escola.model.AulaEntity;
import com.nttdata.projeto.escola.model.ProfessorEntity;
import com.nttdata.projeto.escola.service.AlunoService;
import com.nttdata.projeto.escola.service.AulaService;
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
class AulaControllerTest {
    @MockBean
    AlunoService alunoService;
    @MockBean
    ProfessorService professorService;
    @MockBean
    AulaService aulaService;

    @InjectMocks
    AulaController aulaController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void viewAulasHomePage() {
        //Arrange
        Model modelDouble = mock(Model.class);
        List<AulaEntity> listAulasDouble = new ArrayList<>();

        when(aulaService.listAll()).thenReturn(listAulasDouble);
        //Act
        String result = aulaController.viewAulasHomePage(modelDouble);
        //Assert
        assertEquals("aulas/index", result);
    }

    @Test
    void viewAulasHomePageModelAndView() {
        //Arrange
        ModelAndView modelAndViewDouble = mock(ModelAndView.class);
        List<AulaEntity> listAulasDouble = new ArrayList<>();

        when(aulaService.listAll()).thenReturn(listAulasDouble);
        //Act
        ModelAndView modelAndViewResult = aulaController.viewAulasHomePage2();
        //Assert
        verify(aulaService, times(1)).listAll();
        assertEquals("aulas/index", modelAndViewResult.getViewName());
    }

    @Test
    void showNewAulaPage() {
        //Arrange
        Model modelDouble = mock(Model.class);

        List<ProfessorEntity> listProfessoresDouble = new ArrayList<>();
        when(professorService.listAll()).thenReturn(listProfessoresDouble);
        List<AlunoEntity> listAlunosDouble = new ArrayList<>();
        when(alunoService.listAll()).thenReturn(listAlunosDouble);
        //Act
        String result = aulaController.showNewAulaPage(modelDouble);
        //Assert
        verify(modelDouble, times(1)).addAttribute("professores", listProfessoresDouble);
        verify(modelDouble, times(1)).addAttribute("alunos", listAlunosDouble);
        assertEquals("aulas/new_aula", result);
    }

    @Test
    void saveAula() {
        //Arrange
        AulaEntity aulaDouble = mock(AulaEntity.class);
        //Act
        String result = aulaController.saveAula(aulaDouble);
        //Assert
        verify(aulaService, times(1)).save(aulaDouble);
        assertEquals("redirect:/aulas", result);
    }

    @Test
    void showEditAulaPage() {
        //Arrange
        ModelAndView modelAndViewDouble = mock(ModelAndView.class);
        List<ProfessorEntity> listProfessoresDouble = new ArrayList<>();
        when(professorService.listAll()).thenReturn(listProfessoresDouble);
        List<AlunoEntity> listAlunosDouble = new ArrayList<>();
        when(alunoService.listAll()).thenReturn(listAlunosDouble);
        int generatedId = new Random().nextInt();
        AulaEntity aulaDouble = mock(AulaEntity.class);
        when(aulaService.getAula(generatedId)).thenReturn(aulaDouble);
        //Act
        ModelAndView result = aulaController.showEditAulaPage(generatedId);
        //Assert
        assertEquals("aulas/edit_aula", result.getViewName());
    }

    @Test
    void deleteAula() {
        //Arrange
        int generatedId = new Random().nextInt();
        //Act
        String result = aulaController.deleteAula(generatedId);
        //Assert
        verify(aulaService, times(1)).deleteById(generatedId);
        assertEquals("redirect:/aulas", result);
    }
}