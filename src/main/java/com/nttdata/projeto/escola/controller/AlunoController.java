package com.nttdata.projeto.escola.controller;

import com.nttdata.projeto.escola.dto.EscolaridadeRestDto;
import com.nttdata.projeto.escola.model.AlunoEntity;
import com.nttdata.projeto.escola.model.DisciplinaEntity;
import com.nttdata.projeto.escola.repository.rest.MinisterioRestRepository;
import com.nttdata.projeto.escola.service.AlunoService;
import com.nttdata.projeto.escola.service.MinisterioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.StringCharacterIterator;
import java.util.List;

@Controller
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private MinisterioService ministerioService;

    @GetMapping("/alunos-model")
    public String viewAlunosHomePage(Model model) {
        List<AlunoEntity> alunos = alunoService.listAll();
        model.addAttribute("alunos", alunos);

        return "alunos/index"; //o Spring vai renderizar o arquivo templates/disciplinas/index.html
    }

    @GetMapping("/alunos")
    public ModelAndView viewAlunosHomePage2() {
        List<AlunoEntity> alunos = alunoService.listAll();
        ModelAndView mv = new ModelAndView("alunos/index"); //nome do arquivo html que vai ser renderizado/exibido
        mv.addObject("alunos", alunos);
        return mv;
    }

    @GetMapping("/aluno/newAluno")
    public String showNewAlunoPage(Model model) {
        AlunoEntity aluno = new AlunoEntity();
        model.addAttribute("aluno", aluno);

        List<EscolaridadeRestDto> escolaridades = ministerioService.listAllEscolaridades();
        model.addAttribute("escolaridades", escolaridades);

        return "alunos/new_aluno";
    }

    //save with validation
    @RequestMapping(value = "/saveAluno", method = RequestMethod.POST)
    public String saveAluno(@ModelAttribute("aluno") AlunoEntity aluno, Model m) {
        int idade = aluno.getIdade();
        String ano = aluno.getEscolaridade();

        String[] array1 = ano.split("º ");
        int anoIntroduced = Integer.parseInt(array1[0]);

       if(idade > 17){
           idade = 17;
       }
        EscolaridadeRestDto verifiedEscolaridade = ministerioService.findEscolaridadeByIdade(idade);

        String[] array = verifiedEscolaridade.getAnoEscolar().split("º ");
        int anoVerified = Integer.parseInt(array[0]);

        if(anoIntroduced <= anoVerified) {
            alunoService.save(aluno);
            return "redirect:/alunos";
        }
        m.addAttribute("error", "Username & Password Incorrectos");
        showNewAlunoPage(m);
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
        AlunoEntity aluno = alunoService.getAluno(id);
        mv.addObject("aluno", aluno);

        List<EscolaridadeRestDto> escolaridades = ministerioService.listAllEscolaridades();
        mv.addObject("escolaridades", escolaridades);

        return mv;
    }

    @RequestMapping(value = "/saveAfterEditAluno", method = RequestMethod.POST)
    public String saveAfterEditAluno(@ModelAttribute("aluno") AlunoEntity aluno, Model m) {
        int idade = aluno.getIdade();
        String ano = aluno.getEscolaridade();

        String[] array1 = ano.split("º ");
        int anoIntroduced = Integer.parseInt(array1[0]);

        if(idade > 17){
            idade = 17;
        }
        EscolaridadeRestDto verifiedEscolaridade = ministerioService.findEscolaridadeByIdade(idade);

        String[] array = verifiedEscolaridade.getAnoEscolar().split("º ");
        int anoVerified = Integer.parseInt(array[0]);

        if(anoIntroduced <= anoVerified) {
            alunoService.save(aluno);
            return "redirect:/alunos";
        }
        m.addAttribute("error", "Username & Password Incorrectos");
        List<EscolaridadeRestDto> escolaridades = ministerioService.listAllEscolaridades();
        m.addAttribute("escolaridades", escolaridades);

        return "alunos/edit_aluno";
    }

    @RequestMapping("/deleteAluno/{id}")
    public String deleteAluno(@PathVariable(name = "id") String id, Model m) {
        if(alunoService.deleteById(id)){
            List<AlunoEntity> alunos = alunoService.listAll();
            m.addAttribute("alunos", alunos);
            m.addAttribute("error4", "Username & Password Incorrectos");
            return "alunos/index";
        }else{
            m.addAttribute("error", "Username & Password Incorrectos");
            List<AlunoEntity> alunos = alunoService.listAll();
            m.addAttribute("alunos", alunos);
            return "alunos/index";
        }
    }
}
