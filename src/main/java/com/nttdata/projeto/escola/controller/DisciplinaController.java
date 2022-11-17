package com.nttdata.projeto.escola.controller;

import com.nttdata.projeto.escola.dto.DisciplinaRestDto;
import com.nttdata.projeto.escola.model.DisciplinaEntity;
import com.nttdata.projeto.escola.service.DisciplinaService;
import com.nttdata.projeto.escola.service.MinisterioService;
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
    @Autowired
    private MinisterioService ministerioService;

    @GetMapping("/disciplinas-model")
    public String viewHomePage(Model model) {
        List<DisciplinaEntity> disciplinas = disciplinaService.listAll();
        model.addAttribute("disciplinas", disciplinas);

        return "disciplinas/index"; //o Spring vai renderizar o arquivo templates/disciplinas/index.html
    }

    @GetMapping("/disciplinas")
    public ModelAndView viewHomePage2() {
        List<DisciplinaEntity> disciplinas = disciplinaService.listAll();
        ModelAndView mv = new ModelAndView("disciplinas/index"); //nome do arquivo html que vai ser renderizado/exibido
        mv.addObject("disciplinas", disciplinas);
        return mv;
    }

    @GetMapping("/disciplina/new")
    public String showNewDisciplinaPage(Model model) {
        DisciplinaEntity disciplina = new DisciplinaEntity();
        model.addAttribute("disciplina", disciplina);

        List<DisciplinaRestDto> disciplinasMinisterio = ministerioService.listAllDisciplinas();
        model.addAttribute("disciplinasMinisterio", disciplinasMinisterio);

        List<String> areasMinisterio = ministerioService.listAllDistinctArea();
        model.addAttribute("areasMinisterio", areasMinisterio);

        return "disciplinas/new_disciplina";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveDisciplina(@ModelAttribute("disciplina") DisciplinaEntity disciplina, Model m) throws Exception {
        if (disciplinaService.save(disciplina).equals("Ok")) {
            return "redirect:/disciplinas";
        } else {
            if (disciplinaService.save(disciplina).equals("Existe")) {
                m.addAttribute("error1", "Username & Password Incorrectos");
                showNewDisciplinaPage(m);
                return "disciplinas/new_disciplina";
            }
            if (disciplinaService.save(disciplina).equals("Area")) {
                m.addAttribute("error2", "Username & Password Incorrectos");
                showNewDisciplinaPage(m);
                return "disciplinas/new_disciplina";
            }
        }
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
        DisciplinaEntity disciplina = disciplinaService.getDisciplina(id);
        mv.addObject("disciplina", disciplina);

        List<DisciplinaRestDto> disciplinasMinisterio = ministerioService.listAllDisciplinas();
        mv.addObject("disciplinasMinisterio", disciplinasMinisterio);

        List<String> areasMinisterio = ministerioService.listAllDistinctArea();
        mv.addObject("areasMinisterio", areasMinisterio);

        return mv;
    }

    @RequestMapping("/delete/{id}")
    public String deleteDisciplina(@PathVariable(name = "id") int id, Model m) {
        if(disciplinaService.deleteById(id)){
            List<DisciplinaEntity> disciplinas = disciplinaService.listAll();
            m.addAttribute("disciplinas", disciplinas);
            m.addAttribute("error4", "Username & Password Incorrectos");
            return "disciplinas/index";
        }else{
            m.addAttribute("error", "Username & Password Incorrectos");
            List<DisciplinaEntity> disciplinas = disciplinaService.listAll();
            m.addAttribute("disciplinas", disciplinas);
            return "disciplinas/index";
        }


    }
}
