package com.ironhack.BankingSystem.Model.Users;

import com.ironhack.BankingSystem.Model.secutiry.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "third_party")
public class ThirdParty extends User{

   /* @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long thirdPartyId;

    private String name;*/

    private String HashedKey;


}
