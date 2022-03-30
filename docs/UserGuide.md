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

### Adding a person: `add`
Adds a new person to the system.

Format: `add ROLE /id ID /n NAME /ph PHONE /e EMAIL`

* The `ROLE` should be either doctor or patient.

Example of usage: 

`add doctor /id 1234 /n john /ph 12341234 /e 1234@gmail.com`

`add patient /id 4321 /n Tom /ph 93333333 /e 3233@qq.com`

### Adding an appointment: `add appointment`
Adds a new appointment to the system.

Format: `add appointment /t DATETIME /d DOCTOR_ID /p PATIENT_ID`

Example of usage:

`add appointment /t 2007-12-03T10:15:30 /d 1 /p 1`


### Deleting a person or an appointment: `delete`
Removes a person or an appointment from the system.

Format: `delete ROLE ID` or `delete appointment APPOINTMENT_NO`

* The `ROLE` should be either doctor or patient.

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
* Add Patient `add patient /id ID /n NAME /ph PHONE /e EMAIL`
* Add Appointment `add appointment /t 2022-03-19T15:16:00 /d DOCTOR_ID /p PATIENT_ID`
* Delete Doctor `delete doctor DOCTOR_ID`
* Delete Patient `delete patient PATIENT_ID`
* Delete Appointment `delete appointment APPOINTMENT_NO.`
* View Doctor list `list doctor`
* View Patient list `list patient`
* View Appointment list `list appointment`
