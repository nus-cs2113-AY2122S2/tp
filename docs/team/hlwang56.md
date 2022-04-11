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
  * Justification: This improves the overall structure of the program by applying the idea of SLAP. It makes the process
  of execution clearer and more organized.
  * Highlights: This provides space for further development on adding more features. Developer only need to add new 
  `XYZCommand` if more features are added and are needed to be accessed by user.
* **New Feature**: Implemented `CommandFactory`
  * Functionality: `Commandfactory` is to generate `XYZCommand` according to the keyword of user input.
  * Justification: This will connect `Main` and `XYZCommand` together as in it will return a valid command to execute if
  the input is valid. 
  * Highlights: This could simplify further development on command component since developer only need to add new
    command on without touching previous implementation.
* **New Feature**: Implemented UI component to standardize output.

* **Enhancements to Existing Features**: 
    * Implemented JUnit testing for all commands: [PR90](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/90) [PR163](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/163) [PR257](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/257)
    * Standardize exceptions to make them more organized: [PR147](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/147/5378b7f2bf56bde0ed28324)
#### Documentation Contribution

* **User Guide**:
    * Drafted UG with basic structure and information: [PR128](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/128) [PR129](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/129)
    * Added Screenshot for each commands: [PR237](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/237)
    * Made refinement on Command Summary and Category Summary: [PR237](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/237) 
* **Developer Guide**
    * Added Command component section and UI component section: [PR237](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/237) [PR120](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/120)
    * Added `Add Person` implementation based on the working flow of `Command`: [PR173](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/173) [PR120](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/120)
    * Added UML diagrams and descriptions for Commands and UI: [PR237](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/237) [PR147](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/147) [#120](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/120)

#### Team-based Contribution
* PR reviewd (with non-trival comments): [PR135](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/135) [PR82](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/82) [PR27](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/27) [PR40](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/40)
* Helped to refine issues 

#### Community-based Contribution:
* Above average number of bugs found in PE-D
* Reviewed above average number of iP PR during peer review

