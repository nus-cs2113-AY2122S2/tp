# Wang Huilin - Project Portfolio Page

## Overview

PlanITarium is an application that assists you and your family in managing your finances, optimized for use on the
Command Line Interface (CLI). You can use it to view your monthly financial status, logically group family members for
better management, and categorise your expenditures. PlanITarium is written in, and meant to be run on `Java 11`.

Given below are my contributions to the project.

### Summary of Contributions

#### Code Contribution
 [RepoSense Link](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=hlwang56&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=BradenTeo&tabRepo=AY2122S2-CS2113T-T09-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code&authorshipIsBinaryFileTypeChecked=false)

* **New Feature**: Implemented `XYZCommand` for each feature 
  * Functionality: The `Command` is an abstract class and have `XYZCommands` as subclasses. Each `XYZCommand` will
  overwrite the `execute()` method in `Command`, which help the program to reach lower-level methods. 
  * Justification:
  * Highlights:
* **New Feature**: Implemented `CommandFactory`
  * Functionality: `Commandfactory` is to generate `XYZCommand` according to the keyword of user input.
  * Justification:
  * Highlights:
* **New Feature**: Implemented UI component to standardize output
  * Functionality:
  * Justification:
  * Highlights:
* **Enhancements to Existing Features**: 
    * Implemented JUnit testing for all commands:
    * Standardize exceptions to make them more organized:

#### Documentation Contribution

* **User Guide**:
    * Drafted UG with basic structure and information: [#128](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/128) [#129](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/129)
    * Added Screenshot for each commands
    * Made refinement on Command Summary and Category Summary: 
* **Developer Guide**
    * Added Command component section and UI component section:
    * Added `Add Person` implementation based on the working flow of `Command`: [#173](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/173) [#120](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/120)
    * Added UML diagrams and descriptions for Commands and UI: [#147](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/147) [#120](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/120)

#### Team-based Contribution
* PR reviewd (with non-trival comments):
* Helped to refine issues 

#### Community-based Contribution:
* Above average number of bugs found in PE-D
* Reviewed above average number of iP PR during peer review

