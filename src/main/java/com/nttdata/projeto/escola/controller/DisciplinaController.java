package com.nttdata.projeto.escola.controller;

import com.nttdata.projeto.escola.model.DisciplinaEntity;
import com.nttdata.projeto.escola.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping("/disciplinas-model")
    public String viewHomePage(Model model) {
        List<DisciplinaEntity> disciplinas = disciplinaService.listAll();
        model.addAttribute("disciplinas", disciplinas);

        return "disciplinas/index"; //o Spring vai renderizar o arquivo templates/disciplinas/index.html
    }

    @GetMapping("/disciplinas")
    public ModelAndView viewHomePage2() {
        List<DisciplinaEntity> disciplinas = disciplinaService.listAll();
        //model.addAttribute("listDisciplinas", listDisciplinas);
        /*DisciplinaEntity disciplina1 = new DisciplinaEntity("POO", "Tecnologia");
        disciplina1.setId(1);
        DisciplinaEntity disciplina2 = new DisciplinaEntity("PDS", "Tecnologia");
        disciplina2.setId(2);
        List<DisciplinaEntity> disciplinas = Arrays.asList(disciplina1,disciplina2);*/
        ModelAndView mv = new ModelAndView("disciplinas/index"); //nome do arquivo html que vai ser renderizado/exibido
        mv.addObject("disciplinas", disciplinas);
        return mv;
    }

    @GetMapping("/disciplina/new")
    public String showNewDisciplinaPage(Model model) {
        DisciplinaEntity disciplina = new DisciplinaEntity();
        model.addAttribute("disciplina", disciplina);

        return "disciplinas/new_disciplina";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveDisciplina(@ModelAttribute("disciplina") DisciplinaEntity disciplina) {

        disciplinaService.save(disciplina);

        return "redirect:/disciplinas";
    }

    /*@PostMapping(value = "/save")
    public String saveDisciplina(@ModelAttribute("disciplina") DisciplinaEntity disciplina) {
        disciplinaService.save(disciplina);

        return "redirect:/disciplinas";
    }*/

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditDisciplinaPage(@PathVariable(name = "id") int id) {
        ModelAndView mv = new ModelAndView("disciplinas/edit_disciplina");
        DisciplinaEntity disciplina = disciplinaService.get(id);
        mv.addObject("disciplina", disciplina);

        return mv;
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        disciplinaService.delete(id);
        return "redirect:/disciplinas";
    }
}
