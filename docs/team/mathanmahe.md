# Mathan Mahendran - Project Portfolio Page

## Project: AlloNUS
AlloNUS (“All On Us”) is an all-in-one platform
for tracking your classes, expenses, and personal contacts
optimized for use via a Command Line Interface (CLI).
If you can type fast, AlloNUS can get your schedule, expenses,
and contact management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.



### Summary of Contributions
Responsible for creating the modules package and all its components
* New feature: Add modules: 
  - What it does: Allows the user to add a new module based on module parameters.
  - Justification: Allows user to add modules to keep track of their classes.
  - Highlights: It defined constraints for each module parameter so as to ensure data validation. 
  
* New feature: List modules:
  * What it does: Allows the user to view a list of modules.
  
* New feature: Delete modules:
  * What it does: Deletes an existing module in the list.
  
* New feature: Find modules
  * What it does: Finds an existing module in the list based on a search query.
  * Highlights: Works on a search query that can be based on a keyword from any part of a module parameter.
  
* New feature: Edit modules
  * What it does: Edits an existing module in the list.
  * Highlights: Utilizes predefined constraints and validation functions to ensure data validatoin.

* Enhancement: Parse .ics file from nusmods.com
  * What it does: Parses ics file that contains an NUS students academic schedule then adds all the modules to the list instantly.
  * Justification: Allows the user to instantly add their schedule to the module list without having to key in using individual add commands.
  The implementation was challenging as it required me to debug existing library dependency errors and resolve them.
  I also had to look for alternatives for java.util.date to get it to work on different OS, as there were build errors 
  from using that library on other OS.
  This is an extra feature that is meant for better user experience as it saves time.
  
  * Highlights: Reads the .ics file  
  * Credits: ical4j library that is used to read the .ics file.
* Code contributed: [Reposense link](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=F10-4&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=mathanmahe&tabRepo=AY2122S2-CS2113-F10-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
* Project Management
  * Managed release V1.0.0 on GitHub
  * Suggested solution to gradle build error that team was experiencing despite correct code.
  
* Contributions beyond project team:
* Contributions to the User Guide:
* Contributions to the Developer Guide:
