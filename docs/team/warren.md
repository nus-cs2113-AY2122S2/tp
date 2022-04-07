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
 * Parsing API
   * Provides a full set of methods to parse different arguments of a user input.
   * Performs preliminary validity checking of user input for extraneous arguments and invalid commands.
 * Session 
 * Session Summary
 * Help command
 * PersonCostPair

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
 * Added questions under Frequently asked questions section.
 * Maintained the `Command Summary`

#### Developer Guide

### Miscellaneous Contributions

#### Project Management

