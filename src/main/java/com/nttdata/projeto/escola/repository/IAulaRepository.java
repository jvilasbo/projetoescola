package com.nttdata.projeto.escola.repository;

import com.nttdata.projeto.escola.model.AulaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAulaRepository extends JpaRepository<AulaEntity, Integer> {

}
