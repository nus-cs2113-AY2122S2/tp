# User Guide

## Introduction

IHospital is a desktop application meant for staff in hospitals. 
Its main purpose is to manage patients, doctors, nurses, appointments and operation rooms data, 
and it’s optimised for use via a Command Line Interface (CLI). If you can type fast, 
this application allows you to access relevant hospital information faster than traditional GUI applications.

## Quick Start

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `IHospital` from [here](https://github.com/AY2122S2-CS2113-T11-2/tp/releases).

## Features 

### Viewing help: `help`
View the available commands.

Format: `help`

### Adding a doctor: `add doctor`
Adds a new doctor to the system.

Format: `add doctor /id ID /n NAME /ph PHONE /e EMAIL`

Example of usage: 

`add doctor /id 1234 /n john /ph 12341234 /e 1234@gmail.com`


### Adding a patient: `add patient`
Adds a new patient to the system.

Format: `add patient /id ID /n NAME /ph PHONE /e EMAIL /s SYMPTOM /d DESCRIPTIONS`

Example of usage:

`add patient /id 4321 /n Tom /ph 93333333 /e 3233@qq.com /s symptom /d descriptions`


### Adding an appointment: `add appointment`
Adds a new appointment to the system.

Format: `add appointment /t DATETIME /d DOCTOR_NO /p PATIENT_NO`

* The `DOCTOR_NO` and `PATIENT_NO` refer to their numbers in the list (not their IDs).

* Please make sure that the doctor/patient you're adding to the appointment exist.

Example of usage:

`add appointment /t 2007-12-03T10:15:30 /d 1 /p 1`

### Sorting appointment list: 'sort'
Sorts existing appointments in the system (but does not print out).

Format: `sort appointment`

### Viewing doctor/patient/appointment list: `list`
Lists existing doctors/patients/appointments in the system.

Format: `list WHATYOUWANTTOLIST`

Example of usage:

`list doctor`

`list patient`

`list appointment`

### Searching a person: `search`
Searches existing doctors/patients with their numbers in the list.

Format: `search ROLE NO`

Example of usage:

`search doctor 1`

`search patient 3`

### Searching an appointment: `search`
Searches existing appointments with appointment time.

Format: `search appointment DATETIME`

Example of usage: 

`search appointment 2007-12-03T10:15:30`

### Editing a person's information
Editing an existing person's information.

Format: `edit /d OR /p INDEX /ph OR /e OR /n`

* Note that you can only edit one information at a time.

Example of usage:

`edit /d 1 /ph 12341234`

`edit /p 1 /n Mike`

`edit /d 1 /e 123489@gmail.com`

### Editing an appointment
Editing the details of an appointment.

Format: `edit /a APPOINTMENT_INDEX /doctor DOCTOR_INDEX` OR `edit /a APPOINTMENT_INDEX /patient PATIENT_INDEX` OR `edit /a APPOINTMENT_INDEX /time DATETIME`

* Note that you can only edit one information at a time.

### Deleting a person or an appointment: `delete`
Removes a person or an appointment from the system.

Format: `delete ROLE NO` or `delete appointment APPOINTMENT_NO`

* The `ROLE` should be either doctor or patient.
* The `NO` here refers to the numbers in the list (not IDs).

Example of usage:

`delete doctor 1`

`delete patient 12`

`delete appointment 3`


## FAQ

**Q**: How do I know the command format? 

**A**: You may refer to this User Guide or type `help` to view commands available.

## Command Summary

* View Help `help`
* Add Doctor `add doctor /id ID /n NAME /ph PHONE /e EMAIL`
* Add Patient `add patient /id ID /n NAME /ph PHONE /e EMAIL /s SYMPTOM /d DESCRIPTIONS`
* Add Appointment `add appointment /t 2022-03-19T15:16:00 /d DOCTOR_NO /p PATIENT_NO`
* Delete Doctor `delete doctor DOCTOR_ID`
* Delete Patient `delete patient PATIENT_ID`
* Delete Appointment `delete appointment APPOINTMENT_NO.`
* Sort Appointment List `sort appointment`
* View Doctor list `list doctor`
* View Patient list `list patient`
* View Appointment list `list appointment`
* Search Doctor `search doctor DOCTOR_NO`
* Search Patient `search patient PATIENT_NO`
* Search Appointment `search appointment DATETIME`
* Edit Doctor Info `edit /d DOCTOR_NO /ph PHONE` OR `edit /d DOCTOR_NO /n NAME` OR `edit /d DOCTOR_NO /e EMAIL`
* Edit Patient Info `edit /p PATIENT_NO /ph PHONE` OR `edit /p PATIENT_NO /n NAME` OR `edit /p PATIENT_NO /e EMAIL`
* Edit Appointment `edit /a APPOINTMENT_NO /doctor DOCTOR_NO` OR `edit /a APPOINTMENT_NO /patient PATIENT_NO` OR `edit /a APPOINTMENT_NO /time TIME`
