# Seth Lee - Project Portfolio Page

## Overview
Project: IHospital

IHospital is a desktop application meant for staff in hospitals.
Its main purpose is to manage patients, doctors, nurses, appointments and wards data,
and it’s optimised for use via a Command Line Interface (CLI).
It is written in Java, and has about 3k LoC.

## Summary of Contributions
### Features and Enhancements Implemented
#### New Feature: Edit Nurse
What it does: Allows the user to edit the details of an Nurse.
Justification: This feature improves the product because a user might make mistakes when adding a Nurse to the system
and an edit function allows the user to correct his mistakes. Also, an edit function allows the user to update
the latest details of a Nurse, for example when a Nurse has promoted and her title changes.

#### New Feature: Edit Ward
What it does: Allows the user to edit the details of a Ward.
Justification: This feature improves the product because a user might make mistakes when adding a Ward to the system
and an edit function allows the user to correct his mistakes. Also, an edit function allows the user to update
the latest details of a Ward, for example when a Doctor/Patient/Nurse is reassigned to another ward.

#### New Feature: List Nurse
What it does: Allows the user to view the lists of Nurses.
Justification: This feature improves the product because a user is able to view all the
records of Nurses in IHospital.

#### New Feature: List Ward
What it does: Allows the user to view the lists of Wards.
Justification: This feature improves the product because a user is able to view all the
records of Wards in IHospital.

#### New Feature: Delete Nurse
What it does: Allows the user to remove a Nurse from IHospital.
Justification: This feature improves the product because a user is able to remove a Nurse 
from the system. This makes the product realistic as in the real world, Nurses can leave their current
hospitals to move to a different one.

#### New Feature: Delete Ward
What it does: Allows the user to remove a Ward from IHospital.
Justification: This feature improves the product because a user is able to remove a Ward
from the system. This makes the product realistic as in the real world, hospitals can choose
to close down Wards due to low capacity.

#### New Feature: Search Nurse
What it does: Allows the user to search for a particular Nurse from IHospital.
Justification: This feature improves the product because a user is able to search for a particular
Nurse in IHospital. For example, if the user wants to check if a particular Nurse is working for IHospital

#### New Feature: Search Ward
What it does: Allows the user to search for a particular Ward from IHospital.
Justification: This feature improves the product because a user is able to search for a particular
Ward in IHospital. For example, if the user wants to check if a particular Ward exists in IHospital, 
or check for the details of that particular Ward

#### JUnit Tests: Nurse and NurseList
Added JUnit tests for both Nurse and NurseList

#### Code contributed:
[RepoSense](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=sethlxk&tabRepo=AY2122S2-CS2113-T11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=functional-code&authorshipIsBinaryFileTypeChecked=false)

#### Project management:
Managed releases v1.0 - v2.1 (3 releases) on GitHub

#### Documentation:
User Guide:
Added documentation for the features `add`, `list`, `search`, `edit`, `delete`

Developer Guide:
Added implementation details of the `delete doctor`, `add appointment` and `list nurse` feature.


