# CurrencyExpenseTrackerMicroservice
CurrencyExpenseTrackerMicroservice

Endpoints

/exchange-rates

/limit

/transactions

/balance

Examples using Postman API

/exchange-rates

Gives the exchange rate at the closed value for the current date:

(GET) http://localhost:8080/exchange-rates

/limit

To set limit:

(POST) http://localhost:8080/limit and Body in JSON

{
"limitValue": 2000,
"limitCurrencyShortname": "USD"
}

To update limit:

(PUT) http://localhost:8080/limit/{limitSettingDate} and Body in JSON

{
"limitValue": 2000
}

/transactions

To create transaction:

(POST) http://localhost:8080/transactions and Body in JSON

{
"accountFrom": 1234567890,
"accountTo": 1987654321,
"currencyShortname": "USD",
"transactionAmount": 1000,
"category": "SERVICES"
}

To get transaction by date and time:

(GET) http://localhost:8080/transactions/dateTime=2023-11-24%2019%3A13%3A56.409967 

where dateTime=2023-11-24%2019%3A13%3A56.409967 request parameter with transaction date time in URL encoding

/balance

To get balance:

(GET) http://localhost:8080/balance/date=2023-11-24

where date=2023-11-24 List of transactions dates