#  BANKING SYSTEM 🏦🗝💸

![imagen_intro](img/imagen_intro.png)

---



## Midterm project

###  Author : Josefina Cresta

#### 21 de mayo de 2022

---

For this project, we buit a banking system. 
This bank have 4 types of accounts: 

- Checking 
- StudentChecking (less than 24 years old)
- Savings 
- CreditCard 

In the bank there are 3 types of person: Admins and AccountHolders and Thirdparty.
It is possible to see the activities allowed for each type of person in the following case diagram.

![imagen_intro](img/caseDiagram.png)

# API Endpoint & Methods

In order work in the bank system, the users have to login:

# Login

Used to collect a Bearer Token for a registered User.

**URL** : `/bank/login/`

**Method** : `GET`

**Auth required** : NO

**Query Params**

```json
{
    "username": "valid username",
    "password": "password in plain text"
}
```

**Data example**

```json
{
    "username": "james",
    "password": "1234"
}
```

**Success Response**

**Code** : `200 OK`

**Content example**

```json
{
    "access_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYW1lcyIsInJvbGVzIjpbIlJPTEVfQURNSU4iXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2JhbmsvbG9naW4iLCJleHAiOjE2NTMxNjg0MTF9.DM8loFdcS00Q704eqqWsV-tMuT1lOinQDvQWQQ5gE1c"
}
```

**Error Response**

**Condition** : If 'username' and 'password' combination is wrong.

**Code** : `403 Forbidden`

**Content** :

```json
{
    "non_field_errors": [
        "Error logging in."
    ]
}
```
---
---

Banking workers (Admins) can create the three types of accounts. 
with the followings endpoints:

# Saving Accounts

Used to create a new Saving account for a registered or not registered (but will create a new) client.

**URL** : `bank/accounts/newSavings`

**Method** : `POST`

**Auth required** : YES (Bearer Token)

**Data constraints**

Provide all info of Account to be created.

**Data example**

```json
{
    "balance": {
        "amount":100345.50,
        "currency":"USD"
    },
    "secretKey": "Jofi22 ",
    "penaltyFee": 111.74,
    "status": "ACTIVE",
    "primaryOwner":  {
        "id": 5,
        "name": "Josefina Cresta",
        "username": "jofi11",
        "password": "1234jogi",
        "roles": [],
        "birthDate": "1997-01-13",
        "primaryAddress": {
            "streetAddress" : "Montes de Oca",
            "city" : "Carlos Pellegrini",
            "country": "Argentina",
            "postalCode": "2453"
        },
        "mailingAddress": {
            "streetAddress" : "Montes de Oca",
            "city" : "Carlos Pellegrini",
            "country": "Argentina",
            "postalCode": "2453"
        }
    },
    "secondaryOwner" : "Aiko Tanaka"

}
```

**Success Response**

**Code** : `201 CREATED`

The new account is added to the bank's database.

---
---


# Credit Card Accounts

Used to create a new Credit Card account for a registered or not registered (but will create a new) client.

**URL** : `/bank/accounts/newCredit`

**Method** : `POST`

**Auth required** : YES (Bearer Token)

**Data constraints**

Provide all info of Account to be created.

**Data example**

```json
{
    "balance": {
        "amount":12345,
        "currency":"USD"
    },
    "secretKey": "LALi12 ",
    "primaryOwner":  {
        "id": 6,
        "name": "Lalita",
        "username": "TheOne",
        "password": "POp",
        "roles": [],
        "birthDate": "2000-01-13",
        "primaryAddress": null,
        "mailingAddress": null
    },
    "secondaryOwner" : "Peter",
    "creditLimit":{
        "amount" : 233,
        "currency": "EUR"
    },
    "interestRate": 0.15
}
```

**Success Response**

**Code** : `201 CREATED`

The new account is added to the bank's database.
