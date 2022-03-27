# User Guide

-----------------------------------------------

##Contents
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
    * [Finding](#finding-find)
    * [Saving](#saving)
        * [Saved Data](#saving)
        * [Corrupt Files](#corrupt-files)
* [Future Versions](#future-versions)
* [Errors](#errors)
* [HalpMi Local Storage Information](#halpmi-local-storage-information)
* [FAQ](#faq)
* [Command Summary](#command-summary)

-----------------------------------------------




## Introduction

HALPMI is a Command Line Interface (CLI) Application that allows administrators in clinics to manage the clinic's day-to-day
administrative tasks. More specifically, using HALPMI the user is able to insert new information regarding Doctors, Patients
and Medication. Users can also schedule appointment for Patients with Doctors. Users are also able to track stocks of medications.

###Purpose
The purpose of this User Guide is to get new users of HALPMI to get started with the application and learn the features
that it entails. This User Guide can also be referred to as and when needed when unclear about certain features and commands.

Persons with greater technical knowledge can refer to the [Developer Guide](DeveloperGuide.md) for in-depth explanations on the
workings behind HALPMI.

###How to read the Guide?

The User Guide is structured in a way that it is straight-forward and has a logical flow. Hence it should be easy for any User
like you to get started with HALPMI immediately.

Before reading, it is noteworthy to understand some formats and keywords used in this Guide for better clarity.
* Command - Command refers to the text input that you will be providing on the CLI as and when prompted by HALPMI.
* `Word` - Certain words or sentences in this Guide are often found in this format. These refer to commands that you as a User
  can use in the CLI application.

-----------------------------------------------


## Quick Start

###Setting Up Java


1.Firstly you need to have Java 11 or above installed in your computer. If you are not sure on how to check if you have
    Java 11 installed on your computer, you can refer to [Check Java](https://www.howtogeek.com/717330/how-to-check-your-java-version-on-windows-10/). 

If you do not have Java already installed:

1. You can either download the Java Runtime Environment (JRE) from [Java](https://www.java.com/en/download/manual.jsp).

2. You can download the Java Development Kit (JDK) from [Oracle](https://www.oracle.com/java/technologies/downloads/).

2.Once you are sure that you have Java installed, you can download the latest version of [HALPMI]() from the releases.

3.Move the .jar to an empty folder.

4.Open Command Prompt.

5.In Command Prompt, change your current working directory to the folder containing the .jar 
using $ `cd <Path of folder containing .jar>`

6.Run the .jar using $ `java -jar (latest version).jar`
###Starting up HALPMI

You can launch HALPMI directly from your Command Prompt/Terminal. Change directory to the location where you have saved the
JAR file you have downloaded from our releases. Following which you can type in `java -jar Halpmi.jar` in the command prompt to launch HALPMI.
To learn more about [Command Prompt](https://www.makeuseof.com/tag/a-beginners-guide-to-the-windows-command-line/)
, [Linux Terminal](https://ubuntu.com/tutorials/command-line-for-beginners#1-overview) and [Mac Terminal](https://macpaw.com/how-to/use-terminal-on-mac)
please head to the respective help links.

If you are not ready to handle the intricacies of the Command Prompt, you can use the `.bat` file located in our repository
to launch HALPMI as well. However do note that you will have to change the PATH name included on the bat file to the PATH name
at which Halpmi.jar is located.

If configured correctly, HalpMi would open up and you would be greeted by the welcome page as shown below. 

-----------------------------------------------


![](../../Desktop/Screenshot 2022-03-27 at 11.47.58 PM.png)

-----------------------------------------------

### [Back to top &#x2191;](#contents)

## Features

### Adding: `add`
Adds a new entry into the app. This entry can be either a doctor, patient, medicine or appointment.
    
    ADD DOCTOR

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

If doctor was added successfully, HalmpMi would return a command indicating that the doctor has been added to the list. 

    ADD PATIENT

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

If patient was added successfully, HalmpMi would return a command indicating that the patient has been added to the list.

    ADD MEDICINE

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

    VIEW DOCTOR
To view the records of all doctors in the application, you can simply call `view doctor` without any additional
parameters. You could also give the NRIC as an additional parameter if you want to view a single doctor only.

Format: `view doctor` or `view doctor /info [nric]`

Example: `view doctor /info S1234567A`

    VIEW PATIENT
To view the records of all patients in the application, you can simply call `view patient` without any additional
parameters. You could also give the NRIC as an additional parameter if you want to view a single patient only.

Format: `view patient` or `view patient /info [nric]`

Example: `view doctor /info S1234567A`

    VIEW MEDICINE
To view the records of all medicines in the application, you can simply call `view medicine` without any additional
parameters. You could also give the name as an additional parameter if you want to view a single type of Medicine only.

Format: `view medicine` or `view medicine /info [name]`

Example: `view medicine /info sf123`




### Deleting: `delete`
Allows user to delete existing records of doctors, patients and medicine.

    DELETE DOCTOR
To delete an existing doctor in the application, you can use the `delete doctor` command. This command requires an
additional parameter which is the NRIC of the doctor.

Format: `delete doctor /info [nric]`

Example: `delete doctor /info S1234567A`

    DELETE PATIENT
To delete an existing patient in the application, you can use the `delete patient` command. This command requires an
additional parameter which is the NRIC of the patient.

Format: `delete patient /info [nric]`

Example: `delete patient /info S1234567A`

    DELETE MEDICINE
To delete an existing medicine in the application, you can use the `delete medicine` command. This command requires an
additional parameter which is the Batch ID of the medicine.

Format: `delete medicine /info [Batch ID]`

Example: `delete medicine /info S234`



### Finding: `find`
Allows user to find existing records of doctors, patients and medicine.

    FIND DOCTOR
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

Format:`find doctor /info [parameter], [parameter of doctor]`

Example: `find doctor /info name, Jimmy`


    FIND PATIENT
To find an existing patient in the application, you can use the `find patient` command. This command requires an
additional parameter which can be any of the parameters of patient.
To find a patient you would have to provide one of these parameters using the given keywords:

1. NRIC: `nric`
2. Name: `name`
3. Age: `age`
4. Gender: `gender`
5. Address: `address`
6. Date Of Birth (DOB): `dob`
7. Date Of Admission (DOA): `admissiondate`

Format:`find patient /info [parameter], [parameter of patient]`

Example: `find patient /info nric, S7682373L`

    FIND MEDICINE
To find an existing medicine in the application, you can use the `find medicine` command. This command requires an
additional parameter which can be any of the parameters of medicine.
To find a medicine you would have to provide one of these parameters using the given keywords:

1. Medicine Name: `name`
2. Dosage: `dosage`
3. Expiry Date: `expiry`
4. Side Effects: `sideeffects`
5. Quantity: `quantity`

Format:`find medicine /info [parameter], [parameter of medicine]`

Example: `find medicine /info name, Paracetamol`

## HalpMi Local Storage Information
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

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: Locate your `date` directory and HalpMi JAR file, copy and paste it in your other
computer's desired location. Thereafter, you can run HalpMi as per normal.
(Please see [HalpMi Local Storage Information](#halpmi-local-storage-information) for recommended location).

## Command Summary

| Action          | Format, Examples                                                                                                                                                                 |
|-----------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Add Doctor      | `add doctor /info [nric],[name],[age],[gender],[address],[DOB],[Specialisation]`<br />e.g. `add doctor /info S1234567A, John Doe, 23, M, 10 Baker Street, 1999-12-31, Urinology` |
| Add Patient     | `add patient /info [nric],[name],[age],[gender],[address],[DOB],[DOA]`<br />e.g. `add patient /info S1234567A, John Doe, 23, M, 10 Baker Street, 1999-12-31, 2021-02-15`         |
| Add Medicine    | `add medicine /info [name],[dosage],[expiry date],[side effects],[quantity]`<br />e.g. `add medicine /info Paracetamol, 500, 2023-06-11, Slight headache, 10`                    |
| View Doctor     | `view doctor` or `view docotor /info [nric]`<br />e.g. `view doctor /info S1234567A`                                                                                             |
| View Patient    | `view patient` or `view patient /info [nric]`<br />e.g. `view doctor /info S1234567A`                                                                                            |
| View Medicine   | `view medicine` or `view medicine /info [name]`<br />e.g. `view medicine /info Paracetamol`                                                                                      |
| Delete Doctor   | `delete doctor /info [nric]`<br />e.g. `delete doctor /info S1234567A`                                                                                                           |
| Delete Patient  | `delete patient /info [nric]`<br />e.g. `delete patient /info S1234567A`                                                                                                         |
| Delete Medicine | `delete medicine /info [Batch ID]`<br />e.g. `delete medicine /info S234`                                                                                                        |
| Find Doctor     | `find doctor` or `find doctor /info [parameter][parameter of doctor]`<br />e.g. `find doctor /info name, John`                                                                   |
| Find Patient    | `find patient` or `find patient /info [parameter][parameter of patient]`<br />e.g. `find patient /info name, John`                                                               |
| Find Medicine   | `find medicine` or `find medicine /info [parameter][parameter of medicine]`<br />e.g. `view medicine /info name Paracetamol`                                                     |
|                 |
| `to be updated` | `to be updated`                                                                                                                                                                  |


### [Back to top &#x2191;](#contents)