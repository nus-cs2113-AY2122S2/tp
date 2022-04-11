# Chun Leong's Project Portfolio Page

## <span style="color:orange">Project: Spendvelope</span>
Spendvelope is a desktop application used for recording expenditure. The user interacts with it using a Command Line 
Interface, thus making the application suited for fast typists. The application is written in Java, and has about 2.5 
kLoC.

Given below are my contributions to the project.
* Code contributed: [RepoSense link](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=allyfern72&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)


* Enhancement: Implemented the command parser.
  * What it does: accepts user input command strings and returns a corresponding command object. 
  * Highlights: user input parameters may be input in any order as long as the correct delimiters are used.
* Enhancement: Implemented storage of dates as LocalDate objects.
  * What it does: stores dates of records as LocalDate objects rather than as Strings.
  * Justification: this allows future features to utilise this enhancement by performing comparison or operations on the
date. This enhancement ensures that valid dates are input by the user.
* Enhancement: Implemented a manager for total user expense.
  * What it does: stores and tracks the total user expenditure based on their records. It is updated whenever records 
are added or deleted.
  * Highlights: the implementation is able to handle high precision by using BigDecimal type to store the total expense.


* General code enhancements: 
  * Ensured code adhered to coding standards.
  * Configured code written by different developers to ensure that they function as intended when integrated to the main
app.
  * Formatted and organised the User Guide and Developer Guide.
  * Managed release of v2.0 on GitHub.


* Community:
  * Reported bugs and suggestions for other teams in the class.


* Contributions to Developer Guide:
  * Provided the overall format of the Developer Guide.
  * Added Architecture section.
  * Constructed `add` command sequence diagram


* Contributions to User Guide:
  * Provided the overall format of the Developer Guide.
  * Added Quick Start section.
  * Added `add` section.