package com.nttdata.projeto.escola.controller;

import com.nttdata.projeto.escola.model.AulaEntity;
import com.nttdata.projeto.escola.service.AulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AulaController {

    @Autowired
    private AulaService aulaService;

    @GetMapping("/aulas-model")
    public String viewAulasHomePage(Model model) {
        List<AulaEntity> aulas = aulaService.listAll();
        model.addAttribute("aulas", aulas);

        return "aulas/index"; //o Spring vai renderizar o arquivo templates/disciplinas/index.html
    }

    @GetMapping("/aulas")
    public ModelAndView viewAulasHomePage2() {
        List<AulaEntity> aulas = aulaService.listAll();
        ModelAndView mv = new ModelAndView("aulas/index"); //nome do arquivo html que vai ser renderizado/exibido
        mv.addObject("aulas", aulas);
        return mv;
    }

    @GetMapping("/aula/newAula")
    public String showNewAulaPage(Model model) {
        AulaEntity aula = new AulaEntity();
        model.addAttribute("aula", aula);

        return "aulas/new_aula";
    }

    @RequestMapping(value = "/saveAula", method = RequestMethod.POST)
    public String saveAula(@ModelAttribute("aula") AulaEntity aula) {
        aulaService.save(aula);
        return "redirect:/aulas";
    }

    /*@PostMapping(value = "/saveAula")
    public String saveAula(@ModelAttribute("aula") AulaEntity aula) {
        aulaService.save(aula);

        return "redirect:/aulas";
    }*/

    @RequestMapping("/editAula/{id}")
    public ModelAndView showEditAulaPage(@PathVariable(name = "id") int id) {
        ModelAndView mv = new ModelAndView("aulas/edit_aula");
        AulaEntity aula = aulaService.get(id);
        mv.addObject("aula", aula);

        return mv;
    }

    @RequestMapping("/deleteAula/{id}")
    public String deleteAula(@PathVariable(name = "id") int id) {
        aulaService.delete(id);
        return "redirect:/aulas";
    }
}
