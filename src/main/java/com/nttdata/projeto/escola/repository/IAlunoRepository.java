package com.nttdata.projeto.escola.repository;


import com.nttdata.projeto.escola.model.AlunoEntity;
import com.nttdata.projeto.escola.model.DisciplinaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAlunoRepository extends JpaRepository<AlunoEntity, String> {
}
