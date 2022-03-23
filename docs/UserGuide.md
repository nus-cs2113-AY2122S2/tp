# User Guide

## Introduction

HALPMI is a Command Line Interface (CLI) Application that allows administrators in clinics to manage the clinic's day-to-day
administrative tasks. More specifically, using HALPMI the user is able to insert new information regarding Doctors, Patients
and Medication. Users can also schedule appointment for Patients with Doctors. Users are also able to track stocks of medications.

## Quick Start

Setting up HALPMI is a rather simple task.

Firstly you need to have Java 11 or above installed in your computer. If you are not sure on how to check if you have
Java 11 installed on your computer, you can refer to [Check Java](https://www.howtogeek.com/717330/how-to-check-your-java-version-on-windows-10/).

If you do not have Java already installed...
1. You can either download the Java Runtime Environment (JRE) from [Java](https://www.java.com/en/download/manual.jsp).
2. Or you can download the Java Development Kit (JDK) from [Oracle](https://www.oracle.com/java/technologies/downloads/).

Once you are sure that you have Java installed, you can download the latest version of HALPMI from the releases.

## Features

### Adding: `add`
Adds a new entry into the app. This entry can be either a doctor, patient or medicine.

#### `add doctor`
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

Format: `add doctor /info [nric],[name],[age],[gender],[address],[DOB],[Specialisation]`

Example: `add doctor /info S1234567A, John Doe, 23, M, 10 Baker Street, 1999-12-31, Urinology`

#### `add patient`
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

Format: `add patient /info [nric],[name],[age],[gender],[address],[DOB],[DOA]`

Example: `add patient /info S1234567A, John Doe, 23, M, 10 Baker Street, 1999-12-31, 2021-02-15`

#### `add medicine`
To add a new medicine into your application you can use the `add medicine` command when prompted by HALPMI for your input.
To add a medicine there are some parameters that you would have to provide:
1. Medicine Name
2. Dosage
3. Expiry Date
4. Side Effects
5. Quantity

You will have to follow the formatting given below when calling the command.

Format: `add medicine /info [name],[dosage],[expiry date],[side effects],[quantity]`

Example: `add medicine /info Paracetamol, 500, 2023-06-11, Slight headache, 10`

### Viewing: `view`
Allows user to view existing records of doctors, patients and medicine.

#### `view doctor`
To view the records of all doctors in the application, you can simply call `view doctor` without any additional
parameters. You could also give the NRIC as an additional parameter if you want to view a single doctor only.

Format: `view doctor` or `view doctor /info [nric]`

Example: `view doctor /info S1234567A`

#### `view patient`
To view the records of all patients in the application, you can simply call `view patient` without any additional
parameters. You could also give the NRIC as an additional parameter if you want to view a single patient only.

Format: `view patient` or `view patient /info [nric]`

Example: `view doctor /info S1234567A`

#### `view medicine`
To view the records of all medicines in the application, you can simply call `view medicine` without any additional
parameters. You could also give the Name as an additional parameter if you want to view a single type of Medicine only.

Format: `view medicine` or `view medicine /info [name]`

Example: `view medicine /info Paracetamol`

### Deleting: `delete`
Allows user to delete existing records of doctors, patients and medicine.

#### `delete doctor`
To delete an existing doctor in the application, you can use the `delete doctor` command. This command requires an
additional parameter which is the NRIC of the doctor.

Format: `delete doctor /info [nric]`

Example: `delete doctor /info S1234567A`

#### `delete patient`
To delete an existing patient in the application, you can use the `delete patient` command. This command requires an
additional parameter which is the NRIC of the patient.

Format: `delete patient /info [nric]`

Example: `delete patient /info S1234567A`

#### `delete medicine`
To delete an existing medicine in the application, you can use the `delete medicine` command. This command requires an
additional parameter which is the Batch ID of the medicine.

Format: `delete medicine /info [Batch ID]`

Example: `delete medicine /info S234`

#### `find doctor "by parameter"` (to be updated)

#### `find patient "by parameter"` (to be updated)

#### `find medicine "by parameter"` (to be updated)

## FAQ

**Question**: How do I transfer my data to another computer?

**Answer**: {your answer here}

## Command Summary

| Action             | Format, Examples |
| ------------------ | ---------------- |
| `add doctor`       | `add doctor /info [nric],[name],[age],[gender],[address],[DOB],[Specialisation]`<br />e.g. `add doctor /info S1234567A, John Doe, 23, M, 10 Baker Street, 1999-12-31, Urinology` |
| `add patient`      | `add patient /info [nric],[name],[age],[gender],[address],[DOB],[DOA]`<br />e.g. `add patient /info S1234567A, John Doe, 23, M, 10 Baker Street, 1999-12-31, 2021-02-15` |
| `add medicine`     | `add medicine /info [name],[dosage],[expiry date],[side effects],[quantity]`<br />e.g. `add medicine /info Paracetamol, 500, 2023-06-11, Slight headache, 10` |
| `view doctor`      | `view medicine` or `view medicine /info [name]`<br />e.g. `view medicine /info Paracetamol`|
| `view patient`     | `view patient` or `view patient /info [nric]`<br />e.g. `view doctor /info S1234567A`|
| `view medicine`    | `view medicine` or `view medicine /info [name]`<br />e.g. `view medicine /info Paracetamol`|
| `delete doctor`    | `delete doctor /info [nric]`<br />e.g. `delete doctor /info S1234567A` |
| `delete patient`   | `delete patient /info [nric]`<br />e.g. `delete patient /info S1234567A`|
| `delete medicine`  | `delete medicine /info [Batch ID]`<br />e.g. `delete medicine /info S234` |
| `to be updated`    | `to be updated` |

* Add todo `todo n/TODO_NAME d/DEADLINE`
