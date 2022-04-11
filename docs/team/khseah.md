# Seah Kit Han - Project Portfolio Page

## Overview
`MindMyMoney` (M<sup>3</sup>) is a desktop app for managing your personal finances, optimized for use via a Command Line
Interface (CLI). You can use it to track your expenditures across multiple payment methods, calculate monthly
expenditure, and set financial goals.

## Contribution Summary

### Code Contribution
Code Contribution can be viewed [here](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=khseah&breakdown=true).

1. Set up the initial structure to build our code base upon.
   - `MindMyMoney` class as the entry point, `Ui` class, `Parser` class and `Command` class.
   - Allows the team to have a common foundation to build upon and sets the architecture structure.
   
2. Added the `Ui` class.
   - Implemented the `>` prompt to help the user differentiate between their input and the program's output.
   - Sourced for 49 financial tips, of which 1 would be printed on startup, making it more interactive.
   
3. Added Add, Delete, Update and List functionalities for `Income`.
   - As a Personal Finance app, it is imperative that income can be tracked, so that users can monitor their income
inflow and outflow.
   - Affected existing Add, Delete, Update and List commands as these commands were also used for `Expenditure` and
`CreditCard`.
   
4. Added the `User` class.
   - Abstracts out `ExpenditureList`, `CreditCardList` and `IncomeList` classes into 1 class, increasing OOP.
   - Affected a majority of existing code as previous methods had the 3 lists as parameters.
   
5. Miscellaneous.
   - Added JUnit tests for `HelpCommand`, `AddCommand` and `DeleteCommand`.
   - Fixed bugs raised during PE-Dry testing pertaining to `CreditCard` limit.

### UG Contribution
1. Wrote the `Income` portion.
   - Help, Add, List, Update and Delete.
   - Command Summary (Income) table.


2. Streamlined content page.
   - Shortened section headers and standardized action verbs (Display, Add, Modify etc).

### DG Contribution
1. Wrote the `Architecture overview` portion.
   - Added Fig 1 - Architecture Diagram and Fig 2 - Sequence Diagram.
   
2. Wrote the `Ui` and `Parser Component overview` portion.
   - Added Fig 3 - Ui Class Diagram and Fig 4 - Parser Class Diagram.

3. Wrote the `Add Income implementation` portion.
   - Added Fig 10 - Add Income Sequence Diagram.
   
4. Wrote the `List Command implementation` portion.
   - Added Fig 12 - List Command Sequence Diagram, Fig 13 - List Expenditure Sequence Diagram, Fig 14 - List Credit Card
Sequence Diagram and Fig 15 - List Income Sequence Diagram

### Team-Based tasks Contribution
1. Set up Issue Tracker.
   - Added `Issue type`, `Priority` and `Severity` labels.
   - Created and assigned issues for `v1.0` based on user stories.
   - Created and closed `v1.0` Milestone.

2. Released `v1.0` and `v2.0` JAR files.

### Miscellaneous Contribution
PR reviews (with non-trivial comments):
[#39](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/39), [#46](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/46), 
[#72](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/72), [#86](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/86), 
[#87](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/87), [#143](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/143).

[DG review](https://github.com/nus-cs2113-AY2122S2/tp/pull/6/files/4125efa69fbb7ffda1b2ade950ec48b6e80f5baf) for `Simplst`,
[Bugs found](https://github.com/khseah/ped/issues) during PE-Dry testing for `Spendvelope`.
