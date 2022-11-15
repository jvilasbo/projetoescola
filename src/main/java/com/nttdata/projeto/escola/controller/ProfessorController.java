package com.nttdata.projeto.escola.controller;

import com.nttdata.projeto.escola.model.DisciplinaEntity;
import com.nttdata.projeto.escola.model.ProfessorEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfessorController {

    @GetMapping("/professores")
    public ModelAndView viewHomePage(Model model) {
        //List<DisciplinaEntity> disciplinas = disciplinaService.listAll();
        //model.addAttribute("listDisciplinas", listDisciplinas);
        /*DisciplinaEntity disciplina1 = new DisciplinaEntity("POO", "Tecnologia");
        disciplina1.setId(1);
        DisciplinaEntity disciplina2 = new DisciplinaEntity("PDS", "Tecnologia");
        disciplina2.setId(2);
        List<DisciplinaEntity> disciplinas = Arrays.asList(disciplina1,disciplina2);*/
        List<ProfessorEntity> professores = new ArrayList<>();
        ModelAndView mv = new ModelAndView("professores/index");
        mv.addObject("disciplinas", professores);
        return mv;
    }
}
