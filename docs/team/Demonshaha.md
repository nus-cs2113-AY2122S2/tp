# Zhou Chengxu - Project Portfolio Page

## Overview
Project: IHospital

IHospital is a desktop application meant for staff in hospitals.
Its main purpose is to manage patients, doctors, nurses, appointments and wards data,
and it’s optimised for use via a Command Line Interface (CLI).
It is written in Java, and has about 3k LoC.

### Summary of Contributions
#### New Feature: Added the ability to store the information in the local repository
What it does: Allows the user to store the information they input.

Justification: This feature makes the project able to memory the information that users input, which
is necessary for this IHospital project.
The project should be able to have its own memory so that will not lose data.

#### New Feature: Added Parser class to parse users commands
What it does: Parse users' input to command.

Justification: This feature make the project can understand users input, translate the input to the command
and make the project more manageable.



#### Code contributed:
[RepoSense](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=Demonshaha&tabRepo=AY2122S2-CS2113-T11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

#### Project management:
Managed releases v1.0 - v2.1 (3 releases) on GitHub

#### Enhancements to existing features:
1. Add exception for adding appointments.
2. Add parser test to make sure parser can work in the right way.
3. Add search by doctor to search appointments for specified doctor.
4. Add sort by time function for appointments to better manage appointments.


#### Documentation:
User Guide: 
Added documentation for the features `add`, `sort`, `search`, `edit`, `delete`

Developer Guide:
Added implementation details of the `storage` feature.

#### Community:
Reviewed other PRs and create issues and assign them to teammates to manage the project.
[#91](https://github.com/AY2122S2-CS2113-T11-2/tp/pull/91)