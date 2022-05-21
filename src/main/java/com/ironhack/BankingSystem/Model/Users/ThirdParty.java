package com.ironhack.BankingSystem.Model.Users;

import com.ironhack.BankingSystem.Model.Utils.ShaHash;
import com.ironhack.BankingSystem.Model.secutiry.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static java.nio.charset.StandardCharsets.UTF_8;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "third_party")
public class ThirdParty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long thirdPartyId;

    private String name;

    private byte[] hashedKey;


    public void setHashedKey(byte[] hashedKey) {
        String algorithm = "SHA3-256";
        String pText = "ThirdParty";
        this.hashedKey = ShaHash.digest(pText.getBytes(UTF_8), algorithm);;
    }
}
