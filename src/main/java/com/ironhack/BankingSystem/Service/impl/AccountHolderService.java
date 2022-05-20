package com.ironhack.BankingSystem.Service.impl;

import com.ironhack.BankingSystem.DTO.TransactionDTO;
import com.ironhack.BankingSystem.Model.Accounts.Account;
import com.ironhack.BankingSystem.Model.Users.AccountHolder;
import com.ironhack.BankingSystem.Model.Utils.Money;
import com.ironhack.BankingSystem.Model.secutiry.User;
import com.ironhack.BankingSystem.Repository.Accounts.AccountRepository;
import com.ironhack.BankingSystem.Repository.Users.AccountHolderRepository;
import com.ironhack.BankingSystem.Repository.security.UserRepository;
import com.ironhack.BankingSystem.Service.interfaces.AccountHolderServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountHolderService implements AccountHolderServiceInterface {

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;


    //y aca iran los metodos para la third party

    public AccountHolder getAccountHolderById(Long id) {
        Optional<AccountHolder> foundAccountHolder = accountHolderRepository.findById(id);
        if(foundAccountHolder.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No AccountHolder found with that ID");
        } else {
            return foundAccountHolder.get();
        }
    }

    public void saveAccountHolder(AccountHolder accountHolder) {
        try {
            accountHolderRepository.save(accountHolder);
            //accountHolderRepository.save(new AccountHolder(accountHolder.getName()));
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Malformed AccountHolder");
        }
    }

    public void updateAccountHolder(Long id, AccountHolder AccountHolder) {
        Optional<AccountHolder> foundAccountHolder = accountHolderRepository.findById(id);
        if(foundAccountHolder.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No AccountHolder found with that ID");
        } else {
            if(!AccountHolder.getName().isEmpty()){
                //foundAccountHolder.get().setName(AccountHolder.getName());
                accountHolderRepository.save(AccountHolder);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "AccountHolder Name cannot be blank");
            }
        }
    }

    public void deleteAccountHolder(Long id) {
        Optional<AccountHolder> foundAccountHolder = accountHolderRepository.findById(id);
        if(foundAccountHolder.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No AccountHolder found with that ID");
        } else {
            accountHolderRepository.delete(foundAccountHolder.get());
        }
    }

    @Override
    public Money getBalance(Long accountHolderId, Long accountId) {
        Account accountFromDB = accountRepository.findById(accountId).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        if (accountHolderId == accountFromDB.getPrimaryOwner().getId()){
            System.out.println("The balance of your account with id "+ accountId.toString() + " is");
            System.out.println(accountFromDB.getBalance().toString());
            return accountFromDB.getBalance();
        }else{
            System.out.println("You can't see the balance of this account");
        }
        Money nulo = new Money(new BigDecimal(0));
        return nulo;
    }

    //Funciona
   /* @Override
    public void transaction(Long withdrawId, Long targetId, TransactionDTO transactionDTO) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
        } else {
            String username = principal.toString();
        }

        Account withdrawAccountFromDB = accountRepository.findById(withdrawId).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "WithdrawId Account not found"));
        Account targetAccountFromDB = accountRepository.findById(targetId).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Target Account not found"));

        BigDecimal transactionAmount = transactionDTO.getTransactionAmount().getAmount();
        BigDecimal withdrawBalanceAmount = withdrawAccountFromDB.getBalance().getAmount();
        BigDecimal targetBalanceAmount = targetAccountFromDB.getBalance().getAmount();

        if (withdrawAccountFromDB.getBalance().getAmount().compareTo(transactionAmount)==1){

            BigDecimal newBalanceAmountWithdraw= withdrawBalanceAmount.subtract(transactionAmount);
            withdrawAccountFromDB.setBalance(new Money(newBalanceAmountWithdraw));
            accountRepository.save(withdrawAccountFromDB);

            BigDecimal newBalanceAmountTarget= targetBalanceAmount.add(transactionAmount);
            targetAccountFromDB.setBalance(new Money(newBalanceAmountTarget));
            accountRepository.save(withdrawAccountFromDB);

            System.out.println("TRANSACTION DONE");
        }else{
            throw new UnsupportedOperationException("Not enough funds to carry out the transaction");
        }

    }*/

    public void transaction(Long targetId, TransactionDTO transactionDTO) {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        Long userId = userRepository.findByUsername(username).getId();
        Long withdrawId = accountHolderRepository.findById(userId).get().getAccountList().get(0).getAccountId();


        System.out.println(withdrawId);

        Account withdrawAccountFromDB = accountRepository.findById(withdrawId).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "WithdrawId Account not found"));
        Account targetAccountFromDB = accountRepository.findById(targetId).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Target Account not found"));

        BigDecimal transactionAmount = transactionDTO.getTransactionAmount().getAmount();
        BigDecimal withdrawBalanceAmount = withdrawAccountFromDB.getBalance().getAmount();
        BigDecimal targetBalanceAmount = targetAccountFromDB.getBalance().getAmount();

        if (withdrawAccountFromDB.getBalance().getAmount().compareTo(transactionAmount)==1){

            BigDecimal newBalanceAmountWithdraw= withdrawBalanceAmount.subtract(transactionAmount);
            withdrawAccountFromDB.setBalance(new Money(newBalanceAmountWithdraw));
            accountRepository.save(withdrawAccountFromDB);

            BigDecimal newBalanceAmountTarget= targetBalanceAmount.add(transactionAmount);
            targetAccountFromDB.setBalance(new Money(newBalanceAmountTarget));
            accountRepository.save(withdrawAccountFromDB);
            log.info("TRANSACTION DONE", username);
        }else{
            throw new UnsupportedOperationException("Not enough funds to carry out the transaction");
        }

    }





}
