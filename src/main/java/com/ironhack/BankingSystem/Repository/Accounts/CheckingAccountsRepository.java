package com.ironhack.BankingSystem.Repository.Accounts;

import com.ironhack.BankingSystem.Model.Accounts.CheckingAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingAccountsRepository extends JpaRepository<CheckingAccounts, Long> {
}
