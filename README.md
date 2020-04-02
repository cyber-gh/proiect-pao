Proiect PAO

Clasa de serviciu este banca

Banca are o lista de clienti, si o lista de valute

Clientii au mai multe conturi, in valute diferite

Ei pot schimba valuta dintr-un cont in altul

Toate tranzactiile sunt memorate intr-o lista

Rata de conversie intre valute se tine intr-un Hashmap



Cel putin 8 clase:<br>
```java
BalanceAccount
Bank
Client
CompanyClient
SimpleClient
Transfer
CompanyTransfer
Currency
```
10 interogari <br>
```java
Bank.getCurrencyById()
Bank.getExchangeRate()
Bank.addClient()
Bank.getClientById()
Bank.removeClient()
Client.registerAccount()
Client.makeTransfer()
Client.getLastTransfer()
Client.getTotalBalanceInCurrency()
Client.getTransfersBiggerThan()
```
