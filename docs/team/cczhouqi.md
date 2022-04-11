# Zhou Qi - Project Portfolio Page

## Overview
Project: IHospital

IHospital is a desktop application meant for staff in hospitals.
Its main purpose is to manage patients, doctors, nurses, appointments and wards data,
and it’s optimised for use via a Command Line Interface (CLI).
It is written in Java, and has about 4-5k LoC.

### Summary of Contributions
#### New Feature: Added UI class
What it does: Print messages to interact with the user.

Justification: This feature improves the product because it helps improve the user 
experience and make the product different from similar ones.

#### New Feature: Added Person, Doctor, Patient, DoctorList, PatientList and AppointmentList class
What it does: Allows the user to add and delete a person or an appointment.

Justification: This feature is the base of this product. It allows the target user to record information
for doctors, patients and appointments

#### New Feature: Added the ability to edit an appointment
What it does: Allows the user to edit the details of an appointment.

Justification: This feature improves the product because a user can make mistake
when adding appointment and a patient may want to change the time of their appointments
and the product should provide a convenient way to edit.

#### New Feature: Added the ability to view doctor/patient page
What it does: Allows the user to view the doctors/patients and their appointments.

Justification: This feature

#### JUnit Tests: DoctorTest, PatientTest, PersonTest
Added Junit tests for Doctor, Patient and Person.

#### JUnit Tests: DoctorListTest, PatientListTest, AppointmentTest
Added Junit tests for DoctorList, PatientList and Appointment.

#### Code contributed:
[RepoSense](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=cczhouqi&tabRepo=AY2122S2-CS2113-T11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

#### Project management:
Managed releases v1.0 - v2.1 (3 releases) on GitHub

#### Enhancements to existing features:
Handled Exceptions for `add` and `delete` (Pull request [#97](https://github.com/AY2122S2-CS2113-T11-2/tp/pull/97), [#98](https://github.com/AY2122S2-CS2113-T11-2/tp/pull/98), [#140](https://github.com/AY2122S2-CS2113-T11-2/tp/pull/140)).

Updated process (added exceptions) of 'add appointment': raise exception when adding
appointment to a doctor who has another appointment at that time (Pull request [#129](https://github.com/AY2122S2-CS2113-T11-2/tp/pull/129)).

#### Documentation:
User Guide: 
Added documentation for the features `help`, `add`, `sort`, `list`, `search`, `edit appointment`, `delete`, 
introduction, quick start, FAQ and command summary.

Developer Guide:
Added implementation details of the `exit` and `add doctor` feature. Added basic product information,
user stories, non-functional requirements and instructions for manual testing.

#### Community:
PRs reviewed (with non-trivial review comments): 
[#20](https://github.com/AY2122S2-CS2113-T11-2/tp/pull/20), 
[#24](https://github.com/AY2122S2-CS2113-T11-2/tp/pull/24),
[#25](https://github.com/AY2122S2-CS2113-T11-2/tp/pull/25),
[#27](https://github.com/AY2122S2-CS2113-T11-2/tp/pull/27),
[#44](https://github.com/AY2122S2-CS2113-T11-2/tp/pull/44),
[#46](https://github.com/AY2122S2-CS2113-T11-2/tp/pull/46),
[#91](https://github.com/AY2122S2-CS2113-T11-2/tp/pull/91),
[#99](https://github.com/AY2122S2-CS2113-T11-2/tp/pull/99),
[#105](https://github.com/AY2122S2-CS2113-T11-2/tp/pull/105),
[#111](https://github.com/AY2122S2-CS2113-T11-2/tp/pull/111),
[#121](https://github.com/AY2122S2-CS2113-T11-2/tp/pull/121)
