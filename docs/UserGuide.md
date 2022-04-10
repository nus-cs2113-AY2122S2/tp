# MindMyMoney User Guide

## Content Page

* [Introduction](#introduction)
* [Quick start](#quick-start)
* [Features](#features)
    * [Expenditure](#expenditure)
        * [Display help page for expenditures: `help`](#display-help-page-for-expenditures-help)
        * [Add an expenditure: `add`](#add-an-expenditure-add)
        * [Display expenditures: `list` ](#display-expenditures-list)
        * [Modify an expenditure: `update`](#modify-an-expenditure-update)
        * [Remove an expenditure: `delete`](#remove-an-expenditure-delete)
        * [Calculations that MindMyMoney provide: `calculate`](#calculate-expenditures-calculate)
            * [Expenditure per month: `calculate /epm`](#expenditure-per-month-calculate-epm)
    * [Credit Card](#credit-card)
        * [Display help page for credit cards: `help`](#display-help-page-for-credit-cards-help)
        * [Add a credit card: `add`](#add-a-credit-card-add)
        * [Display credit cards: `list` ](#display-credit-cards-list)
        * [Modify a credit card: `update`](#modify-a-credit-card-update)
        * [Remove a credit card: `delete`](#remove-a-credit-card-delete)
    * [Income](#income)
        * [Display help page for incomes: `help`](#display-help-page-for-incomes-help)
        * [Add an income: `add`](#add-an-income-add)
        * [Display incomes: `list`](#display-incomes-list)
        * [Modify an income: `update`](#modify-an-income-update)
        * [Remove an income: `delete`](#remove-an-income-delete)
    * [Exit MindMyMoney application: `bye`](#exit-mindmymoney-application-bye)
    * [Save the data](#save-the-data)
* [FAQ](#faq)
* [Command summary (Expenditure)](#command-summary-expenditure)
* [Command summary (Credit Card)](#command-summary-credit-card)
* [Command summary (Income)](#command-summary-income)

## Introduction

### MindMyMoney

`MindMyMoney` (M<sup>3</sup>) is a desktop app for managing your personal finances, optimized for use via a Command Line
Interface (CLI). You can use it to track your expenditures across multiple payment methods, calculate monthly
expenditure, and set financial goals. If you are a student looking to manage your personal finances, this app is for
you!

### Using the User Guide

This guide aims to equip you with the knowledge on how to set up the application and to utilise its many features. Click
on the hyperlinks in the [Content Page](#content-page) to quickly navigate the user guide! Along the guide you may
encounter several icons. These icons will provide several useful information.
> **💡 Note:**
>- This tells you that there is additional information that is useful when you are using the application.

> **⚠️Warning⚠️**
>- This tells you that there is some **important** information you should take note of to prevent issues from arising when you are using the application.


<br/>

## Quick Start

1. Ensure that you have Java 11 or above installed. Click
   [here](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html) for the link to the Java 11
   installer.
2. Download the latest version of `MindMyMoney.jar` from [here](https://github.com/AY2122S2-CS2113T-T10-4/tp/releases).
3. Copy the file to the folder you want to use as the _home folder_ for your MindMyMoney.
4. Open a command line terminal in your _home folder_ and run `java -jar MindMyMoney.jar`. The startup interface similar
   to the one below should appear in a few seconds.

````
 __  __ _         _ __  __      __  __
|  \/  (_)_ _  __| |  \/  |_  _|  \/  |___ _ _  ___ _  _
| |\/| | | ' \/ _` | |\/| | || | |\/| / _ \ ' \/ -_) || |
|_|  |_|_|_||_\__,_|_|  |_|\_, |_|  |_\___/_||_\___|\_, |
                           |__/                     |__/
<< Set a budget and stick to it >>

Welcome to MindMyMoney
What can I do for you?
````

5. Type the command in the command box and press Enter to execute it. For example: typing **`help`** and pressing Enter will
   show a help page. <br> Some example commands you can try: <br>
    * **`add`**`/e /pm cash /c Food /d Porridge /a 3 /t 12/03/2022` :
      Adds a $3 expenditure of the description 'Porridge' that was paid in cash on 12 March 2022 to your list of
      expenditures.
    * **`list /e`** : Lists all expenditures.
    * **`calculate`**`/epm 03/2022` : Calculates the total expenditure in the month of March 2022.
    * **`update`**`/e 1 /pm cash /c Food /d Chicken Rice /a 4.50 /t 12/03/2022` :
      Updates the first expenditure on your expenditure list to reflect a $4.50 expenditure of the description 'Chicken
      Rice' that was paid in cash on 12 March 2022.
    * **`delete`**`/e 1` : Deletes the first expenditure in your expenditure list.
    * **`bye`** : Exits the app.

6. Refer to the [Features](#features) for more details of each command.

<br/>

# Features

The following are features of the `MindMyMoney` application. Please ensure that the format of commands given is
accurate.  
Words in `[SQUARE_BRACKETS]` are the parameters. Words starting with a `/` are flags

> **💡 Note:**
>- All parameters are compulsory unless stated in the notes.

> **⚠️Warning⚠️**
>- All parameters are compulsory! Input the parameters in the order shown, or the application will not be able to read your
   > input.
>- ENTER RULES ON FORBIDDEN CHARACTERS HERE

## Expenditure

### Display help page for expenditures: `help`

Prints a list of currently available expenditure-related commands.

#### Format: `help /e`

#### Expected Outcome:
Show help page for expenditure related commands.
````
> help /e
---------------------------------------Expenditure Help Page---------------------------------------
1. Listing all Expenditures: list /e [DATE]
2. Adding an Expenditure entry: add /e /pm [PAYMENT_METHOD] /c [CATEGORY] /d [DESCRIPTION] /a [AMOUNT] /t [DATE]
3. Calculating the total expenditure in a month: calculate /epm [DATE]
4. Updating an Expenditure: update /e [NEW_INDEX] /pm [NEW_PAYMENT_METHOD] /c [NEW_CATEGORY] /d [NEW_DESCRIPTION] /a [NEW_AMOUNT] /t [NEW_DATE]
5. Removing an Expenditure entry: delete /e [INDEX]
6. Exiting the program: bye
---------------------------------------------------------------------------------------------------
````

<br/>

### Add an expenditure: `add`

Adds an expenditure to your program. Only **one** expenditure can only be added per command.

#### Format: `add /e /pm [PAYMENT_METHOD] /c [CATEGORY] /d [DESCRIPTION] /a [AMOUNT] /t [DATE]`

* `[PAYMENT_METHOD]` refers to the method of payment used.
    * Enter `cash` or the name of a credit card you have saved.
* `[CATEGORY]` refers to the category of the expenditure
    * Enter `Food`, `Transport`, `Utilities`, `Personal`, `Entertainment` or `Others`.
* `[DESCRIPTION]` refers to the description of the expenditure.
    * For example: `Nike shoes`.
* `[AMOUNT]` refers to the cost of the expenditure.
    * Enter the amount in dollars, rounded off to the nearest cent.
    * For example: an item that cost 420 dollars and 69 cents will be entered as `420.69`.
* `[DATE]` refers to the date of the purchase of the expenditure.
    * Format of the date is `dd/mm/yyyy`.
    * For example: `12 March 2022` will be entered as `12/03/2022`.

For example: `add /e /pm cash /c Food /d Porridge /a 4.50 /t 12/03/2022`  
Adds a $4.50 expenditure of food item 'Porridge' that was paid in cash on 12 March 2022 to your expenditure list. <br>

#### Expected Outcome:
Add a $4.50 expenditure of food item 'Porridge' that was paid in cash on 12 March 2022 to your expenditure list.
````
> add /e /pm cash /c Food /d Porridge /a 4.50 /t 12/03/2022
Successfully added: 

Description: Porridge
Amount: $4.50
Category: Food
Payment method: Cash
Date: 12/03/2022

into the account
````

> **💡 Note:**
>- `[CATEGORY]` and `[PAYMENT_METHOD]` are **case-insensitive**.
>- Your credit card has to be [added](#add-a-credit-card-add) first before entering the name of the credit card as `[PAYMENT_METHOD]`.
>- `[AMOUNT]` only accepts numbers with 2 decimal places. Any additional decimals will be rounded off or ignored


> **⚠️Warning⚠️**
>- `[CATEGORY]`: Any input that is not `Food`, `Transport`, `Utilities`, `Personal`, `Entertainment` or `Others` will be rejected.
>- `[DATE]`: Any input date not in the format of `dd/mm/yyyy` will be rejected.  
>- `[DATE]`: Any input date later than the current date will be rejected.
>- `[DATE]`: Any input date that is not in a leap year or non leap year is not valid.

<br/>

### Display expenditures: `list`

Prints your current list of expenditures.

#### Format: `list /e [DATE]`

* `[DATE]` refers to the date of the expenditures you would like to view.
    * Enter the `[DATE]` in `dd/mm/yyyy`, `mm/yyyy` or `yyyy` format.

#### Expected Outcome:
- List all your expenditures stored.
```
> list /e
-----------------------------------------------
1. $300.00 was spent on Nike Shoes(Personal) using Cash [30/03/2022]
2. $20.00 was spent on Grab(Transport) using Cash [30/03/2022]
3. $3.21 was spent on Bubble Tea(Food) using Cash [30/01/1999]
4. $4.50 was spent on Porridge(Food) using Cash [12/03/2022]
-----------------------------------------------
```
- List all your expenditures in March 2022.
```
> list /e 03/2022
-----------------------------------------------
1. $300.00 was spent on Nike Shoes(Personal) using Cash [30/03/2022]
2. $20.00 was spent on Grab(Transport) using Cash [30/03/2022]
3. $4.50 was spent on Porridge(Food) using Cash [12/03/2022]
-----------------------------------------------
```
- List all your expenditures in 30 March 2022.
```
> list /e 30/03/2022
-----------------------------------------------
1. $300.00 was spent on Nike Shoes(Personal) using Cash [30/03/2022]
2. $20.00 was spent on Grab(Transport) using Cash [30/03/2022]
-----------------------------------------------
```


> **💡 Note:**
>- `[DATE]` is **optional**. `list /e` will give all expenditures stored in the list.

> **⚠️Warning⚠️**
>- `[DATE]`: Any input not in the format of `dd/mm/yyyy`, `mm/yyyy` or `yyyy` will be rejected.
>- `[DATE]`: There must be an expenditure with the same input date.

<br/>

### Modify an expenditure: `update`

Modifies an expenditure on your expenditure list by specifying its index. <br>
Use the `list /e` command to view the indices of your expenditures.

#### Format: `update /e [INDEX] /pm [NEW_PAYMENT_METHOD] /c [NEW_CATEGORY] /d [NEW_DESCRIPTION] /a [NEW_AMOUNT] /t [NEW_DATE]`

* `[INDEX]` refers to the index of expenditure in list in which you want to update.
    * For example: `1` if you want to update the first expenditure in your list.
* `[NEW_PAYMENT_METHOD]` refers to the new method of payment used.
    * Enter `cash` or the name of a credit card you have saved.
* `[NEW_CATEGORY]` refers to the new category of the expenditure.
    * Enter `Food`, `Transport`, `Utilities`, `Personal`, `Entertainment` or `Others`.
* `[NEW_DESCRIPTION]` refers to the new description of the expenditure.
    * For example: `Chicken rice`.
* `[NEW_AMOUNT]` refers to the updated of the expenditure.
    * Enter the amount in dollars, rounded off to the nearest cent.
    * For example: an item that cost 420 dollars and 69 cents will be entered as `420.69`.
* `[NEW_DATE]` refers to the new date of the purchase of the expenditure.
    * Format of the date is `dd/mm/yyyy`.
    * For example: `12 March 2022` will be entered as `12/03/2022`.

For example: `update /e 1 /pm cash /c Food /d chicken rice /a 5 /t 12/03/2022`.  
Updates the first expenditure in your list to a $5.0 expenditure on food item 'chicken rice' that was paid in cash on 12
March 2022.

#### Expected Outcome:
Update the first expenditure in your list to a $5.0 expenditure on food item 'chicken rice' that was paid in cash on 12
March 2022.
````
> update /e 1 /pm cash /c Food /d chicken rice /a 5 /t 12/03/2022
Successfully set expenditure 1 to:
$5.0 was spent on chicken rice(Food) using Cash [12/03/2022]
````

> **💡 Note:**
>- This command is similar to the [add an expenditure](#add-an-expenditure-add) command.   
   > Fields that are labeled starting with NEW follow the same restrictions base command in [add an expenditure](#add-an-expenditure-add).
   > For example: `[NEW_CATEGORY]` is **case-insensitive** like `[CATEGORY]`
>- Only enter `[INDEX]` that exist in the expenditure list. For example: if you have 4 expenditures in your list, specify `[INDEX]` to be a number from 1 to 4.

> **⚠️Warning⚠️**
>- `[NEW_CATEGORY]`: Any input not in the list will be rejected.
>- `[NEW_DATE]`: Any input not in the format of `dd/mm/yyyy` will be rejected.
>- `[NEW_DATE]`: Any input date later than the current date will be rejected.

<br/>

### Remove an expenditure: `delete`

Deletes an expenditure from your expenditure list by specifying its index.  
Use the `list /e` command to view the current indices of your expenditures.

#### Format: `delete /e [INDEX]`

* `[INDEX]` refers to the index of expenditure in list in which you want to delete.

For example: `delete /e 1`  
Deletes the first expenditure on your list.

#### Expected Outcome:
Delete the first expenditure on your list.
````
> list /e
-----------------------------------------------
1. $300.00 was spent on Nike Shoes(Personal) using Cash [30/03/2022]
2. $20.00 was spent on Grab(Transport) using Cash [30/03/2022]
3. $3.21 was spent on Bubble Tea(Food) using Cash [30/01/1999]
4. $4.50 was spent on Porridge(Food) using Cash [12/03/2022]
-----------------------------------------------

> delete /e 1
I have removed Nike Shoes of $300.00 from the account
````

> **💡 Note:**
>- Only enter `[INDEX]` that exist in your list. For example: if you have 4 expenditures in your list, specify `[INDEX]` to be a number from 1 to 4.
>- Do not use `delete /e` when your expenditure list is empty.

<br/>

### Calculate expenditures: `calculate`

#### Expenditure per month: `calculate /epm`

Shows total expenditure made in a specified day, month or year. <br>
Shows the percentage spent on each category of expenditure, displayed in a horizontal bar chart. <br>

##### Format: `calculate /epm [DATE]`

* `[DATE]` can be of the format `dd/mm/yyyy`, `mm/yyyy` or `yyyy`, depending on the duration you are interested in.

For example: `calculate /epm 03/2022`  
Shows you your total expenditure and its relevant categories on March 2022.

##### Expected Outcome:
Show your total expenditure in March 2022 and breakdown of expenses.
````
> calculate /epm 03/2022
Total expenditure in 03/2022 is $24.50.

BREAKDOWN OF EXPENSES:
-----------------------------------------------
FOOD:          $$$$$$$$ [18.37%]
TRANSPORT:     $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ [81.63%]
UTILITIES:      [0.0%]
PERSONAL:       [0.0%]
ENTERTAINMENT:  [0.0%]
OTHERS:         [0.0%]
-----------------------------------------------
````

> **💡 Note**

> - `calculate` will not work if a date that you would like to calculate by is not found in the entry.
> - Use the `calculate` only if the day/month/year contain at least 1 expenditure


<br/>

## Credit Card

### Display help page for credit cards: `help`

Prints a list of commands for storing credit cards as a payment method.

#### Format: `help /cc`

#### Expected Outcome:
Show the help page for credit card related commands.
````
> help /cc
---------------------------------------Credit Card Help Page---------------------------------------
1. Listing all Credit Cards: list /cc
2. Adding a Credit Card: add /cc /n [CREDIT_CARD_NAME] /cb [CASHBACK] /cl [CREDIT_LIMIT]
3. Updating a Credit Card: update /cc [INDEX] /n [NEW_NAME] /cb [NEW_CASHBACK] /cl [NEW_CREDIT_LIMIT]
4. Removing a credit card: delete /cc [INDEX]
5. Exiting the program: bye
---------------------------------------------------------------------------------------------------
````

<br/>

### Add a credit card: `add`

Adds a credit card to your program. Only **one** credit card can only be added per command.  <br>
#### Format: `add /cc /n [CREDIT_CARD_NAME] /cb [CASHBACK] /cl [CARD_LIMIT]`

* `[CREDIT_CARD_NAME]` refers to the name your Credit Card will be saved as.
    * Use abbreviations for ease of adding expenditures to this credit card. For example:
      storing `DBS Live Fresh Credit Card` as `DBS LF`.
* `[CASHBACK]` refers to the amount of cash back received when spending on the credit card.
    * Enter the amount of cashback in percentage.
    * For example: a credit card with `2% cashback` can be represented as `/cb 2`.
* `[CARD_LIMIT]` refers to the maximum monthly expenditure on this credit card.
    * Enter the monthly maximum amount that can be spent on the credit card in dollars.

For example:`add /cc /n dbs /cb 2 /cl 1000`  
Adds a credit card of the name 'DBS' with a cashback of 2% and a monthly spending limit of $1000.

#### Expected Outcome:
Add your credit card of the name 'DBS' with a cashback of 2% and a monthly spending limit of $1000.
````
> add /cc /n dbs /cb 2 /cl 1000
Successfully added: 

Credit card: dbs
Cash back: 2%
Card limit: $1000

into the account
````

> **💡 Note**
>- `[CREDIT_CARD_NAME]` is **case-insensitive**.


> **⚠️Warning⚠️**
>- `[CREDIT_CARD_NAME]` cannot be `cash`, `CASH`, or a combination of either.
>- `[CASHBACK]` cannot be more than 100%.

<br/>

### Display credit cards: `list`

Prints your current list of credit cards that you have added so far.

#### Format: `list /cc`

#### Expected Outcome:
List all your credit cards stored.
````
> list /cc
-----------------------------------------------
1. Name: dbs [Cashback: 2.00%] [Cashback gained: $0.00] [Card limit: $1000.00]
-----------------------------------------------
````

<br/>

### Modify a credit card: `update`

Modifies a credit card on your credit card list by specifying its index. <br>
Use the `list /cc` command to view the current indices of your credit cards.

#### Format: `update /cc [INDEX] /n [NEW_NAME] /cb [NEW_CASHBACK] /cl [NEW_CARD_LIMIT]`

* `[INDEX]` refers to the index of credit card in list in which you want to update.
    * Enter `1` if you want to update the first credit card in your list.
* `[NEW_NAME]` refers to the name your Credit Card will be updated to.
    * Use abbreviations for ease of adding expenditures to this credit card. For example:
      storing `DBS Live Fresh Credit Card` as `DBS LV`.
* `[NEW_CASHBACK]` refers to the updated amount of cash back received when spending on the credit card.
    * Enter the amount of cashback in percentage.
    * For example: a credit card with `2% cashback` can be represented as `/cb 2`.
* `[NEW_CARD_LIMIT]` refers to the updated maximum monthly expenditure on this credit card.
    * Enter the monthly maximum amount that can be spent on the credit card in dollars.

For example: `update /cc 1 /n OCBC /cb 1.5 /cl 500`  
Updates the first credit card on your list to have a name of 'OCBC' with a cashback of 1.5% and a monthly spending limit of
$500.

#### Expected Outcome:
Update the first credit card on your list to have a name of 'OCBC' with a cashback of 1.5% and a monthly spending limit of
$500.
````
> list /cc
-----------------------------------------------
1. Name: dbs [Cashback: 2.00%] [Cashback gained: $0.00] [Card limit: $1000.00]
-----------------------------------------------

> update /cc 1 /n OCBC /cb 1.5 /cl 500
Successfully set credit card 1 to :
Name: OCBC [Cashback: 1.50%] [Cashback gained: $0.00] [Card limit: $500.00] [Card balance: $500.00]
````

> **💡 Note:**
>- This command is similar to the [add a credit card](#add-a-credit-card-add) command.   
   > Fields that are labeled starting with NEW follow the same restrictions base command in [add a credit card](#add-a-credit-card-add).
   > For example: `[NEW_NAME]` can be abbreviated like `[CREDIT_CARD_NAME]`.
>- Only enter `[INDEX]` that exist in your list. For example if you have 4 items in the credit card list, specify `[INDEX]` to be a number from 1 to 4.
>- You are **not allowed** to update the spending limit to an amount below what you have already spent using this card.
>  - E.g: You have already spent $500 using the card. You will not be allowed to update the spending limit to a 
> number less than $500.

> **⚠️Warning⚠️**
>- Updating a credit card would cause its cashback earned to **reset to 0**. Similarly, its balance left will also
> be **reset to the spending limit**.

<br/>

### Remove a credit card: `delete`

Delete a credit card from your list, by specifying the credit card's index.  
Use the `list /cc` command to view the current indices of your credit cards.

#### Format: `delete /cc [INDEX]`

* `[INDEX]` refers to the index of credit card in your credit card list in which you want to delete.
    * Enter `2` if you want to delete the second credit card in your credit card list.

For example: `delete /cc 1`<br/>
Deletes the first credit card on your credit card list.

#### Expected Outcome
Delete the first credit card on your credit card list.
````
> list /cc
-----------------------------------------------
1. Name: OCBC [Cashback: 1.50%] [Cashback gained: $0.00] [Card limit: $500.00] [Balance left: $500.00]
-----------------------------------------------

> delete /cc 1
I have removed OCBC from your list of credit card(s).
````

> **💡 Note:**
>- Only enter `[INDEX]` that exist in your list. For example: if you have 4 credit cards in your list, specify `[INDEX]` to be a number from 1 to 4.
>- Do not use `delete` when your credit card list is empty.
   <br/>

<br/>

## Income

Income refers to the various sources of income you might have.

### Display help page for incomes: `help`

Prints a list of commands relating to income.

#### Format: `help /i`

#### Expected Outcome:
Show the help page for income related commands.
````
> help /i
--------------------------------Income Help Page---------------------------------------
1. Listing all Incomes: list /i
2. Adding an Income entry: add /i /a [AMOUNT] /c [CATEGORY]
3. Updating an Income entry: update /i [INDEX] /a [NEW_AMOUNT] /c [NEW_CATEGORY]
4. Removing an Income entry: delete /i [INDEX]
---------------------------------------------------------------------------------------
````

<br/>

### Add an income: `add`

Adds an income into the income list.

#### Format: `add /i /a [AMOUNT] /c [CATEGORY]`

* `[AMOUNT]` refers to the monthly sum received, as a whole number.
* `[CATEGORY]` refers to the supported categories of income.
    * Enter `Salary`, `Allowance`, `Investment` or `Others`.
    * Categories are case-insensitive.

For example: `add /i /a 3000 /c salary`  
Adds an income of $3000 categorised as your Salary.

#### Expected Outcome:
Add an income of $3000 categorised as your Salary.
````
> add /i /a 3000 /c salary
Successfully added: 

Amount: 3000
Category: Salary

into the account
````
> **💡 Note:**
>- `[CATEGORY]` is **case-insensitive**.

> **⚠️Warning⚠️**
>- `[CATEGORY]`: Any input that is not `Salary`, `Allowance`, `Investment` or `Others` will be rejected.
>- `[AMOUNT]`: Takes in whole numbers as an input.
   > Round off your income to the nearest whole number when entering it into MindMyMoney.

<br/>

### Display incomes: `list`

Prints your current list of income entries that you have added so far.

#### Format: `list /i`

#### Expected Outcome:
List all your income entries stored.
````
> list /i
-----------------------------------------------
1. Amount: $3000
   Category: Salary
-----------------------------------------------
````

<br/>

### Modify an income: `update`

Modifies an income in your income list by specifying its index.   
Use the `list /i` command to view the current indices of your income entries.

#### Format: `update /i [INDEX] /a [NEW_AMOUNT] /c [NEW_CATEGORY]`

* `[INDEX]` refers to the index of income in list in which you want to update.
    * Enter `1` if you want to update the first entry in the list.
* `[NEW_AMOUNT]` refers to the updated monthly sum received, as a whole number.
* `[NEW_CATEGORY]` refers to the supported categories of income.
    * Enter `Salary`, `Allowance`, `Investment` or `Others`.

For example: `update /i 1 /a 4000 /c salary`  
Updates the first income entry on your income list to $4000 categorised as your salary.

#### Expected Outcome:
Update the first income entry on your income list to $4000 categorised as your salary.
````
> update /i 1 /a 4000 /c salary
Successfully set income 1 to:
Amount: $4000
Category: Salary
````

> **💡 Note:**
>- This command is similar to the [add an income](#add-an-income-add) command.   
   > Fields that are labeled starting with NEW follow the same restrictions base command in [add an income](#add-an-income-add).
   > For example: `[NEW_AMOUNT]` input has to be a whole number, similar to `[AMOUNT]`.
>- `[CATEGORY]` is **case-insensitive**.

>- Only enter `[INDEX]` that exist in your list. For example: if you have 4 incomes in your income list, specify `[INDEX]` to be a number from 1 to 4.

<br/>

### Remove an income: `delete`

Deletes an income from your income list, by specifying its index.  
Use the `list /i` command to view the current indices of your income entries.

#### Format: `delete /i [INDEX]`

* `[INDEX]` refers to the index of income in your income list you want to delete.
    * Enter `3` if you want to delete the third income in your income list.

For example: `delete /i 1`  
Deletes the first income entry on your income list.

#### Expected Outcome:
Delete the first income entry on your income list.
````
> delete /i 1
I have removed Salary from your list of income(s).
````

> **💡 Note:**
>- Only enter `[INDEX]` that exist in your list. For example: if you have 4 incomes in your income list, specify `[INDEX]` to be a number from 1 to 4.
>- Do not use `delete` when your income list is empty.

<br/>

## Exit MindMyMoney application: `bye`

Shuts down the MindMyMoney application.

### Format: `bye`

### Expected Outcome:
Exit your program.
````
> bye
Bye, hope to see you again!
````

> **💡 Note**
> - Any input after the `bye` command is ignored. For example: `bye Hello World` will still exit the program.

<br/>

## Save the data

Your MindMyMoney data is saved in the hard disk automatically after any command that changes the data. There is no need
for you to save manually. You can view the saved contents of MindMyMoney by reading the `list.txt` file in your current directory.

> **💡 Note:**
>- If you wish to back up your MindMyMoney data, you can copy the `list.txt` file into the folder you want to save it in.  
>- To load the backup data into MindMyMoney, copy `list.txt` from the backup folder into the folder containing 
MindMyMoney, replacing the existing copy of `list.txt`.  

> **⚠️Warning⚠️**
>- Do not modify or delete the contents of `list.txt` in your current directory. This may corrupt the data in MindMyMoney.
<br/>

## FAQ

**Q**: Why is my data not saved when I run MindMyMoney in different folders?

**A**: MindMyMoney saves data in the current directory. To ensure all the data is saved properly, run MindMyMoney only
in the _home folder_. If you wish to run MindMyMoney in different folders and still contain your data, 
copy the `list.txt` file found in the current directory into a new folder where you want to run MindMyMoney in.

<br/>

## Command Summary (Expenditure)


| Command   | Format, examples                                                                                                                                                                                                                                                                                                                                           |
|-----------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Help      | `help /e` <br/> Prints a list of commands for storing expenditures.                                                                                                                                                                                                                                                                                        |
| Add       | `add /e /pm [PAYMENT_METHOD] /c [CATEGORY] /d [DESCRIPTION] /a [AMOUNT] /t [DATE]`<br/>For example: `add /e /pm cash /c Food /d Porridge /a 4.5 /t 10/03/2022` <br/> Adds a $4.50 expenditure of food item 'Porridge' that was paid in cash on 10 March 2022 to your expenditure list.                                                                     |
| List      | `list /e [DATE]` <br/> For example: `list /e 03/03/2022` <br/> Displays your current list of expenditures on 3 March 2022.                                                                                                                                                                                                                                 |
| Calculate | `calculate /epm [DATE]` <br/> For example: `calculate /epm 03/2022` <br/> Calculates the total expenditures in March 2022 and prints the percentage of expenditures grouped by categories.                                                                                                                                                                 |
| Delete    | `delete /e [INDEX]`<br/>For example: `delete 1` <br/> Deletes the first expenditure from your expenditure list.                                                                                                                                                                                                                                            | 
| Update    | `update /e [INDEX] /pm [NEW_PAYMENT_METHOD] /c [NEW_CATEGORY] /d [NEW_DESCRIPTION] /a [NEW_AMOUNT] /t [NEW_DATE]`<br/>For example: `update /e 1 /pm cash /c Food /d chicken rice /a 5 /t 12/03/2022` <br/> Updates the first expenditure in your expenditure list to a $5.0 expenditure of food item 'chicken rice' that was paid in cash on 12 March 2022 |
| Exit      | `bye`<br/> Ends the `MindMyMoney` application.                                                                                                                                                                                                                                                                                                             |


<br/>

## Command Summary (Credit Card)

| Command   | Format, examples                                                                                                                                                                                                                                                                                |
|-----------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Help      | `help /cc` <br/> Prints a list of commands for storing credit cards.                                                                                                                                                                                                                            |
| Add       | `add /cc /n [NAME] /cb [CASH_BACK_AMOUNT] /cl [CASH_LIMIT_AMOUNT]`<br/>For example: `add /cc /n dbs /cb 2 /cl 1000` <br/> Adds a credit card of the name 'DBS' with a cashback of 2% and a monthly spending limit of $1000.                                                                     | 
| List      | `list /cc`<br/> Displays your current list of credit cards.                                                                                                                                                                                                                                     |
| Delete    | `delete /cc [INDEX]`<br/>For example: `delete /cc 1` <br/> Deletes the first credit card from your credit card list.                                                                                                                                                                            | 
| Update    | `update /cc [INDEX] /n [NEW_CARD_NAME] /cb [NEW_CASHBACK] /cl [NEW_CREDIT_LIMIT]`<br/>For example: `update /cc 1 /n OCBC /cb 1.5 /cl 500` <br/> Updates the first credit card on your credit card list to have a name of 'OCBC' with a cashback of 1.5% and a monthly spending limit of $500.   |
| Exit      | `bye`<br/> Ends the `MindMyMoney` application.                                                                                                                                                                                                                                                  |

<br/>

## Command Summary (Income)

| Command   | Format, examples                                                                                                                                                                                    |
|-----------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Help      | `help /i` <br/> Prints a list of commands for storing income.                                                                                                                                       |
| Add       | `add /i /a [AMOUNT] /c [CATEGORY]`<br/> For example: `add /i /a 3000 /c salary` <br/> Adds an income of #3000 categorised as your Salary.                                                           | 
| List      | `list /i`<br/> Displays your current list of income entries.                                                                                                                                        |
| Delete    | `delete /i [INDEX]`<br/> For example: `delete /i 1` <br/> Deletes the first income entry from your income list.                                                                                     | 
| Update    | `update /i [INDEX] /a [NEW_AMOUNT] /c [NEW_SALARY]`<br/> For example: `update /i 1 /a 4000 /c salary` <br/> Updates the first income entry on your income list to $4000 categorised as your Salary. |
| Exit      | `bye`<br/> Ends the `MindMyMoney` application                                                                                                                                                       |
