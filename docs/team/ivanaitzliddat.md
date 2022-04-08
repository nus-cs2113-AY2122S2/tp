# Tan Ivan - Project Portfolio Page

## Project - SplitLah
<hr>
SplitLah is a Command Line (CLI) program that is written in Java and meant to run on `Java 11` . SplitLah is an 
application for keeping track of expenses during group outing sessions, specifically for those 
who are budget conscious. It then proceeds to split the expenses according to the group members' individual costs for 
the activities they participated in, for a particular session.

## Summary of Contributions
<hr>

### Code Contributions
Code contribution: [RepoSense Link](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=ivanaitzliddat&tabRepo=AY2122S2-CS2113T-T10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

#### New Features
* Activity
  * This feature was implemented so that details about an activity can be stored in an object.
* Activity Create
  * Allows users to create activities in the application.
* Activity Delete
  * Allows users to delete activities in the application.
* Group View
  * Allows users to view a specific group that was created.
* Group List
  * Allows users to view all previously created groups.

#### Enhanced Features
* Separated dependency of parser class from command class by creating Command Parser classes for certain features.

#### Testing
* Added JUnit tests for `ActivityCreateCommand` and `ActivityDeleteCommand` classes.
* Added JUnit tests for `ActivityCreateCommandParser`, `ActivityDeleteCommandParser` and `GroupViewCommandParser` classes.

### Documentation Contributions
Documentation Contribution: [RepoSense Link](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=ivanaitzliddat&tabRepo=AY2122S2-CS2113T-T10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs&authorshipIsBinaryFileTypeChecked=false)

#### User Guide
* Added documentation for `ActivityCreateCommand`, `ActivityDeleteCommand`, `GroupViewCommand` and `GroupListCommand`.
* Made the first round of edits to the User Guide to ensure that feedbacks from the PE Dry Run were well acknowledged. [#391](https://github.com/AY2122S2-CS2113T-T10-1/tp/pull/391/files)

#### Developer Guide
* Added the sequence diagrams and workflow for `ActivityCreateCommand`, `ActivityDeleteCommand`, `GroupViewCommand` and `GroupListCommand`.
* Assisted in ensuring that diagrams were correct and followed the logic of the application.

### Miscellaneous Contributions
* Updated Issues to ensure that the team is on track.
* Assisted in fixing bugs after the PE dry run.
* Ensured that as much as possible, overall coding standardisation .

#### Project Management
* Summarised the overall timeline for the module to ensure that deadlines are known beforehand.
* Do meeting minutes when necessary in order to ensure that important details are not missed out.
