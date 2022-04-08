# Roy Tang - Project Portfolio Page

## Project - SplitLah
<hr>
SplitLah is a Command Line (CLI) program that is written in Java and meant to run on `Java 11` . SplitLah is an 
application for keeping track of expenses during group outing sessions, specifically for those 
who are budget conscious. It then proceeds to split the expenses according to the group members' individual costs for 
the activities they participated in, for a particular session.


## Summary of Contributions
<hr>

### Code Contributions
Code Contribution: [RepoSense Link](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=froststein&tabRepo=AY2122S2-CS2113T-T10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
#### New Features
* [Session Create](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/command/SessionCreateCommand.java)
  * Allows users to create sessions in the application.
* [Session Delete](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/command/SessionDeleteCommand.java)
  * Allows users to delete sessions in the application.
* [Session Edit](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/command/SessionEditCommand.java) 
  * Allows users to edit previously created sessions.
* [Session List](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/command/SessionListCommand.java)
  * Allows users to view all previously created sessions.
* Storage
* [Manager](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/data/Manager.java)
  * This feature was implemented so that data entered by the user during the lifetime of the application would be stored.
  * This is done by using the `Serializable` class in `java`.

#### Enhancements to existing features:
* Implemented a wrapper class for storing a list of Person objects.
* Added `Junit` Testing for `SessionCreateCommand`, `SessionDeleteCommand`, `SessionEditCommand` and `Profile`.
  * Ensure each Junit Test achieved at least 90% coverage.

### Documentation Contributions
Documentation Contribution: [RepoSense Link](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=froststein&tabRepo=AY2122S2-CS2113T-T10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs&authorshipIsBinaryFileTypeChecked=false)
#### User Guide
* Added documentation for `SessionCreateCommand`, `SessionDeleteCommand`, `SessionEditCommand` and `SessionListCommand`

#### Developer Guide
* Added the introduction and a how to use section.
* Added the application's architecture diagram and component interaction diagram to show how each internal component of the application works together.
* Explained how the `Storage` Component works in the application [#403](https://github.com/AY2122S2-CS2113T-T10-1/tp/pull/403/files).

#### Project Management
* Setup Organization & Team Repository.
* Managed `Issue Tracker` to ensure no task is left out & all pending task are tracked.
* Managed Milestones (`v1.0`, `v2.0`, `v2.1`)
* Managed `v2.0` release.
* Listed out objectives to be met for each team meeting.
* Ensured the team's progress was on schedule.

#### Miscellaneous Contributions
* Set up a Telegram channel for sending pull request messages.
    * This is to ensure all PRs are reviewed in a timely fashion.
    * Also ensures that important PRs are reviewed by at least 2 parties before merging.
* Bugs found for other team during PED [Bugs found](https://github.com/froststein/ped/issues)
