# Jasper Chia's Project Portfolio Page

## Project: Inventory Manager
Inventory Manager is a command line application that aims to help student organisations manage their inventory.  
The target users of this application are executive committee members of student organisations.

## My Contributions

1. [Code Contributed](https://github.com/quitejasper/tp/blob/master/docs/team/quitejasper.md#1-code-contributed-)
2. [Features Implemented](https://github.com/quitejasper/tp/blob/master/docs/team/quitejasper.md#2-features-implemented-)
3. [Enhancements Implemented](https://github.com/quitejasper/tp/blob/master/docs/team/quitejasper.md#3-enhancements-implemented-) 
4. [Contributions to the User Guide](https://github.com/quitejasper/tp/blob/master/docs/team/quitejasper.md#4-contributions-to-the-user-guide-) 
5. [Contributions to the Developer's Guide](https://github.com/quitejasper/tp/blob/master/docs/team/quitejasper.md#5-contributions-to-the-developers-guide-)
6. [Contributions to Team Based Tasks](https://github.com/quitejasper/tp/blob/master/docs/team/quitejasper.md#6-contributions-to-team-based-tasks-)
7. [Review/Mentoring Contributions](https://github.com/quitejasper/tp/blob/master/docs/team/quitejasper.md#7-reviewmentoring-contributions-)

### 1. Code Contributed
Click [here to view the code that I contributed](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=quitejasper&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=quitejasper&tabRepo=AY2122S2-CS2113-F10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

### 2. Features Implemented
- Add Command 
  - What it does: Adds an item to the inventory list, together with its quantity and description.
  - Justification: It is a fundamental feature; items are required in the inventory list for management.
    
- Delete Command
  - What it does: Removes an item from inventory completely. 
  - Justification: Items may have been added by mistake. This command allows users to rectify by deleting the item before adding the item agan. Items may also be removed for other reasons (e.g. lost, broken etc.). 
    
- ListFutureBorrowings Command
  - What it does: Lists all borrowings in the future based on the existing borrow records of every item.. 
  - Justification: Users would be able to quickly retrieve such information for their future planning. 
    
- ListOverdueBorrowings Command
  - What it does: Lists all borrowings that are overdue based on the existing borrow records of every item. 
  - Justification: Users would be able to quickly retrieve such information for their future planning. 
  
### 3. Enhancements Implemented
- Wrote tests to increase coverage:
  - Add Command
  - Delete Command
  - ListFutureBorrowings Command
- Created BorrowRecord stubs to be used for testing for various classes.
- Used Java Streams to enhance implementation of 
  - ListCurrentBorrowingsCommand, 
  - ListFutureBorrowingsCommand and 
  - ListOverdueBorrowingsCommand

### 4. Contributions to the User Guide
- Usage of programme to:
  - Add Items
  - Delete Items
  - List Future Borrowings
  - List Overdue Borrowings
-  Command Summary

### 5. Contributions to the Developer's Guide
 - Implementation (with sequence diagrams) of:
   - Add Command
   - Delete Command
   - ListFutureBorrowings Command
   - ListOverdueBorrowings Command
   
### 6. Contributions to Team Based Tasks
 - Setting up the GitHub team org/repo
 - Necessary general code enhancements
 - Maintained issue tracker
 - Created PR label "review.InProgress" to highlight to members that PRs are still work in progress and that no review is needed at that time

### 7. Review/Mentoring Contributions
Team CS2113-T11-2 Repo:
- [Issue #50](https://github.com/AY2122S2-CS2113-T11-2/tp/issues/50)
- [Issue #51](https://github.com/AY2122S2-CS2113-T11-2/tp/issues/51)
- [Issue #54](https://github.com/AY2122S2-CS2113-T11-2/tp/issues/54)
- [Issue #61](https://github.com/AY2122S2-CS2113-T11-2/tp/issues/61)
- [Issue #63](https://github.com/AY2122S2-CS2113-T11-2/tp/issues/63)
- [Issue #66](https://github.com/AY2122S2-CS2113-T11-2/tp/issues/66)
- [Issue #70](https://github.com/AY2122S2-CS2113-T11-2/tp/issues/70)
- [Issue #74](https://github.com/AY2122S2-CS2113-T11-2/tp/issues/74)

Team CS2113T-T09-2 Repo:
 - Gave feedback on DG. [[Link](https://github.com/nus-cs2113-AY2122S2/tp/pull/5/files/902804ff17611e2aff75739dca100252cba52026)]
 