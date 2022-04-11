# Clarence Chua Ying How - Project Portfolio Page

## Overview
ClubInvMgr is a desktop CLI app for inventory management for CCA clubs. It allows users to add, borrow, and view the items in the inventory easily.

## Summary of Contributions
### Code Contributed
[Click to view code contribution on RepoSense](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=cheshire-doge&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

### Enhancements Implemented
1. Implemented `ListCommand` class to list all items in the inventory
2. Implemented `ListAvailableBorrowingsParser` and `ListAvailableBorrowingsCommand` classes to list all items that can be borrowed through a certain time period.
   1. The implementation of these classes required the checking of every item's future borrowings, and as to whether these future borrowings clash with the dates requested in the command
3. Implemented `CancelFutureBorrowingsParser` and  `CancelFutureBorrowingsCoomand` classes that help cancel future item reservations
   1. There was difficulty implementing these classes as a new method of selecting which reservation had to be created. This was done by numbering future borrowings from a specific person, and needing to specify who's future borrowing was to be removed in the command

### Contributions to the User Guide
1. Wrote the user guide for `cancel`, `list`, `listab`.

### Contributions to the Developer Guide
1. Created `List Command` and its sequence diagram.
2. Created `List Available Borrowings Command` and its sequence diagram.
3. Created `Cancel Future Borrowings Command` and its sequence diagram.
4. Update User Stories for v2.1

### Contributions to team-based-tasks
1. Delivered code enhancements.
2. Raised bugs and inconsistencies to members.

### Review/mentoring contributions
Raising bugs on Team's Repo:
1. [Issue #123](https://github.com/AY2122S2-CS2113-F10-2/tp/issues/123)
2. [Issue #124](https://github.com/AY2122S2-CS2113-F10-2/tp/issues/124)

Team CS2113T-T10-2 Repo:
1. Gave feedbacks on Developer Guide. [[Link](https://github.com/nus-cs2113-AY2122S2/tp/pull/7/files/ee0b51c117f30b51f8aba455aa4dfd1040e4b9c2#diff-1a95edf069a4136e9cb71bee758b0dc86996f6051f0d438ec2c424557de7160b)].