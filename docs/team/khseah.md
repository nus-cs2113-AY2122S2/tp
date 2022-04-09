# Seah Kit Han - Project Portfolio Page

## Overview
`MindMyMoney` (M<sup>3</sup>) is a desktop app for managing your personal finances, optimized for use via a Command Line
Interface (CLI). You can use it to track your expenditures across multiple payment methods, calculate monthly
expenditure, and set financial goals.

<br/>

## Contribution Summary

### Code Contribution
Code Contribution can be viewed [here](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=khseah&breakdown=true).

Enhancements implemented:
1. Set up the initial structure to build our code base upon.
   - `MindMyMoney` class as the entry point into our program.
   - `Ui` class to print welcome message and read user's input.
   - `Parser` class to make sense of user's input.
   - `Command` abstract class for Commands to inherit from.
   - Allows the team to have a common foundation to build upon and sets the architecture structure.
   - Crucial to get the design right as it affects the way future features are implemented.


2. Added the `Ui` class.
   - Prints welcome message and reads user's input.
   - Implemented the `>` prompt to help the user differentiate between their input and the program's output.
   - Sourced for 49 financial tips, of which 1 would be randomly chosen to be printed on startup.
   - Financial tips can educate the user and make the startup page more interactive.


3. Set up the `HelpCommand` class.
   - Prints a list of supported commands formats for reference.
   - Outlined the initial design for the help page.
   - Provides the user a convenient way to view command formats should they forget.


4. Added Add, Delete, Update and List functionalities for `Income`.
   - Users are now able to track their sources of income.
   - As a Personal Finance app, it is imperative that income can be tracked, so that users can monitor their income
inflow and outflow.
   - Affected existing Add, Delete, Update and List commands as these commands were also used for `Expenditure` and
`CreditCard`.


5. Added the `User` class.
   - Abstracts out `ExpenditureList`, `CreditCardList` and `IncomeList` classes into 1 class, increasing OOP.
   - Improves the code as instead of passing 3 lists into the method, only the `User` class is needed.
   - Affected a majority of existing code as previous methods had the 3 lists as parameters, and required significant 
changes.
   

6. Miscellaneous
   - Added JUnit tests for `HelpCommand`, `AddCommand` and `DeleteCommand`.
   - Wrote JavaDoc comments for a majority of methods.
   - Fixed bugs raised during PE-Dry testing pertaining to `CreditCard` limit.

<br/>

### UG Contribution
1. Wrote the `Income` portion.
   - Help, Add, List, Update and Delete.
   - Command Summary (Income) table.


2. Streamlined content page.
   - Shortened section headers to keep it succinct.
   - Standardized action verbs (Display, Add, Modify etc) for consistency.


3. Added whitespace between sections for better readability.

<br/>

### DG Contribution
1. Wrote the `Architecture overview` portion.
   - Added Fig 1 - Architecture Diagram.
   - Added Fig 2 - Sequence Diagram.


2. Wrote the `Ui` and `Parser Component overview` portion.
   - Added Fig 3 - Ui Class Diagram.
   - Added Fig 4 - Parser Class Diagram.


3. Refined the `Add Command implementation` portion.


4. Wrote the `Add Income implementation` portion.
   - Added Fig 10 - Add Income Sequence Diagram.


5. Wrote the `List Command` portion.
   - Added Fig 12 - List Command Sequence Diagram.
   - Added Fig 13 - List Expenditure Sequence Diagram.
   - Added Fig 14 - List Credit Card Sequence Diagram.
   - Added Fig 15 - List Income Sequence Diagram.

<br/>

### Team-Based tasks Contribution
1. Created and added members into `developers` team on GitHub.


2. Set up Issue Tracker.
   - Added `Issue type`, `Priority` and `Severity` labels.
   - Created and assigned issues for `v1.0` based on user stories.
   - Created `v1.0` Milestone.


3. Enabled status checks to pass before merging.


4. Closed `v1.0` Milestone.


5. Enabled GitHub pages.


6. Released `v1.0` and `v2.0` JAR files.


7. Enabled Assertions in Gradle.

<br/>

### Miscellaneous Contribution
PR reviews (with non-trivial comments):
[#39](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/39), [#46](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/46), 
[#72](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/72), [#86](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/86), 
[#87](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/87), [#143](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/143).

DG review for `Simplst` can be found [here](https://github.com/nus-cs2113-AY2122S2/tp/pull/6/files/4125efa69fbb7ffda1b2ade950ec48b6e80f5baf).

Bugs found during PE-Dry testing for `Spendvelope` can be viewed [here](https://github.com/khseah/ped/issues).
