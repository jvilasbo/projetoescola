package com.nttdata.projeto.escola.service;

import com.nttdata.projeto.escola.model.AulaEntity;
import com.nttdata.projeto.escola.repository.IAulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AulaService {

    @Autowired
    private IAulaRepository aulaRepository;


    public void save(AulaEntity aula) {
        aulaRepository.save(aula);
    }

    public AulaEntity getAula(int id) {
        return aulaRepository.findById(id).get();
    }

    /*public AulaEntity get(int id) {
        Optional<AulaEntity> disciplinaOptional = disciplinaRepository.findById(id);
        AulaEntity disciplina = null;
        if(disciplinaOptional.isPresent()){
            disciplina = disciplinaOptional.get();
        }
        return disciplina;
    }*/
    
    public List<AulaEntity> listAll() {
        return aulaRepository.findAll();
    }

    public void deleteById(int id) {
        aulaRepository.deleteById(id);
    }
}
