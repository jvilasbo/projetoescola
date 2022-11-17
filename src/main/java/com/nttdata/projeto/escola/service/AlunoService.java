package com.nttdata.projeto.escola.service;

import com.nttdata.projeto.escola.model.AlunoEntity;
import com.nttdata.projeto.escola.repository.IAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private IAlunoRepository alunoRepository;


    public void save(AlunoEntity aluno) {
        alunoRepository.save(aluno);
    }

    public AlunoEntity getAluno(String id) {
        return alunoRepository.findById(id).get();
    }

    /*public AlunoEntity get(String id) {
        Optional<AlunoEntity> alunoOptional = alunoRepository.findById(id);
        AlunoEntity aluno = null;
        if(alunoOptional.isPresent()){
            aluno = alunoOptional.get();
        }
        return aluno;
    }*/

    public List<AlunoEntity> listAll() {
        return alunoRepository.findAll();
    }

    public boolean deleteById(String id) {
        try {
            alunoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
