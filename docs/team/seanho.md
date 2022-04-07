# Ho Wen Bin, Sean - Project Portfolio Page

## Overview

`MindMyMoney` (M<sup>3</sup>) is a desktop app for managing your personal finances, optimized for use via a Command Line
Interface (CLI). You can use it to track your expenditures across multiple payment methods, calculate monthly
expenditure, and set financial goals.
<br/>

## Contribution Summary

### Code Contribution

Code Contribution can be
viewed [here](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=SeanHoWB&breakdown=true).

Enhancements implemented:

1. Set up the initial structure to build our code base upon.
    - `AddCommand` class to allow users to add commands into `MindMyMoney`.
    - `CalculationInputCommand` class to allow users to calculate their expenses.
    - `Expenditure` class to store details of user expenditure .
    - `CreditCard` class to store details of user's credit card.
    - `AddCommandInputTests` class containing functions to test the inputs in AddCommand.
    - `Calculations` class containing calculation functions.
    - `GeneralFunctions` class containing general functions.
    - `TimeFunctions` class containing functions used to parse time information.
    - Allows the team to use functions and classes essential to `MindMyMoney`.


2. Added the `AddCommand` class.
    - Allows users to track and add their expenditure in `MindMyMoney`, and see a summary of all the inputs they have
      added.
    - Added its relevant `AddCommandInputTests` that serves as input validation and checks the input of each field in
      the add command for its validity.


3. Set up the `CalculationInputCommand` class.
    - Calculates the total amount of expenditures done by a user, according to date.
    - Added `Calculations` class to perform the calculations needed by `CalculationInputCommand`.
    - Served as a basic foundation to calculation that was worked on further by teammate Jierui.
    - Provides the user with the ability to see the total expenditure on a particular date.


4. Added`Expenditure` class.
    - Users are now able to track their sources of expenditure.
    - As a key functionality of `MindMyMoney`, users can now track their expenditures and get their total expenditure.


5. Added `CreditCard` class.
    - Users are now able to input their various credit cards into `MindMyMoney`.
    - Being part of the value proposition of `MindMyMoney`, it allows users to track their multiple forms of payment.


6. Added `GeneralFunction` class
    - Allows the team to call frequently used functions in `MindMyMoney` from just 1 class, increases OOP.
    - Implemented `findItemsInList` function that aided the search for expenditure by date and by category in
      `calculateExpenditurePerMonth`.
    - Implemented `parseInputWithCommandFlag` to parse user input containing flags. This was used throughout the code
      across many commands.
    - Implemented `capitalise`, `formatFloat` functions, to format user input.


7. Added JUnit tests
    - Added JUnit tests to `AddCommand`, `ListCommand`, `UpdateCommand`, `CalculateInputCommand`,
      `GeneralFunctions`, `HelpCommand`, `DeleteCommand`,  `Parser`, `ByeCommand`, `MindMyMoney`, `Ui`.
    - Increased the test coverage for these classes to 88% classes and 87% lines covered
    - Implemented functions to allow capturing of terminal output, used throughout other JUnit tests.


8. Miscellaneous
    - Fixed bugs raised during PE-Dry testing pertaining to inconsistent UG images.
    - `ExpenditureCategoryType` enum for containing the types of category of expenditure.
    - `ExpenditureFields` enum for containing the fields in an add expenditure command.
    - `Indexes` class containing constants referencing indexes used in arrays.
      <br/>

### UG Contribution

1. Wrote the `Add an expenditure` portion.
    - Explained the flags of expenditure and how to use it.


2. Wrote the Introduction
    - Introduced the application to users, sharing what it does and who the application is for.
    - Instructed users on installation and provided notes on symbols used around the UG.
    - Provided Notes and Warnings to the usage of some commands.


3. Re-formatted the UG
    - Changed the way expected output is displayed, from images to text form for better formatting.
    - Formatted the headers and the `Format` and `Expected Outcome` field
      <br/>

### DG Contribution

1. Contributed to the `Add Command` portion.
    - Added `Add Expenditure` portion
    - Added Fig 7 - AddCommand Sequence Diagram
    - Added Fig 8 - Add Expenditure Command Sequence Diagram
    - Added Fig 9 - Add Credit Card Command Sequence Diagram


2. Wrote the `Appendix Requirements`
    - Added Product Scope, Non-Functional Requirements, Glossary


3. Wrote the `Introduction`
    - Added the purpose, acknowledgements and using the developer guide.
      <br/>

### Team-Based tasks Contribution

1. Issued issues.
    - Created and assigned issues for `v2.0` based on user stories.
    - Created `v2.0` Milestone.


2. Closed `v2.0` Milestone.


3. Open `v2.1` Milestone.
   <br/>

### Miscellaneous Contribution

PR reviews (with non-trivial comments):
[#38](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/38), [#78](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/78)
,
[#51](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/51), [#86](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/86)
,
[#74](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/74),

