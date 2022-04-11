# Hans Heng - Project Portfolio Page

## Project: PlanITarium

PlanITarium is an application that assists you and your family in managing your finances, optimized for use on the
Command Line Interface (CLI). You can use it to view your monthly financial status, logically group family members for
better management, and categorise your expenditures. PlanITarium is written in, and meant to be run on `Java 11`.

### Summary of Contributions

* **Code
  Contributed**: [RepoSense Link](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=hanshenggit&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=HansHengGit&tabRepo=AY2122S2-CS2113T-T10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

<p></p>

* **New Feature**: Added Expenditure class
  * Functionality: The expenditure class takes in various details of the user's expenditure input and stores them.
    These details are then used in other operations such as calculating disposable income or displaying the overview
    of all expenditure.
  * Justification: This feature is part of the foundation of the application. Most operations revolve around using
    the expenditure details.

* **New Feature**: Added ExpenditureList class.
  * Functionality: The expenditure list class tracks a list of expenditure objects for each person added to the
    application.
  * Justification: This feature improves the product as it allows the user to have multiple expenditure entry,
    which is also part of our product implementation plan.

* **New Feature**: Implemented storage for saving and loading of data.
  * Functionality: The storage feature that loads existing data from the local file on application start up, 
    and saves data into the local file upon terminating the program. Checks to see if file path or file data is
    corrupted.
  * Justification: The feature is part of the foundation of a tracking application as users can now have a saved 
    record instead of having to input data again upon every application start up. This feature also allows users
    to transfer data.

* **Enhancements to Existing Features**:
    * Implemented JUnit testing for parts of Expenditure and ExpenditureList. [#83](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/83/files)
    * Made Expenditure and ExpenditureList more defensive. [#81](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/81/files)
    * Implemented JUnit testing for Storage and made it more defensive. [#168](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/168/files)
    * Made Storage more defensive. [#165](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/165/files)

<p></p>

* **Documentation**:
    * User Guide (UG):
        * Assisted in documentation for features `addout`, `deleteout` and `editout`. [#142](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/142/files)
        * Provide fixes to markdown. [#245](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/245/files)
    * Developer Guide (DG):
        * Added sections on `Storage` design and implementation. [#112](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/112)
        * Added UML diagrams for `Storage` component and `Storage` implementation. ([#112](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/112/files),
          [#221](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/221))
        * Added manual testing instructions for loading data. [#245](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/245/files)

<p></p>

* **Team-based Contribution**:
  * PRs reviewed (with non-trivial comments): [#21](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/21/files)
  