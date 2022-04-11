# User Guide

-----------------------------------------------

## Contents
* [Introduction](#introduction)
    * [Purpose](#purpose)
    * [How to read the Guide](#how-to-read-the-guide)
* [Quick Start](#quick-start)
  * [Setting up Java](#setting-up-java)
  * [Starting up HALPMI](#starting-up-halpmi)
* [Features](#features)
  * [Adding](#adding-add)
  * [Viewing](#viewing-view)
  * [Deleting](#deleting-delete)
  * [Editing](#editing-edit)
  * [Finding](#findingfind)
  * [Additional Features]()
  * [Saving](#saving)
    * [Saved Data](#saving)
    * [Corrupt Files](#corrupt-files)
* [Future Versions](#future-versions)
* [Errors](#errors)
* [FAQ](#faq)
* [Command Summary](#command-summary)

-----------------------------------------------

## Introduction

HALPMI is a Command Line Interface (CLI) Application that allows administrators in clinics to manage the clinic's day-to-day
administrative tasks. More specifically, using HALPMI the user is able to insert new information regarding Doctors, Patients
and Medication. Users can also schedule appointment for Patients with Doctors. Users are also able to track stocks of medications.

### Purpose
The purpose of this User Guide is to get new users of HALPMI to get started with the application and learn the features
that it entails. This User Guide can also be referred to as and when needed when unclear about certain features and commands.

Persons with greater technical knowledge can refer to the [Developer Guide](DeveloperGuide.md) for in-depth explanations on the
workings behind HALPMI.

### How to read the Guide?
The User Guide is structured in a way that it is straight-forward and has a logical flow. Hence it should be easy for any User
like you to get started with HALPMI immediately.

Before reading, it is noteworthy to understand some formats and keywords used in this Guide for better clarity.
* Command - Command refers to the text input that you will be providing on the CLI as and when prompted by HALPMI.
* `Word` - Certain words or sentences in this Guide are often found in this format. These refer to commands that you as a User
can use in the CLI application.
* `[Word]` - Certain words that are found inside brackets refer to compulsory parameters you have to include following the
command.

-----------------------------------------------

## Quick Start

### Setting Up Java

Firstly, you need to have Java 11 or above installed in your computer. If you are not sure on how to check if you have
Java 11 installed on your computer, you can refer to [Check Java](https://www.howtogeek.com/717330/how-to-check-your-java-version-on-windows-10/).

If you do not have Java already installed...
1. You can either download the Java Runtime Environment (JRE) from [Java](https://www.java.com/en/download/manual.jsp).
2. Or you can download the Java Development Kit (JDK) from [Oracle](https://www.oracle.com/java/technologies/downloads/).

Once you are sure that you have Java installed, you can download the latest version of HALPMI from the releases.

### Starting up HALPMI
You can launch HALPMI directly from your Command Prompt/Terminal. Change directory to the location where you have saved the
JAR file you have downloaded from our releases. Following which you can type in `java -jar Halpmi.jar` in the command prompt to launch HALPMI.
To learn more about [Command Prompt](https://www.makeuseof.com/tag/a-beginners-guide-to-the-windows-command-line/)
, [Linux Terminal](https://ubuntu.com/tutorials/command-line-for-beginners#1-overview) and [Mac Terminal](https://macpaw.com/how-to/use-terminal-on-mac)
please head to the respective help links.

If you are not ready to handle the intricacies of the Command Prompt, you can use the `.bat` file located in our repository
to launch HALPMI as well. However do note that you will have to change the PATH name included on the bat file to the PATH name
at which Halpmi.jar is located.

-----------------------------------------------

## Features
> :warning: Please take note that all the parameters for all the commands will be auto-capitalised. However, if you are confused, please just use it according to the example given below.

### Adding: `add`
Adds a new entry into the app. This entry can be either a doctor, patient, medicine or appointment.

#### ADD DOCTOR

To add a new doctor into your application you can use the `add doctor` command when prompted by HALPMI for your input.
To add a doctor there are some parameters that you would have to provide:
1. NRIC
2. Name
3. Age
4. Gender
5. Address
6. Date Of Birth (DOB)
7. Specialisation

You will have to follow the formatting given below when calling the command.

Format: `add doctor /info [nric], [name], [age], [gender], [address], [DOB], [Specialisation]`

Example: `add doctor /info S7654321A, John Doe, 22, M, 10 Baker Street, 1999-12-31, Urinology`

#### ADD PATIENT

To add a new patient into your application you can use the `add patient` command when prompted by HALPMI for your input.
To add a patient there are some parameters that you would have to provide:
1. NRIC
2. Name 
3. Age
4. Gender
5. Address
6. Date Of Birth (DOB)
7. Date Of Admission (DOA)

You will have to follow the formatting given below when calling the command.

Format: `add patient /info [nric], [name], [age], [gender], [address], [DOB], [DOA]`

Example: `add patient /info S1234567A, John Doe, 22, M, 10 Baker Street, 1999-12-31, 2021-02-15`

#### ADD MEDICINE

To add a new medicine into your application you can use the `add medicine` command when prompted by HALPMI for your input.
To add a medicine there are some parameters that you would have to provide:
1. Medicine ID
1. Medicine Name
2. Dosage
3. Expiry Date
4. Side Effects (any string)
5. Quantity

You will have to follow the formatting given below when calling the command.

Format: `add medicine /info [batch id], [name], [dosage], [expiry date], [side effects], [quantity]`

Example: `add medicine /info A123, Paracetamol, 500, 2023-06-11, drowsy, 10`

#### ADD APPOINTMENT

To add a new appointment into your application you can use the `add appointment` command when prompted by HALPMI for your input.
To add an appointment there are some parameters that you would have to provide:
1. Patient NRIC
2. Doctor NRIC
3. Appointment Date
4. Appointment Details

You will have to follow the formatting given below when calling the command.

Format: `add appointment /info [patient nric], [doctor nric], [appointment date], [appointment details]`

Example: `add appointment /info S1234567A, S7654321A, 2022-10-15, Regular knee checkup`

### Viewing: `view`
Allows user to view existing records of doctors, patients, medicine or appointment.

#### VIEW DOCTOR

To view the records of all doctors in the application, you can simply call `view doctor` without any additional
parameters.

Format: `view doctor`

Examples: `view doctor`

#### VIEW PATIENT

To view the records of all patients in the application, you can simply call `view patient` without any additional
parameters.

Format: `view patient`

Example: `view patient`

#### VIEW MEDICINE

To view the records of all medicines in the application, you can simply call `view medicine` without any additional
parameters.

Format: `view medicine`

Example: `view medicine`

#### VIEW APPOINTMENT

To view the records of all appointments in the application, you can simply call `view appointment` without any
additional parameters.

Format: `view appointment`

Example: `view appointment`

### Deleting: `delete`
Allows user to delete existing records of doctors, patients, medicine or appointment. Do note that all delete commands
are irreversible.

#### DELETE DOCTOR

To delete an existing doctor in the application, you can use the `delete doctor` command. This command requires an
additional parameter which is the NRIC of the doctor.

Format: `delete doctor /info [nric]`

Example: `delete doctor /info S7654321A`

#### DELETE PATIENT

To delete an existing patient in the application, you can use the `delete patient` command. This command requires an
additional parameter which is the NRIC of the patient.

Format: `delete patient /info [nric]`

Example: `delete patient /info S1234567A`

#### DELETE MEDICINE

To delete an existing medicine in the application, you can use the `delete medicine` command. This command requires an
additional parameter which is the batch id of the medicine.

Format: `delete medicine /info [batch id]`

Example: `delete medicine /info S123`

#### DELETE APPOINTMENT

To delete an existing appointment in the application, you can use the `delete appointment` command.
This command requires an additional parameter which is the appointment id of the medicine.

Format: `delete appointment /info [appointment id]`

Example: `delete appointment /info 12356710156543`

### Editing: `edit`
Allows user to edit existing records of doctors, patients, medicine or appointment. Do note that successful edit
commands will overwrite the previous data recorded.
#### EDIT DOCTOR

To edit an existing doctor in your application you can use the `edit doctor` command when prompted by HALPMI for your input.
To edit a doctor there are some parameters that you would have to provide:
1. NRIC (Must be already in the list)
2. Name
3. Age
4. Gender
5. Address
6. Date Of Birth (DOB)
7. Specialisation

You will have to follow the formatting given below when calling the command.

Format: `edit doctor /info [nric], [name], [age], [gender], [address], [DOB], [Specialisation]`

Example: `edit doctor /info S7654321A, John Doe, 22, M, 10 Baker Street, 1999-12-31, Urinology`

#### EDIT PATIENT

To edit an existing patient in your application you can use the `edit patient` command when prompted by HALPMI for your input.
To edit a patient there are some parameters that you would have to provide:
1. NRIC (Must be already in the list)
2. Name
3. Age
4. Gender
5. Address
6. Date Of Birth (DOB)
7. Date Of Admission (DOA)

You will have to follow the formatting given below when calling the command.

Format: `edit patient /info [nric], [name], [age], [gender], [address], [DOB], [DOA]`

Example: `edit patient /info S1234567A, John Doe, 22, M, 10 Baker Street, 1999-12-31, 2021-02-15`

#### EDIT MEDICINE

To edit an existing medicine in your application you can use the `edit medicine` command when prompted by HALPMI for your input.
To edit a medicine there are some parameters that you would have to provide:
1. Batch ID (Must be already on the list)
1. Medicine Name
2. Dosage
3. Expiry Date
4. Side Effects
5. Quantity

You will have to follow the formatting given below when calling the command.

Format: `edit medicine /info [batch id], [name], [dosage], [expiry date], [side effects], [quantity]`

Example: `edit medicine /info A123, Paracetamol, 500, 2023-06-11, drowsy, 10`

#### EDIT APPOINTMENT

To edit an existing appointment in your application you can use the `add appointment` command when prompted by HALPMI for your input.
To edit an appointment there are some parameters that you would have to provide:
1. Appointment ID (Must be already on the list)
2. Patient name
3. Patient NRIC
4. Doctor name
5. Doctor NRIC
6. Appointment Date
7. Appointment Details

You will have to follow the formatting given below when calling the command.

Format: `edit appointment /info [appointment id], [patient nric], [patient name], [doctor nric], [doctor name],
[appointment date], [appointment details]`

Example: `edit appointment /info 12356710156543, S1234567A, Doe, S7654321A, John, 2022-10-15, Regular knee checkup`


### Finding: `find`
Allows user to find existing records of doctors, patients and medicine.

#### FIND DOCTOR

To find an existing doctor in the application, you can use the `find doctor` command. This command requires an
additional parameter which can be any of the parameters of doctor.
To find a doctor you would have to provide one of these parameters:
1. NRIC: `nric`
2. Name: `name`
3. Age: `age`
4. Gender: `gender`
5. Address: `address`
6. Date Of Birth (DOB): `dob`
7. Specialisation: `specialization`

Format: `find doctor /info [parameter], [keyword to find]`

Example 1: `find doctor /info name, John` <br>
Example 2: `find doctor /info nric, S7654321A` <br>
Example 3: `find doctor /info age, 22` <br>
Example 4: `find doctor /info gender, M` <br>
Example 5: `find doctor /info address, 10 BAKER STREET` <br>
Example 6: `find doctor /info dob, 1999-12-31` <br>
Example 7: `find doctor /info specialization, Urinology`


#### FIND PATIENT

To find an existing patient in the application, you can use the `find patient` command. This command requires an
additional parameter which can be any of the parameters of patient.
To find a patient you would have to provide one of these parameters using the given keywords:

1. NRIC: `nric`
2. Name: `name`
3. Age: `age`
4. Gender: `gender`
5. Address: `address`
6. Date Of Birth (DOB): `dob`
7. Registration Date: `registrationdate`

Format: `find patient /info [parameter], [keyword to find]`

Example 1: `find patient /info name, John Doe` <br>
Example 2: `find patient /info nric, S1234567A` <br>
Example 3: `find patient /info age, 22` <br>
Example 4: `find patient /info gender, M` <br>
Example 5: `find patient /info address, 10 BAKER STREET` <br>
Example 6: `find patient /info dob, 1999-12-31` <br>
Example 7: `find patient /info registrationdate, 2021-02-15` <br>

#### FIND MEDICINE

To find an existing medicine in the application, you can use the `find medicine` command. This command requires an
additional parameter which can be any of the parameters of medicine.
To find a medicine you would have to provide one of these parameters using the given keywords:

1. Medicine Name: `name`
2. Dosage: `dosage`
3. Expiry Date: `expiry`
4. Side Effects: `sideeffects`
5. Quantity: `quantity`

Format: `find medicine /info [parameter], [keyword to find]`

Example 1: `find medicine /info name, Paracetamol` <br>
Example 2: `find medicine /info id, A123` <br>
Example 3: `find medicine /info dosage, 500` <br>
Example 4: `find medicine /info expiry, 2023-06-11` <br>
Example 5: `find medicine /info sideeffects, DROWSY` <br>
Example 6: `find medicine /info quantity, 10`

#### FIND APPOINTMENT

To find an existing appointment in the application, you can use the `find appointment` command. This command requires an
additional parameter which can be any of the parameters of appointment.
To find an appointment you would have to provide one of these parameters using the given keywords:

1. Appointment Id: `id`
2. Patient nric: `patient nric`
3. Patient name: `patient name`
4. Doctor nric: `doctor nric`
5. Doctor name: `doctor name`
6. Appointment Date: `date`


Format: `find appointment /info [parameter], [keyword to find]`

Example 1: `find appointment /info id, 12356710156543` <br>
Example 2: `find appointment /info patient nric, S1234567A` <br>
Example 3: `find appointment /info patient name, Doe` <br>
Example 4: `find appointment /info doctor nric, S7654321A` <br>
Example 5: `find appointment /info doctor name, John` <br>
Example 6: `find appointment /info date, 2022-10-15`


### Features involving Medicine Inventory

#### Retrieving List of Expired/Run Out Medicine in the inventory

To retrieve list of expired medicine or depleted medicine in your inventory, you can simply call the `update medicines` command. This command
requires no additional parameter. **{DO NOTE THAT THIS LIST WILL BE CLEARED AND THE INFORMATION WITHIN WILL BE DESTROYED
UPON EXIT OF THE APP}**

Format: `update medicines`

Example: `update medicines`

#### Clearing List of Expired Medicine

If you choose to clear the list of expired medicine manually you can use the `clear old medicines` command. This commmand
requires no additional parameter.

Format: `clear old medicines`

Example: `clear old medicines`

#### Dispensing Medicine to Patients who have appointments on that day

You can use this feature to generate the Batch IDs of the medicines that are required to be dispensed to the patient on
his appointment day. If certain medicines you wish to dispense are not in stock, HalpMi will show you a list of medicine
you are missing in the inventory. If all the required medicines are present in the inventory, HalpMi will show the list
of Medicines you have to dispense along with the Batch ID so that you can clear batches with earlier expiry dates first.

You can use this feature using the `dispense medicine` command. This command takes in the NRIC of an existing patient
followed by a list of medicine you would like to dispense.

Format: `dispense medicine /info [NRIC],[Medicine Name],[Quantity]`

Example: `dispense medicine /info S1234567A,Paracetamol,10,Aspirin,20`

You need to give at list 1 medicine to dispense.

### Saving

#### Saved Data

When you start HalpMi for the first time, the following directory and files would be created:
```
data/        // Primary resource directory for HalpMi
    ├── doctor.txt   // Text file containing doctor list
    ├── patient.txt    // Text file containing patient list
    ├── medicine.txt       // Text file containing medcine list
    └── appointment.txt    // Text file containing appointment list
    
```
Do note that the directory (and by extension, the files) will be created in your terminal's
**current working directory**. Thus, it is highly recommended for you to create a new directory
with the HalpMi JAR file inside it, and run the application from the directory.

When you launch HalpMi in subsequent sessions, please ensure that you run it from the same directory
that you did when starting HalpMi for the first time.

#### Corrupt Files
If the files are corrupted, you will be prompted by HalpMi and the corrupted lines will be moved into a separate text file, with the following name.
```
data/        // Corrupted files
    ├── doctor_corrupted.txt   // Text file containing doctor list
    ├── patient_corrupted.txt    // Text file containing patient list
    ├── medicine_corrupted.txt       // Text file containing medcine list
    └── appointment_corrupted.txt    // Text file containing appointment list
```

-----------------------------------------------

## Future Versions

#### Storage
**File encryption** -> Files will be encrypted in the future. More checks for corurpted files will also be included. Current corrupted file check is a basic preliminary check.

-----------------------------------------------

## Errors

TO BE UPDATED

-----------------------------------------------

## FAQ

**Question**: How do I transfer my data to another computer?

**Answer**: {your answer here}

-----------------------------------------------

## Command Summary

| Action                | Format, Examples                                                                                                                                                                                                                                 |
|-----------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `add doctor`          | `add doctor /info [nric],[name],[age],[gender],[address],[DOB],[Specialisation]`<br />e.g. `add doctor /info S1234567A, John Doe, 23, M, 10 Baker Street, 1999-12-31, Urinology`                                                                 |
| `add patient`         | `add patient /info [nric],[name],[age],[gender],[address],[DOB],[DOA]`<br />e.g. `add patient /info S1234567A, John Doe, 23, M, 10 Baker Street, 1999-12-31, 2021-02-15`                                                                         |
| `add medicine`        | `add medicine /info [batch id], [name], [dosage], [expiry date], [side effects], [quantity]`<br />e.g. `add medicine /info A123, Paracetamol, 500, 2023-06-11, drowsy, 10`                                                                       |
| `add appointment`     | `add appointment /info [patient nric], [doctor nric], [appointment date], [appointment details]`<br />e.g. `add appointment /info S1234567A, S7654321A, 2022-10-15, Regular knee checkup`                                                        |
| `view doctor`         | `view doctor` or `view doctor /info [parameter], [keyword to find]`<br />e.g. `view doctor /info name, Jimmy`                                                                                                                                         |
| `view patient`        | `view patient` or `view patient /info [parameter], [keyword to find]`<br />e.g. `view patient /info name, Steven`                                                                                                                                     |
| `view medicine`       | `view medicine` or `view medicine /info info [parameter], [keyword to find]`<br />e.g. `view medicine /info name, Paracetamol`                                                                                                                        |
| `view appointment`    | `view appointment` or `view appointment /info [parameter], [keyword to find]`<br />e.g. `view appointment /info id, 12356701017647`                                                                                                                   |
| `delete doctor`       | `delete doctor /info [nric]`<br />e.g. `delete doctor /info S1234567A`                                                                                                                                                                           |
| `delete patient`      | `delete patient /info [nric]`<br />e.g. `delete patient /info S1234567A`                                                                                                                                                                         |
| `delete medicine`     | `delete medicine /info [batch id]`<br />e.g. `delete medicine /info S234`                                                                                                                                                                        |
| `delete appointment`  | `delete appointment /info [appointment id]`<br />e.g. `delete appointment /info 12356701017647`                                                                                                                                                  |
| `find doctor`         | `find doctor /info [parameter], [keyword to find]`<br />e.g.`find doctor /info name, Jimmy`<br />parameter must be one of the seven (name, nric, age, gender, address, dob or specialization)                                                    |
| `find patient`        | `find patient /info [parameter], [keyword to find]`<br />e.g.`find patient /info name, Steven Oz`<br />parameter must be one of the seven (name, nric, age, gender, address, dob or registrationdate)                                               |
| `find medicine`       | `find medicine /info [parameter], [keyword to find]`<br />e.g.`find medicine /info name, Paracetamol`<br />e.g.parameter must be one of the six (name, id, dosage, expiry, sideeffects, quantity)                                                |
| `find appointment`    | `find appointment /info [parameter], [keyword to find]`<br />e.g.`find appointment /info id, 12356701017647` <br />e.g.parameter must be one of the six (appointment id, patient nric, patient name, doctor nric, doctor name, appointment date) |
| `edit doctor`         | `edit doctor /info [nric],[name],[age],[gender],[address],[DOB],[Specialisation]`<br />e.g.`edit doctor /info S1234567A, John Doe, 23, M, 10 Baker Street, 1999-12-31, Urinology`                                                                |
| `edit patient`        | `edit patient /info [nric],[name],[age],[gender],[address],[DOB],[DOA]`<br />e.g.`edit patient /info S1234567A, John Doe, 23, M, 10 Baker Street, 1999-12-31, 2021-02-15`                                                                        |
| `edit medicine`       | `edit medicine /info [batch id],[name],[dosage],[expiry date],[side effects],[quantity]`<br />e.g.`edit medicine /info A123,Paracetamol, 500, 2023-06-11, drowsy, 10`                                                                            |
| `update medicines`    | `update medicines` <br />e.g `update medicines`                                                                                                                                                                                                  |
| `clear old medicines` | `clear old medicines` <br />e.g `clear old medicines`                                                                                                                                                                                            |
| `dispense medicine`   | `dispense medicine /info [NRIC],[Medicine Name],[Quantity]`<br/>e.g `dispense medicine /info S1234567A,Paracetamol,10,Aspirin,20`                                                                                                                |
| `help`                | view the help function for all the commands required                                                                                                                                                                                             |
| `bye`                 | exit the programme                                                                                                                                                                                                                               |

