package com.ironhack.BankingSystem.Repository.security;


import com.ironhack.BankingSystem.Model.secutiry.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}