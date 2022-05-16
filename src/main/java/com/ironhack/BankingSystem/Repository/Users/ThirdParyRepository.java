package com.ironhack.BankingSystem.Repository.Users;

import com.ironhack.BankingSystem.Model.Users.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThirdParyRepository extends JpaRepository<ThirdParty, Long> {
}
