package com.ironhack.BankingSystem.Model.Users;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Embeddable
public class Address {
    private String streetAddress;
    private String city;
    private String country;
    private String postalCode;

}