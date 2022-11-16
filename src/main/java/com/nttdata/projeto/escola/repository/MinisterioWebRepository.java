package com.nttdata.projeto.escola.repository;

import com.nttdata.projeto.escola.dto.DisciplinaRestDto;
import com.nttdata.projeto.escola.repository.rest.MinisterioRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MinisterioWebRepository {

    @Autowired
    MinisterioRestRepository ministerioRestRepository;

    public Optional<List<DisciplinaRestDto>> findAllDisciplinas() {

        Optional<List<DisciplinaRestDto>> listOptional = ministerioRestRepository.findAllDisciplinas();
        //if(listOptional.isPresent()){
            return listOptional;
        //}

    }
}
