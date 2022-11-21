package com.nttdata.projeto.escola.service;

import com.nttdata.projeto.escola.dto.DisciplinaRestDto;
import com.nttdata.projeto.escola.model.DisciplinaEntity;
import com.nttdata.projeto.escola.repository.IDisciplinaRepository;
import com.nttdata.projeto.escola.repository.MinisterioWebRepository;
import com.nttdata.projeto.escola.repository.rest.MinisterioRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    private IDisciplinaRepository disciplinaRepository;

    @Autowired
    private MinisterioWebRepository ministerioWebRepository;

    public String save(DisciplinaEntity disciplina) throws Exception {
        String titulo = disciplina.getTitulo();
        boolean verifiedDisciplina = disciplinaRepository.existsByTitulo(titulo);

        DisciplinaRestDto disciplinaVerified = ministerioWebRepository.findDisciplinaByTitulo(titulo).get();

        if (!verifiedDisciplina && disciplina.getArea().equals(disciplinaVerified.getArea())) {
            disciplinaRepository.save(disciplina);
            return "Ok";
        }else{
            if(verifiedDisciplina){
                return "Existe";
            }if(!disciplina.getArea().equals(disciplinaVerified.getArea())){
                return "Area";
            }return "";
        }
    }

    public DisciplinaEntity getDisciplina(int id) {
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

    public boolean deleteById(int id) {
        try{
            disciplinaRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }


}
