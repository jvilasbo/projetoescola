package com.nttdata.projeto.escola.service;

import com.nttdata.projeto.escola.dto.DisciplinaRestDto;
import com.nttdata.projeto.escola.dto.EscolaridadeRestDto;
import com.nttdata.projeto.escola.repository.MinisterioWebRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MinisterioService {

    @Autowired
    MinisterioWebRepository ministerioWebRepository;

    public List<EscolaridadeRestDto> listAllEscolaridades() {
        return ministerioWebRepository.findAllEscolaridades().get();
    }

    public EscolaridadeRestDto findEscolaridadeByIdade (int idade) {
        return ministerioWebRepository.findEscolaridadeByIdade(idade).get();
    }

    public List<DisciplinaRestDto> listAllDisciplinas() {
        return ministerioWebRepository.findAllDisciplinas().get();
    }

    public List<String> listAllDistinctArea() {
        return ministerioWebRepository.findAllDistinctArea();
    }
}
