package com.nttdata.projeto.escola.repository;


import com.nttdata.projeto.escola.model.DisciplinaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDisciplinaRepository extends JpaRepository<DisciplinaEntity, Integer> {

    //DisciplinaEntity findDisciplinaByTitulo(String titulo);
    boolean existsByTitulo(String titulo);


}
