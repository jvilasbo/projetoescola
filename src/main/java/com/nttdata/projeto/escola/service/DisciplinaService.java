package com.nttdata.projeto.escola.service;

import com.nttdata.projeto.escola.dto.DisciplinaRestDto;
import com.nttdata.projeto.escola.model.DisciplinaEntity;
import com.nttdata.projeto.escola.repository.IDisciplinaRepository;
import com.nttdata.projeto.escola.repository.MinisterioWebRepository;
import com.nttdata.projeto.escola.repository.rest.MinisterioRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    private IDisciplinaRepository disciplinaRepository;

    @Autowired
    private MinisterioWebRepository ministerioWebRepository;


    public void save(DisciplinaEntity disciplina) throws Exception {
        Optional<List<DisciplinaRestDto>> listOptional = ministerioWebRepository.findAllDisciplinas();

        if(listOptional.isPresent()){
            for(DisciplinaRestDto d : listOptional.get()){
                if(d.getTitulo().equals(disciplina.getTitulo())){
                    disciplinaRepository.save(disciplina);
                    break;
                }
            }
        }
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
