# Warren Chong - Project Portfolio Page

## Project - SplitLah
<hr>
SplitLah is a Command Line (CLI) program that is written in Java and meant to run on `Java 11`. SplitLah is an 
application for keeping track of expenses during group outing sessions, specifically for those 
who are budget conscious. It then proceeds to split the expenses according to the group members' individual costs for 
the activities they participated in, for a particular session.

## Summary of Contributions
<hr>

### Code Contributions
Code Contribution: [RepoSense Link](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=&sort=totalCommits%20dsc&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=warrencxw&tabRepo=AY2122S2-CS2113T-T10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

#### New Features
* [Parsing API](https://github.com/AY2122S2-CS2113T-T10-1/tp/tree/master/src/main/java/seedu/splitlah/parser)
   * Provides a full set of methods to parse different arguments of a user input.
   * Performs preliminary validity checking of user input for extraneous arguments and invalid commands.
* [Session](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/data/Session.java)
   * Represents a group outing that involves a list of participants and contains one or more activities.
* [Session Summary](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/command/SessionSummaryCommand.java)
   * Creates a transaction summary that helps users calculate how much each person in a session
     must pay and to whom they should pay for all debts to be resolved.
   * Minimises the number of transactions required to be made.
   * One of the key features of `SplitLah`.
* [Help command](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/command/HelpCommand.java)
   * Provides users a reference of all available commands while they are using `SplitLah`.
* [PersonCostPair](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/util/PersonCostPair.java)
   * A utility class that supports `SessionSummaryCommand` in minimising transactions required to be made.
   * Encapsulates a `Person` object and their overall cost in the session as a debt owed by or owed to an individual.

#### Enhancements to existing features
 * CommandParser interface
   * To isolate Parser component related dependencies from Command classes.
 * ParserUtils
 * ParserErrors

#### Testing
 * Added comprehensive JUnit tests for `Parser`, `ParserUtils` and `Session` classes
 * Added JUnit tests for `SessionSummaryCommandParser` and `HelpCommandParser` classes

### Documentation Contributions
Documentation Contribution: [RepoSense Link](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=&sort=totalCommits%20dsc&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=warrencxw&tabRepo=AY2122S2-CS2113T-T10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs&authorshipIsBinaryFileTypeChecked=false)

#### User Guide
 * Added documentation for `Settling all transactions for a session` and `Listing all available commands`
 * Added questions under Frequently Asked Questions (FAQ) section.
 * Maintained the `Command Summary`

#### Developer Guide
 * Added description for `Profile`
 * Added section for `Parser` component and `Command` component
 * Added section under `Implementations` for `Parsing of Commands` and `Settle a session`

### Miscellaneous Contributions
 * Spearheaded replacement of `XYZCommand#prepare` method with a `XYZCommandParser` class
   * Encouraged following of separation of concerns principle to improve code quality
 * Headed the fixing of bugs found by other teams during the PE dry run
   * Set up a documentation for the sorting of bugs and allocation of duties

#### Project Management
* Worked on pre-planning and ideation of project
  * Drafted sketches of class diagrams and visualisation of goals
