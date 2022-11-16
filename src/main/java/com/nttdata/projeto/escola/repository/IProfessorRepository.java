package com.nttdata.projeto.escola.repository;

import com.nttdata.projeto.escola.model.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfessorRepository extends JpaRepository<ProfessorEntity, String> {
}
