package com.nttdata.projeto.escola.service;

import com.nttdata.projeto.escola.model.DisciplinaEntity;
import com.nttdata.projeto.escola.repository.IDisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

    @Autowired
    private IDisciplinaRepository disciplinaRepository;


    public void save(DisciplinaEntity disciplina) {
        disciplinaRepository.save(disciplina);
    }

    public DisciplinaEntity get(int id) {
        return disciplinaRepository.findById(id).get();
    }

    /*public DisciplinaEntity get(int id) {
        Optional<DisciplinaEntity> disciplinaOptional = disciplinaRepository.findById(id);
        DisciplinaEntity disciplina = null;
        if(disciplinaOptional.isPresent()){
            disciplina = disciplinaOptional.get();
        }
        return disciplina;
    }*/
    
    public List<DisciplinaEntity> listAll() {
        return disciplinaRepository.findAll();
    }

    public void delete(int id) {
        disciplinaRepository.deleteById(id);
    }
    
    
}
