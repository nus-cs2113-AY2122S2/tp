# Saurav Venu Matheril - Project Portfolio Page

## Project - SplitLah
<hr>
SplitLah is a Command Line (CLI) program that is written in Java and meant to run on `Java 11` . SplitLah is an 
application for keeping track of expenses during group outing sessions, specifically for those 
who are budget conscious. It then proceeds to split the expenses according to the group members' individual costs for 
the activities they participated in, for a particular session.

### Code Contributions
Code Contribution: [RepoSense Link](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=matheril&tabRepo=AY2122S2-CS2113T-T10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

#### New Features
* Activity Cost
    * This class encapsulates the cost paid and owed for a specific activity by a person.
* Activity Edit
    * Allows users to edit an activity, only having to specify the details they wish to change.
* Session View
    * Allows users to view a session. Displays session details and a list of the activities involved.
* Text UI
    * Acts as a user interface, and offers methods to other classes to print to and read from the user interface.
* Person
    * This class represents a person. It encapsulates its `Name` and a list of `ActivityCost`s associated with it.
* Name
    * This class represents a name. It sanitises the name used to instantiate it by stripping whitespace from it, and 
      includes a method that checks if the name is valid (contains only alphabetical characters).
     
#### Enhanced Features
* Separated dependency of the `Parser` class from the `Command` class by creating `ActivityEditCommandParser` and 
  `SessionViewCommandParser`.
* TableFormatter
    * A `TableFormatter` object creates a table that can be modified with new data at any time. When printed, it
      produces a neatly formatted and decorated table that automatically aligns its borders to the data stored within.
      Other classes can create `TableFormatter` objects, edit them and pass them to `TextUI` to be printed to the user 
      interface.
* TableFormatterRow
    * A helper class for `TableFormatter`. It represents a single row of the table.
* Message
    * Set up this class so other developers can store their hardcoded strings in it, such as error and logging messages.
* InvalidDataException
    * This class represents an exception thrown when data-related errors occur.
* InvalidFormatException
    * This class represents an exception thrown when format-related errors occur.

#### Testing
* Added JUnit tests for `ActivityEditCommand`,`ActivityEditCommandParser`, `Name` and `ActivityCost`.
* As `TextUI` mostly consists of methods that print to the interface instead of returning a value, we decided not to add JUnit tests for this 
  class.
 
### Documentation Contributions
Documentation contribution: [RepoSense Link](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=matheril&tabRepo=AY2122S2-CS2113T-T10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs&authorshipIsBinaryFileTypeChecked=false)

#### User Guide
* Added documentation for `ActivityEditCommand` and `SessionViewCommand`.
* Wrote sections aimed at introducing users to the program, such as the introduction (SplitLah), the target audience
  (Who SplitLah is for) and the basic workflow of the program (How SplitLah works).
* Standardised language and grammar across the guide.
 
#### Developer Guide
* Added documentation for `SessionViewCommand`.

### Miscellaneous Contributions
* Recorded and narrated a demo video with slides and annotations including footage of the program in action for use
  in the product demo presentation.
