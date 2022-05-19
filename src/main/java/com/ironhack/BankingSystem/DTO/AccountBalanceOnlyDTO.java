package com.ironhack.BankingSystem.DTO;

import com.ironhack.BankingSystem.Model.Utils.Money;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import lombok.Data;

@Data
public class AccountBalanceOnlyDTO {

    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "balance_DTO_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "balance_DTO_amount"))
    })
    @Embedded
    private Money balance;


}
