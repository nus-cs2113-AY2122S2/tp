# Lester Sim Jia Jun - Project Portfolio Page

## Overview
ClubInvMgr is a desktop CLI app for inventory management for CCA clubs. It allows users to add, borrow, and view the items in the inventory easily.

## Summary of Contributions
### Code Contributed
[Click to view code contribution on RepoSense](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=lestersimjj&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=lestersimjj&tabRepo=AY2122S2-CS2113-F10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

### Enhancements Implemented
1. Implemented `BorrowCommand` class to borrow an item from the inventory.
   1. The difficulty of implementing this class lies in the complexity of managing different borrowing records. eg. Records of the same item cannot overlap, must be easily retrievable by other classes such as `ListCurrentBorrowings`.
   2. Initial deliberations were the location of storing these records, if they should be stored in a separate ArrayList or attached to an item. After considering other methods' implementation, it was decided that each item would contain an ArrayList of borrowing records.
2. Implemented `Item` class which forms the basic entity of how items are stored.
3. Implemented `ListCurrentBorrowingsCommand` to list all items that are currently being borrowed.
4. Implemented `DescCommand` class to retrieve the description from a chosen item by the user.

### Contributions to the User Guide
1. Wrote the user guide for `borrow`, `desc`, `listcb`.
2. Created the base template for the user guide.
3. Update the Command Summary.

### Contributions to the Developer Guide
1. Created `Command` class diagram.
2. Created `Description Command` sequence diagram.
3. Added v1.0 and v2.0 user stories.

### Contributions to team-based-tasks
1. General code enhancements.
2. Maintained the issue tracker.
3. Release v2.0.

### Review/mentoring contributions
Team's Repo:
1. [Issue #86](https://github.com/AY2122S2-CS2113-F10-2/tp/issues/86)
2. [Issue #87](https://github.com/AY2122S2-CS2113-F10-2/tp/issues/87)
3. [Issue #116](https://github.com/AY2122S2-CS2113-F10-2/tp/issues/116)
4. [Issue #128](https://github.com/AY2122S2-CS2113-F10-2/tp/issues/128)
5. [Issue #165](https://github.com/AY2122S2-CS2113-F10-2/tp/issues/164)
6. [PR #85](https://github.com/AY2122S2-CS2113-F10-2/tp/pull/85)
 
Team CS2113-T11-1 Repo:
1. [Issue #319](https://github.com/AY2122S2-CS2113-T11-1/tp/issues/319)
2. [Issue #314](https://github.com/AY2122S2-CS2113-T11-1/tp/issues/314)
3. [Issue #306](https://github.com/AY2122S2-CS2113-T11-1/tp/issues/306)
4. [Issue #302](https://github.com/AY2122S2-CS2113-T11-1/tp/issues/302)
5. [Issue #296](https://github.com/AY2122S2-CS2113-T11-1/tp/issues/296)
6. [Issue #291](https://github.com/AY2122S2-CS2113-T11-1/tp/issues/291)
7. [Issue #281](https://github.com/AY2122S2-CS2113-T11-1/tp/issues/281)

Team CS2113-F12-1 Repo:
1. Gave feedbacks on DG. [[Link](https://github.com/nus-cs2113-AY2122S2/tp/pull/27/files)].