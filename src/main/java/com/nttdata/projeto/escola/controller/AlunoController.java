package com.nttdata.projeto.escola.controller;

import com.nttdata.projeto.escola.model.AlunoEntity;
import com.nttdata.projeto.escola.model.DisciplinaEntity;
import com.nttdata.projeto.escola.service.AlunoService;
import com.nttdata.projeto.escola.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/alunos-model")
    public String viewHomePage(Model model) {
        List<AlunoEntity> alunos = alunoService.listAll();
        model.addAttribute("alunos", alunos);

        return "alunos/index"; //o Spring vai renderizar o arquivo templates/disciplinas/index.html
    }

    @GetMapping("/alunos")
    public ModelAndView viewHomePage2() {
        List<AlunoEntity> alunos = alunoService.listAll();
        //model.addAttribute("listDisciplinas", listDisciplinas);
        /*DisciplinaEntity disciplina1 = new DisciplinaEntity("POO", "Tecnologia");
        disciplina1.setId(1);
        DisciplinaEntity disciplina2 = new DisciplinaEntity("PDS", "Tecnologia");
        disciplina2.setId(2);
        List<DisciplinaEntity> disciplinas = Arrays.asList(disciplina1,disciplina2);*/
        ModelAndView mv = new ModelAndView("alunos/index"); //nome do arquivo html que vai ser renderizado/exibido
        mv.addObject("alunos", alunos);
        return mv;
    }

    @GetMapping("/aluno/new")
    public String showNewAlunoPage(Model model) {
        AlunoEntity aluno = new AlunoEntity();
        model.addAttribute("aluno", aluno);

        return "alunos/new_aluno";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAluno(@ModelAttribute("aluno") AlunoEntity aluno) {

        alunoService.save(aluno);

        return "redirect:/alunos";
    }

    /*@PostMapping(value = "/save")
    public String saveDisciplina(@ModelAttribute("disciplina") DisciplinaEntity disciplina) {
        disciplinaService.save(disciplina);

        return "redirect:/disciplinas";
    }*/

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditAlunoPage(@PathVariable(name = "id") String id) {
        ModelAndView mv = new ModelAndView("alunos/edit_aluno");
        AlunoEntity aluno = alunoService.get(id);
        mv.addObject("aluno", aluno);

        return mv;
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") String id) {
        alunoService.delete(id);
        return "redirect:/alunos";
    }
}
