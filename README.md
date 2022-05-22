# BANKING SYSTEM 🏦🗝💸

## Midterm project

### Author : Josefina Cresta

#### 21 de mayo de 2022

![imagen_intro](img/HELP.jpeg)

---

For this project, we build a banking system.
This bank have 4 types of accounts:

- Checking
- StudentChecking (less than 24 years old)
- Savings
- CreditCard

In the bank there are 3 types of person: Admins and Account Holders and Third party.
It is possible to see the activities allowed for each type of person in the following case diagram.

![Case diagram](img/casediag.png)

The attributes of each system object can be seen in the following class diagram

![Class Diagram](img/classdiag.png)

# API Endpoint & Methods

In order work in the bank system, the users have to login:

```diff
+ Login
```

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

Banking workers (Admins) can create the three types of accounts with the followings endpoints:

```diff
! Checking Accounts
```

Used to create a new Checking account for a registered or not registered (but will create a new) client.
If the owner is less than 24 years old, a Student Checking account is created.
This accounts have:
    - A balance
    - A secretKey
    - A PrimaryOwner
    - An optional SecondaryOwner
    - A penaltyFee
    - A creationDate
    - A status (FROZEN, ACTIVES

**URL** : `/bank/accounts/newChecking`

**Method** : `POST`

**Auth required** : YES (Bearer Token)

**Data constraints**

Provide all info of Account to be created.

```json
{
    "balance": "Money Object",
    "secretKey": "String code",
    "primaryOwner":  "AccountHolder Object",
        "mailingAddress": "Address Object",
    "secondaryOwner" : "Name"
}
```

**Data example**

```json
{
    "balance": {
        "amount":12345,
        "currency":"USD"
    },
    "secretKey": "Jofi22 ",
    "primaryOwner":  {
        "id": 5,
        "name": "Josefina Cresta",
        "username": "jofi11",
        "password": "1234jogi",
        "roles": [],
        "birthDate": "1997-01-1",
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

If the cliente is more than 24 years old, a new checking account with minimum balance of 250 and a
monthly maintenance fee of 12, is added to the bank's database.
Otherwise, when the client ist younger, a new Student Account is created without monthly maintenance fee neither  minimum balance.


---
---

```diff
! Saving Accounts
```

Used to create a new Saving account for a registered or not registered (but will create a new) client. Savings are identical to Checking accounts except that they do not have a monthly maintenance fee, but they have an interest rate.

**URL** : `bank/accounts/newSavings`

**Method** : `POST`

**Auth required** : YES (Bearer Token)

**Data constraints**

Provide all info of Account to be created.

```json
{
    "balance": "Money Object",
    "secretKey": "String code",
    "penaltyFee": "double",
    "primaryOwner":  "AccountHolder Object",
    "mailingAddress": "Address Object",
    "secondaryOwner" : "Name"
}
```

**Data example**

```json
{
    "balance": {
        "amount":100345.50,
        "currency":"USD"
    },
    "secretKey": "Jofi22 ",
    "penaltyFee": 111.74,
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

The new Saving account with default interest rate of 0.0025 and  minimum balance of 1000, is added to the bank's database.

---
---

```diff
! Credit Card Accounts
```

Used to create a new Credit Card account for a registered or not registered (but will create a new) client. This accounts propeties like the others accounts, and also have:
    - A creditLimit
    - An interestRate
    - A penaltyFee

**URL** : `/bank/accounts/newCredit`

**Method** : `POST`

**Auth required** : YES (Bearer Token)

**Data constraints**

Provide all info of Account to be created.

```json
{
    "balance": "Money Object",
    "secretKey": "String code",
    "penaltyFee": "double",
    "primaryOwner":  "AccountHolder Object",
    "mailingAddress": "Address Object",
    "secondaryOwner" : "Name",
    "interestRate": 0.15
}
```

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

The new Credit card account with default credit Limit of 100 and interest Rate of 0.2 is added to the bank's database.

---
---

Admins can also store a potential account Holder, get information about the clients, modify information of the clients and remove clients from the data base of the bank.

```diff
! Store new client
```

Used to store in the data base a new client with name, date of birth, primary address and an optional mailingAddress.

**URL** : `/bank/accountholders`

**Method** : `POST`

**Auth required** : YES (Bearer Token)

**Data constraints**

Provide all info of client to be created.

```json
{
    "name": "Name",
    "username": "Username",
    "password": "pass",
    "roles": "Collection of roles to add to user",
    "birthDate": "LocalDate",
    "primaryAddress": "Valid adress",
        "mailingAddress": "Valid adress"
}
```

**Data example**

```json
{
    "name": "Chapi CHapi",
    "username": "menea",
    "password": "420Cum",
    "roles": ["ROLE_ACCOUNT_HOLDER"],
    "birthDate": "1997-12-11",
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
}
```

**Success Response**

**Code** : `201 CREATED`

The new client is added to the bank's database.

---
---

```diff
+ Lookup information
```

Used to obtain customer information.
**URL** : `/bank/accountholders/{id}`
`

**Method** : `GET`

**Auth required** : YES

**Path Params**

`"id" = "id of the customer"`

**Success Response**

**Code** : `200 OK`

**Content example**

```json
{
    "id": 5,
    "name": "Josefina",
    "username": "jofi",
    "password": "$2a$10$ZlsxMiR5OMmW7q3rfiGIiOyxOHKsiUoCaiQTag2cpmZm9VkeT8g4m",
    "roles": [
        {
            "id": 3,
            "name": "ROLE_ACCOUNT_HOLDER"
        }
    ],
    "birthDate": "1997-01-13",
    "primaryAddress": {
            "streetAddress" : "Montes de Oca",
            "city" : "Carlos Pellegrini",
            "country": "Argentina",
            "postalCode": "2453"
        },
    "mailingAddress": null
}
```

---
---

```diff
- Add information
```

Used to add information to an existing customer.
**URL** : `/bank/accountholders/{id}`
`

**Method** : `PUT`

**Auth required** : YES

**Permissions required** : Admins

**Path Params**

`"id" = "id of the customer"`

**Data example**

```json
{
    "mailingAddress": {
    "streetAddress": "Montes de Oca",
    "city": "Carlos Pellegrini",
    "country": "Argentina",
    
    "postalCode": "2453"
  }
}
```

**Success Response**

**Code** : `204 No Content`

---
---

```diff
- Delete a customer
```
Used to delete an existing customer.
**URL** : `/bank/accountholders/{id}`
`

**Method** : `DELETE`

**Auth required** : YES

**Permissions required** : Admins

**Path Params**

`"id" = "id of the customer"`

**Success Response**

**Code** : `204 No Content`

---
---

The Account Holders can access their own accounts and only their accounts when passing the correct credentials using Bearer Auth. They can see the balance of their account and they can make transactions.


```diff
+ Check Balance
```

Used to check balance of an account. The account must belong to the user making the query

**URL** : `/bank/accountholders/balance`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : Admins and Account Holders

**Query Params**

```json
{
    "holder_id": "valid account holder id",
    "account_id": "valid account id"
}
```

**Data example**

```json
{
    "username": "6",
    "password": "3"
}
```

**Success Response**

**Code** : `200 OK`

**Content example**

```json
{
    "The balance of your account with id 10 is": 1245.23
}
```


**Error Response**

**Condition** : if the account does not belong to the query.

**Code** : `403 Forbidden`

**Content** :

```json
{
    "error": [
        "You can't see the balance of this account"
    ]
}
```

---
---

```diff
@@ Transactions @@
```

The currently Authenticated User makes a transaction to the specified account for a specified amount.

**URL** : `/bank/accountholders/transactions`
`

**Method** : `PATCH`

**Auth required** : YES

**Permissions required** : Account User

**Query Params**

```json
{
    "target_id": "Id of an existing account"
}
```

**Data example**

```json
{
    "target_id": "4"
}
```


**Data constraints**

Provide the transaction amount and currency.

```json
{
    "transactionAmount": "transaction object"
}
```

**Data example**

```json
{
    "transactionAmount":{
        "amount" : 1000,
        "currency": "EUR"
    }
}
```

**Success Response**

**Code** : `204 No Content`

**Error Response**

**Condition** : if the account does not belong to the query.

**Code** : `403 Forbidden`

**Content** :

```json
{
    "error": [
        "Not enough funds to carry out the transaction"
    ]
}
```


---
---

```diff
@@ Transaction from account to account @@
```

Bankers can make transactions from one account to another without needing to be logged in as clients

**URL** : `/bank/accountholders/transactions/accountToAccount`
`

**Method** : `PATCH`

**Auth required** : YES

**Permissions required** : Admis

**Query Params**

```json
{
    "account_id": "accountHolderId",
    "target_id": "targetId",
}
```

**Data example**

```json
{
    "account_id": "5",
    "target_id": "1",
}
```

**Data constraints**

Provide the transaction amount and currency.

```json
{
    "transactionAmount": "transaction object"
}
```

**Data example**

```json
{
    "transactionAmount":{
        "amount" : 1000,
        "currency": "EUR"
    }
}
```

**Success Response**

**Code** : `204 No Content`

**Error Response**

**Condition** : if the account does not belong to the query.

**Code** : `403 Forbidden`

**Content** :

```json
{
    "error": [
        "Not enough funds to carry out the transaction"
    ]
}
```

---

# Future Work

The system made is for a bank that is in its first days of work. Many things must be improved. The team will continue working in the interaction with accounts of other banks, the detection of fraud, the offer of investment products, among other things. Every effort will be made to achieve a higher quality of service for our customers.

---
---

# Resources

- OpenCamp Java Bootcamp Ironhack Lessons by Raymond and Shaun. Thank you very much for your help and support.

- [hibernate-inheritance](https://www.baeldung.com/hibernate-inheritance).

- [data-authentication](https://freecontent.manning.com/data-authentication-with-keyed-hashing/).
[sha-256](https://www.baeldung.com/sha-256-hashing-java).

- [sha-hashing](https://mkyong.com/java/java-sha-hashing-example/).

- [java-crypto](
https://github.com/mkyong/core-java/tree/master/java-crypto/src/main/java/com/mkyong/crypto/hash).

- [REST API Documentation Templates](https://github.com/jamescooke/restapidocs).
