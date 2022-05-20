package com.ironhack.BankingSystem.DTO;

import com.ironhack.BankingSystem.Model.Utils.Money;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "transaction_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "transaction_amount"))
    })
    @Embedded
    private Money transactionAmount;

}

