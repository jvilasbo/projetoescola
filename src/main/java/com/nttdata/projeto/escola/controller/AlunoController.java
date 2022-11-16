package com.nttdata.projeto.escola.controller;

import com.nttdata.projeto.escola.dto.EscolaridadeRestDto;
import com.nttdata.projeto.escola.model.AlunoEntity;
import com.nttdata.projeto.escola.repository.rest.MinisterioRestRepository;
import com.nttdata.projeto.escola.service.AlunoService;
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

    @Autowired
    private MinisterioRestRepository minRep;

    @GetMapping("/alunos-model")
    public String viewAlunosHomePage(Model model) {
        List<AlunoEntity> alunos = alunoService.listAll();
        model.addAttribute("alunos", alunos);

        return "alunos/index"; //o Spring vai renderizar o arquivo templates/disciplinas/index.html
    }

    @GetMapping("/alunos")
    public ModelAndView viewAlunosHomePage2() {
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

    @GetMapping("/aluno/newAluno")
    public String showNewAlunoPage(Model model) {
        AlunoEntity aluno = new AlunoEntity();
        model.addAttribute("aluno", aluno);

        return "alunos/new_aluno";
    }

    //save working without validation of escolaridade
 /*   @RequestMapping(value = "/saveAluno", method = RequestMethod.POST)
    public String saveAluno(@ModelAttribute("aluno") AlunoEntity aluno) {

        alunoService.save(aluno);

        return "redirect:/alunos";
    }*/

    //save with validation
    @RequestMapping(value = "/saveAluno", method = RequestMethod.POST)
    public String saveAluno(@ModelAttribute("aluno") AlunoEntity aluno, Model m) {
        int idade = aluno.getIdade();
        String ano = aluno.getEscolaridade();
        //EscolaridadeRestDto verifiedEscolaridade = minRep.findByIdade(idade);
        EscolaridadeRestDto verifiedEscolaridade = new EscolaridadeRestDto(6, "1º Ano");
        if(ano.equals(verifiedEscolaridade.getAnoEscolar())){
            alunoService.save(aluno);
            return "redirect:/alunos";
        }
        m.addAttribute("error", "Username & Password Incorrectos");
        return "alunos/new_aluno";
    }

    /*@PostMapping(value = "/save")
    public String saveDisciplina(@ModelAttribute("disciplina") DisciplinaEntity disciplina) {
        disciplinaService.save(disciplina);

        return "redirect:/disciplinas";
    }*/

    @RequestMapping("/editAluno/{id}")
    public ModelAndView showEditAlunoPage(@PathVariable(name = "id") String id) {
        ModelAndView mv = new ModelAndView("alunos/edit_aluno");
        AlunoEntity aluno = alunoService.get(id);
        mv.addObject("aluno", aluno);

        return mv;
    }

    @RequestMapping("/deleteAluno/{id}")
    public String deleteAluno(@PathVariable(name = "id") String id) {
        alunoService.delete(id);
        return "redirect:/alunos";
    }
}
