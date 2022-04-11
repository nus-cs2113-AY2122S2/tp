# Lim Jie Rui - Project Portfolio Page

## Overview
`MindMyMoney` (M<sup>3</sup>) is a desktop app for managing your personal finances, optimized for use via a Command Line
Interface (CLI). You can use it to track your expenditures across multiple payment methods, calculate monthly
expenditure, and set financial goals.

## Contribution Summary

### Code Contribution
Code Contribution can be viewed [here](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=limjierui&breakdown=true)

Enhancements implemented:

1. Refactor initial code to add more abstractions and OOP.
   - Added an `expenditureList` class to tackle the global list issue where we needed to delete all the items after each test.
   - Refactored code for all command classes to have more OOP and to utilise more abstractions.

2. Added `delete` command.
   - Set up the initial `delete` class to delete any items in the expenditure list with an index.
   
3. Added `Bye` command.
   - Added a `bye` class that allows users to exit when they want to end the program.
   
4. Added `MindMyMoneyException` class.
   - Set up the MindMyMoney exception class to allow any other exceptions to be thrown as MindMyMoneyException.
   
5. Enhanced `CalculateInputCommand` command.
    - Added a breakdown of expenses which shows the percentage of money spent on certain categories.
    - Enhanced visual appeal by coding out the details in a bar chart format.
    - Enhanced the command to allow users to search by date`dd/mm/yyy`, month`mm/yyyy` or year `yyyy`,
      which allows users to have more options to calculate their expenditure.
    
6. Enhanced `listCommand` command.
   - Enhanced list command by allowing users to be able to list expenditures by the date specified, either by date, month or year.
   - Allows users to be able to view their expenditures in different ways.
   
7. Enhanced `addCommand` and `updateCommand` commands.
   - Added enhancements to check if the date input is in the correct format `dd/mm/yyyy`. 
   - Added enhancements to check if the date input is a valid date in a leap year and a non leap year.
   - Added enhancements to check if the date input is before the current date of the user.
   - Added checks for `updateCommand` to prevent user from updating a certain expenditure if all field details are the same.
   
8. Added JUnit tests.
   - Added JUnit tests for `deleteCommand`, `calculateInputCommand`, `listCommand`, `helpCommand`, `updateCommand`, `addCommand`
   
9. Miscellaneous
   - Fixed formatting issues in JUnit tests and wrong error message outputs.
   - Standardised and coded clearer output messages across different commands.
   - Removed unnecessary import statements.
   - Wrote JavaDoc comments for methods I have created and enhanced existing JavaDoc comments for other methods.
   - Fixed bugs raised during PE-D.
   
### UG Contribution
1. Added initial example pictures into the UG for different commands.
   
2. Standardised command input parameters.
3. Edited examples of each command to make the flow of example smoother and had a user story to it.

4. Checked and edited wrong example outputs.

### DG Contribution
1. Wrote the `Command` component.
   - Added the command component class diagram.
   - Wrote an explanation on how the command component works.
   
2. Wrote the `Storage` component.
   - Added the storage component class diagram.
   - Wrote an explanation on how the storage component works.
   
3. Wrote the `CalculateInputCommand` component.
   - Added the calculateInputCommand sequence diagram.
   - Wrote an explanation on how the calculateInputCommand works.

### Team-Based Tasks Contribution
1. Issued v2.1 issues to members.

2. Assigned v2.1 milestones to issues after PE-D.

3. Refactored code and added abstractions to allow members to add JUnit tests and functional code more easily in future implementations.
4. Standardised all sequence and class diagrams contributed by all members to have the same format.

### Miscellaneous Contribution
PR reviews (with non-trivial comments):
[#34](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/34), [#69](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/69),
[#77](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/77), [#90](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/90),
[#213](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/213)

High number of bugs found (16) during PE-Dry testing for `AY2122S2-CS2113-F10-1` can be viewed [here](https://github.com/limjierui/ped/issues).