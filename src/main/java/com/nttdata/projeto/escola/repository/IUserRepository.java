package com.nttdata.projeto.escola.repository;

import com.nttdata.projeto.escola.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository  extends JpaRepository<UserEntity,Integer> {

    UserEntity getReferenceByUsername(String username);
}
