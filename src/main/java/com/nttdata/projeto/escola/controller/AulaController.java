package com.nttdata.projeto.escola.controller;

import com.nttdata.projeto.escola.model.AlunoEntity;
import com.nttdata.projeto.escola.model.AulaEntity;
import com.nttdata.projeto.escola.model.ProfessorEntity;
import com.nttdata.projeto.escola.service.AlunoService;
import com.nttdata.projeto.escola.service.AulaService;
import com.nttdata.projeto.escola.service.ProfessorService;
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
    @Autowired
    private ProfessorService professorService;
    @Autowired
    private AlunoService alunoService;

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

        List<ProfessorEntity> professores = professorService.listAll();
        model.addAttribute("professores", professores);

        List<AlunoEntity> alunos = alunoService.listAll();
        model.addAttribute("alunos", alunos);

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
        AulaEntity aula = aulaService.getAula(id);
        mv.addObject("aula", aula);

        List<ProfessorEntity> professores = professorService.listAll();
        mv.addObject("professores", professores);

        List<AlunoEntity> alunos = alunoService.listAll();
        mv.addObject("alunos", alunos);

        return mv;
    }

    @RequestMapping("/deleteAula/{id}")
    public String deleteAula(@PathVariable(name = "id") int id, Model m) {
        if(aulaService.deleteById(id)){
            List<AulaEntity> aulas = aulaService.listAll();
            m.addAttribute("aulas", aulas);
            m.addAttribute("error4", "Username & Password Incorrectos");
            return "aulas/index";
        }else{
            m.addAttribute("error", "Username & Password Incorrectos");
            List<AulaEntity> aulas = aulaService.listAll();
            m.addAttribute("aulas", aulas);
            return "aulas/index";
        }

    }
}
