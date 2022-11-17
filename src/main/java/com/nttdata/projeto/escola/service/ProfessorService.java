package com.nttdata.projeto.escola.service;

import com.nttdata.projeto.escola.model.ProfessorEntity;
import com.nttdata.projeto.escola.repository.IProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private IProfessorRepository professorRepository;


    public void save(ProfessorEntity professor) {
        professorRepository.save(professor);
    }

    public ProfessorEntity getProfessor(String id) {
        return professorRepository.findById(id).get();
    }

    /*public ProfessorEntity get(int id) {
        Optional<ProfessorEntity> disciplinaOptional = disciplinaRepository.findById(id);
        ProfessorEntity disciplina = null;
        if(disciplinaOptional.isPresent()){
            disciplina = disciplinaOptional.get();
        }
        return disciplina;
    }*/

    public List<ProfessorEntity> listAll() {
        return professorRepository.findAll();
    }

    public boolean deleteById(String id) {
        try {
            professorRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
