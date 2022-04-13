#Dolph Xia Project Portfolio Page

## Overview
Project: IHospital

IHospital is a desktop application meant for staff in hospitals.
Its main purpose is to manage patients, doctors, nurses, appointments and wards data,
and it’s optimised for use via a Command Line Interface (CLI).
It is written in Java, and has about 3k LoC.

## Summary of Contributions
### Features and Enhancements Implemented
#### New Feature: Nurse
What it does: Created a nurse class which has unique attributes and behaviour.
Justification: This feature improves the product because Nurse is a essential group of staff in any hospital.
Nurse could also be extended to assigned to patient or doctor based on hospital's requirements in future.
Also, each nurse has a title which describes their position or roles in the hospital.
Each nurse also has a main ward they are assigned to, and they have the responsiblity to treat or attend to the needs of patients in the ward.

#### New Feature: Ward
What it does: Created a ward class which has unique attributes and behaviour.
Justification: This feature improves the product because Ward is a essential of any hospital, patient who stay in the ward can be attended to by doctors and nurses assigned to the ward.
Ward could also be extended to assigned patient and even appointments based on timing.
Each ward also has a list of nurses and doctors assigned to it.
Patient assigned to the ward will be attended by the doctors and nurses in the ward.

#### New Feature and improvements: List Nurse
What it does: Allows the user to view the lists of Nurses.
Justification: This feature improves the product because a user is able to view all the
records of Nurses in IHospital.

#### New Feature and improvements: List Ward
What it does: Allows the user to view the lists of Wards.
Justification: This feature improves the product because a user is able to view all the
records of Wards in IHospital.

#### New Feature: Add Nurse
What it does: Allows the user to add a Nurse to IHospital.
Justification: This feature improves the product because a user is able to add a Nurse
from the system. This makes the product realistic as in the real world, Nurses can join a hospital.

#### New Feature: Add Ward
What it does: Allows the user to add a Ward from IHospital.
Justification: This feature improves the product because a user is able to add a Ward
from the system. This makes the product realistic as in the real world, hospitals can choose
to add a new Wards due to high demand.


#### Code contributed:
[RepoSense]https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=DolphXty&tabRepo=AY2122S2-CS2113-T11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code&authorshipIsBinaryFileTypeChecked=false

#### Project management:
Managed releases v1.0 - v2.1 (3 releases) on GitHub

#### Documentation:
User Guide:
Added documentation for the features `add`, `list`, `search`

Developer Guide:
Added implementation details of the `add ward`, `patient` feature.