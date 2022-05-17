package com.ironhack.BankingSystem.Repository.Users;

import com.ironhack.BankingSystem.Model.Users.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}
