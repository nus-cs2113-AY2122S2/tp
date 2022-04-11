# Tai Kah Kiang - Project Portfolio Page (PPP)

## Overview
HALPMI is a Command Line Interface (CLI) Application that allows administrators in clinics to manage the clinic's day-to-day
administrative tasks. More specifically, using HALPMI the user is able to insert new information regarding Doctors, Patients
and Medication. Users can also schedule appointment for Patients with Doctors and keep track of medication stocks.
## Summary of Contributions

**Code contributed:** [RepoSense link](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=kktai1512&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18)
<br>
**Enhancement implemented:**

| Enhancement                                                 | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
|-------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| View patient                                                | Implemented the view patient logic, to either view a particular patient, or all patients                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| Add patient                                                 | Implemented the add patient function                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     |
| Add doctor                                                  | Added add doctor into Manager                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| Refactor validator                                          | Refactored all the validation out from parser into a separate class, clean up Validator code and remove repetitions to make code cleaner. Reformatted code such that there's a higher cohesion between methods                                                                                                                                                                                                                                                                                                                                                                                                           |
| Exceptions                                                  | Implemented exceptions such that methods now return exceptions instead of printing error message straight from the method itself                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         |
| Exception abstraction                                       | Created a parent exception `HalpmiException` and now `UserInputErrorException`, `DuplicateEntryException` and `NotFoundException` inherits it                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| Bug fixes                                                   | Fix bugs that are reported by other teams                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
| Storage - corrupted files handling & refactoring of methods | Implemented a function such that the program does a preliminary check for the input files using Validator, such that the data makes sense, prompts the user for any corrupted lines, and moves those into a separate file. Since the data is all connected, if one part is deleted, another part should be corrupted too, hence this check ios essenial. Orignally researched on file encryption, and tried to implement it but found out that it makes more sense now to not put encryption such that the admin can easily sees the data outside of the application. Maybe a different encryption method could be used. |


**UG contributions:**
* Basic add, view, delete command summary table for V1.0 submission.
* Storage - corrupted files explanation
* Warnings and additional info
<br>

**DG contributions:**
* Parser class diagram and explanation
* Validator class diagram and explanation
* Parser-validator class logic explanation with sequence diagram
* Exception class explanation
  <br>

**Team-based tasks contributions:**
* Gave recommendations and opinions on features discussed to be implemented
* Gave suggestions on the logic on how certain features are implemented
* Helped fixed bugs by testing out the application
* Set up Zoom meeting for every team meeting
* Reviewed PRs and gave suggestions 
* Updated user/developer docs for typos
* Set up Trello for project management 
* General code clean up

Overall, I'm thankful for a great team, as everyone helps each other out.