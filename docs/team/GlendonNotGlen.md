# Seah Kit Han - Project Portfolio Page

## Overview
`MindMyMoney` (M<sup>3</sup>) is a desktop app for managing your personal finances, optimized for use via a Command Line
Interface (CLI). You can use it to track your expenditures across multiple payment methods, calculate monthly
expenditure, and set financial goals.

## Contribution Summary

### Code Contribution
Code Contribution can be viewed [here](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=glendonnotglen&breakdown=true).

Enhancements implemented:
1. Added `Credit Card` class.
   - Implemented methods for adding, listing, updating and deleting Credit Cards.
   - Implemented relevant testing methods for input validations for adding and updating Credit Cards.
2. Added code structure for `ListCommand`.
   - Added JUnit tests for `HelpCommand`, `AddCommand` and `DeleteCommand`.
3. Updated `HelpCommand` instructions for `help /e` and `help /cc`.
   - Added JUnit tests for `HelpCommand`, `AddCommand` and `DeleteCommand`.
4. Updated `Parser.java` to detect varying sizes of flags.
   - Adds versatility to our program's input.
5. Miscellaneous
    - Added JUnit tests for `DeleteCommand`, `AddCommand`, `ListCommand` and `Parser`.
    - Updated JUnit testing for multiple tests, including `CalculateInput`, `UpdateCommand`, and `HelpCommand`
    - Wrote JavaDoc comments for a majority of methods.
    - Fixed bugs raised during PE-Dry testing pertaining to `CreditCard` addition and improving invalid input handling.

### UG Contribution
1. Wrote the `Credit Card` documentations for Users.
    - Add, List, Update, and Delete commands.
    - Updated Command Summary (Credit Card) table.
    
2. Streamlined content page.
    - Standardised content throughout the User Guide.
    - Improved User Guide as per feedback from Professor.
    - Proofread and reduced Grammatical errors.

### DG Contribution
1. Wrote `Delete` section.
    - Includes deleting `Expenditure`, `Credit Card`, and `Income`.
    - Included Design considerations.
    - Designed relevant command sequence diagrams to improve readability and understanding of Developer's Guide

2. Wrote `Update` section.
   - Includes update `Expenditure`, `Credit Card`, and `Income`. 
   - Included Design considerations.
   - Designed relevant command sequence diagrams to improve readability and understanding of Developer's Guide

3. Redo numerous sequence diagrams to standardise colours used for each Class and improved readability of diagrams

### Team-Based tasks Contribution
1. Created and added members into `developers` team on GitHub.

2. Proofread teammates' contributions to User Guide and Developer Guide.


### Miscellaneous Contribution
PR reviews (with non-trivial comments):
[#69](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/69), [#78](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/78),
[#162](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/162), [#201](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/201)

Moderate number of bugs found (7) during PE-Dry testing for `AY2122S2-CS2113-T11-3` can be viewed [here](https://github.com/glendonnotglen/ped/issues).