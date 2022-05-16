package com.ironhack.BankingSystem.Repository.Accounts;

import com.ironhack.BankingSystem.Model.Accounts.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
