# Wan Yaxin - Project Portfolio Page

## Overview

### Project: CSProjPlanner
CsProjPlanner is a desktop planner application used for planning projects for Computer Science student. The user will interact with it using a CLI. It is written in Java.  
Given below are my contributions to the project.


### Summary of Contributions
#### Code contributed

[Yaxin tP code dashboard](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=yaxinjoy&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=Nineves&tabRepo=AY2122S2-CS2113-F10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* Implement list project feature.
* Implement initial display and exit function.
* Implement save and load feature.

#### Enhancements implemented:
* Refactor some codes of functions implemented by other members in v1.0
  * Add a new method `getProjectName` since both add project and delete project function will use it. 
  * Update addProject to consider the case when a project has been added to the list (avoid adding the same project repeatedly).
* Add more functions of Project and ProjectList class in v2.0
  * More methods are introduced in Project and ProjectList class (e.g., getProject, findProjectIndex), which makes it easier for external class to use.
* Refactor the code based on the reviews in v2.1
  * Turn some string value into magic strings.

#### Contributions to the UG
* Add documentation for the introduction.
* Add documentation for the quick start.
* Add documentation for the feature **Print all projects**.
* Add documentation for the feature **Exit**.
* Add documentation for FAQ.

#### Contributions to the DG
* Add documentation for **Print Project List**.
* Add sequence diagrams for **Print Project List**.
* Add documentation for user stories.

#### Contributions to team-based tasks
* Attend group meetings and finish work before the deadline.
* Contribute to introduction, quick start, FAQ sections of UG

#### Review/mentoring contributions
* Reviewed and merged group members' PRs.
