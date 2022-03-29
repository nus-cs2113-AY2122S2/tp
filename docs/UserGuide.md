# MindMyMoney User Guide


* [Introduction](#introduction)
* [Quick start](#quick-start)
* [Command summary (Expenses)](#command-summary-expenses)
* [Command summary (Credit Card)](#command-summary-credit-card)
* [Features](#features)
    * [Expenses](#expenses)
        * [Display list of currently available commands for expenses: `help`](#display-list-of-currently-available-commands-for-expenses-help)
        * [Add an expenditure to your program: `add`](#add-an-expenditure-to-your-program-add)
        * [Display current list of expenditures: `list` ](#display-current-list-of-expenditures-list)
        * [Modify an expenditure on your list: `update`](#modify-an-expenditure-on-your-list-update)
        * [Removing an expenditure on your list: `delete`](#removing-an-expenditure-on-your-list-delete)
        * [Calculations that Mindmymoney provide: `calculate`](#calculations-that-mindmymoney-provide-calculate)
            * [Expenditure per month: `calculate /epm`](#expenditure-per-month-calculate-epm)
        * [Exiting MindMyMoney application: `bye`](#exiting-mindmymoney-application-bye)
    * [Credit Card](#credit-card)
        * [Display list of currently available commands for credit card: `help`](#display-list-of-currently-available-commands-for-credit-card-help)
        * [Add a credit card to your program: `add`](#add-a-credit-card-to-your-program-add)
        * [Display current list of credit cards: `list` ](#display-current-list-of-expenditures-list)
        * [Modify a credit card on your list: `update`](#modify-a-credit-card-on-your-list-update)
        * [Removing a credit card on your list: `delete`](#removing-a-credit-card-on-your-list-delete)
    * [Saving the data](#saving-the-data)
* [FAQ](#faq)
  
## Introduction
### MindMyMoney

`MindMyMoney` (M<sup>3</sup>) is a desktop app for managing your financials, optimized for use via a
Command Line Interface (CLI). You can use it to track your spending with multiple payment methods,
calculate monthly expenditure, set financial goals and much more! If you are new to managing your finances, this app
is for you!

### User Guide
This guide aims to equip users with the knowledge on how to set up the application and to utilise its many features.
Click on the hyperlinks in the content page above to quick navigation on the page!

## Quick Start


1. Ensure that you have Java 11 or above installed. Click
   [here](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html) for the link to the Java 11
   installer.
2. Down the latest version of `MindMyMoney.jar` from [here](https://github.com/AY2122S2-CS2113T-T10-4/tp/releases)).
3. Copy the file to the folder you want to use as the _home folder_ for your MindMyMoney.

4. Open a command line terminal in your _home folder_ and run `java -jar MindMyMoney.jar`.
   The startup interface similar to the one below should appear in a few seconds.  
   ![M3_Start_Screen](images/start.PNG)
5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
   show a help page. <br> Some example commands you can try: <br>
    * **`list`** : Lists all tasks.
    * **`add`**`/e cash /c Food /d Porridge /a 3 /t 2022-03` :
      Adds $3 porridge that was paid by cash on March 2022 to the list.
    * **`calculate`**`/epm Mar 2022` : Calculates total expenditure in the month of March 2022.
    * **`update`**`1 /pm cash /c Food /d Porridge /a 3 /t 2022-03` :
      Updates index 1 to reflect $3 porridge that was paid by cash on March 2022 to the list.
    * **`delete`**`2` : Deletes the 2nd expenditure shown in the expenditure list.
    * **`bye`** : Exits the app.
    
6. Refer to the [Features](#features) below for details of each command.

## Command Summary (Expenses)

| Command   | Format, examples                                                                                                                                                            |
|-----------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Help      | `help /expenses` <br/> Prints a list of expenses commands.                                                                                                                  |
| Add       | `add /e [PAYMENT_METHOD] /c [CATEGORY] /d [DESCRIPTION] /a [AMOUNT] /t [DATE]`<br/>e.g `add /pm cash /c Food /d Porridge /a 3 /t 2022-03` <br/> Adds an expense to the list |
| List      | `list /expenses`<br/> Displays the current list of expenditures stored by the user.                                                                                         |
| Calculate | `calculate /epm mm yyyy` <br/> e.g `calculate /epm Mar 2022` <br/> Calculates the total expenses and shows the percentage of expenses grouped by different categories.      |
| Delete    | `delete [INDEX]`<br/>e.g `delete 1` <br/> Deletes an expense item from the list.                                                                                            | 
| Update    | `update [INDEX] [NEW_DESCRIPTION] [NEW_AMOUNT]`<br/>e.g `update 2 snacks 5` <br/> Updates an expense item in the list.                                                      |


## Command Summary (Credit Card)

| Command   | Format, examples                                                                                                                                                                                        |
|-----------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Help      | `help /cc` <br/> Prints a list of credit card commands.                                                                                                                                                 |
| Add       | `add /cc /n [NAME] /cb [CASH_BACK_AMOUNT] /cl [CASH_LIMIT_AMOUNT] /bal [CARD_BALANCE]`<br/>e.g `add /cc /n dbs /cb 2 /cl 1000 /bal 1000` <br/> Adds a credit card detail.                               |
| List      | `list /cc`<br/> Displays the current list of credit cards stored by the user.                                                                                                                           |
| Delete    | `delete /cc [INDEX]`<br/>e.g `delete /cc 1` <br/> Deletes an credit card from the list.                                                                                                                 | 
| Update    | `update /cc [INDEX] /n [NEW_CARD_NAME] /cb [NEW_CASHBACK] /cl [NEW_CREDIT_LIMIT] /bal [NEW_BALANCE]`<br/>e.g `update /cc 2 /n DBS /cb 3 /cl 10000 /bal 10000` <br/> Updates an credit card in the list. |
| Exit      | `bye`<br/> Ends the `MindMyMoney` application                                                                                                                                                           |


# Features
The following are features of the `MindMyMoney` application.
Please ensure that the format of commands given is accurate.  
Words in `[SQUARE_BRACKETS]` are the parameters.

## Expenses

### Display list of currently available commands for expenses: `help`
Prints a list of currently available commands.   
Format:`help /expenses`for expenses commands <br>
![help_expenses screenshot](./images/help_expenses.PNG)


### Add an expenditure to your program: `add`
Adds an expenditure to your program. Only **one** expenditure can only be added per command.
Format:`add /e [PAYMENT_METHOD] /c [CATEGORY] /d [DESCRIPTION] /a [AMOUNT] /t [DATE]`
* `[PAYMENT_METHOD]` refers to the method of payment used.
    * Enter `cash` or the name of a credit card you have saved.
* `[CATEGORY]` refers to the category of the expenditure
    * Enter `Food`, `Transport`, `Utilities`, `Personal`, `Entertainment` or `Others`. Any input outside this list is rejected.
* `[DESCRIPTION]` refers to the description of the expenditure.
    * For example `Nike shoes`.
* `[AMOUNT]` refers to the cost of the expenditure.
    * Enter the amount in dollars rounded off to the nearest cent.
    * For example an item that cost $420 and 69 cents will be entered as `420.69`.
* `[DATE]` refers to the date of the purchase of the expenditure.
    * Format of the date is YYYY-MM.
    * For example `March 2022` will be entered as `2022-03`


Example:`add /e cash /c Food /d Porridge /a 4.50 /t 2022-03`<br>
![add_expense screenshot](./images/add_expense.PNG)

> **💡 Note:**
>- `[CATEGORY]` and `[PAYMENT_METHOD]` are **case-insensitive**.
>- `[PAYMENT_METHOD]` credit card has to be added first before using the field `/e [CARD_NAME]`.
>- `[AMOUNT]` only excepts numbers with 2 decimal places. Any more decimal places will be rounded or ignored
>- `[DATE]` does not require any day input.


> **⚠️Warning⚠️**
>- Input realistic values, any unrealistic values can crash the system and damage your saved file!
>- All flags are compulsory! Input the flags in the order given, else the application will not be able to read your
   > input.

### Display current list of expenditures: `list`
Prints in the terminal the current list of expenditures that you have logged so far
Format:`list /expenses` lists the expenses in the list. <br>
![list_expenses screenshot](./images/list_expenses.PNG)

### Modify an expenditure on your list: `update`
Modify an expenditure on your list, by specifying the expenditure's index. <br>
Use the `list /expenses` command to view the current indices of your expenditures.   

Format:`update [INDEX] [NEW_DESCRIPTION] [NEW_AMOUNT]`  
For example: `update 1 drinks 2`  <br>
![update screenshot](./images/update.PNG)

### Removing an expenditure on your list: `delete`
Delete an expenditure from your list, by specifying the expenditure's index. <br>
Use the `list /expenses` command to view the current indices of your expenditures.   

Format:`delete`  
For example: `delete 1` <br>
![delete screenshot](./images/delete_eg.PNG)

### Calculations that Mindmymoney provide: `calculate`
Mindmymoney provides a calculating feature. See below for more details of the feature.

#### Expenditure per month: `calculate /epm`
Shows the sum of the amounts of expenditures you have made in a month of a certain year. <br>
Shows the percentage spent on each category of expenditure, displayed in a horizontal bar chart. <br>

Format:`calculate /epm MMM YYYY`
* Enter Month in `MMM` format, with first letter capitalised. For example `Jan`.
* Enter Year in `YYYY` format. For example `2022`
* Enter Month and Year seperated by a space, `MMM YYYY`. For example `Dec 2021`
  For example: `calculate /epm Mar 2022`  <br>
  ![epm screenshot](./images/calculate_epm.PNG) <br>

> **💡 Note**
> - If you want to search for exact month in year, enter both month and year.
> - Month is **case-sensitive**.
> - However, you can also use this function to search for expenditures in the year by typing `calculate /epm 2022`.


### Exiting MindMyMoney application: `bye`
Shuts down the MindMyMoney application.
Format:`bye`  <br>
![bye screenshot](./images/bye_eg.PNG)

## Credit Card

### Display list of currently available commands for credit card: `help`
Prints a list of currently available commands.
Format 1:`help /cc` for credit card commands <br>
![help_cc screenshot](./images/help_cc.PNG)

### Add a credit card to your program: `add`
Adds a credit card to your program. Only **one** credit card can only be added per command.  <br>
Format:`add /cc /n [CREDIT_CARD_NAME] /cb [CASH_BACK] /cl [CARD_LIMIT] /bal [CARD_BALANCE]`
* `[CREDIT_CARD_NAME]` refers to the name your Credit Card will be saved as
  * Use abbreviations for ease of adding expenditures to this credit card
      For example, storing `DBS Live Fresh Credit Card` as `DBS LV`
* `[CASH_BACK]` refers to the amount of cash back received when spending on the credit card
  * Enter the amount of cashback in percentage
  * For example, a credit card with `2% cashback` can be stored as `/cb 2`
* `[CARD_LIMIT]` refers to the maximum monthly expenditure on this credit card.
    * Enter the maximum amount that can be spent on the credit card in dollars
* `[CARD_BALANCE]` refers to the amount of money left to be spent on the credit card
    * Enter the amount that is left in this card in dollars


Example:`add /cc /n dbs /cb 2 /cl 1000 /bal 1000`  <br>
![add_cc screenshot](./images/add_cc.PNG)
> **💡 Note**
>- `[CREDIT_CARD_NAME]` is **case-insensitive**.


> **⚠️Warning⚠️**
>- Input realistic values, any unrealistic values can crash the system and harm your save file!
>- All flags are compulsory! Input the flags in the order given, or the application will not be able to read your
   > input.

### Display current list of credit cards: `list`
Prints in the terminal the current list of credit cards that you have registered so far   
Format:`list /cc` lists the credit cards in the list. <br>
![list_cc screenshot](./images/list_cc.PNG)


### Modify a credit card on your list: `update`
Modify a credit card on your list, by specifying the credit card's index. <br>
Use the `list /cc` command to view the current indices of your credit cards.   

Format:`update /cc [INDEX] /n [NEW_NAME] /cb [NEW_CASHBACK] /cl [NEW_CREDIT_LIMIT] /bal [NEW_BALANCE]`  
For example: `update /cc 1 /n OCBC /cb 1.5 /cl 500 /bal 1000`  <br>
![update screenshot](./images/update_cc.PNG)

### Removing a credit card on your list: `delete`
Delete a credit card from your list, by specifying the credit card's index.  
Use the `list /cc` command to view the current indices of your credit cards.   
Format:`delete /cc [INDEX]`
For example: `delete /cc 1` <br>
![delete_cc screenshot](./images/delete_cc.PNG)


### Saving the data:
MindMyMoney data are saved in the hard disk automatically after any command that changes the data.
There is no need to save manually.

## FAQ

**Q**: Why is my data not saved when I run MindMyMoney in different folders?

**A**: MindMyMoney saves data in the current directory. To ensure all the data is saved properly,
run MindMyMoney only in the _home folder_. 
