package com.ironhack.BankingSystem.Repository.Accounts;

import com.ironhack.BankingSystem.Model.Accounts.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsRepository extends JpaRepository<Savings, Long> {
}
