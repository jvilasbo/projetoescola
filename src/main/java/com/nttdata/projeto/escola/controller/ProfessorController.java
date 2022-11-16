package com.nttdata.projeto.escola.controller;

import com.nttdata.projeto.escola.model.ProfessorEntity;
import com.nttdata.projeto.escola.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping("/professores-model")
    public String viewProfessoresHomePage(Model model) {
        List<ProfessorEntity> professores = professorService.listAll();
        model.addAttribute("professores", professores);

        return "professores/index"; //o Spring vai renderizar o arquivo templates/disciplinas/index.html
    }

    @GetMapping("/professores")
    public ModelAndView viewProfessoresHomePage() {
        List<ProfessorEntity> professores = professorService.listAll();
        ModelAndView mv = new ModelAndView("professores/index"); //nome do arquivo html que vai ser renderizado/exibido
        mv.addObject("professores", professores);
        return mv;
    }

    @GetMapping("/professor/newProfessor")
    public String showNewProfessorPage(Model model) {
        ProfessorEntity professor = new ProfessorEntity();
        model.addAttribute("professor", professor);

        return "professores/new_professor";
    }

    @RequestMapping(value = "/saveProfessor", method = RequestMethod.POST)
    public String saveProfessor(@ModelAttribute("professor") ProfessorEntity professor) {

        professorService.save(professor);

        return "redirect:/professores";
    }

    /*@PostMapping(value = "/save")
    public String saveProfessor(@ModelAttribute("professor") ProfessorEntity professor) {
        professorService.save(professor);

        return "redirect:/professores";
    }*/

    @RequestMapping("/editProfessor/{id}")
    public ModelAndView showEditProfessorPage(@PathVariable(name = "id") String id) {
        ModelAndView mv = new ModelAndView("professores/edit_professor");
        ProfessorEntity professor = professorService.get(id);
        mv.addObject("professor", professor);

        return mv;
    }

    @RequestMapping("/deleteProfessor/{id}")
    public String deleteProfessor(@PathVariable(name = "id") String id) {
        professorService.delete(id);
        return "redirect:/professores";
    }
}
