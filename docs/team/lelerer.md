# Ren Tianle - Project Portfolio Page

## Project - SplitLah
<hr>
SplitLah is a Command Line (CLI) program that is written in Java and meant to run on `Java 11` . SplitLah is an 
application for keeping track of expenses during group outing sessions, specifically for those 
who are budget conscious. It then proceeds to split the expenses according to the group members' individual costs for 
the activities they participated in, for a particular session.


## Summary of Contributions
<hr>

### Code Contributions
Code Contribution: [RepoSense Link](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=&sort=totalCommits%20dsc&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=lelerer&tabRepo=AY2122S2-CS2113T-T10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

#### New Features
* [Group Create](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/command/GroupCreateCommand.java)
    * Allows users to create groups in the application.
* [Group Delete](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/command/GroupDeleteCommand.java)
    * Allows users to delete groups in the application.
* [Group Edit](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/command/GroupEditCommand.java)
    * Allows users to edit previously created groups.
* [Activity List](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/command/ActivityListCommand.java)
    * Allows users to view all previously created activities within a session.
* [Activity View](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/command/ActivityViewCommand.java)
    * Allows users to view all previously created activities with a session.
* [Group](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/main/java/seedu/splitlah/data/Group.java)
    * Represents a group that involves a list of participants.


#### Enhancements to existing features:


#### Testing
* Added command `Junit` test for
  [GroupCreateCommand](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/test/java/seedu/splitlah/command/GroupCreateCommandTest.java),
  [GroupDeleteCommand](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/test/java/seedu/splitlah/command/GroupDeleteCommandTest.java) and
  [GroupEditCommand](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/test/java/seedu/splitlah/command/GroupEditCommandTest.java).
  [ActivityViewCommand](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/test/java/seedu/splitlah/command/ActivityViewCommandTest.java).
  [ActivityListCommand](https://github.com/AY2122S2-CS2113T-T10-1/tp/blob/master/src/test/java/seedu/splitlah/command/ActivityListCommandTest.java).

### Documentation Contributions
Documentation Contribution:

#### User Guide
* Added documentation for [`Creating a group`](https://ay2122s2-cs2113t-t10-1.github.io/tp/UserGuide.html#creating-a-group-group-create),
  [`Deleting a group`](https://ay2122s2-cs2113t-t10-1.github.io/tp/UserGuide.html#deleting-a-group-group-delete),
  [`Editing a group`](https://ay2122s2-cs2113t-t10-1.github.io/tp/UserGuide.html#editing-a-group-group-edit),
  [`Viewing an activity`](https://ay2122s2-cs2113t-t10-1.github.io/tp/UserGuide.html#viewing-an-activity-activity-view) and
  [`Listing an activity`](https://ay2122s2-cs2113t-t10-1.github.io/tp/UserGuide.html#listing-all-activities-in-a-session-activity-list) sections of the User Guide.

#### Developer Guide
* Added the explanation for [`Add a group`](https://ay2122s2-cs2113t-t10-1.github.io/tp/DeveloperGuide.html#add-a-group),
  [`Remove a group`](https://ay2122s2-cs2113t-t10-1.github.io/tp/DeveloperGuide.html#remove-a-group) and
  [`Edit a group`](https://ay2122s2-cs2113t-t10-1.github.io/tp/DeveloperGuide.html#edit-a-gorup) sections of the Developer Guide.

#### Project Management


#### Miscellaneous Contributions