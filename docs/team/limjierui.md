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
   - Added `expenditureList` class to tackle the global list issue during initial testing.
   - Refactored all command classes to have more OOP and abstractions.

2. Added `bye` and `delete` command.
   - Added `bye` class that allows users to exit when they want to end the program.
   - Set up the `delete` class to delete any items in the expenditure list with an index.

3. Added `MindMyMoneyException` class.
   - Set up `MindMyMoneyException` class to allow exceptions to be thrown as MindMyMoneyException.
   
4. Enhanced `CalculateInputCommand` command.
    - Added a breakdown of expenses by categories.
    - Enhanced visual appeal by displaying details in a bar chart format.
    - Enhanced command to allow users to search by date, month or year.

5. Enhanced `addCommand`,`listCommand`  and `updateCommand` commands.
   - Added enhancements to check if the date input is in the correct format and before current date of user. 
   - Added enhancements to check if the date input is a valid date in a leap year or non leap year.
   - Enhanced `listCommand` to list expenditures by date specified (date, month or year).
   - Added checks for `updateCommand` to prevent user from updating an expenditure if all field details are the same.
   
6. Added and updated JUnit tests.
   - Tests for `deleteCommand`, `calculateInputCommand`, `listCommand`, `helpCommand`, `updateCommand`, `addCommand`
   
7. Miscellaneous
   - Fixed formatting issues and wrong error message outputs in JUnit tests. 
   - Standardised and coded clearer output messages across different commands.
   - Wrote JavaDoc comments for a majority of methods.
   - Fixed bugs raised during PE-D.
   
### UG Contribution
1. Added initial example pictures into the UG for different commands.
2. Standardised command input parameters.
3. Edited examples across commands to have a flow that had a user story to it.
4. Checked and edited wrong example outputs.

### DG Contribution
1. Wrote and explained the `Command` component.
   - Added command component class diagram and how it works.
2. Wrote and explained the `Storage` component.
   - Added storage component class diagram and how it works.
3. Wrote and explained the `CalculateInputCommand` component.
   - Added CalculateInputCommand sequence diagram and how it works.

### Team-Based Tasks Contribution
1. Issued v2.1 issues to members and assigned v2.1 milestones to issues after PE-D.
2. Refactored code and added abstractions to allow members to add JUnit tests and functional code more easily in future implementations.
3. Standardised sequence and class diagrams to have the same format.

### Miscellaneous Contribution
PR reviews (with non-trivial comments):
[#34](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/34), [#69](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/69),
[#77](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/77), [#90](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/90),
[#213](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/213) <br>
High number of [bugs found](https://github.com/limjierui/ped/issues) (16) during PE-Dry testing for `AY2122S2-CS2113-F10-1`.